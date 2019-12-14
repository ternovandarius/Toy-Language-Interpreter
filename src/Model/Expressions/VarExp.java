package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VarExp implements Exp{
	String id;
	
	public VarExp(String id) {
		this.id=id;
	}
	
	@Override
	public String toString(){
		return id;
	}
	
	@Override
	public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
		return tbl.lookup(id);
	}

	@Override
	public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return typeEnv.lookup(id);
	}

}
