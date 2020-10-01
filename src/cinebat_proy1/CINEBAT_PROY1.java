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
        /*
        Group root = new Group();
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("");

        VBox vb = new VBox();

        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        canvas.setPrefSize(900,600);
        Circle circle = new Circle(10,Color.BLUE);
        circle.relocate(x, 500);
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,500);
        //canvas.getChildren().addAll(circle);
        while(x<900){
            x+=50;
            circle.relocate(x, 500);
            canvas.getChildren().addAll(circle);
            
        }
        vb.getChildren().add(canvas);

        scene.setRoot(vb);
        stage.show();
        */
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
        
    }

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
}
