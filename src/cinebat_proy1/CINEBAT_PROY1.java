/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.paint.Color;

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
    //se crean y se agregan las capas que vamos a usar (para el mapa, las balas, los tanques, y la ruta de la bala)
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
    //se ordenan las capas para que se vean las actualizaciones de todo
    capa5.toFront();
    capa4.toFront();
    capa3.toFront();
    capa2.toFront();
    
    
    Mapa mapa = new Mapa();
    //Se genera un mapa al azar
    int numMapa = 1+(int)(Math.random()*3);
    
    switch (numMapa){
        case 1:
            mapa.genMapa(gc);
            break;
        case 2:
            mapa.genMapa2(gc);
            break;
        case 3:
            mapa.genMapa3(gc);
            break;
    }
    
    
    
    //Se posicionan los tanques con valores iniciales realmente irrelevantes
    Tanque tanque1 = new Tanque(500.0, 500.0, 1);
    Tanque tanque2 = new Tanque(1000.0, 500.0, 2);
    tanque1.randomPos(mapa.getX(), mapa.getY());
    tanque2.randomPos(mapa.getX(), mapa.getY());
    //Se crea un objeto tipo bala en la posición del tanque al que le corresponde el turno
    Bala bala = new Bala(tanque1.getX(), tanque1.getY(), 0, 0);
    tanque1.render(root);
    tanque2.render(root);
    
    Turno turno = new Turno(1);
    
    
    TextField angulo1 = new TextField();
    TextField angulo2 = new TextField();
   //Se asignan valores a las variables que almacenan ángulo y fuerza
   //solo se asignan al cuadro del primer tanque porque sabemos que no se van a 
   //ingresar valores en el cuadro del segundo en la primera ronda
    double alpha1 = 0;double alpha2;
    double N1 = 0; double N2;
        
    TextField fuerza1 = new TextField();
    TextField fuerza2 = new TextField();
    
    Button disparar1 = new Button("(J1) Disparar");
    Button disparar2 = new Button("(J2) Disparar");
    
    //Menu de municiones
    
    MenuItem p105 = new MenuItem("*105mm");
    MenuItem pPerf = new MenuItem("Perforante");
    MenuItem p60 = new MenuItem("60mm");
    
    p105.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105.setText("*105mm");
            pPerf.setText("Perforante");
            p60.setText("60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(1);
                    break;
                case 2:
                    tanque2.setBala(1);
                    break;
            }
        }
    });
    
    
    
    pPerf.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105.setText("105mm");
            pPerf.setText("*Perforante");
            p60.setText("60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(2);
                    break;
                case 2:
                    tanque2.setBala(2);
                    break;
            }
            
        }
    });
    
    
    
        p60.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105.setText("105mm");
            pPerf.setText("Perforante");
            p60.setText("*60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(3);
                    break;
                case 2:
                    tanque2.setBala(3);
                    break;
            }
        }
    });
        
    MenuItem p105b = new MenuItem("*105mm");
    MenuItem pPerfb = new MenuItem("Perforante");
    MenuItem p60b = new MenuItem("60mm");
    
    p105b.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105b.setText("*105mm");
            pPerfb.setText("Perforante");
            p60b.setText("60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(1);
                    break;
                case 2:
                    tanque2.setBala(1);
                    break;
            }
        }
    });
    
    
    
    pPerfb.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105b.setText("105mm");
            pPerfb.setText("*Perforante");
            p60b.setText("60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(2);
                    break;
                case 2:
                    tanque2.setBala(2);
                    break;
            }
        }
    });
    
    
    
        p60b.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            p105b.setText("105mm");
            pPerfb.setText("Perforante");
            p60b.setText("*60mm");
            switch(turno.getTurno()){
                case 1:
                    tanque1.setBala(3);
                    break;
                case 2:
                    tanque2.setBala(3);
                    break;
            }
        }
    });
        
    MenuButton proyectil1 = new MenuButton("Tipo de Proyectil", null, p105, pPerf, p60);
    MenuButton proyectil2 = new MenuButton("Tipo de Proyectil", null, p105b, pPerfb, p60b);
    
    proyectil1.relocate(30, 150);
    proyectil2.relocate(1550, 150);
    //Termina menu de municiones
    
    
    
    
    //Textos de la interfaz
    
    Text cont105mm1 = new Text("105mm: "+3);
    Text contPerf1 = new Text("Perforante: "+10);
    Text cont60mm1 = new Text("60mm: "+3);
    
    Text cont105mm2 = new Text("105mm: "+3);
    Text contPerf2 = new Text("Perforante: "+10);
    Text cont60mm2 = new Text("60mm: "+3);
    
    cont105mm1.setX(360);
    contPerf1.setX(360);
    cont60mm1.setX(360);
    
    cont105mm1.setY(65);
    contPerf1.setY(95);
    cont60mm1.setY(125);
    
    cont105mm2.setX(1400);
    contPerf2.setX(1400);
    cont60mm2.setX(1400);
    
    cont105mm2.setY(65);
    contPerf2.setY(95);
    cont60mm2.setY(125);
    
    Text ANGULO1 = new Text();
    Text ANGULO2 = new Text();
    Text FUERZA1 = new Text();
    Text FUERZA2 = new Text();
    
    ANGULO1.setText("Angulo: ");
    ANGULO2.setText("Angulo: ");
    FUERZA1.setText("Fuerza: ");
    FUERZA2.setText("Fuerza: ");
    
    
    angulo1.setPromptText("0-90");
    fuerza1.setPromptText("0-300");
    angulo2.setPromptText("0-90");
    fuerza2.setPromptText("0-300");
    
    angulo1.relocate(130, 50);
    fuerza1.relocate(130, 100);
    disparar1.relocate(215, 150);
    ANGULO1.setX(30);
    FUERZA1.setX(30);
    ANGULO1.setY(70);
    FUERZA1.setY(120);
    
    
    angulo2.relocate(1650, 50);
    fuerza2.relocate(1650, 100);
    disparar2.relocate(1735, 150);
    ANGULO2.setX(1550);
    FUERZA2.setX(1550);
    ANGULO2.setY(70);
    FUERZA2.setY(120);
    
    
    ANGULO1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
    ANGULO1.setFill(Color.SNOW);
    ANGULO1.setStrokeWidth(1); 
    ANGULO1.setStroke(Color.BLACK);
    ANGULO2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
    ANGULO2.setFill(Color.SNOW);
    ANGULO2.setStrokeWidth(1); 
    ANGULO2.setStroke(Color.BLACK);
    
    FUERZA1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
    FUERZA1.setFill(Color.SNOW);
    FUERZA1.setStrokeWidth(1); 
    FUERZA1.setStroke(Color.BLACK);
    FUERZA2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 25));
    FUERZA2.setFill(Color.SNOW);
    FUERZA2.setStrokeWidth(1); 
    FUERZA2.setStroke(Color.BLACK);
    
    
    //Se asignan las acciones de los botones "Disparar" para cada jugador
    disparar1.setOnAction(e -> disparo(angulo1, fuerza1, turno, tanque1, bala) );
    disparar2.setOnAction(e -> disparo(angulo2, fuerza2, turno, tanque2, bala) );
    
    root.getChildren().addAll(angulo1, fuerza1, disparar1, angulo2,fuerza2,disparar2,ANGULO1,ANGULO2,FUERZA1,FUERZA2,proyectil2, proyectil1, cont105mm1, cont105mm2, contPerf1, contPerf2, cont60mm1, cont60mm2);
    
    Text TXT = new Text();
    TXT.setX(750);
    TXT.setY(100);
    TXT.setText("TURNO DEL JUGADOR "+turno.getTurno());
    TXT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
    root.getChildren().add(TXT);
      
    tanque1.render(vida);
    tanque2.render(vida2);
    
    TXT.setStrokeWidth(2); 
    TXT.setStroke(Color.SNOW);   
      
    Text MaxAltura = new Text("Altura Maxima: ");
    Text MaxDistancia = new Text("Distancia Recorrida: ");
    
    MaxAltura.setX(775);
    MaxDistancia.setX(950);
    MaxAltura.setY(50);
    MaxDistancia.setY(50);
    
    root.getChildren().addAll(MaxAltura, MaxDistancia);
    //Cierran textos de la interfaz
    
    //Se crean objetos para interactuar con la animación (que no permite variables que no sean de tipo final u objetos tipo clase)
    Ganador ganador = new Ganador();
    final Bounds limites = root.getBoundsInLocal();
    final LongValue lastNanoTime = new LongValue(System.nanoTime());
    
    //Se imprimen los límites del mapa en consola a modo de control
    System.out.println(limites.getMaxY());
    System.out.println(limites.getMaxX());
    System.out.println(limites.getMinY());
    System.out.println(limites.getMinX());
    //Inicia la animación
    new AnimationTimer()
    {
        @Override
        public void handle(long currentNanoTime)
        {   //se actualiza el tiempo desde el último nanosegundo que se almacenó
            double elapsedTime = (currentNanoTime - lastNanoTime.value()) / 1000000000.0;
            //Validaciones para que la bala no salga de los límites del mapa
            //ni colisione con el mapa en sí
            if (bala.getX()>limites.getMaxX()&&!bala.getMuerto()){bala.setMuerto(true);turno.sigTurno();
                bala.setX(1990);System.out.println("TURNO"+turno);
            }
            //else if (bala.getY()>limites.getMaxY()){bala.setMuerto(true);turno.sigTurno();System.out.println("TURNO"+turno);}
            else if (bala.getX()<limites.getMinX()&&!bala.getMuerto()){bala.setMuerto(true);turno.sigTurno();
                bala.setX(10);
            }
            else if (bala.getY()<limites.getMinY()&&!bala.getMuerto()){bala.setMuerto(true);turno.sigTurno();}
            else if(bala.getX()>0 && bala.getY()<2000&&!bala.getMuerto()){
                if (bala.getY()>= mapa.getY()[(int) bala.getX()/10]){bala.setMuerto(true);turno.sigTurno();}
            }
            
            //Se toman las acciones de la bala dependiendo del turno del jugador
            //Y se actualizan los parámetros del tanque (Munición y HP)
            if (turno.getTurno()==1){
                if(tanque2.getT1()<=0){p105b.setDisable(true);}
                if(tanque2.getT2()<=0){pPerfb.setDisable(true);}
                if(tanque2.getT3()<=0){p60b.setDisable(true);}
                tanque1.rotarBala(p105, pPerf, p60);
                bala.update(tanque1.getX()+70, tanque1.getY()-55, elapsedTime, tanque1, root );
                if (tanque2.intersects(bala)){
                    bala.setMuerto(true);
                    turno.sigTurno();
                    bala.setX(tanque2.getX());
                    bala.setY(tanque2.getY());
                    tanque2.impacto(bala.getTipo());
                    tanque2.render(vida2);
                    if(tanque2.getHP()<=0){ganador.setGanador(1);}
                    
                }
                
            }
            
            else if (turno.getTurno()==2 ){
                if(tanque1.getT1()<=0){p105.setDisable(true);}
                if(tanque1.getT2()<=0){pPerf.setDisable(true);}
                if(tanque1.getT3()<=0){p60.setDisable(true);}
                tanque2.rotarBala(p105b, pPerfb, p60b);
                bala.update(tanque2.getX()-70, tanque2.getY()-55, elapsedTime, tanque2, root);
                if (tanque1.intersects(bala)){
                    bala.setMuerto(true);
                    turno.sigTurno();
                    bala.setX(tanque1.getX());
                    bala.setY(tanque1.getY());
                    tanque1.impacto(bala.getTipo());
                    tanque1.render(vida);
                    if(tanque1.getHP()<=0){ganador.setGanador(2);}
                }
            }
            
            
            
            
            
            
                    
                    
          
            //En caso de quedarse los tanques sin munición, se empata el juego.
            if (tanque1.vacio() && tanque2.vacio()){ganador.setGanador(3);}
            
            //Se limpia la posición anterior de la bala y se actualiza
            sBala.clearRect(0, 0, 2000, 1000);
           
            bala.render(sBala, trayect);
            bala.actYmax(bala.getY());
            
            //Cuando la bala colisiona con alguna de las validaciones (tanques, limites o terreno)
            //Se actualiza la información de la interfaz y se comprueba el estado del juego
            if(bala.getMuerto()){trayect.clearRect(0, 0, 2000, 1000);lastNanoTime.newValue(currentNanoTime);
                
                root.getChildren().remove(TXT);
                TXT.setText("TURNO DEL JUGADOR "+turno.getTurno());
                root.getChildren().add(TXT);
                root.getChildren().removeAll(cont105mm1, cont105mm2, cont60mm1, cont60mm2, contPerf1, contPerf2);
                cont105mm1.setText("105mm: "+tanque1.getT1());
                contPerf1.setText("Perforante: "+tanque1.getT2());
                cont60mm1.setText("60mm: "+tanque1.getT3());
                cont105mm2.setText("105mm: "+tanque2.getT1());
                contPerf2.setText("Perforante: "+tanque2.getT2());
                cont60mm2.setText("60mm: "+tanque2.getT3());
                root.getChildren().addAll(cont105mm1, cont105mm2, cont60mm1, cont60mm2, contPerf1, contPerf2);
                if(turno.getCambio()){
                    MaxAltura.setText("Altura Maxima: "+(int)bala.getYmax());
                    if(turno.getTurno()== 1){MaxDistancia.setText("Distancia Recorrida: "+ (int)(tanque2.getX()-70-bala.getRX()));}
                    else{MaxDistancia.setText("Distancia Recorrida: "+ (int)(bala.getRX()-70-tanque1.getX()));}
                }
                turno.noCambio();
                
                if (ganador.gameOver){ganador.printGanador(root);turno.finJuego();root.getChildren().remove(TXT);disparar1.setDisable(true);disparar2.setDisable(true);}
            }
       
        
        }
        
    
    }.start();
    //Finaliza animación
    theStage.show();
}
    
    //Función aplicada en el botón "Disparar" de ambos jugadores
    private void disparo(TextField A, TextField N, Turno turno, Tanque tanque, Bala bala){
        
        //Se convierte el texto a Double.
        double angulo = Double.parseDouble(A.getText());
        double fuerza = Double.parseDouble(N.getText());
        bala.setTipo(tanque.getBala());
        //Se asegura de que sea el turno del jugador correcto
        if (turno.getTurno() == tanque.getEquipo()){
            bala.setX(tanque.getX());
            bala.setY(tanque.getY());
            //Coloca la bala en movimiento
            bala.setMuerto(false);bala.actualizar(tanque.getX(), tanque.getY(), angulo, fuerza);
            
            //Actualiza la munición del tanque
            tanque.descontarBala();
            bala.resetearYmax();
        }
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

