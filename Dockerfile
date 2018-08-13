FROM openjdk:8


WORKDIR /Workspace

ADD MyAgent.jar /Workspace

CMD ["java", "-javaagent:MyAgent.jar", "-jar", "/Workspace/prova.jar"]
#CMD ["/bin/bash"]