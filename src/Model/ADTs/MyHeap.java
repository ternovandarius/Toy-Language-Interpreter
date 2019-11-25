package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

import Exceptions.MyException;

public class MyHeap<T,V> implements MyIHeap<T,V>{
HashMap<Integer, V> dict = new HashMap<Integer, V>();
static int addr=1;

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		dict.clear();
	}
	
	public void setContent(Map<Integer,V> m)
	{
		dict=(HashMap<Integer, V>) m;
	}
	
	public HashMap<Integer, V> getContent()
	{
		return dict;
	}
	
	public int getLastPos()
	{
		return addr-1;
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
	public V put(V value) {
		// TODO Auto-generated method stub
		Integer a= Integer.valueOf(addr);
		addr+=1;
		return dict.put(a, value);
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
	
	public void update(T id, V val) throws MyException
	{
		if(id instanceof Integer)
			this.dict.replace((Integer)id, val);
		else
			throw new MyException("Invalid key!");
	}

}
