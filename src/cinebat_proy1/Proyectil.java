/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respaldoproyecto;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author sfmnl
 */
public class Proyectil {
    
    int turno; boolean finTurno; private double tiempo; private boolean listo;
    
    public Proyectil (int turno2){this.turno = turno2;tiempo = 0;this.listo = true;}
    
    public void turno(int turno2) {this.turno = turno2;}
    
    
    
    public void disparo(Tanque tanque, Pane root,double alpha, double beta){
        this.tiempo = 0;
        Circle circle = new Circle(10, Color.ORANGE);
        if(turno==1){circle.relocate(tanque.getT1X()+70, tanque.getT1Y()-55);}
        else if(turno==2){circle.relocate(tanque.getT2X()-70, tanque.getT2Y()-55);}
        root.getChildren().add(circle);
        System.out.println(tanque.getT1X());
        
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new
        EventHandler<ActionEvent>() {
            double dx = 0;
            double dy = 0;
            
            
            @Override
            public void handle(final ActionEvent t) {
                
                if(turno==1){circle.setLayoutX(posX(alpha, beta, tanque.getT1X()+70));
                            circle.setLayoutY(posY(alpha, beta,  tanque.getT1Y()-55));}
                else if(turno==2){circle.setLayoutX(posX(alpha, beta,  tanque.getT2X()-70));
                                  circle.setLayoutY(posY(alpha, beta,  tanque.getT2Y()-55));}
                
                
                final Bounds bounds = root.getBoundsInLocal();
                
                if (turno==2){
                    if ( ( circle.getLayoutX() >= tanque.getT1X() - 40 && circle.getLayoutX() <= tanque.getT1X() + 40 ) 
                            && (circle.getLayoutY() >= tanque.getT1Y() - 30 && circle.getLayoutY() <= tanque.getT1Y() + 30 ) ){

                            tanque.ganador(2);
                    }
                }
                if (turno==1){    
                    if ( ( circle.getLayoutX() >= tanque.getT2X() - 40 && circle.getLayoutX() <= tanque.getT2X() + 40 ) 
                            && (circle.getLayoutY() >= tanque.getT2Y() - 30 && circle.getLayoutY() <= tanque.getT2Y() + 30 ) ){

                            tanque.ganador(1);
                    }
                }
                /*
                
                if ( circle.getLayoutY() >= bounds.getMaxY() - circle.getRadius() )
                dy = -dy;
                if ( circle.getLayoutY() <= bounds.getMinY() + circle.getRadius() ) dy = -dy;
                if ( circle.getLayoutX() >= bounds.getMaxX() - circle.getRadius() ) dx = -dx;
                if ( circle.getLayoutX() <= bounds.getMinX() - circle.getRadius() ) dx = -dx; */
               
            }
        })); 

        loop.setCycleCount(Timeline.INDEFINITE);
        
        loop.play(); 
        
        
        this.listo = false;
       
        if(tanque.getEstado()){ loop.stop(); 
            
            Text TXT = new Text();
            String texto = "GANA EL JUGADOR "+tanque.getGanador();
            
            TXT.setText(texto);
            TXT.setX(700);
            TXT.setY(500);
            TXT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
            root.getChildren().add(TXT);
            
        }
        
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(turno==1){turno=2;}
                else if (turno==2){turno=1;}
                listo(true);
                root.getChildren().remove(circle);
                loop.stop();
            }
        });
        delay.play();
        
    
    }
    
    
    private double posX(double alpha, double beta, double primerX){this.tiempo += 0.3;double posX = primerX+(beta)*Math.cos(alpha*Math.PI/180.0)*tiempo;return posX;}
    private double posY(double alpha,double beta,  double primerY){double posY = primerY-(beta)*Math.sin(alpha*Math.PI/180.0)*tiempo+(0.5*Math.pow(tiempo,2));System.out.println(tiempo);return posY;}
    
    private void listo(boolean flag){this.listo = flag;}
    
    public boolean getListo(){return listo;}
    public int getTurno(){return turno;}
}
