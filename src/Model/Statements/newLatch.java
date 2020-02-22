package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyILatchTable;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class newLatch implements IStmt{

	String var;
	Exp exp;
	
	public newLatch(String v, Exp e)
	{
		var=v;
		exp=e;
	}
	
	@Override
	public String toString()
	{
		return "newLatch("+var+", "+exp.toString()+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyILatchTable<Integer,Integer> latch = state.getLatchTable();
		MyIDictionary<String, Value> symTbl = state.getTable();
		
		Value num1 = exp.eval(state.getTable(), state.getHeap());
		
		if (num1.getType().equals(new IntType()))
		{
			IntValue inum1 = (IntValue) num1;
			latch.put(inum1.getVal());
			if (symTbl.containsKey(var))
			{
				if ((symTbl.lookup(var)).getType().equals(new IntType()))
				{
					IntValue inum2 = new IntValue(latch.lookup(inum1.getVal()));
					symTbl.update(var, inum2);
				}
				else {
					throw new MyException("Type of var is not int!");
				}
			}
			else {
				throw new MyException("Var not in symTbl!");
			}
		}
		else
		{
			throw new MyException("Exp does not evaluate to int!");
		}
		state.setTable(symTbl);
		state.setLatchTable(latch);
		return null;
	}
	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return null;
	}
	
}
