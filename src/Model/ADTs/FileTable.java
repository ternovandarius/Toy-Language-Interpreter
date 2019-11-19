package Model.ADTs;

import java.util.HashMap;

public class FileTable<T, V> implements MyITable<T, V>{

HashMap<T, V> dict = new HashMap<T, V>();
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		dict.clear();
	}
	
	public String toString()
	{
		return dict.toString();
	}

	@Override
	public boolean containsKey(T key) {
		// TODO Auto-generated method stub
		return dict.containsKey(key);
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return dict.containsValue(value);
	}

	@Override
	public V get(T key) {
		// TODO Auto-generated method stub
		return dict.get(key);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return dict.isEmpty();
	}

	@Override
	public V put(T key, V value) {
		// TODO Auto-generated method stub
		return dict.put(key, value);
	}

	@Override
	public V remove(T key) {
		// TODO Auto-generated method stub
		return dict.remove(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return dict.size();
	}

	@Override
	public V lookup(T id) {
		return this.get(id);
	}
	
	public void update(T id, V val)
	{
		this.dict.replace(id, val);
	}

}
