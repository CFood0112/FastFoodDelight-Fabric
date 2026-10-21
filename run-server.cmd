@echo off
setlocal
cd /d "%~dp0"
title FastFood Delight dev Server
echo Starting Minecraft dev server with the project JDK 16...
echo Wait until the server prints "Done" before connecting.
echo Type "stop" in this console to stop the server.
echo.
powershell.exe -NoProfile -ExecutionPolicy Bypass -File ".\scripts\build.ps1" runServer
echo.
echo Server process exited. Press any key to close this window.
pause >nul
