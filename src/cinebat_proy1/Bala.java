/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author sfmnl
 */
public class Bala {
    private boolean muerto;
    private double posicionX;
    private double posicionY;
    private double alpha;
    private double fuerza;
    private Circle bala;
    private double Ymax;
    private boolean moviendo;
    private int tipo;
    
    
    public Bala(double x, double y, double A, double N){
        posicionX = x;
        posicionY = y;
        alpha = A;
        fuerza = N;
        muerto = false;
        moviendo = false;
        bala = new Circle(20, Color.ORANGE);
        tipo = 1;
        
    }
    
    public void update(double Xi, double Yi, double t, Tanque jugador, Group root ){
        if (jugador.getEquipo()==1 && moviendo){
            posicionX = Xi+(fuerza)*Math.cos(alpha*Math.PI/180.0)*(t*4);
            
            posicionY = Yi-(fuerza)*Math.sin(alpha*Math.PI/180.0)*(t*4)+(10*Math.pow(t*4,2));
            
        }
        if (jugador.getEquipo()==2 && moviendo){
            posicionX = Xi-(fuerza)*Math.cos(alpha*Math.PI/180.0)*(t*4);
            posicionY = Yi-(fuerza)*Math.sin(alpha*Math.PI/180.0)*(t*4)+(10*Math.pow(t*4,2));
        }
        
        if (muerto){
            moviendo = false;
            root.getChildren().remove(bala);
        }
        
    }
    
    public void render(GraphicsContext gc, GraphicsContext gc2){
        if (!muerto){
            gc.setFill(Color.ORANGE);
            gc.fillOval(posicionX, posicionY-2, 15, 15);
            /*if(posicionY<= Ymax+8 && posicionY>=Ymax-8){
                gc2.setFill(Color.RED);
            }
            
            else{gc2.setFill(Color.BLACK);}*/
            gc2.setFill(Color.SNOW);
            gc2.fillOval(posicionX, posicionY, 10, 10);
        }
    }
    //public double getY(){return posicionY;}
    
    //public void setYmax(double alpha, double fuerza, double Yi){Ymax = (Yi+(Math.pow(fuerza, 2)* Math.pow(Math.sin(alpha*Math.PI/180.0), 2.0) /20.0 ))/15.0;}
    
    //public double getYmax(){return Ymax;}
    public double getX(){return posicionX;}
    public double getY(){return posicionY;}
    public void actualizar(double nuevoX, double nuevoY, double A, double N){posicionX= nuevoX; posicionY = nuevoY; if(A<90&&A>=0){alpha = A;}else{alpha = 90;}; fuerza = N;moviendo = true;}
    
    public void setTipo(int t){tipo = t;}
    public int getTipo(){return tipo;}
    
    public Rectangle2D getBoundary(){return new Rectangle2D(posicionX,posicionY, 10, 10);}
    public void setMuerto(boolean estado){muerto = estado;}
    public boolean getMuerto(){return muerto;}
    
}
