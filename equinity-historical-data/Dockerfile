FROM amazoncorretto:17-alpine

RUN mkdir -p /opt/equinity-historical-data/
WORKDIR /opt/equinity-historical-data/

COPY ./build/libs/equinity-historical-data.jar /opt/equinity-historical-data/app.jar

# This will create a non-root user with the UID of 1001
RUN addgroup -S appgroup && adduser -u 1001 -S appuser -G appgroup
# You must use a UID, not a username, here
USER 1001

EXPOSE 8089 8188
ENTRYPOINT ["java", "-jar","app.jar"]