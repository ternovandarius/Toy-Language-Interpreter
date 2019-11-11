package Model.Expressions;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
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
	public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
		return tbl.lookup(id);
	}

}
