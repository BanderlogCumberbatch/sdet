#!/bin/bash

# Запуск всех тестов
echo "Running DataProviderTest..."
mvn -Dtest=DataProviderTest test

# Проверяем существование файла с упавшими тестами
if [ -f "target/surefire-reports/testng-failed.xml" ]; then
    echo "Failed tests detected. Re-running failed tests..."
    mvn test -Dsurefire.suiteXmlFiles=target/surefire-reports/testng-failed.xml
else
    echo "No failed tests found."
fi