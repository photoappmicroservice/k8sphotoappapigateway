FROM openjdk:8u131-jre

MAINTAINER vijaya krishna "vijayakrishnajava@gmail.com"

ADD k8sphotoappapigateway.jar webapp.jar

CMD ["java","-Xmx50m","-jar","webapp.jar"]
