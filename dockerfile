FROM  java:8
ENV environment=L1
ADD poclistener.jar poclistener.jar
CMD ["java","-jar","poclistener.jar"]