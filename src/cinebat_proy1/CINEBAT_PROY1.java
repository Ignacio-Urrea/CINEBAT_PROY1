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
import javafx.scene.canvas.Canvas;
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
 * @author Witu-Stella1
 */
public class CINEBAT_PROY1 extends Application {
     private int x=20;
    @Override
    public void start(Stage stage) {       
        Pane canvas = new Pane();
    	Scene scene = new Scene(canvas, 300, 300);
    	Circle ball = new Circle(10, Color.RED);
        ball.relocate(0, 10);

        canvas.getChildren().add(ball);

        stage.setTitle("Moving Ball");
        stage.setScene(scene);
        stage.show();

        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new KeyValue(ball.layoutXProperty(), bounds.getMaxX()-ball.getRadius())));
        timeline.setCycleCount(2);
        timeline.play();
        
        
        /*stage.setTitle("Prueba generador de mapas");
        Group root = new Group();
        Canvas canvas2 = new Canvas(2000, 1000);
        GraphicsContext gc = canvas2.getGraphicsContext2D();
        
        //drawShapes(gc);//luego se reemplaza con Mapa()
        Mapa mapa = new Mapa();
        mapa.Mapa(gc);
                
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show(); // <----- Esto es lo que hace el show del mapa no c como mezclarlo con tu codigo, suerte bro
        */
    }
    
    /*public void start(Stage stage) {
        stage.setTitle("Prueba generador de mapas");
        Group root = new Group();
        Canvas canvas = new Canvas(2000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //drawShapes(gc);//luego se reemplaza con Mapa()
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    } */
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void Mover(Circle circle, Pane canvas){
        while(x<900){
            x+=50;
            circle.relocate(x, 500);
            canvas.getChildren().addAll(circle);
            
        }
    }
    /*private void drawShapes(GraphicsContext gc) {
        gc.setStroke(Color.LIMEGREEN);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        double x = 0;
        double xfalso = Math.random() * 10000;
        double y = onda(xfalso)*666 +850;
        double x2;
        double y2;
        
        double mapaX[];
        double mapaY[];
        double mapaX2[];
        double mapaY2[];
        
        mapaX = new double[203];
        mapaY = new double[203];
        mapaX2 = new double[203];
        mapaY2 = new double[203];
        
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
     
    }*/
}
