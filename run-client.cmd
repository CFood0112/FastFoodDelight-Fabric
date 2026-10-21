@echo off
setlocal
cd /d "%~dp0"
title FastFood Delight dev Client
echo Starting Minecraft dev client with the project JDK 16...
echo Leave this console window open while the game runs.
echo.
powershell.exe -NoProfile -ExecutionPolicy Bypass -File ".\scripts\build.ps1" runClient
echo.
echo Client process exited. Press any key to close this window.
pause >nul
