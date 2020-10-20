/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ESTE ES EL CODIGO DEL MAIN, ME DIO PAJA CAMBIARLO COPIARLO EN EL MAIN SI

package respaldoproyecto; 

//DE AQUÍ PABAJO SE COPIA


import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

/**
 *
 * @author sfmnl
 */
public class RespaldoProyecto extends Application {
    
    
  
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Prueba generador de mapas");
        Pane root = new Pane();
        
        Canvas canvas = new Canvas(2000, 1000);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
     
        
        Mapa mapa = new Mapa();
        Tanque tanque = new Tanque();
        
        
        Canvas canvasT1 = new Canvas(2000,1000);
        Canvas canvasT2 = new Canvas(2000,1000);
        
        GraphicsContext T1 = canvasT1.getGraphicsContext2D();
        GraphicsContext T2 = canvasT2.getGraphicsContext2D();
        
        
      
        
        mapa.genMapa(gc);
        
        
        tanque.setTanque(1, mapa.getX(), mapa.getY());
        tanque.setTanque(2, mapa.getX(), mapa.getY());
        
        tanque.desplegar(T1, T2);
        
        
        
        root.getChildren().add(canvas);
        
        
        root.getChildren().add(canvasT1);
        root.getChildren().add(canvasT2);
        
       
        canvasT1.toFront();
        canvasT2.toFront();
        
        
          //Form
        TextField angulo = new TextField();
        
        int alpha = 0; int beta = 0; int turno = 1;
        
        TextField fuerza = new TextField();
        
        Button disparar = new Button("Disparar");
        
      
        Proyectil proyectil = new Proyectil(1);
        disparar.setOnAction(e -> a(alpha, beta, angulo, fuerza, proyectil, tanque, root));
        
        
        
        //Layout
        angulo.setPromptText("Angulo (A°)");
        fuerza.setPromptText("Fuerza");
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 100, 50) );
        layout.setSpacing(5);
        layout.setMargin(angulo, new Insets(20, 1500, 5, 0));  
        layout.setMargin(fuerza, new Insets(20, 1500, 20, 0)); 
        layout.setMargin(disparar, new Insets(20, 20, 10, 50)); 
        
        
        //root.setMaxSize(100, 400);
        layout.getChildren().addAll(angulo,fuerza, disparar);
        GridPane grid = new GridPane();
        
        grid.add(layout,0,0);
        grid.setStyle("-fx-background-color: CORNFLOWERBLUE;");
        
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
   
       
       
    }
        
    private void a(double alpha, double beta, TextField inputA, TextField inputB, Proyectil proyectil, Tanque tanque, Pane root){
        alpha = Double.parseDouble(inputA.getText());
        beta = Double.parseDouble(inputB.getText());
        
        
        if(proyectil.getListo()){proyectil.disparo(tanque, root, alpha, beta);}
    }   
        
        
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
