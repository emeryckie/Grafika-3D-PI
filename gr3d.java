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
	
   public static void okrag(Graphics g, pkt2d a,double r)	
   {
      g.drawOval(cal_x(a.x-r),cal_y(a.y+r),(int)(40D*r),(int)(40D*r));
   }
//---------------------------------------------------------  
   public static void prostokat(Graphics g, pkt2d w,double dl,double sz)
   {	   
	   g.drawRect(cal_x(w.x),cal_y(w.y),(int)(20*dl),(int)(20*sz));
   }
//---------------------------------------------------------   
   public static void panel(Graphics g,pkt2d w,double dl,double sz,Color kolor)
   {
	       g.setColor(kolor);
	       g.fill3DRect(cal_x(w.x),cal_y(w.y),(int)(20*dl),(int)(20*sz),true);
   }
//---------------------------------------------------------   
   public static void barek(Graphics g,pkt2d w,double dl,double sz,Color kolor)
   {
	       g.setColor(kolor);
	       g.fillRect(cal_x(w.x)+1,cal_y(w.y)+1,(int)(20*dl)-1,(int)(20*sz)-1);
	       //g.setColor(Color.black);
	       //g.drawRect(cal_x(w.x),cal_y(w.y),(int)(20*dl)-1,(int)(20*sz)-1);
   }
//--------------------------------------------------------- 
   public static void kropka(Graphics g,pkt2d p,Color kolor)
   {double r=0.1;
	   g.setColor(kolor);
	   g.fillOval(cal_x(p.x-r),cal_y(p.y+r),(int)(40D*r),(int)(40D*r));
	   g.setColor(Color.gray);
	   okrag(g,p,r);
	}
 //--------------------------------------------------------- 
 public static void zaznacz(Graphics g,pkt2d p,Color kolor)
   {double r=0.1;
	   g.setColor(kolor);
	   g.fillOval(cal_x(p.x-r),cal_y(p.y+r),(int)(40D*r),(int)(40D*r));	   
	}
 //---------------------------------------------------------
   public static void czekaj(int ile)
   {
	   try {Thread.sleep(ile);} catch (InterruptedException Ie) {}
  }
//-----------------------------------------------------------  
   public static void czcionka(Graphics g,int typ,int styl,int wys)
   {String kroj[]=
	    {
		    "Serif","SansSerif","Monospaced","Dialog","DialogInput",
		    "Lucida Sans","Lucida Bright","Lucida Sans Typewriter",
		    "Lucida Sans Unicode","Lucida Console"
	    };
	   int jaki_styl[]=
	      {
		      Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD + Font.ITALIC,
	      };
	       Font font=new Font(kroj[typ],jaki_styl[styl],wys);
	       g.setFont(font);     
 }
//--------------------------------------------------------- 
   public static void pisz(Graphics g,pkt2d p,String zd)
   {
	   g.drawString(zd,cal_x(p.x),cal_y(p.y));
	}
 //--------------------------------------------------------- 
 public static void oznacz(Graphics g,pkt2d x,String zd)
   {
	  gr3d.kropka(g,x,Color.red);
    g.setColor(Color.black);
		gr3d.pisz(g,gr3d.p2d(x.x+0.2,x.y+0.2),zd);
  }
//---------------------------------------------------------------------------
  public static String Tostr(double x,int ile)
   {String zd=new String();
    double pom=Math.pow(10D,ile);
    x=Math.ceil(pom*x)/pom;
   return(zd.valueOf(x));
 }
//----------------------------------------------
  public static void wspolrzedne(Graphics g,pkt2d x,String zd)
   {zd=zd+"=("+Tostr(x.x,2)+","+Tostr(x.y,2)+")";
	  oznacz(g,x,zd);
  }
//---------------------------------------------------------------------------
 public static void  punkt(Graphics g,pkt2d p)
   {
      g.drawRect(cal_x(p.x),cal_y(p.y),0,0); 
      k_g.war(p.x,p.y);          
   }
//---------------------------------------------------------
 public static void  punkt_(Graphics g,pkt2d p)
   {
      g.drawRect(cal_x(p.x),cal_y(p.y),1,1); 
      k_g.war(p.x,p.y);          
   }
//---------------------------------------------------------
public static void wycinek(Graphics g,pkt2d w,double pr,double k_st,double k_k)
  {double fi,xp,yp,dt;
      dt=0.05/pr; 
      for(fi=k_st;fi<k_k;fi+=dt)
        {
	        if(fi>=k_k) fi=k_k-dt;
	        xp=w.x+pr*Math.cos(fi);
	        yp=w.y+pr*Math.sin(fi);
	        punkt(g,p2d(xp,yp));
        }        
   }
//--------------------------------------------------------- 
   public static void kolorPiora(Graphics g,Color kolor)
   {
	   g.setColor(kolor);
   }
//--------------------------------------------------------- 
   public static void obramowanie(Graphics g)
   {
     g.setColor(Color.gray);
		g.drawRect(0,0,(int)(2*sx)-1,(int)(2*sy)-1);
   }
//---------------------------------------------------------   
   public static void uklad(Graphics g)
   { int i;	   
      double mx=max_x();
      double my=max_y();
      g.setColor(Color.gray);
      linia(g,p2d(-mx,0),p2d(mx,0));
      linia(g,p2d(0,-my),p2d(0,my));
 		
		linia(g, p2d(mx-0.25,-0.25),p2d(mx,0));
		linia(g, p2d(mx-0.25,0.25),p2d(mx,0));
		linia(g, p2d(mx-0.25,-0.25),p2d(mx-0.25,0.25));
		
		linia(g, p2d(0.25, my-0.25), p2d(0, my));
		linia(g, p2d(-0.25, my-0.25), p2d(0, my));
		linia(g, p2d(-0.25, my-0.25),p2d(0.25, my-0.25));	
							
     	g.setColor(Color.white);     
      for( i = (int)-mx; i < (int)mx; i++ ) punkt(g,p2d(i, 0));
      
      for( i = (int)-my; i < (int)my; i++ ) punkt(g,p2d( 0, i));
      
	   g.setColor(Color.black);             
     }
//---------------------------------------------------------
       
    }
