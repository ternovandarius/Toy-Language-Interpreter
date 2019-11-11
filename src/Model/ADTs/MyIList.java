package Model.ADTs;

public interface MyIList<T> {
	
	void add(int index, T elem);
	
	void clear();
	
	T get(int index);
	
	boolean isEmpty();
	
	boolean contains(T element);
	
	boolean remove(T element);
	
	int size();
}
