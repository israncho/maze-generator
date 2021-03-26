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
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
```

```
$ gradle -v

------------------------------------------------------------
Gradle 6.6.1
------------------------------------------------------------

Build time:   2020-08-25 16:29:12 UTC
Revision:     f3d1fb54a951d8b17d25748e4713bec5d128d7e3

Kotlin:       1.3.72
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.8 compiled on May 10 2020
JVM:          11.0.10 (AdoptOpenJDK 11.0.10+9)
OS:           Linux 5.8
```

## How to use it
Once you are located in the maze-generator folder run the following 
command.
```
gradle run -PappArgs="['20']"
```
Where 20 is the size of the maze, you can change it for your desired 
size, but values bigger than 250 won't work as fine as smaller sizes.

Once executed you'll get a blank box.
| <img src="/img/blankCanvas.png" width="50%"> | 
|:--:|
---------------------------------------------------
To generate the maze press ENTER

| <img src="/img/mazeGenerated1.png" width="50%"> | 
|:--:|
---------------------------------------------------
You can exit the program by pressing the key 'p' or ESC.
