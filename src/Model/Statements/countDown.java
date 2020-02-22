package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyILatchTable;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class countDown implements IStmt{

	String var;
	
	public countDown(String v)
	{
		var=v;
	}
	
	@Override
	public String toString()
	{
		return "countDown("+var+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIList<Value> out=state.getList();
		MyIStack<IStmt> stk = state.getStack();
		MyILatchTable<Integer,Integer> latch = state.getLatchTable();
		MyIDictionary<String, Value> symTbl = state.getTable();
		
		if (symTbl.containsKey(var))
		{
			Value foundIndex = symTbl.lookup(var);
			if (foundIndex.getType().equals(new IntType()))
			{
				IntValue iFoundIndex= (IntValue) foundIndex;
				int actualIndex=iFoundIndex.getVal();
				if(latch.containsKey(actualIndex))
				{
					int remaining = latch.lookup(actualIndex);
					Value outVal = new IntValue(state.getId());
					if(remaining>0)
					{
						latch.update(actualIndex, remaining-1);
						out.add(0, outVal);
					}
					else
					{
						out.add(0, outVal);
					}
				}
				else
				{
					throw new MyException("Latch table does not contain foundIndex!");
				}
			}
			else
			{
				throw new MyException("Var is not of int type!");
			}
		}
		else {
			throw new MyException("Var is not in symbol table!");
		}
		state.setStack(stk);
		state.setLatchTable(latch);
		state.setTable(symTbl);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
