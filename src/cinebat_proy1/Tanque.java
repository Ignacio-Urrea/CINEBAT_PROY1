/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//import javafx.scene.shape.ArcType;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//a

/**
 *
 * @author sfmnl
 */
public class Tanque {
    private double tanque1X; private double tanque1Y;
    private double tanque2X; private double tanque2Y;
    private int T1; private int T2; //indices en X[] , Y[]
    private boolean gameOver; private int ganador;
    
    
    public Tanque(){gameOver = false;}
    
    public void setTanque(int tanque, double[] X, double[] Y){
        Random r = new Random();
        int x = 2+r.nextInt(195); // de 2 a 197 para que quepa el tanque
        
        
        if(tanque==1){
            while(X[x]>=700){x=2+r.nextInt(196);}
            tanque1X = X[x]; tanque1Y = Y[x]-30;T1 = x;
            
        }
        else if (tanque==2){
            
            while ((X[x]-X[T1])<1000 || X[x]<100){System.out.println(X[T1]);x=2+r.nextInt(195);}
            tanque2X = X[x]; tanque2Y = Y[x]-30;T2 = x;
        }
    }
    
    public void desplegar(GraphicsContext tanque1, GraphicsContext tanque2){
        
        
        double[] t1X = new double[6]; double[] t1Y = new double[6];
        double[] t2X = new double[6]; double[] t2Y = new double[6];
        //Base
        t1X[0]=tanque1X-40;t1X[1]=tanque1X-35;t1X[2]=tanque1X+40;t1X[3]=tanque1X+50;t1X[4]=tanque1X+35;t1X[5]=tanque1X-30;
        t1Y[0]=tanque1Y;t1Y[1]=tanque1Y-20;t1Y[2]=tanque1Y-20;t1Y[3]=tanque1Y;t1Y[4]=tanque1Y+15;t1Y[5]=tanque1Y+15;
        
        t2X[0]=tanque2X+40;t2X[1]=tanque2X+35;t2X[2]=tanque2X-40;t2X[3]=tanque2X-50;t2X[4]=tanque2X-35;t2X[5]=tanque2X+30;
        t2Y[0]=tanque2Y;t2Y[1]=tanque2Y-20;t2Y[2]=tanque2Y-20;t2Y[3]=tanque2Y;t2Y[4]=tanque2Y+15;t2Y[5]=tanque2Y+15;
        
        tanque1.setFill(Color.INDIANRED);
        tanque2.setFill(Color.FORESTGREEN);
        
        tanque1.fillPolygon(t1X, t1Y, 6);
        tanque2.fillPolygon(t2X, t2Y, 6);
        
        //Torre
        t1X[0]=tanque1X-28;t1X[1]=tanque1X+30;t1X[2]=tanque1X+30;t1X[3]=tanque1X+20;t1X[4]=tanque1X-28;t1X[5]=tanque1X-29;
        t1Y[0]=tanque1Y-20;t1Y[1]=tanque1Y-20;t1Y[2]=tanque1Y-30;t1Y[3]=tanque1Y-40;t1Y[4]=tanque1Y-35;t1Y[5]=tanque1Y-26;
        
        t2X[0]=tanque2X+28;t2X[1]=tanque2X-30;t2X[2]=tanque2X-30;t2X[3]=tanque2X-20;t2X[4]=tanque2X+28;t2X[5]=tanque2X+29;
        t2Y[0]=tanque2Y-20;t2Y[1]=tanque2Y-20;t2Y[2]=tanque2Y-30;t2Y[3]=tanque2Y-40;t2Y[4]=tanque2Y-35;t2Y[5]=tanque2Y-26;
        
        tanque1.setFill(Color.SALMON);
        tanque1.fillPolygon(t1X, t1Y, 6);
        
        tanque2.setFill(Color.LIMEGREEN);
        tanque2.fillPolygon(t2X, t2Y, 6);
        
        
        t1X = new double[4]; t1Y = new double[4];
        t2X = new double[4]; t2Y = new double[4];
        
        t1X[0] = tanque1X+28;t1X[1] = tanque1X+20; t1X[2] = tanque1X+70; t1X[3] = tanque1X+66;
        t1Y[0] = tanque1Y-28;t1Y[1] = tanque1Y-38; t1Y[2] = tanque1Y-55; t1Y[3] = tanque1Y-45;
        
        t2X[0] = tanque2X-28;t2X[1] = tanque2X-20; t2X[2] = tanque2X-70; t2X[3] = tanque2X-66;
        t2Y[0] = tanque2Y-28;t2Y[1] = tanque2Y-38; t2Y[2] = tanque2Y-55; t2Y[3] = tanque2Y-45;
        
        tanque1.setFill(Color.TOMATO);
        tanque2.setFill(Color.MEDIUMSEAGREEN);
        
        tanque1.fillPolygon(t1X, t1Y, 4);
        tanque2.fillPolygon(t2X, t2Y, 4);
        
    }
    
    public double getT1X(){return tanque1X;}
    public double getT2X(){return tanque2X;}
    
    public double getT1Y(){return tanque1Y;}
    public double getT2Y(){return tanque2Y;}
    
    public void ganador(int gana){ganador = gana; gameOver = true;}
    
    public boolean getEstado(){return gameOver;}
    public int getGanador() {return ganador;}
}
