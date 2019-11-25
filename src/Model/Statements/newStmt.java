package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;

public class newStmt implements IStmt{

	StringValue varName;
	Exp exp;
	
	public newStmt(StringValue name, Exp e)
	{
		varName=name;
		exp=e;
	}
	
	@Override
	public String toString(){
		return "new("+varName.toString()+", "+exp.toString()+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIHeap<Integer, Value> heap = state.getHeap();
		MyIDictionary<String, Value> symTable = state.getTable();
		
		if(!symTable.containsKey(varName.getVal()))
		{
			throw new MyException("This var name hasn't been declared.");
		}
		else
		{
			Value val = symTable.get(varName.getVal());
			Type t = null;
			RefType typ= new RefType(t);
			if(!val.getType().equals(typ))
			{
				throw new MyException("Type is not a RefType!");
			}
			else
			{
				Value newVal=exp.eval(symTable, heap);
				if(!newVal.getType().equals(((RefValue) val).getInnerType()))
				{
					throw new MyException("Types are not equal!");
				}
				else
				{
					heap.put(newVal);
					int addr=heap.getLastPos();
					Type newRefType = ((RefValue)val).getInnerType();
					Value newRefVal = new RefValue(addr, newRefType);
					symTable.update(varName.getVal(), newRefVal);
				}
			}
		}
		state.setHeap(heap);
		state.setTable(symTable);
		return state;
	}

}
