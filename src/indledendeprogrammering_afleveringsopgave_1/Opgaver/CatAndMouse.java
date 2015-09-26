/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indledendeprogrammering_afleveringsopgave_1.Opgaver;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author jespe_000
 */
public class CatAndMouse {
    public static Random mouseMovement = new Random();
    public static int boardSize = 20;
    
    public static void main(String[] args) {
        Point mouse = new Point(9, 2);
        Point cat = new Point(4, 4);
        while (true)
        {            
            System.out.println("cat: " + cat.x + "," + cat.y + ". mouse: " + mouse.x + "," + mouse.y);
            cat = moveCat(cat, mouse);
            if (cat.equals(mouse))
                break;
            cat = moveCat(cat, mouse);
            if (cat.equals(mouse))
                break;            
            mouse = moveMouse(mouse);
            if (cat.equals(mouse)) // to be removed, not needed
                break;
        }   
        System.out.println("cat: " + cat.x + "," + cat.y + ". mouse: " + mouse.x + "," + mouse.y);
    }
    
    public static void runCatAndMouse(int n, int catX, int catY, int mouseX, int mouseY)
    {
        int[][] boardPlacement = new int[n][n];
        if ((mouseX == catX && mouseY == catY))
            System.out.println("Error");
        else {                       
             boardPlacement[catX][catY] = 1;
             boardPlacement[mouseX][mouseY] = 2; 
             Point cat = new Point(catX, catY);
             Point mouse = new Point(mouseX, mouseY);      
             boardSize = n;
             
             while (!cat.equals(mouse))
             {
                 cat.x += 0.5; cat.y += 0.5; mouse.x += 0.5; mouse.y += 0.5;
                 mouse = moveMouse(mouse);
                 cat = moveCat(cat, mouse);
                 cat.x -= 0.5; cat.y -= 0.5; mouse.x -= 0.5; mouse.y -= 0.5;
             }
        }
    }
    
    public static Point moveMouse(Point mouse)
    {
        int moveDirectionMouse = mouseMovement.nextInt(3);
        if (moveDirectionMouse == 0 && mouseInPosition(0, mouse))
            mouse.move(mouse.x + 1, mouse.y);
        if (moveDirectionMouse == 1 && mouseInPosition(1, mouse))
            mouse.move(mouse.x, mouse.y + 1);
        if (moveDirectionMouse == 2 && mouseInPosition(2, mouse))
            mouse.move(mouse.x - 1, mouse.y);
        if (moveDirectionMouse == 3 && mouseInPosition(3, mouse))
            mouse.move(mouse.x, mouse.y - 1);
        return mouse;
    }    
    
    public static boolean mouseInPosition(int Direction, Point mouse) 
    {
        boolean inPosition = false;        
        if (Direction == 0 && mouse.x + 1 < boardSize)
            inPosition = true;
        if (Direction == 1 && mouse.y + 1 < boardSize)
            inPosition = true;
        if (Direction == 2 && mouse.x - 1 > 0)
            inPosition = true;
        if (Direction == 3 && mouse.y - 1 > 0)
            inPosition = true;        
        return inPosition;
    }
    
    public static Point moveCat(Point cat, Point mouse)
    {
        double angle = Math.toDegrees(angleTwoPoints(cat, mouse));
        
        if (((angle > 337.5) && (angle < 360)) || (angle <= 22.5)) 
            cat.move(cat.x + 1, cat.y); 
        else if ((angle > 22.5) && (angle <= 67.5)) 
            cat.move(cat.x + 1, cat.y + 1);   
        else if ((angle > 67.5) && (angle <= 112.5)) 
            cat.move(cat.x, cat.y + 1);   
        else if ((angle > 125.5) && (angle <= 157.5)) 
            cat.move(cat.x - 1, cat.y + 1); 
        else if ((angle > 157.5) && (angle <= 202.5)) 
            cat.move(cat.x - 1, cat.y); 
        else if ((angle > 202.5) && (angle <= 247.5)) 
            cat.move(cat.x - 1, cat.y - 1); 
        else if ((angle > 247.5) && (angle <= 292.5)) 
            cat.move(cat.x, cat.y - 1); 
        else if ((angle > 292.5) && (angle <= 337.5)) 
            cat.move(cat.x + 1, cat.y - 1); 
        else 
        {
            cat = cat;
            throw new ArithmeticException("What happened to the angle?");
        }
        
        return cat;
    }
    
    public static double angleTwoPoints(Point point1, Point point2)
    {
        Point PointRight = new Point(1, 0);
           
        double scalarProduct = (PointRight.x  * (point2.x - point1.x)) + (PointRight.y * (point2.y - point1.y));
        double vectorLength = 1 * getVectorLength(point2, point1);        
        double angle = Math.acos(scalarProduct / vectorLength); 
        
        if (point1.y > point2.y)
            angle = (Math.PI*2) - angle;        
        return angle;
    }
    
    private static double getVectorLength(Point point1, Point point2)
    {
        double Length = Math.sqrt((point1.x - point2.x)*(point1.x - point2.x) + (point1.y - point2.y)*(point1.y - point2.y));
        return Length;
    }
    
    //The cake is a lie
    
}
