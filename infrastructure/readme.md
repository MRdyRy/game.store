infrastructure :
- postgresql
- keycloack

run docker compose using this command :

<p style="background:black; padding:5px">sudo docker-compose -f common.yml -f infrastructure_all.yml up</p>

please create database in postgresql, first need to get in postgres container, using this command :
<p style="background:black; padding:5px">sudo docker exec -it [postgres container name] psql -U [username] -W [password]</p>

in postgres container type this and enter :

<p style="background:black; padding:5px">CREATE DATABASE CUSTOMER;</p>

<p style="background:black; padding:5px">CREATE DATABASE GAME;</p>