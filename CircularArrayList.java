import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;


public class CircularArrayList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;
	
	public CircularArrayList() {
		super();
		this.index = 0;
	}
	
	public CircularArrayList(int i){
		super(i);
		this.index = 0;
	}
	
	public void setIndex(int i){
		this.index = i;
	}
	public int getIndex(){
		return index;
	}
	
	//this will make the for loop work with a circular arraylist
	@Override
	public Iterator<E> iterator(){ 
		return new Iterator<E>(){
			int count = size();
			public void forEachRemaining(Consumer arg0) {
				// TODO Auto-generated method stub
				
			}

			public boolean hasNext() {
				return(count > 0);
			}

			public E next() {
				count--;
				if(index == size()){
					index = 0;
					return get(index++);
				}else{
					return get(index++);
				}
			}

			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
	}
	
}
