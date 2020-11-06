/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

/**
 *
 * @author sfmnl
 */
public class Tanque {
    private Polygon base;
    private Polygon torre;
    private Polygon canon;
    private double posicionX;
    private double posicionY;
    private int equipo;
    private int hp;
    
    public Tanque(double x, double y, int jugador){
        posicionX = x;
        posicionY = y;
        equipo = jugador;
        hp = 100;
        
    }
    
    public void render(Group root){
        if (equipo == 1){
            base = new Polygon();
            base.getPoints().addAll(new Double[] {
                posicionX-30.0, posicionY+15.0,
                posicionX+35.0, posicionY+15.0,
                posicionX+50.0, posicionY,
                posicionX+40.0, posicionY-20.0,
                posicionX-35.0, posicionY-20.0,
                posicionX-40.0, posicionY,
            });
            torre = new Polygon();
            torre.getPoints().addAll(new Double[]{
                posicionX+30, posicionY-20,
                posicionX+30, posicionY-30,
                posicionX+20, posicionY-40,
                posicionX-28, posicionY-35,
                posicionX-28, posicionY-20,
            });
            canon = new Polygon();
            canon.getPoints().addAll(new Double[]{
                posicionX+20, posicionY-35,
                posicionX+20, posicionY-40,
                posicionX+70, posicionY-55,
                posicionX+66, posicionY-45,
            });
            base.setFill(Color.INDIANRED);
            torre.setFill(Color.SALMON);
            canon.setFill(Color.TOMATO);
            
            
        }
        if (equipo == 2){
            base = new Polygon();
            base.getPoints().addAll(new Double[] {
                
                posicionX+30, posicionY+15,
                posicionX-35, posicionY+15,
                posicionX-50, posicionY,
                posicionX-40, posicionY-20,
                posicionX+35, posicionY-20,
                posicionX+40, posicionY,
                
                
            });
            torre = new Polygon();
            torre.getPoints().addAll(new Double[]{
                posicionX-30, posicionY-20,
                posicionX-30, posicionY-30,
                posicionX-20, posicionY-40,
                posicionX+28, posicionY-35,
                posicionX+28, posicionY-20,
            
            });
            canon = new Polygon();
            canon.getPoints().addAll(new Double[]{
                posicionX-20, posicionY-35,
                posicionX-20, posicionY-40,
                posicionX-70, posicionY-55,
                posicionX-66, posicionY-45,
            });
            base.setFill(Color.FORESTGREEN);
            torre.setFill(Color.LIMEGREEN);
            canon.setFill(Color.MEDIUMSEAGREEN);
            
        }
        root.getChildren().addAll(base, torre, canon);
    }
    
    public Rectangle2D getBoundary(){
        Rectangle2D cuerpa;
        if (equipo == 1){
            cuerpa = new Rectangle2D(posicionX-60, posicionY-50, 80, 60);}
        else if (equipo==2){
            cuerpa = new Rectangle2D(posicionX-60, posicionY-50, 80, 60);}
        else {cuerpa = new Rectangle2D(1,1,1,1);}
        
        return cuerpa;
    }
    
    public void randomPos(double X[], double Y[]){
        Random r = new Random();
        int random; // de 2 a 197 para que quepa el tanque
        
        
        if(equipo==1){
            random=3+r.nextInt(72);
            posicionX = X[random]; posicionY = Y[random]-30;
            
        }
        else if (equipo==2){
            
            random=125+r.nextInt(65);
            posicionX = X[random]; posicionY = Y[random]-30;
        }
    }
    
    public int getEquipo(){return equipo;}
    
    public void impacto(int tipo){
        if(tipo==1){hp-=50;}
        else if(tipo==2){hp-=40;}
        else{hp-=30;}
    }
    
    public int getHP(){return hp;}
    
    public boolean intersects(Bala bala){//bala tiene que ser un Rectangle2D
        return bala.getBoundary().intersects(this.getBoundary());
        
    } 
    
    public void render(GraphicsContext gc){
        gc.clearRect(0, 0, 2000, 1000);
        gc.fillText(hp+"%", posicionX, posicionY-100);
        
    }
    
    public double getX(){return posicionX;}
    public double getY(){return posicionY;}
    
}
