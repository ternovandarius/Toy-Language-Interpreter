package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.Value;
import Model.Types.*;

public class LogicExp implements Exp{
	Exp e1;
	Exp e2;
	int op; //1&&, 2||
	char cop;
	
	public LogicExp(char c, Exp ex1, Exp ex2)
	{
		e1=ex1;
		e2=ex2;
		cop=c;
		switch(c)
		{
		case '&':
			op=1;
			break;
		case '|':
			op=2;
			break;
		}
	}
	
	@Override
	public String toString()
	{
		return e1.toString()+cop+e2.toString();
	}
	
	@Override
	public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
		Value v1, v2;
		v1=e1.eval(tbl, hp);
		if(v1.getType().equals(new BoolType())) {
			v2=e2.eval(tbl, hp);
			if(v2.getType().equals(new BoolType())) {
				boolean n1, n2;
				BoolValue b1=(BoolValue)v1;
				BoolValue b2=(BoolValue)v2;
				n1=b1.getValue();
				n2=b2.getValue();
				if(op==1)
					return new BoolValue(n1 && n2);
				else if (op==2)
					return new BoolValue(n1 || n2);
				else
					throw new MyException("Invalid operation!");
			}
			else {
				throw new MyException("Second operand is not boolean!");
			}
		}
		else {
			throw new MyException("First operand is not boolean!");
		}
	}

}
