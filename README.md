# hexagonal-arch
A Hexagonal Arch Model Project

## How to Run Local

* Clone this repository
* Make sure you are using JDK 17
* Build the app
    ```
        ./mvnw clean package
        ## OR
        ./gradlew build
    ```
* Run a MySQL docker image:
    ```
        docker run \
        --detach \
        --name=my-mysql \
        --env="MYSQL_ROOT_PASSWORD=12345" \
        --publish 6603:3306 \
        mysql
    ```
* Run
    ```
        java -jar -Dspring.profiles.active=prod hexagonal-arch-application/target/hexagonal-arch-application-0.0.1.jar
    ```
* Insert a Person:
    ```
        curl --location --request POST 'http://localhost:8080/persons' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "name": "Ana",
            "email": "ana@empresa.com",
            "yearBirth": 1990,
            "birthDate": "1990-12-13",
            "cpf": "13870994002",
            "phone": "(55)11982188121"
        }'
    ```
* List person
    ```
        curl --location --request GET 'http://localhost:8080/persons'
    ```
