# 베이스 이미지로 Nginx 사용
FROM nginx:stable-alpine

# Nginx 설정 파일 복사
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Jenkins에서 빌드된 결과물을 Nginx의 정적 파일 디렉토리로 복사
COPY ./dist /usr/share/nginx/html

# 포트 노출
EXPOSE 80

# 컨테이너 실행 시 Nginx 시작
CMD ["nginx", "-g", "daemon off;"]
