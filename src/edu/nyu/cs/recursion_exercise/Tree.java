package edu.nyu.cs.recursion_exercise;

import processing.core.PApplet;

/**
 * This program uses the PApplet package to draw fractals which looks like a tree. 
 * The fractal is drawn every 5 seconds, the drawFractal() method calling itself 4 times once it is called. 
 * The recursive function repeats itself until the number of recursions, which starts from 9 and decreases, reaches 3. 
 * 
 * @author Foo Barstein, with comments by Troy Kim (sk8199)
 */
public class Tree extends PApplet {
    
    /** The max angle in degrees, 360, as an int. It is used later to modify larger degrees to have a value less than 360. */
    public static final int maxAngle = 360;
    /** The x coordinate of the starting point, as an int. It is used later to set the starting x coordinate as the drawFactal() method is called again. */
    public static final int startX = 600;
    /** The y coordinate of the starting point, as an int. It is used later to set the starting y coordinate as the drawFactal() method is called again. */
    public static final int startY = 800;
    /** The starting point of the number of recursions. It is used later to set the starting value of recursions, which decreases until it meets the base case value. */
    public static final int numOfRecursions = 9;
    /** The starting angle where the line directs. It is used later in the drawFactal() method to get the second x and y coordinates, used with Math.sin and Math.cos methods. */
    public static final int startAngle = 0;
    /** The base tree size used to generate the length of the line of the tree. It is later used with the value number of recursions via the Math.pow method to generate the exact value. */
    public static final double treeSize = 2;
    /** The point used to check for the base case, as an int. It is used later in the drawFractal() method to check whether the number of recursions is small enough to stop. */
    public static final int Detail = 3;
    /** The argument used in the Random.nextInt() method to generate a random integer between 0 (inclusive) and 30 (exclusive). The generated number is used to get a new angle. */
    public static final int randFact = 30;
    /** An int array that stores values used to generate new angles when drawFactal() method is recursively called. */
    public static final int[] constFact = {-60, 05, -50, 45};  
    /** The array that stores integers used to generate new r value for rgb. */
    public static int[] red =   {0, 0, 0, 0, 7, 15, 23, 31, 39, 47, 55, 43};
    /** The array that stores integers used to generate new g value for rgb. */
    public static int[] green = {171, 159, 147, 135, 123, 111, 99, 87, 75, 63, 51, 43};
    /** The array that stores integers used to generate new b value for rgb. */
    public static int[] blue =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};   
    
    /** 
     * Converts degree to radian.
     * - Multiplies the degree with pi value and divides it by 180
     * 
     * @param deg The degree, as an intger
     * @return The radian value, as a double
     */
    public static double degToRad(int deg) {
        return deg * Math.PI / 180;
    }
    
    /**
     * The recursive method which draws lines until it repeats itself certain number of times.
     * For each time, the method generates four shorter lines, as the branches of trees. This is continued until the number or recursions reaches the value of Detail.
     * 
     * @param x The starting x coordinate, as an int.
     * @param y The starting y coordinate, as an int.
     * @param n The given number of recursions, as an int.
     * @param angle The starting angle, in degrees, as an int.
     */
    public void drawFractal(int x, int y, int n, int angle) {

        // The base case. If the value of the number of recursions is equal to the value of Detail, the method returns.
        if (n == Detail) return;

        // This line of code generates the length of the of line, or the branch.
        int len = (int) Math.round(Math.pow(treeSize, n - 1));
        
        // Sets the ending x coordinate of the line.
        int xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
        // Sets the ending y coordinate of the line.
        int yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));
        // Sets the midpoint of the x coordinates.
        int mid1x = (x + xn1) / 2;
        // Sets the midpoint of the y coordinates.
        int mid1y = (y + yn1) / 2;
        // Sets the 3/4 point of the x coordinates.
        int mid2x = (mid1x + xn1) / 2;
        // Sets the 3/4 point of the y coordinates.
        int mid2y = (mid1y + yn1) / 2;
        // Sets the 1/4 point of the x coordinates.
        int mid3x = (x + mid1x) / 2;
        // Sets the 1/4 point of the y coordinates.
        int mid3y = (y + mid1y) / 2;
        // Sets the 3/8 point of the x coordinates.
        int mid4x = (mid3x + mid1x) / 2;
        // Sets the 3/8 point of the y coordinates. 
        int mid4y = (mid3y + mid1y) / 2;
        
        // This Random object is from the java.util package, which is used to generate random integers.
        java.util.Random randy = new java.util.Random();

        // Using the newly generated x and y coordinates, this line of code draws a new fractal, until it stops itself. This is a part of recursion.
        drawFractal(mid1x, mid1y, n - 1, (angle + randy.nextInt(randFact) + constFact[0]) % maxAngle);
        // Using the newly generated x and y coordinates, this line of code draws a new fractal, until it stops itself. This is a part of recursion.
        drawFractal(mid2x, mid2y, n - 1, (angle + randy.nextInt(randFact) + constFact[1]) % maxAngle);
        // Using the newly generated x and y coordinates, this line of code draws a new fractal, until it stops itself. This is a part of recursion.
        drawFractal(mid3x, mid3y, n - 1, (angle + randy.nextInt(randFact) + constFact[2]) % maxAngle);
        // Using the newly generated x and y coordinates, this line of code draws a new fractal, until it stops itself. This is a part of recursion.
        drawFractal(mid4x, mid4y, n - 1, (angle + randy.nextInt(randFact) + constFact[3]) % maxAngle);
        
        // Generates a new float r value for a new color.
        float r = Tree.red[(randy.nextInt() % 3) + n];
        // Generates a new float g value for a new color.
        float g = Tree.green[(randy.nextInt() % 3) + n];
        // Generates a new float b value for a new color.
        float b = Tree.blue[(randy.nextInt() % 3) + n];
        
        // This line of code sets the color used to draw the lines with the generated r, g, b values. It refers to PApplet.stroke() from the extended PApplet class.
        this.stroke(r, g, b);
        // This line of code draws the line, with starting point of (x,y) and ending point of (xn1, yn1).
        this.line(x, y, xn1, yn1);
    }

    /**
     * This overridden method is called to modify settings of the window. 
     */
    public void settings() {
        // this line of code refers to PApplet.size() from the extended PApplet class. 
        this.size(1200, 1000);
    }

    /**
     * This overridden method is automatically called when the program runs. 
     * - Sets the background as black (RGB 0, 0, 0) and calls the drawFractal() method
     */
    public void setup() {
        background(0, 0, 0);
        drawFractal(startX, startY, numOfRecursions, startAngle);
    }
    
    /**
     * This overridden method is continously called by default.
     * - Checks if 5 seconds have passed, and sets the background as black and calls the drawFractal method
     */
    public void draw() {
        if (PApplet.second() % 5 == 0) {
            background(0, 0, 0);
            drawFractal(startX, startY, numOfRecursions, startAngle);
        }
    }
    
    /** 
     * The main method is automatically called first in a Java program.
     * 
     * @param args An array of any command-line arguments.
     */
    public static void main(String args[]) {
        PApplet.main("edu.nyu.cs.recursion_exercise.Tree");
    }
}