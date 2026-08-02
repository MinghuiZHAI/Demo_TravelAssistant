# 第一阶段：构建阶段（使用 Maven 镜像编译打包）
FROM maven:3.9-amazoncorretto-21 AS builder
WORKDIR /app

# 复制 pom.xml，利用 Docker 缓存层，避免每次都下载依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并打包
COPY src ./src
RUN mvn clean package -DskipTests

# 第二阶段：运行阶段（轻量级 JDK 镜像）
FROM amazoncorretto:21-alpine
WORKDIR /app

# 从构建阶段复制打好的 jar 包
COPY --from=builder /app/target/*.jar app.jar

# 暴露后端端口
EXPOSE 3200

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]