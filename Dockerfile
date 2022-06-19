FROM openjdk:11-jdk
RUN apt-get -y update && apt-get -y install build-essential && apt-get install sudo && apt-get install -y unzip \
&& sudo curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add - && sudo echo "deb [arch=amd64]  http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
&& sudo apt-get -y update && sudo apt-get -y install google-chrome-stable && wget https://chromedriver.storage.googleapis.com/102.0.5005.61/chromedriver_linux64.zip \
&& unzip chromedriver_linux64.zip && sudo mv chromedriver /usr/bin/chromedriver && sudo chown root:root /usr/bin/chromedriver && sudo chmod +x /usr/bin/chromedriver 
ARG JAR_FILE=flatB-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]
