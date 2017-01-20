import grafikaJava.*;
import java.awt.*;
import java.applet.*;

public class ostroslup extends Applet implements Runnable
{
    double kat=20*Math.PI/9,r=5,h=10,mx,ta=0.05;;
    pkt3d w[]=new pkt3d[7];
    int n=6;
    pkt2d obs=new pkt2d(5,5);
    Cursor pokaz=Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    Cursor przesun=Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    Cursor normalny=Cursor.getDefaultCursor();

    Scrollbar s_n,s_h,s_s;
    TextField p_n,p_h,p_s;

    Image strona;
    Graphics g_strony;

    Thread akcja;
    boolean ruch=true;
    boolean aktywna=true;
    //-------------------------------------------------------------
    public void init()
    {
        gr3d.init(getSize());
        setBackground(Color.white);  // lub jakikolwiek inny kolor
        mx=gr3d.max_x();
        komponenty();
        _3d.model(obs.x,obs.y);
        wierzcholki();
        strona=createImage(getWidth(),getHeight());
        g_strony=strona.getGraphics();
    }

    //---------------------------------------------------------
    void komponenty()
    {
        setLayout(null);
        s_n=new Scrollbar(0,n,1,3,7);
        s_n.setBounds(5,10,170,18);
        add(s_n);
        p_n=new TextField("Il krawedzi="+n);
        p_n.setBounds(176,10,90,18);
        p_n.setEnabled(false);
        add(p_n);

        s_h=new Scrollbar(0,(int)h,1,1,20);
        s_h.setBounds(5,30,170,18);
        add(s_h);
        p_h=new TextField("wysokosc="+h);
        p_h.setBounds(176,30,90,18);
        p_h.setEnabled(false);
        add(p_h);

        s_s=new Scrollbar(0,(int)ta,1,(int)ta,2);
        s_s.setBounds(5,50,170,18);
        add(s_s);
        p_s=new TextField("Wolno/Szybko");
        p_s.setBounds(176,50,90,18);
        p_s.setEnabled(false);
        add(p_s);

    }
    //-------------------------------------------------------
    double odl(pkt2d p, pkt2d k)
    {
        return Math.sqrt((p.x-k.x)*(p.x-k.x)+(p.y-k.y)*(p.y-k.y));
    }
    //---------------------------------------------------------
    void wierzcholki()
    {
        double k=2*Math.PI/n;
        for(int i=0;i<n;i++)
        {
            w[i]=new pkt3d(r*Math.cos(i*k),r*Math.sin(i*k),-h/2);
        }
        w[n]=new pkt3d(0,0,h/2);
    }
    //---------------------------------------------------------
    void uklad3d(Graphics g)
    {
        pkt3d xp=new pkt3d(-10,0,0),xk=new pkt3d(10,0,0),
                yp=new pkt3d(0,-10,0), yk=new pkt3d(0,10,0),
                zp=new pkt3d(0,0,-10), zk=new pkt3d(0,0,10);
        g.setColor(Color.gray);
        _3d.linia3d(g,xp,xk);
        _3d.linia3d(g,yp,yk);
        _3d.linia3d(g,zp,zk);

        _3d.zaznacz3d(g,xk);
        _3d.zaznacz3d(g,yk);
        _3d.zaznacz3d(g,zk);


    }
    //---------------------------------------------------------
    void rysuj(Graphics g)
    {
        for(int i=0;i<n;i++)
        {
            g.setColor(Color.black);
            _3d.linia3d(g,w[i],w[(i+1)%n]);  //podstawa dolna
            _3d.linia3d(g,w[i],w[n]);  //krawêdzie boczne
            _3d.zaznacz3d(g,w[i]);
        }
        _3d.zaznacz3d(g,w[n]);
        gr3d.oznacz(g,obs,"Obs");
    }
    //----------------------------------------------------------
    double vide(double a)
    {
        return a*Math.PI/mx;
    }
    //----------------------------------------------------
    public void start()
    {
        akcja=new Thread(this);
        akcja.start();
    }
    //----------------------------------------------
    public void stop()
    {
        akcja=null;

        ruch=false;
    }
    //----------------------------------------------
    public void run()
    {
        while(ruch)
        {
            obs.x-=0.01;
            if(obs.x<10)obs.war(obs.x-ta,obs.y);
            if(obs.x<-10) obs.x=10;

            obs.y-=0.01;
            if(obs.y<10)obs.war(obs.x,obs.y-ta);
            if(obs.y<-10) obs.y=10;

            _3d.model(vide(obs.x),vide(obs.y));

            repaint();
            try
            {
                Thread.sleep(20);
                synchronized(this)
                {
                    while(!aktywna) wait();
                }
            }
            catch(InterruptedException e){}
        }
    }
    //--------------------------------------------------------
    public boolean handleEvent(Event zd)
    {pkt2d m=gr3d.r2(zd.x,zd.y);
        if(zd.id ==Event.MOUSE_MOVE)
        {
            if(odl(m,obs)<0.75)setCursor(pokaz);
            else setCursor(normalny);
        }
        if(zd.id ==Event.MOUSE_DOWN)
        {
            if(odl(m,obs)<0.75)
            {
                setCursor(przesun);
                obs=gr3d.r2(zd.x,zd.y);
                _3d.model(obs.x/10,obs.y/10);
                repaint();
            }
        }
        if(zd.id ==Event.MOUSE_DRAG)
        {
            if(odl(m,obs)<0.75)
            {
                setCursor(przesun);
                obs=gr3d.r2(zd.x,zd.y);
                _3d.model(obs.x/10,obs.y/10);
                repaint();
            }
        }
        if(zd.target == s_n)
        {
            n = s_n.getValue();
            p_n.setText("Il krawedzi="+n);

            wierzcholki();
            repaint();
        }
        if(zd.target == s_h)
        {
            h = s_h.getValue();
            p_h.setText("wysokosc="+h);
            wierzcholki();
            repaint();
        }
        if(zd.target == s_s)
        {
            ta = s_s.getValue();
            p_s.setText("Wolno/Szybko");
            repaint();
        }
        return true;
    }
    //--------------------------------------------------
    public void update(Graphics g)
    {
        paint(g);
    }
    //----------------------------------------------------
    public void paint(Graphics g)
    {
        gr3d.czysc(g_strony,Color.white);
        uklad3d(g_strony);
        rysuj(g_strony);
        g.drawImage(strona,0,0,this);
    }
}