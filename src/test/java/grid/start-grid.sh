#!/bin/bash

echo "Starting Selenium Grid..."

# Запуск Hub в фоне с лог-файлом
java -jar selenium-server-4.35.0.jar hub > hub.log 2>&1 &
HUB_PID=$!
echo "Hub started with PID $HUB_PID (logs: hub.log)"

# Запуск Node 1 на порту 5555
java -jar selenium-server-4.35.0.jar node --port 5555 --max-threads 2 > node1.log 2>&1 &
NODE1_PID=$!
echo "Node 1 started with PID $NODE1_PID (logs: node1.log)"

# Запуск Node 2 на порту 6666
java -jar selenium-server-4.35.0.jar node --port 6666 --max-threads 2 > node2.log 2>&1 &
NODE2_PID=$!
echo "Node 2 started with PID $NODE2_PID (logs: node2.log)"

echo "Grid started successfully. Check logs for details."
echo "To stop all processes: kill $HUB_PID $NODE1_PID $NODE2_PID"