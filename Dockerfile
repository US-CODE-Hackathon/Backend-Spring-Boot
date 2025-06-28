# ✅ 1단계: 빌드 (Gradle 캐싱 최적화 포함)
FROM gradle:8.7.0-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# ✅ 2단계: 경량 실행용 이미지 (최소한의 JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# JAR 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 환경변수 설정 (옵션)
ENV JAVA_OPTS=""

# 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
