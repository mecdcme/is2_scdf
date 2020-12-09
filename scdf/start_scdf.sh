java -jar spring-cloud-skipper-server-2.6.0-SNAPSHOT.jar &
java -jar spring-cloud-dataflow-server-2.7.0-SNAPSHOT.jar  \
    --spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=is2_scdf \
    --spring.datasource.username=postgres \
    --spring.datasource.password=postgres \
    --spring.datasource.driver-class-name=org.postgresql.Driver &

