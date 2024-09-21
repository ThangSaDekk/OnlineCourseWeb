# Chọn hình ảnh chính thức của Tomcat với JDK 17
FROM tomcat:9.0.91-jdk17-temurin-jammy

# Thiết lập thư mục làm việc trong container
WORKDIR /usr/local/tomcat/webapps

# Mở cổng 8080 cho Tomcat
EXPOSE 8080

# Sao chép file war từ thư mục build của ứng dụng vào Tomcat
COPY target/OnlineCourseWeb-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat sẽ tự động triển khai các file war từ thư mục webapps
# Lệnh để khởi động Tomcat khi container bắt đầu
CMD ["catalina.sh", "run"]
