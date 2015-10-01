/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indledendeprogrammering_afleveringsopgave_1.Opgaver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jespe_000
 */

public class CatAndMouse {
    public static Random mouseMovement = new Random();
    public static int boardSize = 20;
    
    public static void main(String[] args) {
        Point mouse = new Point(3, 7);
        Point cat = new Point(9, 15);        
    }
    
    public static void runCatAndMouse(int n, int catX, int catY, int mouseX, int mouseY)
    {
        boardSize = n;
        if ((mouseX == catX && mouseY == catY)) // or the mouse or cat are placed in the lake.
            System.out.println("Error");
        else {                       
             Point cat = new Point(catX, catY);
             Point mouse = new Point(mouseX, mouseY);              
             
            while (true)
            {            
               System.out.println("cat: " + cat.x + "," + cat.y + ". mouse: " + mouse.x + "," + mouse.y);
               cat = moveCat(cat, mouse);
               if (cat.equals(mouse))
                   break;
               cat = moveCat(cat, mouse);
               if (cat.equals(mouse))
                   break;            
               mouse = moveMouse(mouse, cat);
               if (cat.equals(mouse)) // to be removed, not needed
                   break;
            }   
            System.out.println("cat: " + cat.x + "," + cat.y + ". mouse: " + mouse.x + "," + mouse.y);
        }
    }
    
    public static Point moveMouse(Point mouse, Point cat)
    {
        List<String> possibleMoves = new ArrayList<String>();
        mouseInPosition(possibleMoves, mouse, cat);            
        int moveDirectionMouse = mouseMovement.nextInt(possibleMoves.size()); //Lav en list af muligheder
        if (possibleMoves.get(moveDirectionMouse).equals("Stay"))
            return mouse;
        else if (possibleMoves.get(moveDirectionMouse).equals("Right"))
            mouse.move(mouse.x + 1, mouse.y);
        else if (possibleMoves.get(moveDirectionMouse).equals("Uo"))
            mouse.move(mouse.x, mouse.y + 1);
        else if (possibleMoves.get(moveDirectionMouse).equals("Left"))
            mouse.move(mouse.x - 1, mouse.y);
        else if (possibleMoves.get(moveDirectionMouse).equals("Down"))
            mouse.move(mouse.x, mouse.y - 1);        
        return mouse;
    }    
     
    public static void mouseInPosition(List<String> possibleMoves, Point mouse, Point cat) 
    {
        possibleMoves.add("Stay");
        if (mouse.x + 1 < boardSize && !mouseCatOverlap("Right", mouse, cat))
            possibleMoves.add("Right");
        if (mouse.y + 1 < boardSize && !mouseCatOverlap("Up", mouse, cat))
            possibleMoves.add("Up");
        if (mouse.x - 1 > 0 && !mouseCatOverlap("Left", mouse, cat))
            possibleMoves.add("Left");
        if (mouse.y - 1 > 0 && !mouseCatOverlap("Down", mouse, cat))
            possibleMoves.add("Down");
    }
    
    public static boolean mouseCatOverlap(String direction, Point mouse, Point cat)
    {
        boolean overlap = false;
        if (direction.equals("Right")) {
            mouse.translate(1, 0); 
            if (cat.equals(mouse)) 
                overlap = true;
            mouse.translate(-1, 0);            
        }
        if (direction.equals("Up")) {
            mouse.translate(0, 1); 
            if (cat.equals(mouse)) 
                overlap = true;
            mouse.translate(0, -1);            
        }
        if (direction.equals("Left")) {
            mouse.translate(-1, 0); 
            if (cat.equals(mouse)) 
                overlap = true;
            mouse.translate(1, 0);            
        }
        if (direction.equals("Down")) {
            mouse.translate(0, -1); 
            if (cat.equals(mouse)) 
                overlap = true;
            mouse.translate(0, 1);            
        }
        return overlap;
    }
    
    public static Point moveCat(Point cat, Point mouse)
    {
        double angle = Math.toDegrees(angleTwoPoints(cat, mouse));  //Bare lad det være i radianer...
        double angleShift = 2*Math.PI/(8*2);        
        
        if (((angle > angleShift * 15) && (angle < angleShift * 16)) || (angle <= angleShift * 1)) //Skriv det som et antal af 22.5 grader.
            cat.move(cat.x + 1, cat.y); 
        else if ((angle > angleShift * 1) && (angle <= angleShift * 3)) 
            cat.move(cat.x + 1, cat.y + 1);   
        else if ((angle > angleShift * 3) && (angle <= angleShift * 5))  
            cat.move(cat.x, cat.y + 1);   
        else if ((angle > angleShift * 5) && (angle <= angleShift * 7))  
            cat.move(cat.x - 1, cat.y + 1); 
        else if ((angle > angleShift * 7) && (angle <= angleShift * 9)) 
            cat.move(cat.x - 1, cat.y); 
        else if ((angle > angleShift * 9) && (angle <= angleShift * 11)) 
            cat.move(cat.x - 1, cat.y - 1); 
        else if ((angle > angleShift * 11) && (angle <= angleShift * 13)) 
            cat.move(cat.x, cat.y - 1); 
        else if ((angle > angleShift * 13) && (angle <= angleShift * 15)) 
            cat.move(cat.x + 1, cat.y - 1); 
        else 
        {
            throw new ArithmeticException("What happened to the angle?");
        }
        
        return cat;
    }
    
    public static double angleTwoPoints(Point point1, Point point2) //Redo
    {
        Point PointRight = new Point(1, 0);
        Point point1InZero = new Point((point2.x - point1.x), (point2.y - point1.y)); //Omnavngiver senere.
           
        double scalarProduct = (PointRight.x  * point1InZero.x) + (PointRight.y * point1InZero.y);
        double vectorLength = point1InZero.distanceSq(0, 0);
        double angle = Math.acos(scalarProduct / vectorLength); 
        
        if (point1.y > point2.y)
            angle = (Math.PI*2) - angle;        
        return angle;
    }
    
    private static double getVectorLength(Point pointInZero) //Kan slettes
    {
        double Length = Math.sqrt(Math.pow(pointInZero.x, 2) + Math.pow(pointInZero.y, 2));
        return Length;
    }
    
    //The cake is a lie
    
}
