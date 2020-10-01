/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;


import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 *
 * @author sfmnl
 */

public class Mapa {
    private double mapaX[];
    private double mapaY[];
    private double mapaX2[];
    private double mapaY2[];
    
    
    public void Mapa(GraphicsContext gc) {
        gc.setStroke(Color.LIMEGREEN);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        double x = 0;
        double xfalso = Math.random() * 10000;
        double y = onda(xfalso)*666 +850;
        double x2;
        double y2;
        
        /*double mapaX[];
        double mapaY[];
        double mapaX2[];
        double mapaY2[];*/
        
        this.mapaX = new double[203];
        this.mapaY = new double[203];
        this.mapaX2 = new double[203];
        this.mapaY2 = new double[203];
        
        int puntos = 0;
        
        while (x<2000){
            x2 = x+10;
            xfalso = xfalso+10;
            y2 = (onda(xfalso)*666)+850 + (Math.random() * (8 + 4 + 1) -4);
            gc.strokeLine(x, y, x2, y2);
            
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
     
    }
    
    
    
    
    
    private static double onda(double x){
        double y;
        y = Math.cos(7*3.14*1000)*Math.sin(2*3.14*(1)/(1000)*x);
        return y;
    }
}
