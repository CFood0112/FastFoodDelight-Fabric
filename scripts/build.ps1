# Builds FastFood Delight Fabric with the project-local JDK 16 and the
# ZipFS workaround required by this Windows/DSH environment.
# Usage:
#   .\scripts\build.ps1                # build
#   .\scripts\build.ps1 build          # same
#   .\scripts\build.ps1 runServer      # dedicated server dev run
#   .\scripts\build.ps1 runClient      # client dev run

[CmdletBinding()]
param(
    [Parameter(ValueFromRemainingArguments = $true)]
    [string[]]$GradleArgs
)

$ErrorActionPreference = 'Stop'
$ProjectRoot = Split-Path -Parent $PSScriptRoot
$WorkspaceRoot = Split-Path -Parent $ProjectRoot
$Jdk = Join-Path $WorkspaceRoot '.jdks\temurin-16.0.2+7'

if (-not (Test-Path (Join-Path $Jdk 'bin\java.exe'))) {
    throw "JDK 16 not found at: $Jdk"
}

$BuildDir = Join-Path $ProjectRoot '.build'
$SourceRoot = Join-Path $BuildDir 'jdk-zipfs-src'
$PatchRoot = Join-Path $BuildDir 'jdk-zipfs-patch'
$PatchModule = Join-Path $PatchRoot 'jdk.zipfs'

if (-not (Test-Path (Join-Path $PatchModule 'jdk\nio\zipfs\ZipFileSystem.class'))) {
    Write-Host 'Preparing local jdk.zipfs patch (environment workaround)...'
    New-Item -ItemType Directory -Force -Path $BuildDir | Out-Null
    if (Test-Path $SourceRoot) { Remove-Item -Recurse -Force $SourceRoot }
    Expand-Archive -Path (Join-Path $Jdk 'lib\src.zip') -DestinationPath $SourceRoot -Force

    $zipfs = Join-Path $SourceRoot 'jdk.zipfs\jdk\nio\zipfs\ZipFileSystem.java'
    if (-not (Test-Path $zipfs)) { throw "ZipFileSystem.java not found in JDK src.zip" }

    $text = [System.IO.File]::ReadAllText($zipfs)
    $pattern = 'boolean writeable = AccessController\.doPrivileged\(\s*\(PrivilegedAction<Boolean>\)\(\)->Files\.isWritable\(zfpath\)\);\s*this\.readOnly = !writeable;'
    $replacement = 'this.readOnly = false; // DSH environment workaround: Files.isWritable is false while writes succeed.'
    $newText = [regex]::Replace($text, $pattern, $replacement)
    if ($newText -eq $text) { throw 'Could not patch ZipFileSystem.java (pattern not found)' }
    [System.IO.File]::WriteAllText($zipfs, $newText, (New-Object System.Text.UTF8Encoding($false)))

    if (Test-Path $PatchRoot) { Remove-Item -Recurse -Force $PatchRoot }
    & (Join-Path $Jdk 'bin\javac.exe') --module-source-path $SourceRoot -d $PatchRoot --module jdk.zipfs
    if ($LASTEXITCODE -ne 0) { throw "javac failed while building jdk.zipfs patch (exit $LASTEXITCODE)" }
}

$GradleCandidates = Get-ChildItem (Join-Path $env:USERPROFILE '.gradle\wrapper\dists\gradle-7.4.2-bin\*\gradle-7.4.2\bin\gradle.bat') -ErrorAction SilentlyContinue
$Gradle = $GradleCandidates | Select-Object -First 1
if (-not $Gradle) {
    $command = Get-Command gradle -ErrorAction SilentlyContinue
    if (-not $command) { throw 'Gradle 7.4.2 not found (expected cached wrapper distribution or gradle on PATH)' }
    $Gradle = $command.Source
}
if ($Gradle -is [System.IO.FileInfo]) { $Gradle = $Gradle.FullName }

if (-not $GradleArgs -or $GradleArgs.Count -eq 0) { $GradleArgs = @('build') }
$GradleArgs = @($GradleArgs)
if ($GradleArgs -notcontains '--no-daemon') { $GradleArgs += '--no-daemon' }
if ($GradleArgs -notcontains '--no-watch-fs') { $GradleArgs += '--no-watch-fs' }

$env:JAVA_HOME = $Jdk
$env:GRADLE_USER_HOME = Join-Path $ProjectRoot '.gradle-home'
$env:GRADLE_OPTS = "-Djava.io.tmpdir=$(Join-Path $ProjectRoot '.tmp')"
$env:JAVA_TOOL_OPTIONS = "--patch-module=jdk.zipfs=$PatchModule"

Write-Host "Gradle: $Gradle"
Write-Host "JAVA_HOME: $env:JAVA_HOME"
Write-Host "Arguments: $($GradleArgs -join ' ')"
& $Gradle @GradleArgs
exit $LASTEXITCODE
