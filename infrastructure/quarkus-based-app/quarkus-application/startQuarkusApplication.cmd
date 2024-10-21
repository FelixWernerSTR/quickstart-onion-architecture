set JAVA_HOME=D:/devel/java/jdk-21.0.2
set PATH=%JAVA_HOME%/bin;%PATH%
%JAVA_HOME%/bin/java -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -jar target/quarkus-app/quarkus-run.jar