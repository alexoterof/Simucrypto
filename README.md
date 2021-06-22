# Simucrypto
This repo consists of two applications wich runs over Angular and SpringBoot. It implements features such as JWT securization, real-time chat, communication with external API...
It allows you to invest in crypto market with fake money. It allows you to see historic data and current prices in real time of several cryptos.

### Installation
To get the front up and running you need Angular 10 and node 14.x installed on your sistem. Then, in "front" folder 
```
npm install
npm start
```

You will also need JDK 8 and Maven 3.6.X to compile an run the back-end. The jar contains an embedded Tomcat. You can compile to war changing the preferences on the pom.xml and run it over your preferred server
```
maven clean install
java -jar target/simucrypto-0.0.1-SNAPSHOT.jar
```

### Demo
A functional demo of this project (Still work in progress) can be located deployed [here.](https://alexoterof.github.io/Simucrypto/) 
