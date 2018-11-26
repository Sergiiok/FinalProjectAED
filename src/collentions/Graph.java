package collentions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph<T, N extends Number> {

	public static final int INFINITO=-1;
    private ArrayList<ArrayList<Edge<N>>> Adjacenciesmatrix;
    private HashMap<Integer, ArrayList<Vertex<T>>> AdjacenciesList;
    private ArrayList<Vertex<T>> vertex;
    private int edge;
    private int vertexn;
   

   
    
    public Graph() {
        this.Adjacenciesmatrix = new ArrayList<>();
        this.Adjacenciesmatrix.add(new ArrayList<Edge<N>>());
        this.AdjacenciesList = new HashMap<>();
        this.AdjacenciesList.put(0, new ArrayList<Vertex<T>>());
        this.vertex = new ArrayList<>();
        this.vertex.add(new Vertex());
        this.Adjacenciesmatrix.get(0).add(null);
        this.vertexn = 1;
       
    }


    public Graph(int n) throws IllegalArgumentException {
        this();
        if (n < 1) {
            throw new IllegalArgumentException("Error al crear grafo: n < 1");
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.vertex.add(new Vertex<T>());
                this.vertexn++;
                this.Adjacenciesmatrix.add(new ArrayList<Edge<N>>());
                this.AdjacenciesList.put(i, new ArrayList<Vertex<T>>());
               
            }
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0)) {
                    this.Adjacenciesmatrix.get(i).add(null);
                }
            }
        }
    }

    
    
   
         

   
   

   
    public ArrayList<ArrayList<Edge<N>>> getAdjacenciesmatrix() {
		return Adjacenciesmatrix;
	}


	public void setAdjacenciesmatrix(ArrayList<ArrayList<Edge<N>>> adjacenciesmatrix) {
		Adjacenciesmatrix = adjacenciesmatrix;
	}


	public HashMap<Integer, ArrayList<Vertex<T>>> getAdjacenciesList() {
		return AdjacenciesList;
	}


	public void setAdjacenciesList(HashMap<Integer, ArrayList<Vertex<T>>> adjacenciesList) {
		AdjacenciesList = adjacenciesList;
	}


	public ArrayList<Vertex<T>> getVertex() {
		return vertex;
	}


	public void setVertex(ArrayList<Vertex<T>> vertex) {
		this.vertex = vertex;
	}


	public int getEdge() {
		return edge;
	}


	public void setEdge(int edge) {
		this.edge = edge;
	}


	public int getVertexn() {
		return vertexn;
	}


	public void setVertexn(int vertexn) {
		this.vertexn = vertexn;
	}


	private boolean existeIdVertice(int id) {
        return id >= 0 && id < this.vertexn;
    }

   
    private boolean existeVertice(Vertex v) {
        return this.vertex.contains(v);
    }

    
    public int idVertice(Vertex v) throws IllegalArgumentException {
        if (!this.vertex.contains(v)) {
            throw new IllegalArgumentException("Error al consultar el identificador del vértice: el vértice no existe.");
        }

        return this.vertex.indexOf(v);
    }

   
   

    public ArrayList<Edge> aristasIncidentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar aristas incidentes: id de vértice no existe.");
        }
        ArrayList<Edge> aristas = new ArrayList<>();
        for (int j = 0; j < this.vertexn; j++) {
            if (id != j && this.adyacentes(id, j)) {
                aristas.add(this.arista(id, j));
            }
        }
        return aristas;
    }


    private ArrayList<Vertex> verticesAdyacentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar vértices adyacentes: id de vértice no existe.");
        }
        ArrayList<Vertex> vAd = new ArrayList<>();
        for (int j = 0; j < this.vertexn; j++) {
            if (id != j && this.adyacentes(id, j)) {
                vAd.add(this.vertice(j));
            }
        }
        return vAd;
    }

   
    public ArrayList<Vertex> verticesAdyacentes(Vertex v) throws IllegalArgumentException {
        if (!this.existeVertice(v)) {
            throw new IllegalArgumentException("Error al consultar vértices adyacentes: vértice no existe.");
        }
        return this.verticesAdyacentes(this.idVertice(v));
    }

   
    public ArrayList<Integer> camino(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en camino(int,int): algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en camino(int,int): deben ser identificadores diferentes.");
        }

        //BFS. Busco el camino de destino a origen para no tener que inveertir el array al final
        int[] predecesores = new int[this.vertexn];

        ArrayList<Vertex> Q = new ArrayList<>();
        boolean[] visitado = new boolean[this.vertexn];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        Vertex v = this.vertex.get(idDestino);
        Q.add(v);
        visitado[idDestino] = true;
        Vertex t = null;
        while (!Q.isEmpty() && idVertice(t = Q.get(0)) != idOrigen) {
            Q.remove(t);
            for (Vertex u : this.verticesAdyacentes(t)) {
                if (!visitado[this.idVertice(u)]) {
                    predecesores[this.idVertice(u)] = this.idVertice(t);
                    visitado[this.idVertice(u)] = true;
                    Q.add(u);
                }
            }
        }
        ArrayList<Integer> camino = new ArrayList<>();
        int id = idVertice(t);
        if (id == idOrigen) {
            while (id != idDestino) {
                camino.add(id);
                id = predecesores[id];
            }
            camino.add(id);
        }
        return camino;
    }

    
    public boolean conectaTodosVertices(ArrayList<Edge> aristas) throws IllegalArgumentException {
        ArrayList<Vertex> V = new ArrayList<>();
        int visitados = 0;
        for (int i = 0; i < aristas.size() && visitados < this.vertexn; i++) {
            Edge a = aristas.get(i);
            if (a == null) {
                throw new IllegalArgumentException("Error al comprobar arista: referencia nula a la arista.");
            }
            int idOrigen = a.getBegin();
            int idDestino = a.getEnd();
            if (idOrigen == idDestino) {
                throw new IllegalArgumentException("Error al comprobar arista: la arista tiene el mismo origen y destino.");
            }
            if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
                throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
            }

            Vertex v1 = this.vertice(idOrigen);
            Vertex v2 = this.vertice(idDestino);
            if (!V.contains(v1)) {
                V.add(v1);
                visitados++;
            }
            if (!V.contains(v2)) {
                V.add(v2);
                visitados++;
            }
        }
        return visitados == this.vertexn;
    }

    /**
     * Devuelve cierto si el grafo es completo, falso en caso contrario.
     *
     * @return Cierto si el grafo es completo, falso en caso contrario.
     */
    public boolean completo() {
        boolean esCompleto = true;
        for (int i = 0; i < this.vertexn && esCompleto; i++) {
            for (int j = i + 1; j < this.vertexn; j++) {
                if (this.Adjacenciesmatrix.get(i).get(j) == null) {
                    esCompleto = false;
                }
            }
        }
        return esCompleto;
    }

    /**
     * Devuelve cierto si el grafo es conexo, falso en caso contrario.
     *
     * @return Cierto si el grafo es conexo, falso en caso contrario.
     */
    public boolean conexo() {
        //BFS
        ArrayList<Vertex> Q = new ArrayList<>();
        Q.add(this.vertex.get(0));
        boolean[] visitado = new boolean[this.vertexn];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        visitado[0] = true;
        int countVis = 1;
        while (!Q.isEmpty()) {
        	Vertex t = Q.get(0);
            Q.remove(t);
            for (Vertex u : this.verticesAdyacentes(t)) {
                if (!visitado[this.idVertice(u)]) {
                    visitado[this.idVertice(u)] = true;
                    countVis++;
                    Q.add(u);
                }
            }
        }
        return countVis == this.vertexn;
    }

   
    
    public void anadirVertice(Vertex v) throws IllegalArgumentException {
        if (this.vertex.contains(v)) {
            throw new IllegalArgumentException("Error al añadir vértice: el vértice ya existe.");
        }
        this.vertex.add(v);
        this.Adjacenciesmatrix.add(new ArrayList<Edge<N>>());
        
        this.AdjacenciesList.put(this.vertexn, new ArrayList<Vertex<T>>());

        this.vertexn++;
        for (int i = 0; i < this.vertexn; i++) {
            int dif = this.vertexn - this.Adjacenciesmatrix.get(i).size();
            for (int j = 0; j < dif; j++) {
                this.Adjacenciesmatrix.get(i).add(null);
            }
        }
    }

   
    public void modificarVertice(int id, Vertex v) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al modificar vértice: identificador no válido.");
        }
        //Modificamos listas de adyacencias
        HashMap<Integer, ArrayList<Vertex<T>>> listas = this.AdjacenciesList;
        for (int i = 0; i < this.vertexn; i++) {
            ArrayList<Vertex<T>> lista = listas.get(i);
            if (i != id) {
            	Vertex<T> w = this.vertice(id);
                if (lista.contains(w)) {
                    lista.set(lista.indexOf(w), v);
                } else {
                    lista.add(v);
                }
            }
        }
      
        this.vertex.set(id, v);
    }

   
    public Vertex vertice(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar vértice: identificador no válido.");
        }
        return this.vertex.get(id);
    }

    
    public boolean adyacentes(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): deben ser identificadores diferentes.");
        }
        return this.Adjacenciesmatrix.get(idOrigen).get(idDestino) != null || this.Adjacenciesmatrix.get(idDestino).get(idOrigen) != null;
    }


    public Edge arista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al consultar una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al consultar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al consultar una arista: los vértices no son adyacentes.");
        }

        Edge a = this.Adjacenciesmatrix.get(idOrigen).get(idDestino);
        return a;
    }


    private void ponerArista(int idOrigen, int idDestino, Edge arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al poner una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        
        //Actualizar matriz de adyacencia
        arista.setBegin(idOrigen);
        arista.setEnd(idDestino);
        this.Adjacenciesmatrix.get(idOrigen).set(idDestino, arista);
        Edge inv = new Edge(arista);
        inv.setBegin(idDestino);
        inv.setEnd(idOrigen);
        this.Adjacenciesmatrix.get(idDestino).set(idOrigen, inv);
        //Actualizar listas de adyacencias
        ArrayList<Vertex<T>> listAdyOri = this.AdjacenciesList.get(idOrigen);
        ArrayList<Vertex<T>> listAdyDest = this.AdjacenciesList.get(idDestino);
        Vertex origen = this.vertice(idOrigen);
        Vertex destino = this.vertice(idDestino);
        if (!listAdyOri.contains(destino)) {
            listAdyOri.add(destino);
        }
        if (!listAdyDest.contains(origen)) {
            listAdyDest.add(origen);
        }

        this.edge++;
    }

    
    public void ponerArista(Edge arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(arista.getBegin()) && this.existeIdVertice(arista.getEnd()))) {
            throw new IllegalArgumentException("Error al poner una arista: algún vértice no existe.");
        }
        if (arista.getBegin() == arista.getEnd()) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        this.ponerArista(arista.getBegin(), arista.getEnd(), arista);
    }

   
    public void eliminarArista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al eliminar una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al eliminar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al eliminar una arista: la arista no existe.");
        }
       
        //Actualizar matriz de adyacencia
        this.Adjacenciesmatrix.get(idOrigen).set(idDestino, null);
        this.Adjacenciesmatrix.get(idDestino).set(idOrigen, null);
        //Actualizar listas de adyacencias
        ArrayList<Vertex<T>> listAdyOri = this.AdjacenciesList.get(idOrigen);
        ArrayList<Vertex<T>> listAdyDest = this.AdjacenciesList.get(idDestino);
        Vertex origen = this.vertice(idOrigen);
        Vertex destino = this.vertice(idDestino);
        listAdyOri.remove(destino);
        listAdyDest.remove(origen);

        this.edge--;
    }
    
    
    public int[][] PrimAlgoritm(int[][] Matriz) {  //Llega la matriz a la que le vamos a aplicar el algoritmo
        boolean[] marcados = new boolean[vertex.size()]; //Creamos un vector booleano, para saber cuales están marcados
        Vertex vertice = vertex.get(0); //Le introducimos un nodo aleatorio, o el primero
        return AlgPrim(Matriz, marcados, vertice, new int[Matriz.length][Matriz.length]); //Llamamos al método recursivo mandándole 
    }                                                                                     //un matriz nueva para que en ella nos 
                                                                                          //devuelva el árbol final
    private int[][] AlgPrim(int[][] Matriz, boolean[] marcados, Vertex vertice, int[][] Final) {
        marcados[vertex.indexOf(vertice)] = true;//marcamos el primer nodo
        int aux = -1;
        if (!TodosMarcados(marcados)) { //Mientras que no todos estén marcados
            for (int i = 0; i < marcados.length; i++) { //Recorremos sólo las filas de los nodos marcados
                if (marcados[i]) {
                    for (int j = 0; j < Matriz.length; j++) {
                        if (Matriz[i][j] != 0) {        //Si la arista existe
                            if (!marcados[j]) {         //Si el nodo no ha sido marcado antes
                                if (aux == -1) {        //Esto sólo se hace una vez
                                    aux = Matriz[i][j];
                                } else {
                                    aux = Math.min(aux, Matriz[i][j]); //Encontramos la arista mínima
                                }
                            }
                        }
                    }
                }
            }
            //Aquí buscamos el nodo correspondiente a esa arista mínima (aux)
            for (int i = 0; i < marcados.length; i++) {
                if (marcados[i]) {
                    for (int j = 0; j < Matriz.length; j++) {
                        if (Matriz[i][j] == aux) {
                            if (!marcados[j]) { //Si no ha sido marcado antes
                                Final[i][j] = aux; //Se llena la matriz final con el valor
                                Final[j][i] = aux;//Se llena la matriz final con el valor
                                return AlgPrim(Matriz, marcados, vertex.get(j), Final); //se llama de nuevo al método con
                                                                                               //el nodo a marcar
                            }
                        }
                    }
                }
            }
        }
        return Final;
    }
    public boolean TodosMarcados(boolean[] vertice) { //Método para saber si todos están marcados
        for (boolean b : vertice) {
            if (!b) {
                return b;
            }
        }
        return true;
    }
    public ArrayList<Vertex> dijkstraAlgoritm(Vertex origen, Vertex destino) {
		  ArrayList<Vertex> camino= new ArrayList<Vertex>();
		  int distancia=INFINITO;
		  Vertex nodo=origen;
		  boolean fin=true;
		  camino.add(nodo);
		  while(fin) {
			  
			  
			  if(nodo==destino) {
				  fin=false;
			  }
		  }
		  
		  return camino;
	  }
    
    
    
    
    
    
    
    
    
    
}