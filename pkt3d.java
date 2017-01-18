 package grafikaJava;
 public  class pkt3d
        {
	        public double x,y,z;
	        public pkt3d(double wx,double wy,double wz)
	           {
		           this.x=wx;this.y=wy;this.z=wz;
	           }
//------------------------------------------------------------------
          public  double dl()
          {
	          return(Math.sqrt(x*x+y*y+z*z));   
          } 	 
//------------------------------------------------------------------          
	        public void war(double nx,double ny, double nz)
	           {
		           x=nx; y=ny; z=nz;
	           }
//------------------------------------------------------------------	 
	        public  void ob_x( int kat )
         {
	         double fi=(double)kat*Math.PI/180;
          double yo=y;
          y = -z * Math.sin(fi) + yo * Math.cos(fi);
          z = z * Math.cos(fi) + yo * Math.sin(fi);
         }
//------------------------------------------------------------------
         public void ob_y( int kat )
	       {double fi=(double)kat*Math.PI/180;
          double xo = x;
          x = xo * Math.cos(fi) + z * Math.sin(fi);
          z = -xo * Math.sin(fi) + z * Math.cos(fi);
	       }
//------------------------------------------------------------------
         public void ob_z( float kat )
       	 {double fi=(double)kat*Math.PI/180;
         double xo = x;
         x = xo * Math.cos(fi)-y * Math.sin(fi);
         y = y * Math.cos(fi)+xo * Math.sin(fi);
	       }   
 }