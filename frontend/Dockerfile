# 베이스 이미지로 Node.js 21 버전 사용
FROM node:21-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# package.json과 package-lock.json 복사
COPY package*.json ./

# 의존성 패키지 설치
RUN npm ci

# 소스 코드 복사
COPY . .

# React 애플리케이션 빌드
RUN npm run build

# Nginx를 사용하여 서빙할 경량 이미지 생성
FROM nginx:stable-alpine

# Nginx 설정 파일 복사
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 빌드 결과물을 Nginx의 정적 파일 디렉토리로 복사
COPY --from=build /app/dist /usr/share/nginx/html

# 포트 노출
EXPOSE 80

# 컨테이너 실행 시 Nginx 시작
CMD ["nginx", "-g", "daemon off;"]
