#!/bin/bash

# 의존성 패키지 확인
if ! command -v git &> /dev/null; then
    echo "git이 설치되어 있지 않습니다."
    exit 1
fi

# JRE 17 설치 확인 및 설치
if ! command -v java &> /dev/null; then
    echo "Java 17 (JRE)이 설치되어 있지 않습니다."
    exit 1
fi

project_dir="belog-springboot"
store_url="https://github.com/belogstudy/belog-springboot.git"
submodule_url="https://$ACTION_TOKEN@github.com/belogstudy/belog-springboot-secret.git"
dockerfile_path="$HOME/$project_dir/Dockerfile"

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

# 서브모듈 가져오기
echo "설정파일을 변경사항을 확인중입니다."
if [ -d "$project_dir/belog-springboot-secret" ]; then
    echo "설정 파일을 업데이트합니다."
    rm -rf "$project_dir/belog-springboot-secret"
fi
git clone "$submodule_url"
mv belog-springboot-secret/application.yml "$project_dir/belog-springboot-secret"

# 프로젝트 빌드
echo "프로젝트를 빌드합니다."
chmod +x gradlew
./gradlew bootJar || { echo "프로젝트 빌드에 실패했습니다."; exit 1; }

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
docker build -t velog-spring-app-image -f "$dockerfile_path" "$HOME/$project_dir" || { echo "도커 이미지 빌드에 실패했습니다."; exit 1; }
echo "도커 컨테이너를 실행합니다."
docker run --name velog-spring-app -p 80:8080 -d velog-spring-app-image

