package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.IntType;
import Model.Values.*;

public class ArithExp implements Exp{
	Exp e1;
	Exp e2;
	int op; //1+; 2-; 3*; 4/;
	char cop;
	
	public ArithExp(char c, Exp ex1, Exp ex2)
	{
		e1=ex1;
		e2=ex2;
		cop=c;
		switch(c)
		{
		case '+':
			op=1;
			break;
		case '-':
			op=2;
			break;
		case '*':
			op=3;
			break;
		case '/':
			op=4;
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
		if(v1.getType().equals(new IntType()))
		{
			v2=e2.eval(tbl, hp);
			if(v2.getType().equals(new IntType()))
			{
				IntValue i1= (IntValue)v1;
				IntValue i2 =(IntValue)v2;
				int n1, n2;
				n1=i1.getVal();
				n2=i2.getVal();
				if(op==1)
					return new IntValue(n1+n2);
				else if(op==2)
					return new IntValue(n1-n2);
				else if(op==3)
					return new IntValue(n1*n2);
				else if(op==4) {
					if(n2==0)
						throw new MyException("Cannot divide by zero!");
					else
						return new IntValue(n1/n2);
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
