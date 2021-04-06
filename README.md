# maze-generator
### Jesús Israel Gutiérrez Elizalde
Implementation of a maze generator in java using processing and the
build tool gradle.
## Requirements 
You need JDK 11 and gradle 6.6.1 installed in your system, all later 
versions and some older should work.
To make sure that you have all necessary run the following commands,
and if generates some similar output then all is ready to go.
```
$ java -version
openjdk 11.0.8 2020-07-14
OpenJDK Runtime Environment (build 11.0.8+10)
OpenJDK 64-Bit Server VM (build 11.0.8+10, mixed mode, sharing)
```

```
$ gradle -v

------------------------------------------------------------          
Gradle 6.6.1                                                          
------------------------------------------------------------          

Build time:   2019-01-10 23:05:02 UTC                                 
Revision:     3c9abb645fb83932c44e8610642393ad62116807                

Kotlin DSL:   1.1.1                                                   
Kotlin:       1.3.11                                                  
Groovy:       2.5.4                                                   
Ant:          Apache Ant(TM) version 1.9.13 compiled on July 10 2018  
JVM:          10.0.2 (openjdk 11.0.8)                 
OS:           Linux 
```

## How to use it
Once you are located in the maze-generator folder run the following 
command.
```
gradle run -PappArgs="['20']"
```
Where 20 is the size of the maze, you can change it for your desired 
size, but values bigger than 250 won't work as fine as smaller sizes.

Once executed you'll get a maze.
| <img src="/img/maze.png" width="50%"> | 
|:--:|
---------------------------------------------------
To solve the maze press ENTER.

| <img src="/img/solvedMaze.png" width="50%"> | 
|:--:|
---------------------------------------------------
You can generate more mazes and solve them by pressing ENTER.
You can exit the program by pressing the key 'q' or ESC.
