package grafikaJava;   // plik znajduje sie w folderze 'grafikaJava'
import java.awt.*;
//========================================================== 
public class gr3d  // nazwa pliku: gr3d.java
{ 
  public static  double sx,sy;
  public static Color tlo,pioro;
  public static pkt2d k_g=new pkt2d(0,0);    // po³o¿enie kursora graficznego
//---------------------------------------------------------  
  public static pkt2d p2d(double a,double b)
          {
	          return(new pkt2d(a,b));
          }
//==========================================================     
  public static void init(Dimension w)  //wywolac: gr3d.init(getSize());
   {  
	   gr3d.sx = w.width / 2;
	   gr3d.sy = w.height  / 2;
	   gr3d.tlo = new Color(245, 245, 220);
       gr3d.pioro = new Color(25, 25, 112);
      //setBackground(gr3d.tlo);w metodzie paint() lub init()
	}
//---------------------------------------------------------
   public static pkt2d r2(int x,int y)
   {    
	  return(new pkt2d(((double)x-gr3d.sx)/20.0,(gr3d.sy-(double)y)/20.0));
	 } 
//-------------------------------------------------	     
   public static int cal_x(double x)
	{ 
		return ( (int)(20D * x +sx) );  
            //20D - D, podkreslenie, ze x jest rzeczywisty
	}
//---------------------------------------------------------
 	public static int cal_y(double y)
	{ 
	   return ((int)(-20D * y +sy));  
	}
//---------------------------------------------------------
   public static double max_x()
   {
      return (double)(sx/20);
   }
//---------------------------------------------------------
   public static double max_y()
   {
      return (double)(sy/20);
   }
//--------------------------------------------------------- 
  public static void czysc(Graphics g,Color kolor)

   {
     g.setColor(kolor);
     g.fillRect(0,0,2*(int)gr3d.sx,2*(int)gr3d.sy);
   }
//------------------------------------------------------
   public static void przerywana(Graphics g)
    {float przer1[] = {5.0f};
      BasicStroke przer = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
      BasicStroke.JOIN_ROUND, 5.0f, przer1, 0.0f);

      Graphics2D g2 = (Graphics2D)g;
      g2.setStroke(przer);          
    }      
//---------------------------------------------------------
   public static void ciagla(Graphics g,int gr)
    {BasicStroke normalna;
      
      Graphics2D g2 = (Graphics2D)g;
      normalna=new BasicStroke((float)gr);
      g2.setStroke(normalna);          
    }      
//---------------------------------------------------------
   public static void linia(Graphics g, pkt2d p,pkt2d k)
   {
      g.drawLine(cal_x(p.x),cal_y(p.y),cal_x(k.x),cal_y(k.y));
      k_g.war(k.x,k.y);
   }
//---------------------------------------------------------
   public static void kursor(Graphics g, pkt2d p)
   {
      k_g.war(p.x,p.y);
   }
//---------------------------------------------------------
   public static void kreska(Graphics g, pkt2d p)
   {
	    linia(g,k_g,p);
        k_g.war(p.x,p.y);
   }
//---------------------------------------------------------
