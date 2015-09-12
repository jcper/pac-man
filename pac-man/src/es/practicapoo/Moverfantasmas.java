package es.practicapoo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Event;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.util.*;
import java.awt.Graphics2D;


public class Moverfantasmas {
	
	
	int pos;//variable posicion dentro de pantalla de datos;
	int contador;//variable contador desplazamiento
	int[] dx; //desplazamiento aleatorio eje x;
	int[] dy; //desplazamiento aleatorio eje y;
	int[] fantasmas;//array de fantasmas son cuatro;
	int numeroFantasmas;
	int[] dx_fantasmas;//desplazamiento eje x;
	int[] dy_fantasmas;//desplazamiento eje y;
	int[] posicionX_fantasmas;//posicion inicial de eje x fantasmas;
	int[] posicionY_fantasmas;//posicion inicial de eje y fantasmas;
	private final int velocidad[]={4,4,2,2};
	private int movX_actualBlinky;//variable que guarda la posicion en el eje x actual.
	private int movY_actualBlinky;//variable que guarda la posicion en el eje y actual.
	private int movX_actualPinky;
	private int movY_actualPinky;
	private int movX_actualInky;
	private int movY_actualInky;
	private int movX_actualClyde;
	private int movY_actualClyde;
    private Image BlinkyCollisions,PinkyCollisions,InkyCollisions,ClydeCollisions;
    private int BlinkyCollisions_width;//ancho de imagen Blinky
	private int BlinkyCollisions_height;//alto de imagen Blinky
	private int PinkyCollisions_width;//ancho de imagen Pinky
	private int PinkyCollisions_height;//alto de imagen Pinky
	private int InkyCollisions_width;//ancho de imagen Inky
	private int InkyCollisions_height;//alto de imagen Inky
	private int ClydeCollisions_width;//ancho de imagen Clyde
	private int ClydeCollisions_height;//alto de imagen Clyde
	private boolean Casa_fantasmasM=false;
	
	
	
	public Moverfantasmas(){
    numeroFantasmas=4;//posicion 3 ya que son 4 fantasmas 0,1,2,3
	fantasmas=new int[numeroFantasmas];
	dx_fantasmas=new int[numeroFantasmas];
	dy_fantasmas=new int[numeroFantasmas];
	posicionX_fantasmas=new int[numeroFantasmas];
	posicionY_fantasmas=new int[numeroFantasmas];
	dx=new int[numeroFantasmas];
	dy=new int[numeroFantasmas];
	PosicionInicial();
	 BlinkyCollisions = Tablero.getImageBlinky();
	 PinkyCollisions  = Tablero.getImagePinky(); 
	 InkyCollisions   = Tablero.getImageInky();
	 ClydeCollisions  = Tablero.getImageClyde();
	 BlinkyCollisions_width=BlinkyCollisions.getWidth(null);//ancho de imagen Blinky
	 BlinkyCollisions_height= BlinkyCollisions.getHeight(null);//alto de imagen Blinky
	 PinkyCollisions_width= PinkyCollisions.getWidth(null);//ancho de imagen Pinky
     PinkyCollisions_height=PinkyCollisions.getHeight(null);//alto de imagen Pinky
     InkyCollisions_width=InkyCollisions.getWidth(null);//ancho de imagen Inky
 	 InkyCollisions_height= InkyCollisions.getHeight(null);//alto de imagen Inky
 	ClydeCollisions_width= ClydeCollisions.getWidth(null);//ancho de imagen Clyde
	ClydeCollisions_height=ClydeCollisions.getHeight(null) ;//alto de imagen Clyde
    }
	
	public void PosicionInicial() {
		
		for (int  i=0; i<numeroFantasmas; i++){
			posicionX_fantasmas[i]=4*Tablero.cuadrado;
			posicionY_fantasmas[i]=4*Tablero.cuadrado;
			
		}
		
	}
	
	public void Movghost(){
		    short i;
		
	        for ( i = 0; i < numeroFantasmas; i++) {
	            if (posicionX_fantasmas[i] % Tablero.cuadrado == 0 && posicionY_fantasmas[i] % Tablero.cuadrado == 0) {
	                pos = posicionX_fantasmas[i] / Tablero.cuadrado + Tablero.numero_cuadrados * (int) (posicionY_fantasmas[i] / Tablero.cuadrado);
	               
	                contador = 0;
	              
	                if ((Tablero.datospantalla[pos] & 1) == 0 && dx_fantasmas[i] != 1) {
	                    dx[contador] = -1;
	                    dy[contador] = 0;
	                    contador++;
	                }

	                if ((Tablero.datospantalla[pos] & 2) == 0 && dy_fantasmas[i] != 1) {
	                    dx[contador] = 0;
	                    dy[contador] =-1;
	                    contador++;
	                }

	                if ((Tablero.datospantalla[pos] & 4) == 0 && dx_fantasmas[i] != -1) {
	                    dx[contador] = 1;
	                    dy[contador] = 0;
	                    contador++;
	                }

	                if ((Tablero.datospantalla[pos] & 8) == 0 && dy_fantasmas[i] != -1) {
	                    dx[contador] = 0;
	                    dy[contador] = 1;
	                    contador++;
	                }

	               if (contador == 0) {

	                    if ((Tablero.datospantalla[pos] & 15) == 15) {
	                        dx_fantasmas[i] = 0;
	                        dy_fantasmas[i] = 0;
	                    } else {
	                        dx_fantasmas[i] = -dx_fantasmas[i];
	                        dy_fantasmas[i] = -dy_fantasmas[i];
	                    }

	                } else {
                    
	                    contador = (int) (Math.random() * contador);

	                    if (contador > 3) {
	                        contador = 3;
	                    }
                    
                       }
	                    dx_fantasmas[i] = dx[contador];
	                    dy_fantasmas[i] = dy[contador];
	               

	            }
	           
	           

	            posicionX_fantasmas[i] = posicionX_fantasmas[i] + (dx_fantasmas[i] * velocidad[i]);
	            movX_actualBlinky=posicionX_fantasmas[0];//fantasma Blinky;
	            movX_actualPinky=posicionX_fantasmas [1];//fantasma Pinky;
	            movX_actualInky  =posicionX_fantasmas[2];//fantasma Inky;
	            movX_actualClyde =posicionX_fantasmas[3];//fantasma Clyde;
	            posicionY_fantasmas[i] = posicionY_fantasmas[i] + (dy_fantasmas[i] * velocidad[i]);
	            movY_actualBlinky= posicionY_fantasmas[0];//fantasma Blinky;
	            movY_actualPinky = posicionY_fantasmas[1];//fantasma Pinky;
	            movY_actualInky =  posicionY_fantasmas[2];//fantasma Inky;
	            movY_actualClyde=  posicionY_fantasmas[3];//fantasma Clyde;
	   }
	}

	public boolean getCasa_fantasmasM(){
		return Casa_fantasmasM;
	}
	public void setCasa_fantasmasM(boolean M){
		Casa_fantasmasM=M;
		
	}
	public void setXCasa_fantasmasBlinky(){
		  movX_actualBlinky= 13*Tablero.cuadrado;
	}
	public void setYCasa_fantasmasBlinky(){
		  movY_actualBlinky= 14*Tablero.cuadrado;
	}
	public void setXCasa_fantasmasInky(){
		  movX_actualInky= 13*Tablero.cuadrado;
	}
	public void setYCasa_fantasmasInky(){
		  movY_actualInky= 14*Tablero.cuadrado;
	}
	public void setXCasa_fantasmasClyde(){
		  movX_actualClyde= 13*Tablero.cuadrado;
	}
	public void setYCasa_fantasmasClyde(){
		  movY_actualClyde= 14*Tablero.cuadrado;
	}
	public void setXCasa_fantasmasPinky(){
		  movX_actualInky= 13*Tablero.cuadrado;
	}
	public void setYCasa_fantasmasPinky(){
		  movY_actualInky= 14*Tablero.cuadrado;
	}
	
	     public int getXactualBlinky(){
	         return movX_actualBlinky;
	           }
	     public int getYactualBlinky(){
	    	 return movY_actualBlinky;
			}
	     public int getXactualPinky(){
	    	return movX_actualPinky;
		     }
		 public int getYactualPinky(){
		    return movY_actualPinky;
			}
	     public int getXactualInky(){
		    return movX_actualInky;
			}
	    public int getYactualInky(){
			return movY_actualInky;
			}
	    public int getXactualClyde(){
		   return movX_actualClyde;
		    }
	    public int getYactualClyde(){
		   return movY_actualClyde;
		    }
	    public void setXactualBlinky(int x){
			movX_actualBlinky=x;
			}
	    public void setYactualBlinky(int y){
		    movY_actualBlinky=y;
			}
	    public void setXactualClyde(int x){
			 movX_actualClyde=x;
				}
		public void setYactualClyde(int y){
			movY_actualClyde=y;
				}
		public void setXactualPinky(int x){
		    movX_actualPinky=x;
				}
		public void setYactualPinky(int y){
			movY_actualPinky=y;
				} 
		public void setXactualInky(int x){
			movX_actualInky=x;
				}
				  
		public void setYactualInky(int y){
			movY_actualInky=y;
				} 
		
		
		//obtener cambio de rumbo y direccion cuando colisionan.
	    public int getDxBlinky(){
	    	   return dx_fantasmas[0];
	    }
	    
	    public int getDxPinky(){
	    	   return dx_fantasmas[1];
	    }
	    
	    public int getDxInky(){
	    	   return dx[2];
	    }
	    
	    public int getDxClyde(){
	    	   return dx[3];
	    }
	    
	    public void setDxBlinky(int x){
	    	   dx_fantasmas[0]=x;
	    }
	    public void setDxPinky(int x){
	    	   dx_fantasmas[1]=x;
	    }
	    public void setDxInky(int x){
	    	   dx[2]=x;
	    }
	    public void setDxClyde(int x){
	    	   dx[3]=x;
	    }
	    
	    public int getDyBlinky(){
	    	   return dy_fantasmas[0];
	    }
	    
	    public int getDyPinky(){
	    	   return dy_fantasmas[1];
	    }
	    
	    public int getDyInky(){
	    	   return dy[2];
	    }
	    
	    public int getDyClyde(){
	    	   return dy[3];
	    }
	    
	    public void setDyBlinky(int x){
	    	   dy_fantasmas[0]=x;
	    }
	    public void setDyPinky(int x){
	    	   dy_fantasmas[1]=x;
	    }
	    public void setDyInky(int x){
	    	   dy[2]=x;
	    }
	    public void setDyClyde(int x){
	    	   dy[3]=x;
	    }
	public Rectangle getBoundsBlinky(){//devuelve un objeto rectangulo.
     return new Rectangle(movX_actualBlinky,movY_actualBlinky,BlinkyCollisions_width,
    		 BlinkyCollisions_height);
                  
				}
	public Rectangle getBoundsPinky(){
	     return new Rectangle(movX_actualPinky,movY_actualPinky,PinkyCollisions_width,
	    		 PinkyCollisions_height);
					}
	
	public Rectangle getBoundsInky(){
	     return new Rectangle(movX_actualInky,movY_actualInky,InkyCollisions_width,
	    		 InkyCollisions_height);
					}
	public Rectangle getBoundsClyde(){
	     return new Rectangle(movX_actualClyde,movY_actualClyde,ClydeCollisions_width,
	    		 ClydeCollisions_height);
					}
	
	
	
}


