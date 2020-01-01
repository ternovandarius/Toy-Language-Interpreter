package Model.ADTs;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {

	List<T> list = new ArrayList<T>();
	
	@Override
	public synchronized void add(int index, T elem) {
		// TODO Auto-generated method stub
		list.add(index, elem);
	}
	
	@Override
	public synchronized String toString()
	{
		//return list.toString();
		String msg="";
		int i=list.size()-1;
		for(i=list.size()-1; i>=0; i--)
		{
			msg+=list.get(i).toString()+"\n";
		}
		return msg;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	@Override
	public synchronized T get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public synchronized boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public synchronized boolean contains(T element) {
		// TODO Auto-generated method stub
		return list.contains(element);
	}

	@Override
	public synchronized boolean remove(T element) {
		// TODO Auto-generated method stub
		return list.remove(element);
	}

	@Override
	public synchronized int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
