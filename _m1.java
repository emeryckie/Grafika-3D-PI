 package grafikaJava;
 import java.awt.*;
 public  class _m1
 {
  public static double sk,fi,sf,cf;
  public static void model(double skala,double kat)
	{
		sk=skala;
		fi=kat;
		
		sf=Math.sin(fi);
		cf=Math.cos(fi);
	}
	public static pkt2d rzut(pkt3d p)
	{
		return new pkt2d(p.x+p.y*sk*cf,p.z+p.y*sk*sf);
	}
	public static void linia3d(Graphics g,pkt3d p,pkt3d k)
	{
		gr3d.linia(g,rzut(p),rzut(k));
	}
	public static void zaznacz3d(Graphics g,pkt3d p)
	{
		gr3d.zaznacz(g,rzut(p),Color.red);
	}

 }