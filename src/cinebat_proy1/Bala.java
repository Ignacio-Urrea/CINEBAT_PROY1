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
    private boolean muerto; //estado de la bala
    private double posicionX;//posición en eje X de la bala
    private double posicionY;//posición en eje Y de la bala
    private double alpha;//ángulo de la trayectoria
    private double fuerza;//fuerza de la trayectoria
    private Circle bala;//modelo visual de la bala
    private double Ymax;//Altura máxima
    private boolean moviendo;//Determina si la bala se está moviendo o no
    private int tipo;//El tipo de munición, luego será eliminado y se consultará la munición que usa el tanque enemigo directamente
    private double respaldoX;//Respaldo de la posición X e Y, para evitar ciertos errores específicos
    private double respaldoY;
    
    
    public Bala(double x, double y, double A, double N){
        posicionX = x;
        posicionY = y;
        respaldoX = x;
        respaldoY = y;
        alpha = A;
        fuerza = N;
        muerto = true;
        moviendo = false;
        bala = new Circle(20, Color.ORANGE);
        tipo = 1;
        
    }
    //Actualización de la posición de la bala
    public void update(double Xi, double Yi, double t, Tanque jugador, Group root ){
        if (jugador.getEquipo()==1 && moviendo){
            respaldoX= posicionX;
            posicionX = Xi+(fuerza)*Math.cos(alpha*Math.PI/180.0)*(t*4);
            respaldoY = posicionY;
            posicionY = Yi-(fuerza)*Math.sin(alpha*Math.PI/180.0)*(t*4)+(10*Math.pow(t*4,2));
            
        }
        if (jugador.getEquipo()==2 && moviendo){
            respaldoX= posicionX;
            posicionX = Xi-(fuerza)*Math.cos(alpha*Math.PI/180.0)*(t*4);
            respaldoY = posicionY;
            posicionY = Yi-(fuerza)*Math.sin(alpha*Math.PI/180.0)*(t*4)+(10*Math.pow(t*4,2));
            
        }
        //Si la bala está muerta se remueve del cuadro
        if (muerto){
            moviendo = false;
            root.getChildren().remove(bala);
        }
        
        
    }
    //Actualización de la representación visual de la bala
    public void render(GraphicsContext gc, GraphicsContext gc2){
        if (!muerto){
            switch (tipo){
                case 1:
                    gc.setFill(Color.ORANGE);
                    break;
                case 2:
                    gc.setFill(Color.RED);
                    break;
                case 3:
                    gc.setFill(Color.VIOLET);
                    break;
                
            }
            
            gc.fillOval(posicionX, posicionY-2, 15, 15);
            
            gc2.setFill(Color.SNOW);
            gc2.fillOval(posicionX, posicionY, 10, 10);
        }
    }
    
    
    //public void setYmax(double alpha, double fuerza, double Yi){Ymax = (Yi+(Math.pow(fuerza, 2)* Math.pow(Math.sin(alpha*Math.PI/180.0), 2.0) /20.0 ))/15.0;} //No  funciona porque usamos t*4, ya que es demasiado lento usar solo t
    //Interacción con función main
    public double getYmax(){return Ymax;}
    public double getX(){return posicionX;}
    public double getY(){return posicionY;}
    
    //Esta función actualiza los datos de la bala (angulo, fuerza, posicion inicial, estado de movimiento
    public void actualizar(double nuevoX, double nuevoY, double A, double N){posicionX= nuevoX; posicionY = nuevoY; if(A<90&&A>=0){alpha = A;}else{alpha = 90;}; fuerza = N;moviendo = true;}
    //Más funciones que interactúan con el resto del código
    public void setTipo(int t){tipo = t;}
    public int getTipo(){return tipo;}
    //Actualiza (o asigna) el valor del objeto que puede colisionar con el mapa (es un rectángulo)
    //Se puede decir que es la bala "real", mientras que el render es la imagen.
    public Rectangle2D getBoundary(){return new Rectangle2D(posicionX,posicionY, 10, 10);}
    
    public void setMuerto(boolean estado){muerto = estado;}
    public boolean getMuerto(){return muerto;}
    public void resetearYmax(){Ymax = 1;}
    //Almacena el máximo valor de Y
    public void actYmax(double y){
        y=1000-y;
        if (y>Ymax){Ymax = y;}
    }
    public double getRX(){return respaldoX;}
    public double getRY(){return respaldoY;}
    public void setX(double x){posicionX = x;}
    public void setY(double y){posicionY = y;}
}

