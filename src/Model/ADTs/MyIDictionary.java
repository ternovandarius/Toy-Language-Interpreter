package Model.ADTs;

public interface MyIDictionary<T, V> {

	void clear();
	boolean containsKey(T key);
	boolean containsValue(V value);
	V get(T key);
	boolean isEmpty();
	V put(T key, V value);
	V remove(T key);
	int size();
	V lookup(T id);
	void update(T id, V val);

}
