@echo off
echo Starting Selenium Grid...

:: Запуск Hub в новом окне
start "Selenium Hub" cmd /k "java -jar selenium-server-4.35.0.jar hub"

:: Запуск Node 1 в новом окне
start "Selenium Node 1" cmd /k "java -jar selenium-server-4.35.0.jar node --port 5555 --max-threads 2"

:: Запуск Node 2 в новом окне
start "Selenium Node 2" cmd /k "java -jar selenium-server-4.35.0.jar node --port 6666 --max-threads 2"


echo Grid started successfully