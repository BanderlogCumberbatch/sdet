@echo off
echo Starting Selenium Grid...

:: Запуск Hub в новом окне
start "Selenium Hub" cmd /k "java -jar selenium-server-4.35.0.jar -role hub -port 4444"
timeout /t 3

:: Запуск Node 1 в новом окне
start "Selenium Node 1" cmd /k "java -jar selenium-server-4.35.0.jar -role node -hub http://localhost:4444/grid/register -port 5555 -maxSession 5"

:: Запуск Node 2 в новом окне
start "Selenium Node 2" cmd /k "java -jar selenium-server-4.35.0.jar -role node -hub http://localhost:4444/grid/register -port 5556 -maxSession 5"

echo Selenium Grid is running...
echo Hub: http://localhost:4444
echo Nodes: 5555, 5556
echo.
echo Grid started successfully! Close command windows to stop.
pause