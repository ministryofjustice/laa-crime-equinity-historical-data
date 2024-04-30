FROM amazoncorretto:17-alpine

# Downloading dependencies
RUN apk update \
&& apk add curl unzip wget \
&& apk add aws-cli


# Start building App for deployment
RUN mkdir -p /opt/equinity-historical-data/
WORKDIR /opt/equinity-historical-data/

COPY ./equinity-historical-data/build/libs/equinity-historical-data.jar /opt/equinity-historical-data/app.jar

# This will create a non-root user with the UID of 1001
RUN addgroup -S appgroup && adduser -u 10001 -S appuser -G appgroup
# You must use a UID, not a username, here
USER 10001

EXPOSE 8089 8188
ENTRYPOINT ["java", "-jar","app.jar"]
