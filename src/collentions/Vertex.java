package collentions;

public class Vertex<T> {
    private T info;
    
       
    public Vertex(){
        this.info = null;
    }

        
    public Vertex(T info){
        this.info = info;
    }
    
        
    public Vertex(Vertex<T> origin){
        if(origin.info == null) this.info = null;
        else this.info = origin.info;
    }


	public T getInfo() {
		return info;
	}


	public void setInfo(T info) {
		this.info = info;
	}
    
    
   
}