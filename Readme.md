# run below cmd and input as below
```
./gradlew tasks drawing -q

enter command: C 20 4
enter command: L 1 2 6 2
enter command: L 6 3 6 4
enter command: R 14 1 18 3
enter command: B 10 3 o
enter command: Q
```
# test cases 
```
./gradlew test

```

# run below cmd for non-interactive run
```
./gradlew tasks nonInteractive -q

```



# The Problem 

__Description__

You're given the task of writing a simple console version of a drawing program. 
At this time, the functionality of the program is quire limited but this might change in the future. 
In a nutshell, the program should work as follows:
 1. Create a new canvas
 2. Start drawing on the canvas by issuing various commands
 3. Quit


Command 		Description
C w h           Should create a new canvas of width w and height h.
L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.
B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
                behaviour of this is the same as that of the "bucket fill" tool in paint
                programs.
Q               Should quit the program.

# solution
 1. **InteractiveDrawingBoard** is main driver program
 2. user input are matched as reg-ex defined in **ValidCommands**
 3. **CommandFactory** is responsible to parse input and for invalid data return UNKNOW type
 4. Line is composition Point(x,y)s, Rectangle is modeled as composition of Lines
 5. **Canvas** is modled as container class which handles drwaing/re-drawing on console once a shape being added to it.
 6. **GraphicsContext** encapsulates 2d char data-structure to handle state of drwaing board


Below is a sample run of the program. User input is prefixed with **enter command:**
```

enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

enter command: Q
```
#Dependencies
* java 1.8
* spock as testing framework

