# Introsde 2015 Final Project

<p align="center">
  <br/><b><a href="https://en.wikipedia.org/wiki/Distributed_computing">Distribuited Systems (SOA)</a></b><br/>
  <img src="https://avatars2.githubusercontent.com/u/16534367?v=3&s=300" width="200">
</p>

## Datastore Service (1/5)

**Student:** Andrea Galloni ([Twitter](https://twitter.com/andreagalloni92))

**E-Mail:** andrea [dot] galloni [at] studenti [dot] unitn [dot] it

**Organization:** [UniTN](http://www.unitn.it/en)

**Course:** [Introduction to Service Design and Engineering](https://sites.google.com/site/introsdeunitn/)

<p align="center"><br/></p>
<p align="center">
  <img src="https://s3.amazonaws.com/kinlane-productions/bw-icons/bw-conductor.png" width="50">
  <br/><b><a href="https://en.wikipedia.org/wiki/Orchestration_(computing)">Topic: Services Orchestration</a></b><br/>
</p>

### INTRODUCTION:

This client is a console application that allows the user to keep track of daily tasks assigned by a medic and store health measurements (e.g.: weight, blood pressure) keeping track of progresses. A score-motivational system is also implemented, in fact, the application  assigns some goals ( DailyGoals and LongTermGoals ) to the user.
The use case is that the user at the and of the day should insert his/her measurements and if a Long Term Goal is archived the System will provide a Song as prize to the efforts made to archive it and the doctor will be notified with an e-mail, otherwise, if the Goal is not met the system will provide a motivational quotation. Is also possible to insert edit and delete Daily Goals.  

### PROJECT ARCHITECTURE:

The project is composed by seven different services, those entities interact all together thanks to **the process centric service**. Follows a short description of those services:

+ **DataBase Service:** it is based on SOAP web service technology. It manages the data and acts as data source, it performs reads and writes on the database. The SOAP protocol exposes only methods to serve and store data, it does not provide any other functionality.

+ **Adapter Service:**

<p align="center">
  <br/><b><a href="https://en.wikipedia.org/wiki/Service-oriented_architecture">
  Project Architecture:</a></b><br/>
  <img src="imgs/HealthCareSystem.png">
</p>


<p align="center">
  <br/><b><a href="https://maven.apache.org/">Build Tool:</a></b><br/>
  <a href="https://ant.apache.org/">
  <img src="http://jansensan.net/images/blog/post0016_001.jpg">
  </a>
</p>

<p align="center">
  <br/><b><a href="https://www.jetbrains.com/idea/">Editor Used:</a></b><br/>
  <a href="https://www.jetbrains.com/idea/">
  <img src="https://pbs.twimg.com/profile_images/674914166239571968/0R_pWWlt.png" width="200">
  </a>
</p>

## .

<p align="center">
  <a href="http://unitn.it/en">
  <img src="https://raw.githubusercontent.com/sn1p3r46/introsde-2015-assignment-3-client/master/images/LogoUniTn.png" width="300">
  </a>
</p>
