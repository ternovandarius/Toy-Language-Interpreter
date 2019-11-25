package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

import Exceptions.MyException;

public interface MyIHeap<T, V> {

	void clear();
	boolean containsKey(T key);
	boolean containsValue(V value);
	V get(T key);
	boolean isEmpty();
	V put(V value);
	V remove(T key);
	int size();
	V lookup(T id);
	void update(T id, V val) throws MyException;
	int getLastPos();
	HashMap<Integer, V> getContent();
	void setContent(Map<Integer, V> map);
}