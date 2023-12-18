#!/bin/bash

# git 설치 확인 및 설치
if ! command -v git &> /dev/null; then
    echo "git이 설치되어 있지 않습니다. git을 설치합니다."
    sudo yum install -y git
fi

# JRE 17 설치 확인 및 설치
if ! command -v java &> /dev/null; then
    echo "Java 17 (JRE)이 설치되어 있지 않습니다. Java 17을 설치합니다."
    sudo yum install -y java-17-openjdk
fi

project_dir="belog-springboot"
store_url="https://github.com/belogstudy/belog-springboot.git"

# 프로젝트 가져오기
echo "프로젝트의 변경사항을 확인중입니다."
# 프로젝트 디렉토리가 이미 존재하는지 확인
if [ -d "$project_dir" ]; then
    # 디렉토리가 존재하면 업데이트
    cd "$project_dir" || exit 1
    git pull
else
    # 디렉토리가 존재하지 않으면 클론
    git clone "$store_url" || exit 1
    cd "$project_dir" || exit 1  # 클론 후 프로젝트 폴더로 이동
fi

# 프로젝트 빌드
echo "프로젝트를 빌드합니다."
chmod +x gradlew
./gradlew bootJar || { echo "프로젝트 빌드에 실패했습니다."; exit 1; }

# 도커 컨테이너 배포
echo "도커 컨테이너에 배포합니다."
docker stop velog-spring-app
docker rm velog-spring-app
docker rmi velog-spring-app-image || true  # 이미지가 없는 경우를 고려하여 에러를 무시

docker build -t velog-spring-app-image .  # Dockerfile의 경로를 지정
docker run --name velog-spring-app -p 80:8080 -d velog-spring-app-image
