# maze-generator
### Jesús Israel Gutiérrez Elizalde
Implementation of a maze generator in java using processing and the
build tool gradle.
## Requirements 
You need JDK 11 and gradle 6.6.1 installed in your system, all later 
versions and some older should work.
### How to use it
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
