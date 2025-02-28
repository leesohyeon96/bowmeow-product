# 1. Java 기반 이미지로 시작
FROM openjdk:21-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 로컬에서 빌드된 JAR 파일을 Docker 컨테이너로 복사
COPY build/libs/bowmeow-product-0.0.1-SNAPSHOT.jar /app/bowmeow-product.jar

# 4. 더미 환경 변수 설정 (빌드 시점에는 실제 민감한 정보가 필요하지 않도록 설정 > Deployment.yml(런타임)시점에 환경변수 넣어줌)
ARG JWT_SECRET_KEY=dummy-secret
ARG DB_USERNAME=dummy-user
ARG DB_PASSWORD=dummy-password
ENV JWT_SECRET_KEY=$JWT_SECRET_KEY
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD


# 4. 컨테이너에서 실행할 명령어 (Spring Boot 애플리케이션 실행)
CMD ["java", "-jar", "bowmeow-product.jar"]

# 5. 컨테이너가 사용할 포트 (Spring Boot의 기본 포트 8080)
EXPOSE 8080
