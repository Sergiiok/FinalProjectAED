package model;

import java.util.ArrayList;

public class Company {
	
	
	String nombre;
	ArrayList<PlaceDelivery>  lugares;
	Employee empleado;
		
	
	public Company(String nombre,ArrayList<PlaceDelivery>  lugares) {
		this.nombre=nombre;
		this.lugares=lugares;
		empleado=new Employee("Sergio",lugares.size());
		
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ArrayList<PlaceDelivery> getLugares() {
		return lugares;
	}


	public void setLugares(ArrayList<PlaceDelivery> lugares) {
		this.lugares = lugares;
	}


	public Employee getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Employee empleado) {
		this.empleado = empleado;
	}
	
	
	
	
	
	
	
}
