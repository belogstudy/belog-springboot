#!/bin/bash

jar_path="$HOME/velogProject-0.0.1-SNAPSHOT.jar"
dockerfile_path="$HOME/Dockerfile"

# JAR 파일 확인
if [ ! -f "$jar_path" ]; then
    echo "에러: JAR 파일이 $jar_path 에 존재하지 않습니다."
    exit 1
fi

# 도커 컨테이너 배포
echo "도커 컨테이너에 배포합니다."

# 이전에 실행 중이던 컨테이너를 중지하고 삭제합니다.
echo "이전 컨테이너를 중지하고 삭제합니다."
docker stop velog-spring-app || true  # 컨테이너가 없을 경우 에러를 무시
docker rm velog-spring-app || true    # 컨테이너가 없을 경우 에러를 무시

# 이전에 생성된 이미지를 삭제합니다.
echo "이전 이미지를 삭제합니다."
docker rmi velog-spring-app-image || true  # 이미지가 없을 경우 에러를 무시

# 새로운 이미지를 빌드하고 컨테이너를 실행합니다.
echo "도커 이미지를 빌드합니다."
curl -O https://raw.githubusercontent.com/belogstudy/belog-springboot/main/Dockerfile
docker build -t velog-spring-app-image -f "$dockerfile_path" . || { echo "도커 이미지 빌드에 실패했습니다."; exit 1; }
echo "도커 컨테이너를 실행합니다."
docker run --name velog-spring-app -p 80:8080 -d velog-spring-app-image

