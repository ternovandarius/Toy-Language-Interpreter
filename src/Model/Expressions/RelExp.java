package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelExp implements Exp{

	Exp e1;
	Exp e2;
	String rel;
	
	public RelExp(Exp exp1, Exp exp2, String relation)
	{
		e1=exp1;
		e2=exp2;
		rel=relation;
	}
	
	@Override
	public String toString()
	{
		return e1.toString()+rel+e2.toString();
	}
	
	@Override
	public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
		Value v1, v2;
		v1=e1.eval(tbl);
		if(v1.getType().equals(new IntType()))
		{
			v2=e2.eval(tbl);
			if(v2.getType().equals(new IntType()))
			{
				IntValue i1= (IntValue)v1;
				IntValue i2 =(IntValue)v2;
				int n1, n2;
				n1=i1.getVal();
				n2=i2.getVal();
				if(rel=="<")
				{
					return new BoolValue(n1<n2);
				}
				else if(rel==">")
				{
					return new BoolValue(n1>n2);
				}
				else if(rel=="<=")
				{
					return new BoolValue(n1<=n2);
				}
				else if(rel==">=")
				{
					return new BoolValue(n1>=n2);
				}
				else if(rel=="==")
				{
					return new BoolValue(n1==n2);
				}
				else if(rel=="!=")
				{
					return new BoolValue(n1!=n2);
				}
				else
					throw new MyException("Invalid operation!");
			}
			else {
				throw new MyException("Second operand is not an integer!");
			}
		}
		else {
			throw new MyException("First operand is not an integer!");
		}
	}
}
