package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
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
			BoolType boolTest = new BoolType();
			StringType stringTest = new StringType();
			if (typ.equals(intTest))
			{
				symTbl.put(name, intTest.defaultValue());
			}
			else if(typ.equals(boolTest)){
				symTbl.put(name,  boolTest.defaultValue());
			}
			else {
				symTbl.put(name,  stringTest.defaultValue());
			}
			state.setTable(symTbl);
		}
		return state;
	}

}
