package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt{
	
	String id;
	Exp exp;
	
	public AssignStmt(String i, Exp e)
	{
		id=i;
		exp=e;
	}
	
	@Override
	public String toString()
	{
		return id+"="+exp.toString();
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException{
		MyIDictionary<String, Value> symTbl = state.getTable();
		MyIHeap<Integer, Value> heap = state.getHeap();
		
		if(symTbl.containsKey(id))
		{
			Value val = exp.eval(symTbl, heap);
			Type typId=(symTbl.lookup(id)).getType();
			if ((val.getType()).equals(typId)){
				symTbl.update(id, val);
			}
			else
			{
				throw new MyException("Declared type of variable "+id+" and type of the assigned expression do not match.");
			}
		}
		else {
			throw new MyException("The used variable "+id+" was not declared before.");
		}
		state.setTable(symTbl);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typevar = typeEnv.lookup(id);
		Type typexp = exp.typecheck(typeEnv);
		
		if (typevar.equals(typexp))
			return typeEnv;
		else
			throw new MyException("Assignment: right hand side and left hand side have different types!");
	}
}
