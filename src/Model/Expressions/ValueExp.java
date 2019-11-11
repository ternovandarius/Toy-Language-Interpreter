package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
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
	public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
		return e;
	}

}
