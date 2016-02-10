# Introsde 2015 Final Project

<p align="center">
  <br/><b><a href="https://en.wikipedia.org/wiki/Distributed_computing">Distribuited Systems (SOA)</a></b><br/>
  <img src="https://avatars2.githubusercontent.com/u/16534367?v=3&s=300" width="100">
</p>

## Health Care System

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
The use case is that the user, at the and of the day, should insert his/her measurements and if a Long Term Goal is archived the System will provide a Song as prize to efforts made to archive the new health status, on the other side the doctor will be notified about the patient progress via e-mail, otherwise, if the Goal is not met the system will provide a motivational quotation.

### PROJECT ARCHITECTURE:

The project is composed by seven different services, those entities interact all together thanks to the **process centric service**. Follows a short description of those services:

+ **DataBase Service:** it is based on [SOAP](https://en.wikipedia.org/wiki/SOAP) document style protocol. It manages the data and acts as data source, basically it performs reads and writes on the database. The [SOAP](https://en.wikipedia.org/wiki/SOAP) protocol **exposes only methods to serve and store data**, it does not provide any other functionality, this service **ignores the existence of all other entities.**

+ **Adapter Service:** it is based on [SOAP](https://en.wikipedia.org/wiki/SOAP) protocol. The service exposes some data methods that serves data. The data, in this case, as opposed to the Database Service, comes from external [APIs](https://en.wikipedia.org/wiki/Web_API), the adapter service **acts as mediator getting data from external services** reshaping and cleaning the content from useless attributes and it serves the final product in known and minimal format. In particular, in this specific case, it gets quotes from [QuotesOnDesign.com](http://quotesondesign.com/) and songs [soundcloud.com](https://soundcloud.com/).  

+ **Storage Service:**: it implements the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) architectural style. The service developed in this module filters and integrates data from the two services described above. It **acts as mediator wrapper** connecting data sources to the System Logic in a **completely transparent** way, in fact thanks to the Storage Service higher level services (System Logic Services) ignore the nature of underlying services.

+ **Business Logic Service:** it implements the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) architectural style. This service is designed as **decision maker and calculator** it processes data coming from both Process Centric Service (and more deeply from the user) and Storage Service. The Business Logic service is the entity that **implements (thus knows) the final purpose of the application**. This service can be seen as the "CPU" of the entire project. In this specific case it decides if a user Long-Term Goal is archived moreover it computes the daily user score, it decides if contact the doctor about a patient progress or not.

+ **Process Centric Service:** it implements the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) architectural style. This service is receiving all users requests. It is the **entry point** of the Health Care Application. This service acts as a router **dispatching requests** to the right service reshaping the data if needed, it acts as **mediator between the user client and all others services** within the application contest. This layer is doing nothing more than redirecting a request to a proper underlying service or a set of services. This service clearly implements the concept of **Service Orchestration**.

+ **Notification Service** it is based on [SOAP](https://en.wikipedia.org/wiki/SOAP) protocol. It is very basic service that when asked sends e-mail to a given address with a given content provided by the caller. In this specific case this service interacts only with the Business Logic Service.

<p align="center">
  <br/><b><a href="https://en.wikipedia.org/wiki/Service-oriented_architecture">
  Project Architecture Design:</a></b><br/>
  <img src="imgs/HealthCareSystem.png">
</p>

The data format used by [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) Services to exchange data is [JSON](https://en.wikipedia.org/wiki/JSON). This choice is due to the fact that [JSON](https://en.wikipedia.org/wiki/JSON) compared to [XML](https://en.wikipedia.org/wiki/XML) is lighter (thus faster to transfer) less verbose and better readable by humans ( [reference link](https://www.quora.com/Markup-Languages/What-are-the-advantages-of-JSON-over-XML) ). Moreover nowadays [JSON](https://en.wikipedia.org/wiki/JSON) seems to be much common to expose [Web APIs](https://en.wikipedia.org/wiki/Web_API).

While [SOAP](https://en.wikipedia.org/wiki/SOAP) services are using [XML](https://en.wikipedia.org/wiki/XML) by definition of the protocol ([SOAP W3C specs](https://www.w3.org/TR/soap/)).

<br/>
##### The specific documentation can be found on the specific wikipage of every service.
<br/>

### FUTURE WORK AND IMPROVEMENTS:

+ Integration of more services (e.g.: Runtastic)
+ Prettier user interface (Web, Mobile)
+ Notification System to remember to do Daily Exercises
+ User Statistics
+ Food programme
+ Chat service with the doctor
+ More and more and more...


### TOOLS & TECHNOLOGIES:

<p align="center">

  <a href="https://www.sqlite.org/">
  <img src="http://vignette1.wikia.nocookie.net/databasemanagement/images/c/c2/SQLite_Logo-3e5453f0a4c3e6f5.gif/revision/latest?cb=20111014145321" width="150">
  </a>
  <a href="https://en.wikipedia.org/wiki/Java_(programming_language)">
  <img src="http://blog.newrelic.com/wp-content/uploads/javalogo.png" width="150">
  </a>
  <a href="https://git-scm.com/">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Git-logo.svg/2000px-Git-logo.svg.png" width="200">
  </a>
  <a href="https://www.jetbrains.com/idea/">
  <img src="https://pbs.twimg.com/profile_images/674914166239571968/0R_pWWlt.png"  height="90">
  </a>
</p>



<p align="center">
  <br/><b><a href="https://ant.apache.org/">Build Tool:</a></b><br/>
  <a href="https://ant.apache.org/">
  <img src="http://jansensan.net/images/blog/post0016_001.jpg" height="90">
  </a>
</p>


<p align="center">
  <br/><b><a href="https://www.heroku.com/">Deployed On:</a></b><br/>
  <a href="https://www.heroku.com/">
  <img src="https://upload.wikimedia.org/wikipedia/en/a/a9/Heroku_logo.png" width="200">
  </a>
</p>


## .

<p align="center">
  <a href="http://unitn.it/en">
  <img src="https://raw.githubusercontent.com/sn1p3r46/introsde-2015-assignment-3-client/master/images/LogoUniTn.png" width="300">
  </a>
</p>
