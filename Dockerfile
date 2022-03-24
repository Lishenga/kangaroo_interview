FROM openjdk:latest
ADD target/kangaroo_interview.jar kangaroo_interview.jar
EXPOSE 81
ENTRYPOINT [ "java", "-jar", "kangaroo_interview.jar" ]