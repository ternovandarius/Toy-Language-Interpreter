package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class writeHeap implements IStmt{

	String varName;
	Exp exp;
	
	public writeHeap(String var_name, Exp e)
	{
		varName=var_name;
		exp=e;
	}
	
	@Override
	public String toString()
	{
		return "wH("+varName+", "+exp.toString()+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIHeap<Integer, Value> heap = state.getHeap();
		MyIDictionary<String, Value> symTable = state.getTable();
		
		if(!symTable.containsKey(varName))
		{
			throw new MyException("Key not in symbol table!");
		}
		else
		{
			Value val = symTable.get(varName);
			Type testType = new RefType(new IntType());
			if(!val.getType().equals(testType))
			{
				throw new MyException("Invalid type!");
			}
			else
			{
				RefValue refVal = (RefValue)val;
				int addr=refVal.getAddr();
				if(!heap.containsKey(addr))
				{
					throw new MyException("Address not in heap!");
				}
				else
				{
					Value newVal = exp.eval(symTable, heap);
					if(!newVal.getType().equals(refVal.getInnerType()))
					{
						throw new MyException("Types are not equal!");
					}
					else
					{
						heap.update(addr, newVal);
					}
				}
			}
		}
		state.setTable(symTable);
		state.setHeap(heap);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typevar = typeEnv.lookup(varName);
		Type typexp = exp.typecheck(typeEnv);
		
		if (typevar.equals(new RefType(typexp)))
			return typeEnv;
		else
			throw new MyException("WH stmt: right hand side and left hand side have different types ");
	}
}
