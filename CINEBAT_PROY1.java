package cinebat_proy1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    
    void cleanup(){
        
    }
    
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
    Canvas capa4 = new Canvas(2000,1000);
    Canvas capa5 = new Canvas(2000,1000);
    
    root.getChildren().add(capa2);
    root.getChildren().add(capa3);
    root.getChildren().add(capa4);
    root.getChildren().add(capa5);
    
    GraphicsContext sBala = capa2.getGraphicsContext2D();
    GraphicsContext trayect = capa3.getGraphicsContext2D();
    GraphicsContext vida = capa4.getGraphicsContext2D();
    GraphicsContext vida2 = capa5.getGraphicsContext2D();
    
    capa5.toFront();
    capa4.toFront();
    capa3.toFront();
    capa2.toFront();
    
    
    Mapa mapa = new Mapa();
    mapa.genMapa(gc);
    
    Bala bala = new Bala(0, 0, 0, 0);
    
    Tanque tanque1 = new Tanque(500.0, 500.0, 1);
    Tanque tanque2 = new Tanque(1000.0, 500.0, 2);
    tanque1.randomPos(mapa.getX(), mapa.getY());
    tanque2.randomPos(mapa.getX(), mapa.getY());
    
    tanque1.render(root);
    tanque2.render(root);
    
    Turno turno = new Turno(1);
    
    
    TextField angulo1 = new TextField();
    Alerta.integerTextField(angulo1);
    TextField angulo2 = new TextField();
    Alerta.integerTextField(angulo2);
        
    double alpha1 = 0;double alpha2;
    double N1 = 0; double N2;
        
    TextField fuerza1 = new TextField();
    Alerta.integerTextField(fuerza1);
    TextField fuerza2 = new TextField();
    Alerta.integerTextField(fuerza2);
    
    Button disparar1 = new Button("(J1) Disparar");
    Button disparar2 = new Button("(J2) Disparar");
    Button reset = new Button("Nueva partida");
    Button finalizar = new Button("Finalizar juego");
    
    MenuItem p105 = new MenuItem("105mm");
    
    p105.setOnAction((ActionEvent event) -> {
        bala.setTipo(1);
    });
    
    MenuItem pPerf = new MenuItem("Perforante");
    
    pPerf.setOnAction((ActionEvent event) -> {
        bala.setTipo(2);
    });
    
    MenuItem p60 = new MenuItem("60mm");
    
        p60.setOnAction((ActionEvent event) -> {
            bala.setTipo(3);
    });
        
    MenuItem p105b = new MenuItem("105mm");
    
    p105b.setOnAction((ActionEvent event) -> {
        bala.setTipo(1);
    });
    
    MenuItem pPerfb = new MenuItem("Perforante");
    
    pPerfb.setOnAction((ActionEvent event) -> {
        bala.setTipo(2);
    });
    
    MenuItem p60b = new MenuItem("60mm");
    
        p60b.setOnAction((ActionEvent event) -> {
            bala.setTipo(3);
    });
        
    MenuButton proyectil1 = new MenuButton("Tipo de Proyectil", null, p105, pPerf, p60);
    MenuButton proyectil2 = new MenuButton("Tipo de Proyectil", null, p105b, pPerfb, p60b);
    
    proyectil1.relocate(40, 150);
    proyectil2.relocate(1590, 150);
    
    
    //Texto en gris
    
    Text ANGULO1 = new Text();
    Text ANGULO2 = new Text();
    Text FUERZA1 = new Text();
    Text FUERZA2 = new Text();
    
    ANGULO1.setText("Angulo (0-90): ");
    ANGULO2.setText("Angulo (0-90): ");
    FUERZA1.setText("Fuerza (0-300): ");
    FUERZA2.setText("Fuerza (0-300): ");
    
    angulo1.relocate(140, 50);
    fuerza1.relocate(140, 100);
    disparar1.relocate(200, 150);
    ANGULO1.setX(40);
    FUERZA1.setX(40);
    ANGULO1.setY(70);
    FUERZA1.setY(120);
    
    
    angulo2.relocate(1690, 50);
    fuerza2.relocate(1690, 100);
    disparar2.relocate(1750, 150);
    ANGULO2.setX(1590);
    FUERZA2.setX(1590);
    ANGULO2.setY(70);
    FUERZA2.setY(120);
    
    reset.relocate(900, 920);
    finalizar.relocate(899, 960);
    
    disparar1.setOnAction(e -> disparo(angulo1, fuerza1, turno, tanque1, bala) );
    disparar2.setOnAction(e -> disparo(angulo2, fuerza2, turno, tanque2, bala) );
    
    reset.setOnAction(e -> restart(theStage));
    
    root.getChildren().addAll(reset, finalizar, angulo1, fuerza1, disparar1, angulo2,fuerza2,disparar2,ANGULO1,ANGULO2,FUERZA1,FUERZA2,proyectil2, proyectil1);
    
    Text TXT = new Text();
    TXT.setX(750);
    TXT.setY(100);
    TXT.setText("TURNO DEL JUGADOR "+turno.getTurno());
    TXT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
    root.getChildren().add(TXT);
      
    tanque1.render(vida);
    tanque2.render(vida2);
    
    
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
                if (tanque2.intersects(bala)){
                    bala.setMuerto(true);
                    tanque2.impacto(bala.getTipo());
                    tanque2.render(vida2);
                    if(tanque2.getHP()<=0){ganador.setGanador(1);}
                    else{turno.sigTurno();}
                }
                
            }
            
            if (turno.getTurno()==2 ){
                
                bala.update(tanque2.getX()-70, tanque2.getY()-55, elapsedTime, tanque2, root);
                if (tanque1.intersects(bala)){
                    bala.setMuerto(true);
                    tanque1.impacto(bala.getTipo());
                    tanque1.render(vida);
                    if(tanque1.getHP()<=0){ganador.setGanador(2);}
                    else{turno.sigTurno();}
                }
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
    
    void restart(Stage theStage){
        cleanup();
        start(theStage);
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
