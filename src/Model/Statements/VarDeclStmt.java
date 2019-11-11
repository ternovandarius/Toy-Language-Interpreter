package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{

	String name;
	Type typ;
	
	public VarDeclStmt(String n, Type t) {
		name=n;
		typ=t;
	}
	
	@Override
	public String toString()
	{
		return typ.toString()+" "+name;
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIDictionary<String, Value> symTbl = state.getTable();
		if(symTbl.containsKey(name))
		{
			throw new MyException("Variable is already declared! ");
		}
		else {
			IntType intTest = new IntType();
			if (typ.equals(intTest))
			{
				IntValue intVal = new IntValue(0);
				symTbl.put(name, intVal);
			}
			else {
				BoolValue boolVal = new BoolValue(false);
				symTbl.put(name,  boolVal);
			}
			state.setTable(symTbl);
		}
		return state;
	}

}
