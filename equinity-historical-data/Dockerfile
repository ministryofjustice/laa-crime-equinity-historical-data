FROM amazoncorretto:17-alpine

RUN mkdir -p /opt/equinity-historical-data/
WORKDIR /opt/equinity-historical-data/

COPY ./build/libs/equinity-historical-data.jar /opt/equinity-historical-data/app.jar

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"

RUN apt-get update && \
    apt-get install unzip wget -y 
RUN unzip awscliv2.zip
RUN ./aws/install


# This will create a non-root user with the UID of 1001
RUN addgroup -S appgroup && adduser -u 1001 -S appuser -G appgroup
# You must use a UID, not a username, here
USER 1001

EXPOSE 8089 8188
ENTRYPOINT ["java", "-jar","app.jar"]
