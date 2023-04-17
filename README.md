# Rates-CQRS-model
CQRS-ES Api model. Get currency rates - only show or export this to file (for example .csv).
This API uses an external nbp.API.

# Tasks naming

COMMAND[CMD] [#numberTask] feat/chore/refactor/fix  
COMMON[COM] [#numberTask] feat/chore/refactor/fix  
QUERY[QRY] [#numberTask] feat/chore/refactor/fix  


# Work and start project:

##  Preparing environment   

   **Prerequisites:**  

To start, you need three things: **MySQL** database, **MongoDB** and **Kafka**.  Running this project requires installing docker, maven and java jdk 11.   

### Very important: If you need _NETWORK_ for docker please execute command:  

`sudo docker network create --attachable -d bridge ratesNet` <br> (ratesNet it's name which can be change)  

a) **MongoDB** is used by _COMMAND API_
  - open MongoDB Compass and create new database/collection
  - use docker and execute command: 

  `sudo docker run -it -d --name mongo-container -p 27017:27017 --network ratesNet --restart always -v`  

b) **MySQL**  is used by _QUERY API_
- open MySQL database on your local environment and create new database
- use docker and execute command:

`sudo docker run -it -d --name mysql-container -p 3306:3306 --network ratesNet -e MYSQL_ROOT_PASSWORD=ratesRootPsw --restart always -v mysql_data_container:/var/lib/mysql mysql:latest`

c) **Apache Kafka**:
  - create new file or use exists file docker-compose.yml path:  
    **docker**/docker-compose.yml
  - entry to folder path and execute command `docker-compose up -d`