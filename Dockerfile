FROM openjdk:11
VOLUME [ "/home" ]
ENTRYPOINT [ "java","-jar","/home/nisum-0.0.1-SNAPSHOT.jar" ]
