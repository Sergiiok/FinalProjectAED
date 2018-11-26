package collentions;

public class Vertex<T> {
    private T informacion;
    
    /**
     *Constructor por defecto.
     */    
    public Vertex(){
        this.informacion = null;
    }

    /**
     *Constructor.
     * @param t Informaci�n T que contendr� el v�rtice creado.
     */     
    public Vertex(T t){
        this.informacion = t;
    }
    
    /**
     *Constructor copia.
     * @param original V�rtice original que se va a copiar.
     */     
    public Vertex(Vertex<T> original){
        if(original.informacion == null) this.informacion = null;
        else this.informacion = original.informacion;
    }
    
    /**
     *Devuelve la informaci�n T del v�rtice.
     * @return La informaci�n del v�rtice.
     */     
    public T info() throws IllegalArgumentException {
        if(this.informacion == null) throw new IllegalArgumentException("Error al consultar informacion de un v�rtice: referencia nula.");
        return this.informacion;
    }
    
     /**
     *Modifica la informaci�n T del v�rtice.
     * @param nuevaInfo La nueva informaci�n T asociada al v�rtice.
     */     
    public void modificarInfo(T nuevaInfo){
        this.informacion = nuevaInfo;
    }
    
}