package com.marado.entities;

import com.marado.app.App;
import com.marado.app.Environment;
import com.marado.graphicInterface.UI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

public class BouncingObject {
    
    public boolean isBouncing = false; // When the object is bouncing it's true
    
    public double y = 0; // The windowHeight of the object
    public int objWidth = 64;
    public int objHeight = 64;
    public double initVelocity = 15; // The initial velocity that the object
    double velocity = 0; // The velocity on the air
    double time = 0; // Time in the air
    
    public void act(){
        bounce(); //Object bounce. See the function
    }
    
    public void render(Graphics graphics){
        graphics.setColor(new Color(192, 192, 192));
        graphics.fillOval(App.windowWidth/2-objWidth/2, App.windowHeight-(int)y - objHeight - UI.codeBarHeight, objWidth, objHeight);
    }

    public void bounce(){ //Bounce Action
        if(isBouncing){ // If you clicked <SPACE> now the object it's bouncing
            boolean finishedBounce = time >= initVelocity/Environment.gravity; // If the object has reached the max height so he completed the bounce and now it's falling
            y = initVelocity*time-0.5*Environment.gravity*Math.pow(time, 2); // Calculate the object height
            time += (double) 1/App.UPS; // 1s/UPS
            velocity = Math.abs(initVelocity-Environment.gravity*time); // Calculate velocity
            if(velocity == 0) // I think it's unnecessary but...
                    initVelocity = Environment.gravity*time; // Calculate the initial velocity
            if(y <= 0 && finishedBounce){ // When the object touch the ground and has already bounced
            y = 0; // Height is 0
            time = 0; // Reset time
            velocity = initVelocity; // Velocity is 0
            isBouncing = false; // Isn't bouncing now
            }
        }
    }
    
    public String getInitVelocity(){
        return (String.format("Initial Velocity: %.3fm/s", initVelocity));
    }
    
    public String getHeight(){
        return (String.format("Height: %.3fm", y));
    }
    
    public String getTime(){
        return (String.format("Time: %.3fs", time));
    }
    
    public String getVelocity(){
        return (String.format("Velocity: %.3fm/s", velocity));
    }
}
