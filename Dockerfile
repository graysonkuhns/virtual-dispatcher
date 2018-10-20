# Build image
FROM maven:3.5.4-jdk-8 AS build-env

WORKDIR /build
COPY . /build

RUN mvn clean install

# Execution image
FROM centos:7.5.1804

# Install java
RUN  yum makecache fast && \
     yum install -y java-1.8.0-openjdk && \
     yum clean -y all

# Copy in the app
RUN mkdir -p /opt/virtual-dispatcher /etc/virtual-dispatcher
COPY --from=build-env /build/config.yml /etc/virtual-dispatcher/
COPY --from=build-env /build/target/virtual-dispatcher-0.1.0-SNAPSHOT.jar /opt/virtual-dispatcher/vd.jar

# Run the app
CMD ["java", "-jar", "/opt/virtual-dispatcher/vd.jar", "server", "/etc/virtual-dispatcher/config.yml"]
