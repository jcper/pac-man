package es.practicapoo;

import java.awt.Image;
import java.awt.Rectangle;

public class Fantasma {
	int pos;//variable posicion dentro de pantalla de datos;
	int contador;//variable contador desplazamiento
	int dx; //desplazamiento eje x;
	int dy; //desplazamiento  eje y;
	int fantasma;// un fantasma;
    int dx_fantasma;//desplazamiento eje x;
	int dy_fantasma;//desplazamiento eje y;
	int posicionX_fantasmas;//posicion inicial de eje x fantasmas;
	int posicionY_fantasmas;//posicion inicial de eje y fantasmas;
	private final int velocidad[]={4,4,2,2};
	
	
	
	
	public Fantasma(){
	
	PosicionInicial();
	
    }
	
	public void PosicionInicial() {
		
	
			posicionX_fantasmas=4*Tablero.cuadrado;
			posicionY_fantasmas=4*Tablero.cuadrado;
			
		
		
	}
	
	public void Movghost(){
	
		
	      
	            if (posicionX_fantasmas % Tablero.cuadrado == 0 && posicionY_fantasmas % Tablero.cuadrado == 0) {
	                pos = posicionX_fantasmas / Tablero.cuadrado + Tablero.numero_cuadrados * (int) (posicionY_fantasmas / Tablero.cuadrado);
	               
	               
	              
	                if ((Tablero.datospantalla[pos] & 1) == 0 && dx_fantasma != 1) {
	                    dx= -1;
	                    dy = 0;
	                   
	                }

	                if ((Tablero.datospantalla[pos] & 2) == 0 && dy_fantasma != 1) {
	                    dx = 0;
	                    dy =-1;
	                    
	                }

	                if ((Tablero.datospantalla[pos] & 4) == 0 && dx_fantasma != -1) {
	                    dx = 1;
	                    dy = 0;
	                  
	                }

	                if ((Tablero.datospantalla[pos] & 8) == 0 && dy_fantasma != -1) {
	                    dx = 0;
	                    dy = 1;
	                  
	                }

	             

	                    if ((Tablero.datospantalla[pos] & 15) == 15) {
	                        dx_fantasma = 0;
	                        dy_fantasma = 0;
	                    } else {
	                        dx_fantasma = -dx_fantasma;
	                        dy_fantasma = -dy_fantasma;
	                    }
                    
	                  

	               
	                    dx_fantasma = dx;
	                    dy_fantasma = dy;
	               

	            }
	           
	           

	            posicionX_fantasmas= posicionX_fantasmas + (dx_fantasma * velocidad[1]);
	            posicionY_fantasmas = posicionY_fantasmas + (dy_fantasma * velocidad[1]);

	            
	   }
	   public int getPosicionX_fantasmas(){
		   return posicionX_fantasmas;
	   }
       public int getPosicionY_fantasmas(){
    	   return posicionY_fantasmas;
       }
       public void setPosicionX_fantasmas(int x){
    	   posicionX_fantasmas=x;
    	   
       }
       public void setPosicionY_fantasmas(int y){
    	   posicionY_fantasmas=y;
       }
       public int getDx_fantasma(){
		   return dx_fantasma;
	   }
       public int getDy_fantasma(){
    	   return dy_fantasma;
       }
       public void setDx_fantasma(int x){
    	    dx_fantasma=x;
    	   
       }
       public void setDy_fantasma(int y){
    	   dy_fantasma=y;
       }
}



