package com.marado.graphicInterface;

import com.marado.app.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Data {
    
    boolean showUnFPS = true;
    
    public void toogleUnFPS(){
        showUnFPS = !showUnFPS;
    }
    
    public void render(Graphics graphics){
        graphics.setColor(new Color(255, 255, 255));
        graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
        graphics.drawString(Environment.getGravity(), 4, 12);
        graphics.drawString(App.obj.getInitVelocity(), 4, 24);
        graphics.drawString(App.obj.getVelocity(), 4, 36);
        graphics.drawString(App.obj.getHeight(), 4, 48);
        graphics.drawString(App.obj.getTime(), 4, 60);
        if(showUnFPS){
            graphics.setColor(new Color(255, 255, 255));
            graphics.setFont(new Font("Monospaced", Font.PLAIN, 10));
            graphics.drawString(String.format("UPS: %d, FPS: %d\n", App.ups, App.fps), 4, 70); 
        }
    }
}
