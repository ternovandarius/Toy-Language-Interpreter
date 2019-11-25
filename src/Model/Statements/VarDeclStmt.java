package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
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
			RefType refTest = new RefType(new IntType());
			if (typ.equals(intTest))
			{
				symTbl.put(name, intTest.defaultValue());
			}
			else if(typ.equals(boolTest)){
				symTbl.put(name,  boolTest.defaultValue());
			}
			else if(typ.equals(stringTest)){
				symTbl.put(name,  stringTest.defaultValue());
			}
			else if(typ.equals(refTest)) {
				RefType refTyp = (RefType) typ;
				symTbl.put(name,  refTyp.defaultValue());
			}
			state.setTable(symTbl);
		}
		return state;
	}

}
