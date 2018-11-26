package collentions;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T, N extends Number> {

    private ArrayList<ArrayList<Edge<N>>> matrizAdyacencias;
    private HashMap<Integer, ArrayList<Vertex<T>>> listasAdyacencias;
    private ArrayList<Vertex<T>> vertices;
    private int nAristas;
    private int nVertices;
    private ArrayList<Integer> grados;

    /**
     * Aumenta el grado de un v�rtice.
     *
     * @param id Identificador del v�rtice.
     */
    private void aumentarGrado(int id) throws IllegalArgumentException {
        if (!(this.existeIdVertice(id))) {
            throw new IllegalArgumentException("Error al aumentar grado: el v�rtice no existe.");
        }
        this.grados.set(id, this.grados.get(id) + 1);
    }

    /**
     * Disminuye el grado de un v�rtice.
     *
     * @param id Identificador del v�rtice.
     */
    private void disminuirGrado(int id) throws IllegalArgumentException {
        if (!(this.existeIdVertice(id))) {
            throw new IllegalArgumentException("Error al disminuir grado: el v�rtice no existe.");
        }
        this.grados.set(id, this.grados.get(id) - 1);
    }

    /**
     * Constructor por defecto. Crea un grafo con un v�rtice.
     */
    public Graph() {
        this.matrizAdyacencias = new ArrayList<>();
        this.matrizAdyacencias.add(new ArrayList<Edge<N>>());
        this.listasAdyacencias = new HashMap<>();
        this.listasAdyacencias.put(0, new ArrayList<Vertex<T>>());
        this.vertices = new ArrayList<>();
        this.vertices.add(new Vertex());
        this.matrizAdyacencias.get(0).add(null);
        this.nVertices = 1;
        this.grados = new ArrayList<>();
        this.grados.add(0);
    }

//    /**
//     * Contructor. Crea un grafo con el v�rtice v.
//     * @param v El v�rtice con el que se va a crear el grafo.
//     */
//    public Grafo(Vertice v) {
//        this();
//        this.vertices.set(0, v);
//    }
    /**
     * Constructor. Crea un grafo con n v�rtices.
     *
     * @param n N�mero de v�rtices que se crear�n en el grafo.
     * @throws IllegalArgumentException
     */
    public Graph(int n) throws IllegalArgumentException {
        this();
        if (n < 1) {
            throw new IllegalArgumentException("Error al crear grafo: n < 1");
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.vertices.add(new Vertex<T>());
                this.nVertices++;
                this.matrizAdyacencias.add(new ArrayList<Edge<N>>());
                this.listasAdyacencias.put(i, new ArrayList<Vertex<T>>());
                this.grados.add(0);
            }
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0)) {
                    this.matrizAdyacencias.get(i).add(null);
                }
            }
        }
    }

   
         

    /**
     * Devuelve la matriz de adyacencias del grafo.
     *
     * @return Devuelve la matriz de adyacencias del grafo.
     */
    public ArrayList<ArrayList<Edge<N>>> matrizAdyacencias() {
        return this.matrizAdyacencias;
    }

    /**
     * Devuelve las listas de adyacencias del grafo.
     *
     * @return Devuelve las listas de adyacencias del grafo.
     */
    public HashMap<Integer, ArrayList<Vertex<T>>> listasAdyacencias() {
        return this.listasAdyacencias;
    }

//     /**
//     * Devuelve la lista de adyacencias de un v�rtice.
//     * @param id El identificador del v�rtice
//     * @return Devuelve la lista de adyacencias de un v�rtice.
//     */        
//    public ArrayList<Vertice<T>> listaAdyacenciasVertice(int id) throws IllegalArgumentException {
//        if(!this.existeIdVertice(id)) throw new IllegalArgumentException("Error al consultar lista de adyacencias de un v�rtice: identificador no v�lido.");
//        return this.listasAdyacencias.get(id);   
//    }
    /**
     * Devuelve el n�mero de v�rtices del grafo.
     *
     * @return El n�mero de v�rtices del grafo.
     */
    public int numVertices() {
        return this.nVertices;
    }

    /**
     * Devuelve el n�mero de arsitas del grafo.
     *
     * @return El n�mero de aristas del grafo.
     */
    public int numAristas() {
        return this.nAristas;
    }

    /**
     * Determina si existe un v�rtice con identificador=id.
     *
     * @param id El identificador del v�rtice a comprobar.
     * @return Cierto si el grafo contiene un v�rtice con identificador=id,
     * falso en caso contrario.
     */
    private boolean existeIdVertice(int id) {
        return id >= 0 && id < this.nVertices;
    }

    /**
     * Determina si v est� contenido en el grafo.
     *
     * @param v El v�rtice a comprobar.
     * @return Cierto si el grafo contiene v, falso en caso contrario.
     */
    private boolean existeVertice(Vertice v) {
        return this.vertices.contains(v);
    }

    /**
     * Devuelve el id de un v�rtice contenido en el grafo.
     *
     * @param v El v�rtice cuyo identificador queremos conocer.
     * @return El id de v contenido en el grafo.
     */
    public int idVertice(Vertice v) throws IllegalArgumentException {
        if (!this.vertices.contains(v)) {
            throw new IllegalArgumentException("Error al consultar el identificador del v�rtice: el v�rtice no existe.");
        }

        return this.vertices.indexOf(v);
    }

    /**
     * Devuelve el grado de un v�rtice contenido en el grafo.
     *
     * @param id El identificador del v�rtice cuyo grado queremos conocer.
     * @return El grado del v�rtice.
     */
    public int gradoVertice(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar grado v�rtice: identificador no v�lido.");
        }
        return this.grados.get(id);
    }

//     /**
//     * Devuelve el grado de un v�rtice contenido en el grafo.
//     * @param v El v�rtice cuyo grado queremos conocer.
//     * @return El grado del v�rtice.
//     */
//    public int gradoVertice(Vertice v) throws IllegalArgumentException {
//        if(!this.vertices.contains(v)) throw new IllegalArgumentException("Error al consultar grado v�rtice: v�rtice no existe.");
//        return this.gradoVertice(this.vertices.indexOf(v));
//    }
    /**
     * Devuelve las aristas incidentes en un v�rtice del grafo.
     *
     * @param id El identificador del v�rtice.
     * @return Las aristas incidentes en v.
     */
    public ArrayList<Arista> aristasIncidentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar aristas incidentes: id de v�rtice no existe.");
        }
        ArrayList<Arista> aristas = new ArrayList<>();
        for (int j = 0; j < this.nVertices; j++) {
            if (id != j && this.adyacentes(id, j)) {
                aristas.add(this.arista(id, j));
            }
        }
        return aristas;
    }

//     /**
//     * Devuelve las aristas incidentes en un v�rtice del grafo.
//     * @param v El v�rtice.
//     * @return Las aristas incidentes en v.
//     */
//    public ArrayList<Arista> aristasIncidentes(Vertice v) throws IllegalArgumentException {
//        if(!this.existeVertice(v)) throw new IllegalArgumentException("Error al consultar aristas incidentes: v�rtice no existe.");
//        return this.aristasIncidentes(this.idVertice(v));
//    }
    /**
     * Devuelve los v�rtices adyacentes a un v�rtice del grafo.
     *
     * @param id El identificador del v�rtice.
     * @return Los v�rtices adyacentes a v.
     */
    private ArrayList<Vertice> verticesAdyacentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar v�rtices adyacentes: id de v�rtice no existe.");
        }
        ArrayList<Vertice> vAd = new ArrayList<>();
        for (int j = 0; j < this.nVertices; j++) {
            if (id != j && this.adyacentes(id, j)) {
                vAd.add(this.vertice(j));
            }
        }
        return vAd;
    }

    /**
     * Devuelve los v�rtices adyacentes a un v�rtice del grafo.
     *
     * @param v El v�rtice.
     * @return Los v�rtices adyacentes a v.
     */
    public ArrayList<Vertice> verticesAdyacentes(Vertice v) throws IllegalArgumentException {
        if (!this.existeVertice(v)) {
            throw new IllegalArgumentException("Error al consultar v�rtices adyacentes: v�rtice no existe.");
        }
        return this.verticesAdyacentes(this.idVertice(v));
    }

    /**
     * Devuelve uno de los caminos posibles con menor longitud entre dos
     * v�rtices. El camino ser� un array con los indentificadores de los
     * v�rtices que forman el camino. Si no existe camino, devuelve un array
     * vac�o.
     *
     * @param idOrigen Identificador de un v�rtice.
     * @param idDestino Identificador de un v�rtice.
     * @return Uno de los caminos posibles con menor longitud entre dos
     * v�rtices.
     */
    public ArrayList<Integer> camino(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en camino(int,int): alg�n v�rtice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en camino(int,int): deben ser identificadores diferentes.");
        }

        //BFS. Busco el camino de destino a origen para no tener que inveertir el array al final
        int[] predecesores = new int[this.nVertices];

        ArrayList<Vertice> Q = new ArrayList<>();
        boolean[] visitado = new boolean[this.nVertices];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        Vertice v = this.vertices.get(idDestino);
        Q.add(v);
        visitado[idDestino] = true;
        Vertice t = null;
        while (!Q.isEmpty() && idVertice(t = Q.get(0)) != idOrigen) {
            Q.remove(t);
            for (Vertice u : this.verticesAdyacentes(t)) {
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
    public boolean conectaTodosVertices(ArrayList<Arista> aristas) throws IllegalArgumentException {
        ArrayList<Vertice> V = new ArrayList<>();
        int visitados = 0;
        for (int i = 0; i < aristas.size() && visitados < this.nVertices; i++) {
            Arista a = aristas.get(i);
            if (a == null) {
                throw new IllegalArgumentException("Error al comprobar arista: referencia nula a la arista.");
            }
            int idOrigen = a.origen();
            int idDestino = a.destino();
            if (idOrigen == idDestino) {
                throw new IllegalArgumentException("Error al comprobar arista: la arista tiene el mismo origen y destino.");
            }
            if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
                throw new IllegalArgumentException("Error al comprobar arista: alg�n v�rtice no existe.");
            }

            Vertice v1 = this.vertice(idOrigen);
            Vertice v2 = this.vertice(idDestino);
            if (!V.contains(v1)) {
                V.add(v1);
                visitados++;
            }
            if (!V.contains(v2)) {
                V.add(v2);
                visitados++;
            }
        }
        return visitados == this.nVertices;
    }

    /**
     * Devuelve cierto si el grafo es completo, falso en caso contrario.
     *
     * @return Cierto si el grafo es completo, falso en caso contrario.
     */
    public boolean completo() {
        boolean esCompleto = true;
        for (int i = 0; i < this.nVertices && esCompleto; i++) {
            for (int j = i + 1; j < this.nVertices; j++) {
                if (this.matrizAdyacencias.get(i).get(j) == null) {
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
        ArrayList<Vertice> Q = new ArrayList<>();
        Q.add(this.vertices.get(0));
        boolean[] visitado = new boolean[this.nVertices];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        visitado[0] = true;
        int countVis = 1;
        while (!Q.isEmpty()) {
            Vertice t = Q.get(0);
            Q.remove(t);
            for (Vertice u : this.verticesAdyacentes(t)) {
                if (!visitado[this.idVertice(u)]) {
                    visitado[this.idVertice(u)] = true;
                    countVis++;
                    Q.add(u);
                }
            }
        }
        return countVis == this.nVertices;
    }

    /**
     * Devuelve cierto si el grafo es euleriano.
     *
     * @return Cierto si el grafo es euleriano. Falso en caso contrario.
     */
    public boolean euleriano() {
        for (int g : this.grados) {
            if (g % 2 == 1) {
                return false;
            }
        }
        return this.conexo();
    }

    /**
     * A�ade un nuevo v�rtice al grafo. El v�rtice no puede estar ya contenido
     * en el grafo.
     *
     * @param v El v�rtice que se a�adir� al grafo.
     */
    public void anadirVertice(Vertice v) throws IllegalArgumentException {
        if (this.vertices.contains(v)) {
            throw new IllegalArgumentException("Error al a�adir v�rtice: el v�rtice ya existe.");
        }
        this.vertices.add(v);
        this.matrizAdyacencias.add(new ArrayList<Arista<N>>());
        this.grados.add(0);
        this.listasAdyacencias.put(this.nVertices, new ArrayList<Vertice<T>>());

        this.nVertices++;
        for (int i = 0; i < this.nVertices; i++) {
            int dif = this.nVertices - this.matrizAdyacencias.get(i).size();
            for (int j = 0; j < dif; j++) {
                this.matrizAdyacencias.get(i).add(null);
            }
        }
    }

    /**
     * Modifica un v�rtice del grafo. El identificador debe cumplir: id>=0 y
     * id menor que el n�mero de v�rtices del grafo. 
     * @param id El identificador del v�rtice que va a ser sustituido.
     * @param v El v�rtice que sustituir� al original.
     */
    public void modificarVertice(int id, Vertice v) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al modificar v�rtice: identificador no v�lido.");
        }
        //Modificamos listas de adyacencias
        HashMap<Integer, ArrayList<Vertice<T>>> listas = this.listasAdyacencias;
        for (int i = 0; i < this.nVertices; i++) {
            ArrayList<Vertice<T>> lista = listas.get(i);
            if (i != id) {
                Vertice<T> w = this.vertice(id);
                if (lista.contains(w)) {
                    lista.set(lista.indexOf(w), v);
                } else {
                    lista.add(v);
                }
            }
        }
        //////
        this.vertices.set(id, v);
    }

    /**
     * Devuelve el v�rtice con identificador=id. El identificador debe cumplir:
     * id>=0 y id menor que el n�mero de v�rtices del grafo.
     * @param id El identificador del v�rtice que va a ser consultado.
     * @return El v�rtice solicitado.
     */
    public Vertice vertice(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar v�rtice: identificador no v�lido.");
        }
        return this.vertices.get(id);
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
        return this.matrizAdyacencias.get(idOrigen).get(idDestino) != null || this.matrizAdyacencias.get(idDestino).get(idOrigen) != null;
    }

//     /**
//     * Devuelve cierto si los v�rtices son adyacentes. Falso en caso contrario.
//     * @param origen Un v�rtice que existe en el grafo.
//     * @param destino Un v�rtice que existe en el grafo.
//     * @return Cierto si los dos v�rtices son adyacentes, falso en caso contrario.
//     */   
//    public boolean adyacentes(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.vertices.contains(origen) && this.vertices.contains(destino))) 
//            throw new IllegalArgumentException("Error en adyacentes(Vertice,Vertice): alg�n v�rtice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error en adyacentes(Vertice,Vertice): deben ser v�rtices diferentes.");
//        int idOrigen = this.vertices.indexOf(origen);
//        int idDestino = this.vertices.indexOf(destino);
//        return this.adyacentes(idOrigen, idDestino);
//    }
//     /**
//     * Devuelve cierto si la arista est� presente en el grafo. Falso en caso contrario.
//     * @param idOrigen Identificador del v�rtice origen de la arista.
//     * @param idDestino Identificador del v�rtice destino de la arista.
//     * @return Cierto si la arista est� presente en el grafo. Falso en caso contrario.
//     */     
//    public boolean existeArista(int idOrigen, int idDestino) throws IllegalArgumentException {
//        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
//            throw new IllegalArgumentException("Error al comprobar arista: alg�n v�rtice no existe.");
//        if(idOrigen==idDestino) throw new IllegalArgumentException("Error al comprobar arista: deben ser identificadores diferentes.");
//        return this.matrizAdyacencias.get(idOrigen).get(idDestino)!=null || this.matrizAdyacencias.get(idDestino).get(idOrigen)!=null;
//    }
//     /**
//     * Devuelve cierto si la arista est� presente en el grafo. Falso en caso contrario.
//     * @param origen V�rtice origen de la arista.
//     * @param destino V�rtice destino de la arista.
//     * @return Cierto si la arista est� presente en el grafo. Falso en caso contrario.
//     */  
//    public boolean existeArista(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.existeVertice(origen)&& this.existeVertice(destino))) 
//            throw new IllegalArgumentException("Error al comprobar arista: alg�n v�rtice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error al comprobar arista: deben ser v�rtices diferentes.");
//        int idOrigen = this.vertices.indexOf(origen);
//        int idDestino = this.vertices.indexOf(destino);
//        return existeArista(idOrigen, idDestino);
//    }
    /**
     * Devuelve la arista incidente en los v�rtices con idOrigen y idDestino.
     *
     * @param idOrigen Indentificador del v�rtice origen incidente en la arista.
     * @param idDestino Indentificador del v�rtice destino incidente en la
     * arista.
     * @return Arista incidente en origen y destino.
     */
    public Arista arista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al consultar una arista: alg�n v�rtice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al consultar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al consultar una arista: los v�rtices no son adyacentes.");
        }

        Arista a = this.matrizAdyacencias.get(idOrigen).get(idDestino);
        return a;
    }

//     /**
//     * Devuelve una arista incidente en los v�rtices origen y destino. 
//     * @param origen V�rtice origen incidente en la arista.
//     * @param destino V�rtice destino incidente en la arista.
//     * @return Arista incidente en origen y destino.
//     */
//    public Arista arista(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.existeVertice(origen) && this.existeVertice(destino))) 
//            throw new IllegalArgumentException("Error al consultar una arista: alg�n v�rtice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error al consultar una arista: deben ser v�rtices diferentes.");
//        if(!this.adyacentes(origen, destino)) 
//            throw new IllegalArgumentException("Error al consultar una arista: los v�rtices no son adyacentes.");
//        return this.arista(this.idVertice(origen), this.idVertice(destino));
//    }
    /**
     * Pone una arista en el grafo que incidir� en los v�rtices idOrigen y
     * idDestino. Si ya hay una, la sustituye.
     *
     * @param idOrigen Identificador de un v�rtice.
     * @param idDestino Identificador de un v�rtice.
     * @param arista Arista que se va a a�adir al grafo.
     */
    private void ponerArista(int idOrigen, int idDestino, Arista arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al poner una arista: alg�n v�rtice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            this.aumentarGrado(idOrigen);
            this.aumentarGrado(idDestino);
        }
        //Actualizar matriz de adyacencia
        arista.modificarOrigen(idOrigen);
        arista.modificarDestino(idDestino);
        this.matrizAdyacencias.get(idOrigen).set(idDestino, arista);
        Arista inv = new Arista(arista);
        inv.modificarOrigen(idDestino);
        inv.modificarDestino(idOrigen);
        this.matrizAdyacencias.get(idDestino).set(idOrigen, inv);
        //Actualizar listas de adyacencias
        ArrayList<Vertice<T>> listAdyOri = this.listasAdyacencias.get(idOrigen);
        ArrayList<Vertice<T>> listAdyDest = this.listasAdyacencias.get(idDestino);
        Vertice origen = this.vertice(idOrigen);
        Vertice destino = this.vertice(idDestino);
        if (!listAdyOri.contains(destino)) {
            listAdyOri.add(destino);
        }
        if (!listAdyDest.contains(origen)) {
            listAdyDest.add(origen);
        }

        this.nAristas++;
    }

    /**
     * Pone una arista en el grafo que incidir� en los v�rtices arista.origen()
     * y arista.destino(). Si ya hay una, la sustituye.
     *
     * @param arista Arista que se va a a�adir al grafo.
     */
    public void ponerArista(Arista arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(arista.origen()) && this.existeIdVertice(arista.destino()))) {
            throw new IllegalArgumentException("Error al poner una arista: alg�n v�rtice no existe.");
        }
        if (arista.origen() == arista.destino()) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        this.ponerArista(arista.origen(), arista.destino(), arista);
    }

    /**
     * Eliminina una arista del grafo.
     *
     * @param idOrigen Identificador del extremo origen de la arista.
     * @param idDestino Identificador del extremo destino de la arista.
     */
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
        if (this.adyacentes(idOrigen, idDestino)) {
            this.disminuirGrado(idOrigen);
            this.disminuirGrado(idDestino);
        }
        //Actualizar matriz de adyacencia
        this.matrizAdyacencias.get(idOrigen).set(idDestino, null);
        this.matrizAdyacencias.get(idDestino).set(idOrigen, null);
        //Actualizar listas de adyacencias
        ArrayList<Vertice<T>> listAdyOri = this.listasAdyacencias.get(idOrigen);
        ArrayList<Vertice<T>> listAdyDest = this.listasAdyacencias.get(idDestino);
        Vertice origen = this.vertice(idOrigen);
        Vertice destino = this.vertice(idDestino);
        listAdyOri.remove(destino);
        listAdyDest.remove(origen);

        this.nAristas--;
    }
}