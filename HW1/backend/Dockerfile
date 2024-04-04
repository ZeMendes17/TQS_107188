FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

RUN apt update && apt install -y wget && apt install firefox-esr -y

RUN wget https://github.com/mozilla/geckodriver/releases/download/v0.33.0/geckodriver-v0.33.0-linux64.tar.gz && \
    tar -xvzf geckodriver-v0.33.0-linux64.tar.gz && \
    chmod +x geckodriver && \
    mv geckodriver /usr/local/bin/

CMD mvn spring-boot:run