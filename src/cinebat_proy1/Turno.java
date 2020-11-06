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
public class Turno {
    private int turno;
    public Turno(int n){turno = n;}
    public void sigTurno(){
        if (turno==1){turno=2;}
        else {turno = 1;}
    }
    public int getTurno(){return turno;}
    public void finJuego(){turno = 0;}
}
