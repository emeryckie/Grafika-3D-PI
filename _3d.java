 package grafikaJava;
 import java.awt.*;
 public  class _3d
 {
	        public static double alfa,fi,sa,ca,sf,cf;
	        public static pkt3d ob;
	        
//---------------------------------------------------------
public static void model(double a,double f)
	{
	 alfa=a;fi=f;
	 sa=Math.sin(alfa);
	 ca=Math.cos(alfa);
	 sf=Math.sin(fi);
	 cf=Math.cos(fi);
	 ob= new pkt3d(sa*cf,cf*ca,sf);
	}
//----------------------------------------------------------
public static pkt2d rzut(pkt3d p)
	{
			return new pkt2d(p.y*sa+p.x*ca,(p.y*ca-p.x*sa)*sf+p.z*cf);
	}
//----------------------------------------------------------
public static void linia3d(Graphics g ,pkt3d p, pkt3d k)
	{
		gr3d.linia(g,rzut(p),rzut(k));
	}
//----------------------------------------------------------
public static void zaznacz3d(Graphics g ,pkt3d p)
	{
		gr3d.zaznacz(g,rzut(p),Color.red);
	}
//----------------------------------------------------------
 
 }