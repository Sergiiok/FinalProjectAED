package collentions;

public class Edge<N extends Number> implements Comparable {

    private int begin;
    private int end;
    private N weight;

   
    public Edge() {
        this.begin = -1;
        this.end = -2;
        this.weight = null;
    }

 
  
    public Edge(int begin, int end, N weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

  
    public Edge(Edge<N> origin) {
        this.begin = origin.begin;
        this.end = origin.end;
        if (origin.weight == null) {
            this.weight = null;
        } else {
            this.weight = origin.weight;
        }
    }

    
  
   


    public int getBegin() {
		return begin;
	}



	public void setBegin(int begin) {
		this.begin = begin;
	}



	public int getEnd() {
		return end;
	}



	public void setEnd(int end) {
		this.end = end;
	}



	public N getWeight() {
		return weight;
	}



	public void setWeight(N weight) {
		this.weight = weight;
	}



	@Override
    public int compareTo(Object o) {
    	Edge<N> a = (Edge<N>)o;
        int res = 0;
        Double weight1 = (Double) this.getWeight();
        Double weight2 = (Double) a.getWeight();
        if (weight1 < weight2) {
            res = -1;
        } else if (weight1 == weight2) {
            res = 0;
        } else if (weight1 > weight2) {
            res = 1;
        }
        return res;
    }
}
