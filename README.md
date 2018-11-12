# Pokegame
Pokemon card game in a web application with Java EE and no web framework

## Author: Charles-Antoine Hardy , ID: 27417888
## Date: Fall 2018
## Course: SOEN 387
## Email: m.user.work@gmail.com / m.hardy.inc@gmail.com


### How to make it work ?
- Download and Extract the `.zip` folder
- Make sure you have Eclipse, MySQL and Tomcat 8.5 installed
- Install/Fix `.classpath` and `.settings`
- Change connection parametes in `app/DataSource` in the class `DatabaseConnector.java`
- Go to `app/util/` and run the test `testInitDB()`
- Install make sure to rebuild class path and install libraries with Eclipse...
- You also might need to install `mysql-connector` jar file
- Add the server into Eclipse once Tomcat is setup
- Run poke on server you should see a welcome message at http://localhost:PORT/poke/

