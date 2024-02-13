#!/bin/bash

CURRENT_PORT=$(cat /etc/nginx/conf.d/service-url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0
MAX_RETRIES=20

check_service() {
  local RETRIES=0
  local URL=$1
  while [ $RETRIES -lt $MAX_RETRIES ]; do
    echo "Checking service at $URL... (attempt: $((RETRIES+1)))"
    sleep 3

    REQUEST=$(curl $URL)
    if [ -n "$REQUEST" ]; then
      echo "health check success"
      return 0
    fi

    RETRIES=$((RETRIES+1))
  done;

  echo "Failed to check service after $MAX_RETRIES attempts."
  return 1
}

# Toggle port Number
if [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq 8082 ]; then
    TARGET_PORT=8081
else
    echo "> No WAS is connected to nginx"
    exit 1
fi

echo "set \$service_url http://127.0.0.1:$TARGET_PORT;" | sudo tee /etc/nginx/conf.d/service-url.inc > /dev/null

echo "> Start health check of WAS at 'http://127.0.0.1:${TARGET_PORT}' ..."

if ! check_service "http://127.0.0.1:$TARGET_PORT"; then
  echo "health check 가 실패했습니다."
  exit 1
fi
