FROM  java:8
RUN mkdir library
COPY /library /library
COPY invoke.sh . 
RUN chmod 777 invoke.sh
ADD poclistener.jar poclistener.jar
#CMD ["java","-jar","poclistener.jar"]
CMD sh invoke.sh
