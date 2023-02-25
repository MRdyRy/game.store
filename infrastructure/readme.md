infrastructure :
- postgresql
- keycloack
- redis
- zookeeper
- kafka
- elasticsearch
- zipkin

run docker compose using this command :

<p style="background:black; padding:5px">sudo docker-compose -d -f common.yml -f zookeeper.yml -f kafka_cluster.yml -f infrastructure_all.yml -f additional_trace.yml up</p>
pic :
<img style=' display: block;
  margin-left: auto;
  margin-right: auto;
  width: 100%;' src="/infrastructure/doc/Screenshot from 2023-02-26 02-00-52.png" alt="execute docker compose"/>
  
result : 
<img style=' display: block;
  margin-left: auto;
  margin-right: auto;
  width: 100%;' src="/infrastructure/doc/Screenshot from 2023-02-26 01-58-47.png" alt="list container running"/>


after that run init_kafka.yml :
<p style="background:black; padding:5px">sudo docker-compose -d -f init_kafka.yml up</p>

please create database in postgresql, first need to get in postgres container, using this command :
<p style="background:black; padding:5px">sudo docker exec -it [postgres container name] psql -U [username] -W [password]</p>

in postgres container type this and enter :

<p style="background:black; padding:5px">CREATE DATABASE CUSTOMER;</p>

<p style="background:black; padding:5px">CREATE DATABASE GAME;</p>
