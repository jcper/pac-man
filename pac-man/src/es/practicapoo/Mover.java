package es.practicapoo;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Event;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Mover {

	
	private int pacmanx, pacmany,pacmandx,pacmandy;
	private int posicionx_pacman, posiciony_pacman;
	private int puntosPantalla;
	private int dotconsumidospantalla;
	private boolean Pausajuego;
	private int dx=0;
	private int dy=0;
    private int pacman2up_width;//ancho de imagen pacmanup
	private int pacman2up_height;//alto de imagen pacmanup
	private Image pacmanCollisions;
	
	
	
	
	
	public Mover(){//Posicion inicial;
		pacmanx=15* Tablero.cuadrado;
		pacmany=16*Tablero.cuadrado;
		pacmandx=0;
		pacmandy=0;
		posicionx_pacman=0;
		posiciony_pacman=0;
		pacmanCollisions=Tablero.getImagePacman();
		pacman2up_width=pacmanCollisions.getWidth(null);
		pacman2up_height=pacmanCollisions.getHeight(null);
	    

		
		
	}
	public void moverPacman(){
     int pos;
     short ch;

     if ( dx == -pacmandx && dy == -pacmandy) {
		            pacmandx = dx;
		            pacmandy = dy; 
		            posicionx_pacman = pacmandx;
		            posiciony_pacman = pacmandy;
	    }

		        if (pacmanx % Tablero.cuadrado == 0 && pacmany % Tablero.cuadrado == 0) {
		            pos = pacmanx / Tablero.cuadrado + Tablero.numero_cuadrados * (int) (pacmany / Tablero.cuadrado);
		            ch = Tablero.datospantalla[pos];

		            if ((ch & 16) != 0) {
		            Tablero.datospantalla[pos] = (short)(ch & 15);
		              puntosPantalla= Tablero.getPuntos()+10;
		              Tablero.setPuntos(puntosPantalla);
		              dotconsumidospantalla=Tablero.getDotconsumidos()+1;
		              Tablero.setDotconsumidos( dotconsumidospantalla);
		              
		              
		             }
		            
		            if ((ch & 32) != 0) {
			            Tablero.datospantalla[pos] = (short)(ch & 15);
			             
			              Tablero.modo_azul();
			           
			         }
		            

		           if ( dx!= 0 ||dy != 0) {
		                if (!((dx  == -1 && dy  == 0 && (ch & 1) != 0)
		                       || (dx == 1 && dy== 0 && (ch & 4) != 0)
		                        || (dx == 0 && dy == -1 && (ch & 2) != 0)
		                        || (dx == 0 && dy == 1 && (ch & 8) != 0))) {
		                      pacmandx =dx ;
		                      pacmandy =dy ;
		                      posicionx_pacman = pacmandx;
		                      posiciony_pacman = pacmandy;
		                }
		            }

		           
		            if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0)||
		            		pacmandx == 1 && pacmandy == 0&& (ch==4)|| //no puede atravesar los muros
		                       (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0) 
		                      || (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
		                      || (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
		                   pacmandx = 0;
		                   pacmandy = 0;
		            }
		       }
		        pacmanx = pacmanx + 6 * pacmandx;
		       
		        pacmany = pacmany + 6 * pacmandy;
		       
    }
	public int getX(){
		return pacmanx;
	}
	public int getY(){
		return pacmany;
	}
    public void setPacmanx(int Pacmanx_move){
    	pacmanx=Pacmanx_move;
    }
    public void setPacmany(int Pacmany_move){
    	pacmany=Pacmany_move;
    }
	
	public int getPosicionx_pacman(){
		return posicionx_pacman;
	}
	
	public int getPosiciony_pacman(){
		return posiciony_pacman;
	}
	public int getPacmandx(){
		return pacmandx;
	}
	public int getPacmandy(){
		return pacmandy;
	}
	 public void keyPressed(KeyEvent e){//detecta pulsacion tecla;
			int key = e.getKeyCode();
			
			if(key==KeyEvent.VK_LEFT){
				dx=-1;
				dy=0;
			}
			if(key==KeyEvent.VK_RIGHT){
				dx=1;
				dy=0;
			}
			if(key==KeyEvent.VK_UP){
				dy=-1;
				dx=0;
			}
			if(key==KeyEvent.VK_DOWN){
				dy=1;
				dx=0;
			}
			if(key=='p'||key=='P'){
				
				
				if(Tablero.timer.isRunning()){
					
					Tablero.timer.stop();
					
					
				}else{
					Pausajuego=true;
					Tablero.setParado(Pausajuego);
				   Tablero.timer.start();
					
				}
			}
			
	 }
			public void keyReleased(KeyEvent e){
				int key = e.getKeyCode();
				
				if(key==Event.LEFT||key==Event.RIGHT||key==Event.UP||key==Event.DOWN){
					dx=0;
					dy=0;
				}
	
	}
			 public Rectangle getBounds(){
				 return new Rectangle(pacmanx,pacmany, pacman2up_width,pacman2up_height);
			 }
			
		
}		
	
	


