#from alpine:3.11.2
#run apk add --no-cache openjdk11
#copy build/libs/ders1_2-0.0.1-SNAPSHOT.jar /app/
#workdir /app/
#cmd ["java","-jar","ders1_2-0.0.1-SNAPSHOT.jar"]
## run apk update
## run apk upgrade
## run apk search openjdk
#
## copy build/libs/ders1_2-0.0.1-SNAPSHOT.jar /app/
## copy openjdk-19.0.2_linux-x64_bin.tar.gz /app/
#
## cmd ["mkdir","app" ]
## workdir /app/
## run wget https://download.java.net/java/GA/jdk19.0.2/fdb695a9d9064ad6b064dc6df578380c/7/GPL/openjdk-19.0.2_linux-x64_bin.tar.gz
##
## cmd ["tar", "-xzf", "./openjdk-19.0.2_linux-x64_bin.tar.gz"]
##
## cmd ["pwd"]
## cmd ["ls","-al"]

FROM openjdk:11
ADD build/libs/ders1_2-0.0.1-SNAPSHOT.jar dockerimage.jar
EXPOSE 8085
#RUN bash -c 'touch /deneme-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","dockerimage.jar"]