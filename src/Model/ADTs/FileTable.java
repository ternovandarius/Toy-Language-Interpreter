package Model.ADTs;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FileTable<T, V> implements MyITable<T, V>{

HashMap<T, V> dict = new HashMap<T, V>();
	
	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		dict.clear();
	}
	
	@Override
	public synchronized String toString()
	{
		return dict.toString();
	}

	@Override
	public synchronized boolean containsKey(T key) {
		// TODO Auto-generated method stub
		return dict.containsKey(key);
	}

	@Override
	public synchronized boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return dict.containsValue(value);
	}

	@Override
	public synchronized V get(T key) {
		// TODO Auto-generated method stub
		return dict.get(key);
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return dict.isEmpty();
	}

	@Override
	public synchronized V put(T key, V value) {
		// TODO Auto-generated method stub
		return dict.put(key, value);
	}

	@Override
	public synchronized V remove(T key) {
		// TODO Auto-generated method stub
		return dict.remove(key);
	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return dict.size();
	}

	@Override
	public synchronized V lookup(T id) {
		return this.get(id);
	}
	
	@Override
	public synchronized void update(T id, V val)
	{
		this.dict.replace(id, val);
	}

	@Override
	public Set<T> getKeys() {
		return this.dict.keySet();
	}

}
