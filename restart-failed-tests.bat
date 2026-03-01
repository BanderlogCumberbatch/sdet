@echo off

:: Запуск всех тестов
call mvn -Dtest=DataProviderTest test

:: Проверяем существование файла с упавшими тестами
if exist "target\surefire-reports\testng-failed.xml" (
    call mvn test -Dsurefire.suiteXmlFiles=target/surefire-reports/testng-failed.xml
)