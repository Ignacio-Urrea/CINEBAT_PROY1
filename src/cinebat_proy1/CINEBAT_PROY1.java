/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sfmnl
 */
public class CINEBAT_PROY1 extends Application {
    
    @Override
    public void start(Stage theStage) 
{
    theStage.setTitle( "Timeline Example" );
 
    Group root = new Group();
    Scene theScene = new Scene( root );
    theStage.setScene( theScene );
 
    Canvas canvas = new Canvas( 2000, 1000 );
    root.getChildren().add( canvas );
 
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
    Canvas capa2 = new Canvas(2000,1000);
    Canvas capa3 = new Canvas(2000,1000);
    root.getChildren().add(capa2);
    root.getChildren().add(capa3);
    
    GraphicsContext sBala = capa2.getGraphicsContext2D();
    GraphicsContext trayect = capa3.getGraphicsContext2D();
    
    capa3.toFront();
    capa2.toFront();
    
    Mapa mapa = new Mapa();
    mapa.genMapa(gc);
    
    Tanque tanque1 = new Tanque(500.0, 500.0, 1);
    Tanque tanque2 = new Tanque(1000.0, 500.0, 2);
    tanque1.randomPos(mapa.getX(), mapa.getY());
    tanque2.randomPos(mapa.getX(), mapa.getY());
    
    tanque1.render(root);
    tanque2.render(root);
    
    Turno turno = new Turno(1);
    
    
    TextField angulo1 = new TextField();
    TextField angulo2 = new TextField();
        
    double alpha1 = 0;double alpha2;
    double N1 = 0; double N2;
        
    TextField fuerza1 = new TextField();
    TextField fuerza2 = new TextField();
    
    Button disparar1 = new Button("Disparar");
    Button disparar2 = new Button("Disparar");
    //Texto en gris
    angulo1.setPromptText("Angulo (A°)");
    fuerza1.setPromptText("Fuerza");
    angulo2.setPromptText("Angulo (A°)");
    fuerza2.setPromptText("Fuerza");
    
    angulo1.relocate(100, 50);
    fuerza1.relocate(100, 100);
    disparar1.relocate(150, 150);
    
    angulo2.relocate(1650, 50);
    fuerza2.relocate(1650, 100);
    disparar2.relocate(1700, 150);
     
    Bala bala = new Bala(0, 0, 0, 0);
    
    disparar1.setOnAction(e -> disparo(angulo1, fuerza1, turno, tanque1, bala) );
    disparar2.setOnAction(e -> disparo(angulo2, fuerza2, turno, tanque2, bala) );
    
    root.getChildren().addAll(angulo1, fuerza1, disparar1, angulo2,fuerza2,disparar2);
    
    Text TXT = new Text();
    TXT.setX(750);
    TXT.setY(100);
    TXT.setText("TURNO DEL JUGADOR "+turno.getTurno());
    TXT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
    root.getChildren().add(TXT);
      
      
    
    Ganador ganador = new Ganador();
    final Bounds limites = root.getBoundsInLocal();
    final LongValue lastNanoTime = new LongValue(System.nanoTime());
    //final long startNanoTime = System.nanoTime();
 
    new AnimationTimer()
    {
        @Override
        public void handle(long currentNanoTime)
        {
            double elapsedTime = (currentNanoTime - lastNanoTime.value()) / 1000000000.0;
            //lastNanoTime.newValue(currentNanoTime); //usar esto al cambio de turno
            //double elapsedTime = (currentNanoTime - startNanoTime) / 1000000000.0;
            if (turno.getTurno()==1){
                
                bala.update(tanque1.getX()+70, tanque1.getY()-55, elapsedTime, tanque1, root );
                if (tanque2.intersects(bala)){bala.setMuerto(true);ganador.setGanador(1);}
                
            }
            
            if (turno.getTurno()==2 ){
                
                bala.update(tanque2.getX()-70, tanque2.getY()-55, elapsedTime, tanque2, root);
                if (tanque1.intersects(bala)){bala.setMuerto(true);ganador.setGanador(2);}
            }
            
            //System.out.println(elapsedTime);
            //double x = 232 + 128 * Math.cos(elapsedTime); //Crear funcion para actualizar x activable con click.
            //double y = 232 + 128 * Math.sin(elapsedTime); //Encargarse de que tiempo= 0 antes de cada disparo
            //bala.update(1000-30,500-25,elapsedTime, 75, 20, tanque2, root); //Xi, Yi, tiempo, Angulo, Fuerza, De donde se dispara, la root
            
            
            
            
                    
                    
           
            if (bala.getX()>=limites.getMaxX()){bala.setMuerto(true);turno.sigTurno();}
            if (bala.getY()>=limites.getMaxY()){bala.setMuerto(true);turno.sigTurno();}
            if (bala.getX()<=limites.getMinX()){bala.setMuerto(true);turno.sigTurno();}
            if (bala.getY()<=limites.getMinY()){bala.setMuerto(true);turno.sigTurno();}
            
            if (bala.getY()>= mapa.getY()[(int) bala.getX()/10]){bala.setMuerto(true);turno.sigTurno();}
            
            sBala.clearRect(0, 0, 2000, 1000);
           
            bala.render(sBala, trayect);
            
            if(bala.getMuerto()){trayect.clearRect(0, 0, 2000, 1000);lastNanoTime.newValue(currentNanoTime);
                root.getChildren().remove(TXT);
                TXT.setText("TURNO DEL JUGADOR "+turno.getTurno());
                root.getChildren().add(TXT);
                if (ganador.gameOver){ganador.printGanador(root);turno.finJuego();root.getChildren().remove(TXT);disparar1.setDisable(true);disparar2.setDisable(true);}
            }
       
        
        }
        
    
    }.start();
 
    theStage.show();
}
    private void disparo(TextField A, TextField N, Turno turno, Tanque tanque, Bala bala){
        double angulo = Double.parseDouble(A.getText());
        double fuerza = Double.parseDouble(N.getText());
        if (turno.getTurno() == tanque.getEquipo()){ bala.setMuerto(false);bala.actualizar(tanque.getX(), tanque.getY(), angulo, fuerza);}
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

