package model;

public class Package {
	
	
	
	boolean entregado;
	 public Package() {
		entregado=false;
	}
	 
	 
	 public boolean paqueteEntregado(boolean si) {
		 boolean entre=false;
		 if(si) {
			 entre=true;
			 
		 }
		 
		 return entre;
	 }
	 
	

}
