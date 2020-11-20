/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinebat_proy1;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author sfmnl
 */
public class Mapa {
    private double X[];//Almacena coordenadas del terreno en chunks de a 10 pixeles
    private double Y[];
    
    public Mapa(){ X = new double[200];Y = new double[200];}
    
    public double[] getX() {return X;}
    public double[] getY() {return Y;}
    //Genera el tipo de mapa 1 (Cerros)
    public void genMapa(GraphicsContext gc) {
        gc.setStroke(Color.LIMEGREEN);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        double x = 0;
        double xfalso = Math.random() * 10000; //Para que el tren de ondas no sea siempre igual
        double y = onda(xfalso)*350 +800;//+800 para que no suba de la mitad de la pantalla
        double x2;
        double y2;
        
        double mapaX2[];//Necesarios para colorear el mapa
        double mapaY2[];
        double mapaX[];
        double mapaY[];
        
        
        mapaX = new double[203];
        mapaY = new double[203];
        mapaX2 = new double[203];
        mapaY2 = new double[203];
        
        int puntos = 0;
        //Va generando el mapa en chunks de 10 px hasta llegar al límite derecho (2000 por ahora)
        while (x<2000){
            x2 = x+10;
            xfalso = xfalso+10;
            y2 = (onda(xfalso)*350)+800 + (Math.random() * (8 + 4 + 1) -4);
            gc.strokeLine(x, y, x2, y2);
            
            X[puntos] = x;
            Y[puntos] = y;
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
        //return gc;
    }
    //Genera el mapa de tipo 2 (Montañas)
    public void genMapa2(GraphicsContext gc){
        gc.setStroke(Color.SNOW);
        gc.setFill(Color.CHOCOLATE);
        gc.setLineWidth(50);
        
        int terreno = vom();
        
        double x = 0;
        double y;
        double x2;
        double y2;
        
        double yMeta;
        
        double mapaX2[];
        double mapaY2[];
        double mapaX[];
        double mapaY[];
        
        
        mapaX = new double[203];
        mapaY = new double[203];
        mapaX2 = new double[203];
        mapaY2 = new double[203];
        
        int puntos = 0;
        int iter;
        
        if (terreno==1){y = valle();yMeta = montana();terreno=2;}
        else{y = montana();yMeta = valle();terreno=1;}
        
        //Sintaxis general, pero en vez de tren de ondas crea un camino irregular
        //Desde un punto inicial a un punto objetivo (montes y valles)
        while (x<2000){
            
            iter = 0;
            
            if(terreno==1){
                while(y<yMeta && x<2000 && iter<40){    

                    X[puntos] = x;
                    Y[puntos] = y;
                    x2 = x+10;
                    y2 = acercar(y, yMeta, iter);
                    gc.strokeLine(x, y, x2, y2);

                  
                    mapaX[puntos]=x;
                    mapaY[puntos] = y+20;
                    mapaX2[puntos]=x;
                    mapaY2[puntos]=y-20;

                    puntos++;
                    iter++;

                    x = x2;
                    y=y2;


                }
            }
            else{
                while(y>yMeta && x<2000 && iter<40){    

                    X[puntos] = x;
                    Y[puntos] = y;
                    x2 = x+10;
                    y2 = acercar(y, yMeta, iter);
                    gc.strokeLine(x, y, x2, y2);

                  
                    mapaX[puntos]=x;
                    mapaY[puntos] = y+20;
                    mapaX2[puntos]=x;
                    mapaY2[puntos]=y-20;

                    puntos++;
                    iter++;

                    x = x2;
                    y=y2;


                }
            }
            if (terreno==1){yMeta = montana();terreno=2;}
            else{yMeta = valle();terreno=1;}
            
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
        
    }
    //Genera mapa tipo 3 (Arcoiris), se asemeja al anterior pero es más bajo y con montañas menos empinadas
    public void genMapa3(GraphicsContext gc){
        
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.CHOCOLATE);
        
        
        int terreno = vom();
        
        double x = 0;
        double y;
        double x2;
        double y2;
        
        double yMeta;
        
        double mapaX2[];
        double mapaY2[];
        
        
        
        
        mapaX2 = new double[403];
        mapaY2 = new double[403];
        
        int puntos = 0;
        int puntos2 = 0;
        int iter;
        int arcoiris=1;
        
        if (terreno==1){y = valle2();yMeta = montana2();terreno=2;}
        else{y = montana2();yMeta = valle2();terreno=1;}
        
        
        while (x<2000){
            
            iter = 0;
            
            if(terreno==1){
                while(y!=yMeta && x<2000 && iter<40){    
                    
                    switch(arcoiris){
                        case 1:
                            gc.setFill(Color.VIOLET);
                            arcoiris = 2;
                            break;
                        case 2:
                            gc.setFill(Color.INDIGO);
                            arcoiris = 3;
                            break;
                        case 3:
                            gc.setFill(Color.MEDIUMBLUE);
                            arcoiris = 4;
                            break;
                        case 4:
                            gc.setFill(Color.SPRINGGREEN);
                            arcoiris = 5;
                            break;
                        case 5:
                            gc.setFill(Color.YELLOW);
                            arcoiris = 6;
                            break;
                        case 6:
                            gc.setFill(Color.ORANGE);
                            arcoiris = 7;
                            break;
                        case 7:
                            gc.setFill(Color.RED);
                            arcoiris = 1;
                            break;
                    }
                    
                    X[puntos] = x;
                    Y[puntos] = y;
                    x2 = x+10;
                    y2 = acercar2(y, yMeta, iter);
                    gc.fillRoundRect(x, y, 10, 1000-y,5,5);

            
                    
                    mapaX2[puntos2]=x;
                    mapaX2[puntos2+1]=x2;
                    mapaY2[puntos2]=y;
                    mapaY2[puntos2+1]=y;

                    puntos++;
                    puntos2+=2;
                    iter++;

                    x = x2;
                    y=y2;


                }
            }
            else{
                while(y!=yMeta && x<2000 && iter<40){    
                    
                    switch(arcoiris){
                        case 1:
                            gc.setFill(Color.VIOLET);
                            arcoiris = 2;
                            break;
                        case 2:
                            gc.setFill(Color.INDIGO);
                            arcoiris = 3;
                            break;
                        case 3:
                            gc.setFill(Color.MEDIUMBLUE);
                            arcoiris = 4;
                            break;
                        case 4:
                            gc.setFill(Color.SPRINGGREEN);
                            arcoiris = 5;
                            break;
                        case 5:
                            gc.setFill(Color.YELLOW);
                            arcoiris = 6;
                            break;
                        case 6:
                            gc.setFill(Color.ORANGE);
                            arcoiris = 7;
                            break;
                        case 7:
                            gc.setFill(Color.RED);
                            arcoiris = 1;
                            break;
                    }
                    
                    X[puntos] = x;
                    Y[puntos] = y;
                    x2 = x+10;
                    y2 = acercar2(y, yMeta, iter);
                    gc.fillRoundRect(x, y, 10, 1000-y,5,5);

                 
                   
                    mapaX2[puntos2]=x;
                    mapaX2[puntos2+1]=x2;
                    mapaY2[puntos2]=y;
                    mapaY2[puntos2+1]=y;

                    puntos++;
                    puntos2+=2;
                    iter++;

                    x = x2;
                    y=y2;


                }
            }
            if (terreno==1){yMeta = montana2();terreno=2;}
            else{yMeta = valle2();terreno=1;}
            
        }
        
        mapaX2[400] = 2000;
        mapaY2[400] = 0;
        mapaX2[401] = 0;
        mapaY2[401]=0;
        mapaX2[402] = mapaX2[0];
        mapaY2[402] = mapaY2[0];
        
        
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillPolygon(mapaX2, mapaY2, puntos2+3);
        
    } 
    //Ecuación de un tren de ondas con valores arbitrarios
    private static double onda(double x){
        double y;
        y = Math.cos(7*3.14*667)*Math.sin(2*3.14*(1)/(667)*x);//1250 Math.cos(7*3.14*901)*Math.sin(2*3.14*(1)/(901)*x)
        return y;
    }
    
    //Genera un punto que representa un valle, congruente con la sintaxis del mapa 2
    private double valle2(){
        
        double y = 850 + Math.random() * ((950-850)+1); // min y max basados en 1000, que es la altura máxima del mapa
        return y;
    }
    //Genera un punto que representa una montaña, congruente con la sintaxis del mapa 2
    private double montana2(){
        double y = 650 + Math.random() * ((750-650)+1);
        return y;
    }
    
    //entrega un número que acerca al y actual al punto yObjetivo en base a números aleatorios
    private double acercar2(double yActual, double yObjetivo, double iter){
        double y2;
        if(yObjetivo>=850){
            if (iter<10){y2 = 1 + Math.random()*((10-1)+1) ;}
            else if(iter>10 && iter<30){y2 = 5 + Math.random()*((20-5)+1);}
            else {y2 = 10 + Math.random()*((25-10)+1);}
            if ((yActual+y2)>yObjetivo){y2 = yObjetivo-yActual;}
        }
        
        else{
            if (iter<10){y2 = -(1 + Math.random()*((10-1)+1)) ;}
            else if(iter>10 && iter<30){y2 = -(5 + Math.random()*((20-5)+1));}
            else {y2 = -(10 + Math.random()*((25-10)+1));}
            if ((yActual+y2)<yObjetivo){y2 = -(yActual-yObjetivo);}
        }
        y2+=yActual;
        return y2;
    }
    
    //Genera un punto que representa un valle, congruente con la sintaxis del mapa 1
    private double valle(){
        
        double y = 660 + Math.random() * ((950-660)+1); // min y max basados en 1000, que es la altura máxima del mapa
        return y;
    }
    //Genera un punto que representa una montaña, congruente con la sintaxis del mapa 1
    private double montana(){
        double y = 450 + Math.random() * ((600-400)+1);
        return y;
    }
    
    //entrega un número que acerca al y actual al punto yObjetivo en base a números aleatorios
    private double acercar(double yActual, double yObjetivo, double iter){
        double y2;
        if(yObjetivo>=660){
            if (iter<10){y2 = 1 + Math.random()*((10-1)+1) ;}
            else if(iter>10 && iter<30){y2 = 5 + Math.random()*((20-5)+1);}
            else {y2 = 10 + Math.random()*((25-10)+1);}
            if ((yActual+y2)>yObjetivo){y2 = yObjetivo-yActual;}
        }
        
        else{
            if (iter<10){y2 = -(1 + Math.random()*((10-1)+1)) ;}
            else if(iter>10 && iter<30){y2 = -(5 + Math.random()*((20-5)+1));}
            else {y2 = -(10 + Math.random()*((25-10)+1));}
            if ((yActual+y2)<yObjetivo){y2 = -(yActual-yObjetivo);}
        }
        y2+=yActual;
        return y2;
    }
    //escoge un punto inicial valle o montaña
    private int vom(){
        int punto = 1+(int)(Math.random()*((2-1)+1));
        return punto;
    }
}
