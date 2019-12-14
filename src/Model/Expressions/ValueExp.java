package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp{
	Value e;
	
	public ValueExp(Value val)
	{
		e=val;
	}
	
	@Override
	public String toString()
	{
		return e.toString();
	}
	
	@Override
	public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
		return e;
	}

	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return e.getType();
	}

}
