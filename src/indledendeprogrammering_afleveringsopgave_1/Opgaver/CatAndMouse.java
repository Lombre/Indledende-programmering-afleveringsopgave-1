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
    public static int boardSize = 0;
    
    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 0);
        double angle = Math.toDegrees(angleTwoPoints(point1, point2));
        angle += 1;
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
                 mouse = moveMouse(mouse);
                 //cat
             }
        }
    }
    
    public static Point moveMouse(Point mouse)
    {
        int moveDirectionMouse = mouseMovement.nextInt(3);
        if (moveDirectionMouse == 0 && mouseInPosition(0, mouse))
            mouse.move(mouse.x + 1, mouse.y);
        return mouse;
    }    
    
    public static boolean mouseInPosition(int Direction, Point mouse) //Can be combined with the calling function
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
    
    public static Point moveCat(Point cat)
    {
        
        return cat;
    }
    
    public static double angleTwoPoints(Point point1, Point point2)
    {
        double angle = 0;  
        double scalarProduct = Math.abs((point1.x * point2.x) + (point1.y * point2.y));
        double vectorLength = getVectorLength(point1) * getVectorLength(point2);        
        angle = Math.acos(scalarProduct / vectorLength);        
        return angle;        
    }
    
    private static double getVectorLength(Point point)
    {
        double Length = Math.sqrt((point.x)*(point.x) + (point.y)*(point.y));
        return Length;
    }
    
        
}
