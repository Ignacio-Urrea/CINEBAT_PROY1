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
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

/**
 *
 * @author sfmnl
 */
public class Tanque {
    private Polygon base;//Imagen de la base del tanque
    private Polygon torre;//Imagen de la torre del tanque
    private Polygon canon;//Imagen del cañón del tanque
    private double posicionX;//Posiión en eje X del tanque "real"
    private double posicionY;//Posición en eje Y del tanque "real"
    private int equipo;//El equipo en el que se encuentra el tanque
    private int hp;//La vida actual del tanque
    private int tipoBala;//El tipo de bala que tiene cargada el tanque
    private int tipo1;//La munición de cada tipo de bala
    private int tipo2;
    private int tipo3;
    
    public Tanque(double x, double y, int jugador){
        posicionX = x;
        posicionY = y;
        equipo = jugador;
        hp = 100;
        tipoBala = 1;
        tipo1 = 3;
        tipo2 = 10;
        tipo3 = 3;
        
    }
    //Se dibuja el tanque
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
    //Se posiciona el modelo real del tanque (rectángulo)
    public Rectangle2D getBoundary(){
        Rectangle2D cuerpa;
        if (equipo == 1){
            cuerpa = new Rectangle2D(posicionX-60, posicionY-50, 80, 60);}
        else if (equipo==2){
            cuerpa = new Rectangle2D(posicionX-60, posicionY-50, 80, 60);}
        else {cuerpa = new Rectangle2D(1,1,1,1);}
        
        return cuerpa;
    }
    //Se posiciona en lugar aleatorio el tanque
    public void randomPos(double X[], double Y[]){
        Random r = new Random();
        int random; // de 2 a 197 para que quepa el tanque
        
        
        if(equipo==1){
            random=5+r.nextInt(45);
            while(Y[random+7]<Y[random]){random=5+r.nextInt(45);}
            posicionX = X[random]; posicionY = Y[random]-20;
            
        }
        else if (equipo==2){
            
            random=145+r.nextInt(50);
            while(Y[random-7]<Y[random]){random=125+r.nextInt(65);}
            posicionX = X[random]; posicionY = Y[random]-20;
            
        }
    }
    
    public int getEquipo(){return equipo;}
    //Recibe imacto de bala
    public void impacto(int tipo){
        if(tipo==1){hp-=50;}
        else if(tipo==2){hp-=40;}
        else{hp-=30;}
    }
    
    public int getHP(){return hp;}
    //Comprueba si la bala impacta
    public boolean intersects(Bala bala){//bala tiene que ser un Rectangle2D
        return bala.getBoundary().intersects(this.getBoundary());
        
    } 
    //Actualiza la barra de vida
    public void render(GraphicsContext gc){
        gc.clearRect(0, 0, 2000, 1000);
        gc.fillText(hp+"%", posicionX, posicionY-100);
    }
    
    public double getX(){return posicionX;}
    public double getY(){return posicionY;}
    
    public void setBala(int tipo){
        tipoBala = tipo;
    }
    public void descontarBala(){
        switch(tipoBala){
            case 1:
                tipo1--;
                
                break;
            case 2:
                tipo2--;
                break;
            case 3:
                tipo3--;
                break;
        }
    }
    
    public int getBala(){return tipoBala;}
    public int getT1(){return tipo1;}
    public int getT2(){return tipo2;}
    public int getT3(){return tipo3;}
    //Comprueba si el tanque tiene munición y cambia el tipo de munición en caso de estar agotado
    public boolean vacio(){
        return tipo1 <= 0 && tipo2<=0 && tipo3 <=0;
    }
    public void rotarBala(MenuItem p105boton, MenuItem perfBoton, MenuItem p60boton){
        if (tipo1 <=0){
            if (tipo2>0){tipoBala=2;p105boton.setText("105mm");perfBoton.setText("*Perforante");p60boton.setText("60mm");}
            else if(tipo3>0){tipoBala=3;p105boton.setText("105mm");perfBoton.setText("Perforante");p60boton.setText("*60mm");}
        }
        if (tipo2 <=0){
            if (tipo1>0){tipoBala=1;p105boton.setText("*105mm");perfBoton.setText("Perforante");p60boton.setText("60mm");}
            else if(tipo3>0){tipoBala=3;p105boton.setText("105mm");perfBoton.setText("Perforante");p60boton.setText("*60mm");}
        }
        if (tipo3 <=0){
            if (tipo1>0){tipoBala=1;p105boton.setText("*105mm");perfBoton.setText("Perforante");p60boton.setText("60mm");}
            else if(tipo2>0){tipoBala=2;p105boton.setText("105mm");perfBoton.setText("*Perforante");p60boton.setText("60mm");}
        }
    }
    
}
