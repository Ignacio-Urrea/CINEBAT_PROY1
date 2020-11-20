/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



/**
 *
 * @author sfmnl
 */

//Determina el estado del juego y el ganador e interactua con la animación
public class Ganador {
    int ganador;
    boolean gameOver;
    public Ganador(){
        ganador = 0;
        gameOver = false;
    }
    //Muestra en pantalla el jugador ganador
    public void printGanador(Group root){
        Text TXT = new Text();
        TXT.setX(750);
        TXT.setY(500);
        TXT.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        if(ganador==3){
            TXT.setText("ES UN EMPATE");
        }
        else{
            TXT.setText("GANA EL JUGADOR "+ganador);
        }
        root.getChildren().add(TXT);
    
    }
    
    public void setGanador(int n){ganador = n;gameOver = true;}
    public boolean gameOver(){return gameOver;}
    
}
