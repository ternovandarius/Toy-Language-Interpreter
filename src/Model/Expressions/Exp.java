package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
	Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;
	Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
	@Override
	public String toString();
}
