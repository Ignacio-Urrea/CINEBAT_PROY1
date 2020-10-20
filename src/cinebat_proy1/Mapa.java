/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

//a

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author sfmnl
 */
public class Mapa {
    private double X[];
    private double Y[];
    
    public Mapa(){ X = new double[200];Y = new double[200];}
    
    public double[] getX() {return X;}
    public double[] getY() {return Y;}
    
    public void genMapa(GraphicsContext gc) {
        gc.setStroke(Color.LIMEGREEN);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        double x = 0;
        double xfalso = Math.random() * 10000;
        double y = onda(xfalso)*666 +800;
        double x2;
        double y2;
        
        double mapaX2[];
        double mapaY2[];
        double mapaX[];
        double mapaY[];
        
        
        mapaX = new double[203];
        mapaY = new double[203];
        mapaX2 = new double[203];
        mapaY2 = new double[203];
        
        int puntos = 0;
        
        while (x<2000){
            x2 = x+10;
            xfalso = xfalso+10;
            y2 = (onda(xfalso)*666)+800 + (Math.random() * (8 + 4 + 1) -4);
            gc.strokeLine(x, y, x2, y2);
            
            X[puntos] = x;
            Y[puntos] = y;
            mapaX[puntos]=x;
            mapaY[puntos] = y+20;
            mapaX2[puntos]=x;
            mapaY2[puntos]=y-20;
            
            puntos++;
            
            
            x = x2;
            y=y2;
            
            
        }
        
        
        
        mapaX2[puntos] = 2000;
        mapaY2[puntos] = 0;
        mapaX2[puntos+1] = 0;
        mapaY2[puntos+1]=0;
        mapaX2[puntos+2] = mapaX2[0];
        mapaY2[puntos+2] = mapaY2[0];
        
                
        mapaX[puntos] = 2000;
        mapaY[puntos]= 1000;
        mapaX[puntos+1]=0;
        mapaY[puntos+1]=1000;
        mapaX[puntos+2]=mapaX[0];
        mapaY[puntos+2]=mapaY[0];
        gc.fillPolygon(mapaX, mapaY, puntos+3);
        
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillPolygon(mapaX2, mapaY2, puntos+3);
        //return gc;
    }
    
    public void actMapa(GraphicsContext gc){
        gc.setStroke(Color.LIMEGREEN);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        
        double mapaX2[] = new double[203];
        double mapaY2[] = new double[203];
        double mapaX[] = new double[203];
        double mapaY[] = new double[203];
        
        for (int i= 0; i<200; i++){
            
            if (i==199){gc.strokeLine(X[i], Y[i], X[i]+10, Y[i]);}
            else{gc.strokeLine(X[i], Y[i], X[i+1], Y[i+1]);}
            mapaX[i] = X[i];mapaX2[i] = X[i];
            mapaY[i] = Y[i]+20;mapaY2[i] = Y[i]-20;
        }
        
         mapaX2[200] = 2000;
        mapaY2[200] = 0;
        mapaX2[201] = 0;
        mapaY2[201]=0;
        mapaX2[202] = mapaX2[0];
        mapaY2[202] = mapaY2[0];
        
                
        mapaX[200] = 2000;
        mapaY[200]= 1000;
        mapaX[201]=0;
        mapaY[201]=1000;
        mapaX[202]=mapaX[0];
        mapaY[202]=mapaY[0];
        gc.fillPolygon(mapaX, mapaY, 202);
        
        gc.setFill(Color.BLUE);
        gc.fillPolygon(mapaX2, mapaY2, 202);
    }
    
    private static double onda(double x){
        double y;
        y = Math.cos(7*3.14*1000)*Math.sin(2*3.14*(1)/(1000)*x);
        return y;
    }
}
