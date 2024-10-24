FROM openjdk:17


COPY "./target/MongoWikiPlant-1.jar" "app.jar"
EXPOSE 8070
ENTRYPOINT [ "java", "-jar", "app.jar" ]
