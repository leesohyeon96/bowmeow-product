# 1. Java 기반 이미지로 시작
FROM openjdk:21-jre-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 로컬에서 빌드된 JAR 파일을 Docker 컨테이너로 복사
COPY build/libs/bowmeow-product-0.0.1-SNAPSHOT.jar /app/bowmeow-product.jar

# 4. 컨테이너에서 실행할 명령어 (Spring Boot 애플리케이션 실행)
CMD ["java", "-jar", "bowmeow-product.jar"]

# 5. 컨테이너가 사용할 포트 (Spring Boot의 기본 포트 8080)
EXPOSE 8080
