# Game Suite Application

This Java Game Suite Application consists of Tic Tac Toe and Numerical Tic Tac Toe.

## Description

This application involves a graphical user interface for both Tic Tac Toe and Numerical Tic Tac Toe. Additionally, a command prompt version of the Tic Tac Toe game can be played as well.  The purpose is to demonstrate cObject-Oriented principles including encapsulation, inheritance and implement the concepts learned into my code. 

## Getting Started

### Dependencies

* Latest version of JUnit testing framework: 'junit:junit:4.13'
* Latest version of gradle is required
* Functional on both MacOS and Windows


### Executing the GUI Application

* To build the program using Gradle, in your terminal head to the directory that contains the project files. Ensure that the build.gradle file is included in this directory. In the command line type:
```
gradle build
```
* The program should build successfully. Now type:
```
gradle run
```
* The terminal shoud ouptut the run task of the program which looks like this:
```
To run the program:
java -jar build/libs/A3.jar
```
* Now copy and paste that jar command into a separate local terminal and you are ready to play!

### Executing the Tic Tac Toe of Command Prompt Version

* Open your build.gradle file and change the attribute of the jar from 
```
attributes 'Main-Class': 'boardgame.GameUI'
```

to

```
attributes 'Main-Class': 'boardgame.TextUI'
```

* To build the program using Gradle, in your terminal head to the directory that contains the project files. Ensure that the build.gradle file is included in this directory. In the command line type:
```
gradle build
```
* The program should build successfully. Now type:
```
gradle run
```
* The terminal shoud ouptut the run task of the program which looks like this:
```
To run the program:
java -jar build/libs/A3.jar
```
* Now copy and paste that jar command into the terimal and you are ready to play!


## Limitations

The command prompt version of Tic Tac Tac does not save and load the board.

## Author Information

* Manu Konnur
* mkonnur@uoguelph.ca

## Development History

* 1.1: Friday, November 25, 2022
    * Fixed some remaining checkstyle errors
    * Completed readMe and program is ready for submission
* 1.0: Thursday, November 24, 2022
    * Completed saving and load player profiles
    * Enhanced GUI apperance
    * Completed error checking and fixed exception erorrs
* 0.9: Tuesday, November 22, 2022
    * Save and Load of the board for both games is now functional
    * Performed few bug fixed
* 0.8: Saturday, November 19, 2022
    * Completed GUI for Numerical Tic Tac Toe
    * Began to plan out design for saving and loading interface
* 0.7: Thursday, November 17, 2022
    * Completed GUI for Tic Tac Toe
    * Started GUI for Numerical Tic Tac Toe
* 0.6: Tuesday, November 15, 2022
    * Begin the graphical user interface
    * Started to construct the GameUI class and display menu options
* 0.5: Saturday, November 12, 2022
    * Tested TextUI class and completed command prompt version of Tic Tac Toe
    * Various bug fixed and optimized methods to adhere to OOP principles
* 0.4: Wednesday, November 9, 2022
    * Completed methods to check winning and tie scenarios
    * Formed the methods for game functionality in Tic Tac Toe and Numerical Tic Tac Toe.
    * Optimized classes to follow coding conventions and proper formatting
* 0.2: Tuesday November 8, 2022
    * Began making required classes
    * Created accessors and mutators of instance variables  
    * Fixed various bugs detected in other methods
* 0.1: Friday November 4, 2022
    * Git clone repository into local system
    * Ran a hello world demo to ensure program is running

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)






