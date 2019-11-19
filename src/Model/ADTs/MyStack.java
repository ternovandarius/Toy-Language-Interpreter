package Model.ADTs;

import java.util.ListIterator;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

	Stack<T> stack = new Stack<T>();
	
	public String toString()
	{
		String msg="";
		ListIterator<T> iterator = stack.listIterator(stack.size());
		while(iterator.hasPrevious())
		{
			msg+=iterator.previous().toString();
			if(iterator.hasPrevious())
				msg+="\n";
		}
		return msg;
	}
	
	@Override
	public T pop() {
		return stack.pop();
	}

	@Override
	public void push(T v) {
		stack.push(v);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

}
