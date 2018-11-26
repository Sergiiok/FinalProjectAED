package collentions;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T, N extends Number> {

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

   
         

   
   

   
    private boolean existeIdVertice(int id) {
        return id >= 0 && id < this.vertexn;
    }

   
    private boolean existeVertice(Vertex v) {
        return this.vertex.contains(v);
    }

    
    public int idVertice(Vertex v) throws IllegalArgumentException {
        if (!this.vertex.contains(v)) {
            throw new IllegalArgumentException("Error al consultar el identificador del v�rtice: el v�rtice no existe.");
        }

        return this.vertex.indexOf(v);
    }

   
   

    public ArrayList<Edge> aristasIncidentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar aristas incidentes: id de v�rtice no existe.");
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
            throw new IllegalArgumentException("Error al consultar v�rtices adyacentes: id de v�rtice no existe.");
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
            throw new IllegalArgumentException("Error al consultar v�rtices adyacentes: v�rtice no existe.");
        }
        return this.verticesAdyacentes(this.idVertice(v));
    }

   
    public ArrayList<Integer> camino(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en camino(int,int): alg�n v�rtice no existe.");
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

    /**
     * Devuelve cierto si las aristas conectan todos los v�rtices del grafo.
     * Falso en caso contrario.
     *
     * @param aristas Las aristas.
     * @return Cierto si las aristas conectan todos los v�rtices del grafo.
     * Falso en caso contrario.
     */
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
                throw new IllegalArgumentException("Error al comprobar arista: alg�n v�rtice no existe.");
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

   
    /**
     * A�ade un nuevo v�rtice al grafo. El v�rtice no puede estar ya contenido
     * en el grafo.
     *
     * @param v El v�rtice que se a�adir� al grafo.
     */
    public void anadirVertice(Vertex v) throws IllegalArgumentException {
        if (this.vertex.contains(v)) {
            throw new IllegalArgumentException("Error al a�adir v�rtice: el v�rtice ya existe.");
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
            throw new IllegalArgumentException("Error al modificar v�rtice: identificador no v�lido.");
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
            throw new IllegalArgumentException("Error al consultar v�rtice: identificador no v�lido.");
        }
        return this.vertex.get(id);
    }

    /**
     * Devuelve cierto si los dos v�rtices con identificador idOrigen y
     * idDestino, respectivamente, son adyacentes. Falso en caso contrario.
     *
     * @param idOrigen El identificador de un v�rtice que existe en el grafo.
     * @param idDestino El identificador de un v�rtice que existe en el grafo.
     * @return Cierto si los dos v�rtices son adyacentes, falso en caso
     * contrario.
     */
    public boolean adyacentes(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): alg�n v�rtice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): deben ser identificadores diferentes.");
        }
        return this.Adjacenciesmatrix.get(idOrigen).get(idDestino) != null || this.Adjacenciesmatrix.get(idDestino).get(idOrigen) != null;
    }


    public Edge arista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al consultar una arista: alg�n v�rtice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al consultar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al consultar una arista: los v�rtices no son adyacentes.");
        }

        Edge a = this.Adjacenciesmatrix.get(idOrigen).get(idDestino);
        return a;
    }


    private void ponerArista(int idOrigen, int idDestino, Edge arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al poner una arista: alg�n v�rtice no existe.");
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
            throw new IllegalArgumentException("Error al poner una arista: alg�n v�rtice no existe.");
        }
        if (arista.getBegin() == arista.getEnd()) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        this.ponerArista(arista.getBegin(), arista.getEnd(), arista);
    }

   
    public void eliminarArista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al eliminar una arista: alg�n v�rtice no existe.");
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
}