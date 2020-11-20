/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;

/**
 *
 * @author sfmnl
 */

//Variables necesarias para llevar la cuenta de turnos dentro de la animación
public class Turno {
    private int turno;//El turno del jugador o el fin de este en caso de acabar el juego
    private boolean cambio;//Determina si hubo un cambio de turno o no (para optimizar actualizacion de interfaz)
    public Turno(int n){turno = n;cambio=false;}
    public void sigTurno(){
        if (turno==1){turno=2;}
        else if(turno==2){turno = 1;}
        
        cambio = true;
    }
    public int getTurno(){return turno;}
    public boolean getCambio(){return cambio;}
    public void noCambio(){cambio = false;}
    public void finJuego(){turno = 0;}
}
