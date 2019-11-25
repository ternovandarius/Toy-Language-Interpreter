package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Values.RefValue;
import Model.Values.Value;
import Model.Types.IntType;
import Model.Types.Type;

public class readHeap implements Exp{

	Exp exp;
	
	public readHeap(Exp e)
	{
		exp=e;
	}
	
	@Override
	public String toString()
	{
		return "rH("+exp.toString()+")";
	}
	
	@Override
	public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
		
		Value val = exp.eval(tbl, hp);
		Type valType = val.getType();
		Type testType = new IntType();
		RefValue testVal = new RefValue(1, testType);
		if(!valType.equals(testVal.getType()))
		{
			throw new MyException("Invalid type!");
		}
		else
		{
			int addr=((RefValue)val).getAddr();
			if(!hp.containsKey(addr))
			{
				throw new MyException("Key not contained in heap!");
			}
			else
			{
				return hp.get(addr);
			}
		}
	}

}
