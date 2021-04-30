package com.marado.app;

import com.marado.entities.BouncingObject;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import javax.swing.JFrame;

public class App extends Canvas implements Runnable, KeyListener{
    
    static Scanner input = new Scanner(System.in);
    
    static BouncingObject obj = new BouncingObject();
    
    static boolean isRunning;
    static boolean showFPS;
    private Thread thread;
    
    static JFrame window;
    int width = 100;
    int height = 144;
    int scale = 2;
    
    public App(){
        addKeyListener(this);
        setPreferredSize(new Dimension(width*scale,height*scale));
        initWindow();
    }
    
    public void initWindow(){
        window = new JFrame("Bouncing Object by Marado");
        window.add(this);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    public synchronized void startApp(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }
    
    public synchronized void stopApp(){
        
    }
    
    static void toggleFPS(){ //Show UPS/FPS ON and OFF
        showFPS = !showFPS; //If it's true now it's false If it's false now it's true
    }
    
    static void readInput(){
        
    }
    
    static void act(){
        
    }
    
    static void render(){
        
    }
      
    public static void main(String[] args) {
        App app = new App();
        app.startApp();
    }

    @Override
    public void run(){
        long initialTime = System.nanoTime(); // Catch the current time of the System in nanoseconds. It will be the time that you called the function
        final double UPS_AMOUNT = 1000000000 / 144; //How much UPS(Updates Per Second) but /1*10^9 to be in nanoseconds
        final double FPS_AMOUNT = 1000000000 / 60; //Same thing to UPS but now for FPS(Frames Per Second)
        double deltaUPS = 0, deltaFPS = 0; // The intreval between each update/frame
        int ticks = 0, frames = 0; // It will be to count the number of ticks and frames
        long timer = System.currentTimeMillis(); // Catch the current time of the System in millisseconds
        while(isRunning) { //While it's running 
            long curTime = System.nanoTime(); // Catch the current time of the System in nanoseconds. It will be the time that the loop started. It change every loop
            deltaUPS += (curTime - initialTime) / UPS_AMOUNT; //The intreval between an update that it's 0 at the start it will be now the time at the start of the loop (imagine 2s btw that's too much probably it will be like nanoseconds so fast) - the time at the start of the function (0s) = 2. Now divide by the amount of updates (imagine 60UPS) => 2/60 = 0.0(3). So now we add 0.0333 to the time between each update. We are at 3% of an update (0.03333 = 3.333%) and that means that we need it to reach 100% so we do the loop.
            deltaFPS += (curTime - initialTime) / FPS_AMOUNT; //Same thing above
            initialTime = curTime; // Now the time that you called the function will be the time that the loop started. If you remove that line your FPS will increase insanely Kappa remove the line to see
            if(deltaUPS >= 1){ //If we completed 100% (=1) of an update
                readInput(); //See what the function do
                act(); //see what the function do
                ticks++; //Count 1 more tick
                deltaUPS--; //-1 to return to 0 and start again
            }
            if(deltaFPS >= 1){
                render(); //See what the function do
                frames++;
                deltaFPS--;
            }
            if(System.currentTimeMillis() - timer >= 1000 && showFPS) { //Just to show the UPS/FPS over 1s. If the time now (imagine 2s) - the time at the start of the function (0s) it's > than 1000 millisseconds(1s) and you want to show the UPS/FPS
                System.out.format("UPS: %d, FPS: %d\n", ticks, frames); //Print the ticks and frames counted
                frames = 0; //Counter return to 0
                ticks = 0;
                timer += 1000; //because passed 1 second. If you don't do this timer will be forever 0 and it will show the UPS/FPS without passing 1s. Remove the line to see
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            obj.bounce();
            System.out.println(obj.getValues());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
