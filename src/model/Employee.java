package model;

public class Employee {
	
	String nombre;
	boolean[] entregasRealizadas;
	
	 public Employee(String nombre,int s) {
		 this.nombre=nombre;
		 entregasRealizadas= new boolean[s];
	}

}
