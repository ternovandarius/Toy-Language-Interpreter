package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

import Exceptions.MyException;

public class MyLatchTable<T,V> implements MyILatchTable<T,V>{
	HashMap<Integer, V> dict = new HashMap<Integer, V>();
	private int addr=5;
	
	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		dict.clear();
	}
	
	@Override
	public synchronized int generateNext() {
		addr+=1;
		return addr;
	}
	
	@Override
	public synchronized void setContent(Map<Integer,V> m)
	{
		dict=(HashMap<Integer, V>) m;
	}
	
	@Override
	public synchronized HashMap<Integer, V> getContent()
	{
		return dict;
	}
	
	@Override
	public synchronized String toString()
	{
		return dict.toString();
	}

	@Override
	public synchronized boolean containsKey(T key) {
		return dict.containsKey(key);
	}

	@Override
	public synchronized boolean containsValue(V value) {
		return dict.containsValue(value);
	}

	@Override
	public synchronized V get(T key) {
		return dict.get(key);
	}

	@Override
	public synchronized boolean isEmpty() {
		return dict.isEmpty();
	}

	@Override
	public synchronized V put(V value) {
		V aux = dict.put(addr, value);
		this.generateNext();
		return aux;
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
	public synchronized void update(T id, V val) throws MyException
	{
		if(id instanceof Integer)
			this.dict.replace((Integer)id, val);
		else
			throw new MyException("Invalid key!");
	}

}
