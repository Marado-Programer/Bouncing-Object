package com.marado.graphicInterface;

import com.marado.app.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI {
    
    public static int codeBarWidth = 480;
    public static int codeBarHeight = 30;
    
    public boolean hasCode = false;
    
    public boolean showCompleteCode = false;
    
    public String input = "";
    public String code;
    public String[] command = new String[3];
    
    private String[] message = {"Made by Marado", "Type '.' to write commands. <Tab> shows list"};
    
    Color ok = new Color(0, 255, 0);
    Color error = new Color(255, 0, 0);
    Color curColor;

    public static boolean isNumeric(String str) {
        if(str == null || str.length() == 0)
            return false;
        for(char c : str.toCharArray())
            if(!Character.isDigit(c) && c != '.')
                return false;
        return true;
    }
    
    private static String transformCode(String code){
        code = code.toLowerCase();
        code = code.replace(',', '.');
        code = code.trim();
        return code;
    }
    
    public void readInput(){
        if(input.equals(".")){
            message[0] = "";
            message[1] = "";
        }
        if(hasCode){
            transformCode(code);
            int i = 0;
            for(String cmds: code.split(" ")){
                command[i++] = cmds;
            }
            if(command[0].startsWith(".")) {
                if(command[0].equals(".ping")){
                    curColor = ok;
                    message[0] = ":pong.";
                }else if(command[0].equals(".bounce") || command[0].equals(".jump")){
                    App.obj.isBouncing = true;
                        curColor = ok;
                    message[0] = ":objects are bouncing.";
                }else if(command[0].equals(".change")){
                    if(command[1].equals("gravity") || command[1].equals("g")){
                        if(Environment.celestialObjects.isCelestialObject(command[2])){
                            Environment.celestialObjects.changeGravity(command[2]);
                            curColor = ok;
                            message[0] = (":gravity changed to " + Environment.gravity);
                        }else{
                            curColor = error;
                            message[0] = ":number not specified. ";
                            message[1] = "try a name of a celestial object or digit a number.";
                        }
                    }else if(command[1].equals("initial-velocity") || command[1].equals("vi") || command[1].equals("v0") || command[1].equals("vo")){
                        if(isNumeric(command[2])){
                            App.obj.initVelocity = Double.parseDouble(command[2]);
                            curColor = ok;
                            message[0] = (":initial velocity changed to " + App.obj.initVelocity);
                        }else{
                            curColor = error;
                            message[0] = ":number not specified. ";
                            message[1] = "try to digit a number";
                        }
                    }else{
                        curColor = error;
                        message[0] = ":unknown parameter.";
                    }
                //}else if(command[0].equals(".exit") || command[0].equals(".quit")){
                //    App.stopApp();
                }else{
                    curColor = error;
                    message[0] = ":no/unknown parameter.";
                }
            }else{
                curColor = error;
                message[0] = ":commands start with the prefix '.'.";
            }
            input = "";
            hasCode = false;
        }
    }
    
    public void render(Graphics graphics){
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, App.windowHeight - codeBarHeight, codeBarWidth , codeBarHeight);
        graphics.setColor(new Color(255, 255, 255));
        graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
        graphics.drawString(input, 0, 556);
        graphics.setColor(curColor);
        graphics.drawString(message[0], 0, 544);
        graphics.drawString(message[1], 0, 556);
        if(showCompleteCode){
            graphics.setColor(new Color(0, 0, 0, (int)0.25*255));
            graphics.fillRect(100, 100, 100, 100);
        }
    }
}
