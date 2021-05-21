package com.marado.app;

import com.marado.entities.BouncingObject;
import com.marado.graphicInterface.Data;
import com.marado.graphicInterface.UI;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class App extends Canvas implements Runnable, KeyListener{ //Canvas create a blank retangular that comunicates with the user and will have the proprieties of the window. Runnable to be executed by a Thread. KeyListener to read the keys
     
    public static BouncingObject obj = new BouncingObject(); //Create the object that will bounce
    static UI userInterface = new UI();
    static Data data = new Data();
    
    public static boolean isRunning; //It's the program running?
    private static Thread thread; //Create thread idk how to explain
    
    public static int UPS = 300, FPS = 60; //Number of UPS and FPS that you want
    public static int ups = 0, fps = 0;
    
    boolean receivingCommand = false;
    String command = "";
    
    static JFrame window; //Create a window using JFrame
    public static int windowWidth = 480; //Dimensions and Scale of the window:
    public static int windowHeight = 560;
    public static int windowScale = 1;

    private BufferedImage image;
    private Graphics g;
    
    public App(){ //Constructor method. It's for inititiate variables. idk how it works well
        addKeyListener(this);  //Say to the key listener to receive key events from this component
        setPreferredSize(new Dimension(windowWidth*windowScale,windowHeight*windowScale)); //Tell what dimension you want for the window
        initWindow(); //See function
        image = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    }
    
    public static void main(String[] args) { //Just what's on the main method run!
        App app = new App(); //New App
        app.startApp(); //Start app
    }
    
    private void initWindow(){
        window = new JFrame("Bouncing Object by Marado"); //New window with that name
        window.add(this); //Add the proprieties of Canvas. Why "this"? --> idk
        window.setResizable(false); //Can't change dimensions
        window.pack(); //Causes this window to be sized to fit the preferred size
        window.setLocationRelativeTo(null); //On the center of the screen
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close window = end program
        window.setVisible(true); //You can see it
    }
    
    public synchronized void startApp(){ //Synchronized because the method can only be accessed by one thread at a time
        thread = new Thread(this); //New Thread. Why "this"? --> idk I think it's because of the Runnable
        isRunning = true; //YEP
        thread.start(); //Thread begin execution
    }
    
    public static synchronized void stopApp(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void readInput(){
        userInterface.readInput();
    }
    
    void act(){ //Will do all the actions
        obj.act();
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        g = image.getGraphics();
        g.setColor(new Color(64, 64, 64));
        g.fillRect(0, 0, windowWidth*windowScale, windowHeight*windowScale);
        userInterface.render(g);
        data.render(g);
        obj.render(g);
        
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, windowWidth*windowScale, windowHeight*windowScale, null);

        bs.show(); 
    }

    @Override
    public void run(){
        requestFocus();
        long initialTime = System.nanoTime(); // Catch the current time of the System in nanoseconds. It will be the time that you called the function
        final double UPS_AMOUNT = 1000000000 / UPS; //How much UPS(Updates Per Second) but /1*10^9 to be in nanoseconds
        final double FPS_AMOUNT = 1000000000 / FPS; //Same thing to UPS but now for FPS(Frames Per Second)
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
            if(System.currentTimeMillis() - timer >= 1000) { //Just to show the UPS/FPS over 1s. If the time now (imagine 2s) - the time at the start of the function (0s) it's > than 1000 millisseconds(1s) and you want to show the UPS/FPS
                ups = ticks;
                fps = frames;
                ticks = 0; //Counter return to 0
                frames = 0;
                timer += 1000; //because passed 1 second. If you don't do this timer will be forever 0 and it will show the UPS/FPS without passing 1s. Remove the line to see
            }
        }
        stopApp();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_PERIOD)
            receivingCommand = true;
        if(receivingCommand){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                userInterface.hasCode = true;
                userInterface.code = userInterface.input;
                receivingCommand = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                userInterface.input = userInterface.input.substring(0, userInterface.input.length() - 1);
            else if(e.getKeyCode() == KeyEvent.VK_TAB){
                userInterface.showCompleteCode = true;
            }else
                userInterface.input += e.getKeyChar();
        }else{
            if(e.getKeyCode() == KeyEvent.VK_SPACE) { //If the key it's <SPACE>
                if(!obj.isBouncing) //If the object isn't bouncing yet
                    obj.isBouncing = true; //Now it is
            }
            if(e.getKeyCode() == KeyEvent.VK_F) { //If the key it's <SPACE>
                data.toogleUnFPS();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
