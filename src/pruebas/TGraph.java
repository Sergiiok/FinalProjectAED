package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import collentions.Graph;
import collentions.Vertex;

class TGraph {
	
	Graph grafo;
	
	void escenario1() {
		grafo= new Graph<>(2);
	}

	@Test
	void test1() {
		escenario1();
		Vertex<String> v= new Vertex<String>("Cra 45");
		grafo.anadirVertice(v);
		
		assertTrue(v.getInfo().equals(((Vertex<String>) grafo.getVertex().get(0)).getInfo()));
	}
	
}
