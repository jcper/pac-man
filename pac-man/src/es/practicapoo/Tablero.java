package es.practicapoo;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.*;
import java.awt.Font.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JButton;




public class Tablero extends JPanel implements ActionListener{
   
	
	private final Color pac_dots= new Color(192,192,0);
	private final Font letra= new Font("Helvetica", Font.BOLD,30);
	private final Font letra1= new Font("Helvetica", Font.BOLD,20);
	private Color linea_tablero;
	public  static final int cuadrado=24;
	public  static final int numero_cuadrados=26;
	public  static final int matriz_cuadrados=cuadrado*numero_cuadrados;
	private static Image Blinky,Pinky,Inky,Clyde;
	private static Image pacman1, pacman2up, pacman2left, pacman2right,pacman2down;
	private static Image pacman3up, pacman3down,pacman3left,pacman3right;
	private static Image pacman4up, pacman4down,pacman4left,pacman4right;
	private static Image Fantasma_P;
	private static int puntos;
	public  static Timer timer;//variable publica para acceder desde la clase Mover
	public static Timer Animacion;
	private Mover movPacman;
	private Moverfantasmas fantasma;
	private Fantasma fantasma1;
	public  static Timer azul_timer;
	public  Timer terminado_timer;
	private static boolean ModoAzul=false;
	private static boolean parado=true;
    private static boolean terminado=false;
	private int vidas;
	private int posboca=0;
	private int dirAnima=1;
	private int contadorAnim=2;
	private static int dotconsumidos;
	private  boolean muerte0=false;
	private boolean  muerte1=false;
	private boolean  muerte2=false;
	private boolean  muerte3=false;
	private boolean  ingame=true;//juego en funcionamiento.
	public static Timer casa_fantasmas;//Timer de tiempo los fantasmas se quedan en la casa un tiempo determinado.
	
	
	
	
	
	
	


	
	
	private final short mapa[]={
   0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	
   0,19,18,18,18,18,18,18,18,18,18,18,22, 0,19,18,18,18,18,18,18,18,18,18,18,22,
   0,17,24,24,24,24,16,24,24,24,24,24,20, 0,17,24,24,24,24,24,16,24,24,24,24,20,
   0,37, 0, 0, 0, 0,21, 0, 0, 0, 0, 0,21, 0,21, 0, 0, 0, 0, 0,21, 0, 0, 0, 0,37,
   0,17,18,18,18,18,16,18,18,18,18,18,16,18,16,18,18,18,18,18,16,18,18,18,18,20,
   0,17,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,20,
   0,17,24,24,24,24,16,16,24,16,24,24,24,24,24,24,24,24,16,24,16,24,24,24,24,20,
   0,21, 0, 0, 0, 0,17,20, 0,21, 0, 0, 0, 0, 0, 0, 0, 0,21, 0,21, 0, 0, 0, 0,21,
   0,25,26,26,26,26,24,20, 0,17,18,18,18,22, 0,19,18,18,20, 0,17,26,26,26,26,28,
   0,0, 0, 0, 0, 0, 0,21, 0,25,24,24,16,20, 0,17,24,24,28, 0,21, 0, 0, 0, 0,  0,
   0,0, 0, 0, 0, 0, 0,21, 0, 0, 0, 0,17,16,18,20, 0, 0, 0, 0,21, 0, 0, 0, 0,  0,
   0,0, 0, 0, 0, 0, 0,21, 0,19,18,18,16,16,16,16,18,18,22, 0,21, 0, 0, 0, 0,  0,
   0,19,18,18,18,18,18,16,18,16,16,16,24,24,24,24,16,16,16,18,16,18,18,18,18,22,
   0,17, 0, 0, 0, 0, 0,16,16,16,16,20,3, 2, 2, 6,17,16,16,16,16, 0, 0, 0, 0, 20,
   0,17, 0, 0, 0, 0, 0,16,24,16,16,20,9, 8, 8, 12,17,16,16,24,16, 0, 0, 0, 0, 20,
   0,25,24,24,24,24,24,20, 0,17,16,16,18,18,18,18,16,16,20, 0,17,24,24,24,24,28,
   0, 0, 0, 0, 0, 0, 0,21, 0,17,16,24,24,24,24,24,16,16,20, 0,21, 0, 0, 0, 0, 0,
   0, 0, 0, 0, 0, 0, 0,21, 0,17,20, 0, 0, 0, 0, 0,17,16,20, 0,21, 0, 0, 0, 0, 0,
   0, 0, 0, 0, 0, 0, 0,21, 0,17,16,18,22, 0,19,18,16,16,20, 0,21, 0, 0, 0, 0, 0,
   0,19,18,26,26,18,18,24,26,24,24,24,20, 0,17,24,24,24,24,26,16,18,26,26,18,22,
   0,41,20, 0, 0,17,20, 0, 0, 0, 0, 0,17,18,20, 0, 0, 0, 0, 0,17,20, 0, 0,17,36,
   0, 0,17,22, 0,17,16,18,18,18,26,26,24,24,24,26,26,18,26,18,16,16,22, 0,17,20,
   0,19,16,16,18,24,16,16,16,20, 0, 0, 0, 0, 0, 0, 0,21, 0,25,24,24,24,26,24,20,
   0,17,24,24,28, 0,25,24,24,16,18,18,22, 0,19,18,18,28, 0, 0, 0, 0, 0, 0, 0,21,
   0,21, 0, 0, 0, 0, 0, 0, 0,17,16,16,20, 0,17,16,20, 0, 0, 0, 0, 0, 0, 0, 0,21,
   0,25,26,26,26,26,26,26,26,24,24,24,24,26,24,24,24,26,26,26,26,26,26,26,26,28,
	};
	
	public static short[] datospantalla;
	
	
	public Tablero(){
		addKeyListener(new TAdapter());
		cargarImagenes();
		inicioJuego();
		setBackground(Color.black);
		setFocusable(true);
		setDoubleBuffered(true);
		timer=new Timer(40, this);
		timer.start();
	    movPacman=new Mover();
		fantasma=new Moverfantasmas();
		fantasma1=new Fantasma();
		azul_timer=new Timer (5000, new TimerListener());
		terminado_timer=new Timer(5000, new TimerListener2());
		
		
		
	
	
	}
	private void inicioJuego(){
		if(ingame){
		datospantalla= new short[numero_cuadrados*numero_cuadrados];
		linea_tablero = Color.blue;
		
		
		for(int i=0; i< numero_cuadrados*numero_cuadrados; i++){
		datospantalla[i]=mapa[i];
		}
		vidas=1;
		puntos=0;
		dotconsumidos=0;
	  }
		
	}
	
	    private void cargarImagenes(){
		Blinky =  new ImageIcon("images/Blinky.gif").getImage();
		Pinky  =  new ImageIcon("images/Pinky.gif").getImage();
		Inky   =  new ImageIcon("images/Inky.jpg").getImage();
		Clyde  =  new ImageIcon("images/Clyde.jpg").getImage();
		pacman1 = new ImageIcon("images/pacman1.png").getImage();
		pacman2down = new ImageIcon("images/down1.png").getImage();
        pacman3down = new ImageIcon("images/down2.png").getImage();
        pacman4down = new ImageIcon("images/down3.png").getImage();
        pacman2left = new ImageIcon("images/left1.png").getImage();
        pacman3left = new ImageIcon("images/left2.png").getImage();
        pacman4left = new ImageIcon("images/left3.png").getImage();
        pacman2right = new ImageIcon("images/right1.png").getImage();
        pacman3right = new ImageIcon("images/right2.png").getImage();
        pacman4right = new ImageIcon("images/right3.png").getImage();
        pacman2up    = new ImageIcon("images/up1.png").getImage();
        pacman3up    = new ImageIcon("images/up2.png").getImage();
        pacman4up    = new ImageIcon("images/up3.png").getImage();
        Fantasma_P   = new ImageIcon("images/GhostScared1.gif").getImage();
    
	}
	private void dibujarPuntuacion(Graphics2D g){
		String Score="Puntos: " + puntos;
		String numvidas="Vidas: " + vidas;
		g.setColor(new Color(192, 192, 0));
        g.setFont(letra);
        g.drawString(Score,40,670);
        g.drawString(numvidas,40,700);
        comprobarPausajuego(g);
        
        
	}
	private void dibujarMensajeFelicitacion(Graphics2D g){
		String Terminado="Enhorabuena, has terminado el juego";
		  if(dotconsumidos==403 && terminado==false){
			  timer.stop();
			  terminado_timer=new Timer(5000,new TimerListener2());
			  terminado_timer.start();
			  g.setColor(new Color(192,192,0));
			  g.setFont(letra);
			  g.drawString(Terminado,100,300);
			  
		  }
    }
		
    private void AnimacionPacman(){
    	contadorAnim--;
    	if(contadorAnim<=0){
    		contadorAnim=2;
    		posboca=posboca + dirAnima;
    	if(posboca==(4-1)|| posboca==0){
    		  dirAnima=-dirAnima;
    	}
    	
    }
		
    }	
	
	
	private void dibujarGameOver(Graphics2D g){
		String gameover="GAME OVER";
		 
		 
		  if(muerte0||muerte1||muerte2||muerte3 && ModoAzul==false){
		   if(vidas==0 && ModoAzul==false){
		  timer.stop();
		  g.setColor(new Color(192,192,0));
		  g.setFont(letra);
		  g.drawString(gameover,250,300);
		}else if(ModoAzul==false){
			vidas--;
			reiniciarNivel();
		}
	}  
	}
	
	
	private void comprobarPausajuego(Graphics2D g)
	{
		String JuegoPausa="Pausar juego=p ";
		String JuegoContinua="continuar=p ";
		String controles="arriba=Up abajo=Down izquierda=Left derecha=Right";
		 if(Tablero.getParado()){
	            g.setFont(letra1);
	            g.drawString(JuegoPausa + JuegoContinua,250,670);	
	            g.drawString(controles, 250,700);
	            }
	}
	private void dibujarFantasmas(Graphics2D g){
		
	   g.drawImage(Pinky,fantasma.getXactualPinky(),fantasma.getYactualPinky(),null);
	   g.drawImage(Inky, fantasma.getXactualInky(),fantasma.getYactualInky(),null);
	   g.drawImage(Clyde,fantasma.getXactualClyde(),fantasma.getYactualClyde(),null);
	   g.drawImage(Blinky,fantasma.getXactualBlinky(),fantasma.getYactualBlinky(),null);
		
		
	 }
	    private void dibujarFantasmas1(Graphics2D g){//prototipo fantasma de prueba
		
		   g.drawImage(Fantasma_P,fantasma1.getPosicionX_fantasmas(),fantasma1.getPosicionY_fantasmas(),null);
	    }
	
	private void dibujarMapa(Graphics2D g2)  {
      
        short i = 0;
        int x, y;
        
       

        for (y = 0; y < matriz_cuadrados; y += cuadrado) {
            for (x = 0; x < matriz_cuadrados; x += cuadrado) {

                g2.setColor(linea_tablero);
                g2.setStroke(new BasicStroke(3));

                if ((datospantalla[i] & 1) != 0) { 
                    g2.drawLine(x, y, x, y + cuadrado - 1);
                }

                if ((datospantalla[i] & 2) != 0) { 
                    g2.drawLine(x, y, x + cuadrado - 1, y);
                }

                if ((datospantalla[i] & 4) != 0) { 
                    g2.drawLine(x + cuadrado - 1, y, x + cuadrado - 1,
                            y + cuadrado - 1);
                }

                if ((datospantalla[i] & 8) != 0) { 
                    g2.drawLine(x, y + cuadrado - 1, x + cuadrado - 1,
                            y + cuadrado - 1);
                }

                if ((datospantalla[i] & 16) != 0) { 
                    g2.setColor(pac_dots);
                    g2.fillRect(x + 11, y + 11, 3, 3);
                }
                if ((datospantalla[i]& 32) !=0) {
                	g2.setColor(pac_dots);
                	g2.fillRect(x +6,y +6,10,10);
                	
                }
               

                i++;
            }
        }
        AnimacionPacman();
        dibujarPuntuacion(g2);
        dibujarFantasmas(g2);
        dibujarFantasmas1(g2);
        dibujarGameOver(g2);
        dibujarMensajeFelicitacion(g2);
        dibujarPacman(g2);
       
        
    }
	private void dibujarPacman(Graphics2D g2d)  {

        if (movPacman.getPosicionx_pacman() == -1) {
     
        switch (posboca) {
        case 1:
        	g2d.drawImage(pacman2left,movPacman.getX(),movPacman.getY(),this);
        	 break;
        case 2:	 
            g2d.drawImage(pacman3left,movPacman.getX(),movPacman.getY(),this);
         break; 
        case 3:
        	g2d.drawImage(pacman4left,movPacman.getX(),movPacman.getY(),this);
        break;	
        default:
        	g2d.drawImage(pacman1,movPacman.getX(),movPacman.getY(),this);
           }
        

            
        } else if (movPacman.getPosicionx_pacman() == 1) {
        	switch(posboca){
        	 case 1:
        	g2d.drawImage(pacman2right,movPacman.getX(),movPacman.getY(),this);
        	break;
        	 case 2:
        	g2d.drawImage(pacman3right,movPacman.getX(),movPacman.getY(),this);
        	break;
        	 case 3:
        	g2d.drawImage(pacman4right,movPacman.getX(),movPacman.getY(),this);
        	break;
        	 default:
        	g2d.drawImage(pacman1,movPacman.getX(),movPacman.getY(),this);
        	
        	}
        
        	
        } else if (movPacman.getPosiciony_pacman() == -1) {
        	switch(posboca){
        	case 1:
        	g2d.drawImage(pacman2up,movPacman.getX(),movPacman.getY(),this);
        	break;
        	case 2:
            g2d.drawImage(pacman3up,movPacman.getX(),movPacman.getY(),this);
            break;
        	case 3:
            g2d.drawImage(pacman4up,movPacman.getX(),movPacman.getY(),this);
            break;
            default:
            g2d.drawImage(pacman1,movPacman.getX(),movPacman.getY(),this);
        	}
        	
        } else {
        	switch(posboca){
        	case 1:
        	g2d.drawImage(pacman2down,movPacman.getX(),movPacman.getY(),this);
        	break;
        	case 2:
        	g2d.drawImage(pacman3down,movPacman.getX(),movPacman.getY(),this);
        	break;
        	case 3:
        	g2d.drawImage(pacman4down,movPacman.getX(),movPacman.getY(),this);
        	break;
        	default:
        	g2d.drawImage(pacman1,movPacman.getX(),movPacman.getY(),this);
        	
        	}
        	
        }
    }
	
	public void paint(Graphics g)
	{
		super.paint(g);//clase padre 
		Graphics2D g2=(Graphics2D) g;//realizamos un casting de g a clase 2D
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(rh);//Pasamos este valor al objeto g2.
		g2.setColor(Color.black);
        Dimension size = getSize();
		g2.fillRect(0, 0, size.width, size.height);
		double  h= size.getHeight();
		double  w= size.getWidth();
		System.out.println("Ancho: "+w+"Alto: "+h);
		Toolkit.getDefaultToolkit().sync();
		dibujarMapa(g2);
	    g2.dispose();
       }
	
	
	public void actionPerformed(ActionEvent e){
		movPacman.moverPacman();
		fantasma.Movghost();
		//fantasma1.Movghost();
		movFantasma1();
		//movPinky();
	   checkcollisions();
	
		repaint();
	}
   private class TAdapter extends KeyAdapter{
	        public void keyReleased(KeyEvent e){
	        	movPacman.keyReleased(e);
	        }
	        public void keyPressed(KeyEvent e){
	        	movPacman.keyPressed(e);
	        }
	  
			}
			
	  
	  

   public static int getPuntos(){
	   return puntos;
   }
   public static  void setPuntos(int puntosPacman){
		  
		  puntos=puntosPacman;
		  }
   
   public static int getDotconsumidos(){
	    return dotconsumidos;
   }
   
   public static void setDotconsumidos(int x){
	   dotconsumidos=x;
	    
   }
   public static boolean getParado(){
	   return parado;
	   
   }
  public static void setParado(boolean pausa){
	  parado=pausa;
  }
  
  public static  void modo_azul(){
	
	  ModoAzul = true;
	   azul_timer = new Timer (5000, new TimerListener());
	   azul_timer.start();
	   Inky=Fantasma_P;
 	  Blinky=Fantasma_P;
 	  Clyde=Fantasma_P;
 	  Pinky=Fantasma_P;
	 
	  
}
  public static  class TimerListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) 
      {   
    	  Inky= new ImageIcon("images/Inky.jpg").getImage();
    	  Blinky= new ImageIcon("images/Blinky.gif").getImage();
    	  Clyde=new ImageIcon("images/Clyde.jpg").getImage();
    	  Pinky=new ImageIcon("images/Pinky.gif").getImage();
          //Termina el modo Azul
           ModoAzul = false;
           azul_timer.stop();
       }
}
  public   class TimerListener2 implements ActionListener {
      public void actionPerformed(ActionEvent evt) 
      {  
    	   timer.start();
    	  inicioJuego();
		  reiniciarNivel();
          //Termina el modo timer
		
           terminado = false;
           terminado_timer.stop();
      }
}
 
  
  
  
  
  public static Image getImageBlinky(){//devuelve un objeto imagen Blinky;
	    return Blinky;
  }
  
  public static Image getImageInky(){//devuelve un objeto imagen Inky;
	  return Inky;
   }
  
  public static Image getImageClyde(){//devuelve un objeto imagen Clyde;
	  return Clyde;
  }
  
  public static Image getImagePinky(){//devuelve un objeto imagen Pinky; 
	  return Pinky;
  }
  
  public static Image getImagePacman(){//devuelve un objeto imagen Pacman.
	  return pacman2up;
  }
  public int getVidas(){//devuelve el numero de vidas.
	  return vidas;
  }
  public static boolean getModoAzul(){
	  return ModoAzul;
  }

  public  void checkcollisions() {
	  
	  Rectangle r4=movPacman.getBounds();
	  Rectangle r3=fantasma.getBoundsBlinky();
	  Rectangle r2=fantasma.getBoundsClyde();
	  Rectangle r1=fantasma.getBoundsInky();
	  Rectangle r0=fantasma.getBoundsPinky();
	  
	 
	  if(ModoAzul){
		 		
		  
		 if(r4.intersects(r3)&& Blinky==Fantasma_P){
			    
				 fantasma.setXCasa_fantasmasBlinky();
				 fantasma.setYCasa_fantasmasBlinky();
		         Blinky= new ImageIcon("images/Blinky.gif").getImage();
			     
			    
			     puntos+=100;
			}
		  if(r4.intersects(r2)&& Clyde==Fantasma_P){
			 
			 fantasma.setXCasa_fantasmasClyde();
	 		 fantasma.setYCasa_fantasmasClyde();
			 Clyde=new ImageIcon("images/Clyde.jpg").getImage();
			
	  		 puntos+=100;
            }

		  if(r4.intersects(r1) && Inky==Fantasma_P){
			
			 fantasma.setXactualInky(14*cuadrado);
	 		 fantasma.setYactualInky(13*cuadrado);
			 Inky= new ImageIcon("images/Inky.jpg").getImage();
			
	 		 puntos+=100;
           }
		 if(r4.intersects(r0) && Pinky==Fantasma_P){
			
			 fantasma.setXactualPinky(14*cuadrado);
 			 fantasma.setYactualPinky(13*cuadrado);
 			 Pinky=new ImageIcon("images/Pinky.gif").getImage();
 			
			 
 			 puntos+=100;
		   }
		}
	  
	  //si colisionan pacman con algun fantasma le quita una vida o terminar el juego.
	        if(vidas==1 || vidas==0 && ModoAzul==false){
	        muerte0=(r4.intersects(r0));
	        muerte1=(r4.intersects(r1));
	        muerte2=(r4.intersects(r2));
	        muerte3=(r4.intersects(r3));
	        }
	        
	       
	   //si colosionan 2 fantasmas supone un cambio de rumbo;
	        if(r3.intersects(r2)){
			 fantasma.setXactualBlinky(-fantasma.getXactualBlinky());
			 fantasma.setYactualBlinky( fantasma.getYactualBlinky());
			 fantasma.setXactualClyde ( fantasma.getXactualClyde());
			 fantasma.setYactualClyde(-fantasma.getYactualClyde());
		  }
		 if(r1.intersects(r0)){
			 fantasma.setXactualInky(-fantasma.getXactualBlinky());
			 fantasma.setYactualInky( fantasma.getYactualBlinky());
			 fantasma.setXactualPinky( fantasma.getXactualPinky());
			 fantasma.setYactualPinky(-fantasma.getYactualPinky());
		  }
		    if(r0.intersects(r3)){
			 fantasma.setYactualPinky(- fantasma.getYactualPinky());
			 fantasma.setYactualPinky( fantasma.getYactualPinky());
			 fantasma.setXactualBlinky ( fantasma.getXactualBlinky());
			 fantasma.setYactualBlinky(-fantasma.getYactualBlinky());
			 
		   }
		   if(r1.intersects(r2)){
			   fantasma.setXactualInky(-fantasma.getXactualInky());
			   fantasma.setYactualInky( fantasma.getYactualInky());
			   fantasma.setXactualClyde( fantasma.getXactualClyde());
			   fantasma.setYactualClyde(-fantasma.getYactualClyde());
		     }
		    	 
		     
		   if(r1.intersects(r3)){
			   fantasma.setXactualInky(-fantasma.getXactualBlinky());
			   fantasma.setYactualInky( fantasma.getYactualBlinky());
			   fantasma.setXactualBlinky(fantasma.getXactualBlinky());
		       fantasma.setYactualBlinky(-fantasma.getYactualBlinky());
		   }
		   if(r0.intersects(r2)){
			   fantasma.setYactualPinky(- fantasma.getYactualPinky());
			   fantasma.setYactualPinky( fantasma.getYactualPinky());
			   fantasma.setXactualClyde ( fantasma.getXactualClyde());
			   fantasma.setYactualClyde(-fantasma.getYactualClyde());
		   }
       
       }
  
		  
              
   
           public  void reiniciarNivel(){
        	movPacman.setPacmanx(15*cuadrado);
        	movPacman.setPacmany(16*cuadrado);
        	fantasma.PosicionInicial();
        	   
         }
   
       	public void movFantasma1(){//Fantasma1 intenta colisionar con pacman
       		int pos;
       		int posx_pacman=movPacman.getX()/cuadrado;
       		int posy_pacman=movPacman.getY()/cuadrado;
       		int posx_fantasma1=fantasma1.getPosicionX_fantasmas()/cuadrado;
       		int posy_fantasma1=fantasma1.getPosicionY_fantasmas()/cuadrado;
       		int resultado_x= posx_pacman-posx_fantasma1;//variable distancia en eje x entre pacman y blinky.
       		int resultado_y=posy_pacman-posy_fantasma1; //variable distancia en eje y entre pacman y blinky.
       	    
       		
       	  if (fantasma1.getPosicionX_fantasmas() % cuadrado == 0 && fantasma1.getPosicionY_fantasmas() % cuadrado == 0) {
       		  
       	     pos = fantasma1.getPosicionX_fantasmas()/ cuadrado + numero_cuadrados * (int) (fantasma1.getPosicionY_fantasmas()/ cuadrado);
       	   if((datospantalla[pos] & 1) == 0 && fantasma1.getDx_fantasma() != 1){
       		   fantasma1.setDx_fantasma(-1);
       		   fantasma1.setDy_fantasma(0);
       	   }
       	   if((datospantalla[pos] & 2) == 0 && fantasma1.getDy_fantasma() != 1){
      		   fantasma1.setDx_fantasma(0);
      		   fantasma1.setDy_fantasma(-1);
      	   }
       	   
       	  if((datospantalla[pos] & 4) == 0 && fantasma1.getDx_fantasma() != -1){
    		   fantasma1.setDx_fantasma(1);
    		   fantasma1.setDy_fantasma(0);
    	   }
       	  
       	 if((datospantalla[pos] & 8) == 0 && fantasma1.getDy_fantasma() != -1){
    		   fantasma1.setDx_fantasma(0);
    		   fantasma1.setDy_fantasma(1);
    	   }
       }
     	 fantasma1.setPosicionX_fantasmas(fantasma1.posicionX_fantasmas+fantasma1.getDx_fantasma());
         fantasma1.setPosicionY_fantasmas(fantasma1.posicionY_fantasmas+fantasma1.getDy_fantasma()+resultado_y);
     }

}

     
	