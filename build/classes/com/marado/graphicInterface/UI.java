package com.marado.graphicInterface;

import com.marado.app.App;
import java.awt.Color;
import java.awt.Graphics;

public class UI {
    
    public static int codeBarWidth = 480;
    public static int codeBarHeight = 18;
        
    public void render(Graphics graphics){
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, App.windowHeight - codeBarHeight, codeBarWidth , codeBarHeight);
    }
}
