package com.marado.entities;

import com.marado.app.App;
import com.marado.app.Environment;
import java.awt.Color;
import java.awt.Graphics;

public class BouncingObject {
    
    public boolean isBouncing = false; //When the object is bouncing it's true
    
    public double y = 0; //The windowHeight of the object
    public int objWidth, objHeight = 16;
    double initVelocity = 35; //The initial velocity that the object
    double velocity; //The velocity on the air
    double time = 0.001; //Time in the air

    public void render(Graphics g){
        g.setColor(new Color(0, 255, 0));
        g.fillRect((App.windowWidth)/2, (App.windowHeight)-((int)y), objWidth, objHeight);
    }
    
    public void bounce(){ //Bounce Action
        if(isBouncing){ //If you clicked <SPACE> now the object it's bouncing
            y = initVelocity*time-0.5*Environment.g*Math.pow(time, 2); //Calculate the object height
            time += (double) 1/App.UPS; //1s/UPS
            if(time < initVelocity/Environment.g) //If the moviment it's ascendent
                velocity = initVelocity-Environment.g*time; //Calculate velocity
            else //If the moviment it's descendent
                velocity = initVelocity+Environment.g*time; //Calculate velocity

            if(velocity == 0) //I think it's unnecessary but...
                    initVelocity = Environment.g*time; //Calculate the initial velocity

            if(y <= 0){ //When the object touch the ground
                y = 0; //Height is 0
                time = 0.001; //Reset time
                velocity = 0; //Velocity is 0
                isBouncing = false; //Isn't bouncing now
            }
        }
    }
    
    public String getValues(){
        return (String.format("Height: %.3fm\tInitial Velocity: %.3fm/s\tTime: %.3fs\tVelocity: %.3fm/s\n", y, initVelocity, time, velocity)); //Show the values
    }
}
