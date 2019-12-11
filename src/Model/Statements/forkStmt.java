package Model.Statements;

import java.util.HashMap;
import java.util.Map;

import Exceptions.MyException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.MyStack;
import Model.ADTs.PrgState;
import Model.Values.Value;

public class forkStmt implements IStmt{

	IStmt st;
	
	public forkStmt(IStmt statement)
	{
		st=statement;
	}
	
	@Override
	public String toString() {
		return "fork("+st.toString()+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> forkedStack = new MyStack<IStmt>();
		forkedStack.push(st);
		
		MyIDictionary<String, Value> symTableOriginal = state.getTable();
		HashMap<String, Value> symTableOriginalValues=symTableOriginal.getContent();
		MyIDictionary<String, Value> symTableCopy = new MyDictionary<String, Value>();
		for (Map.Entry<String, Value> i : symTableOriginalValues.entrySet())
		{
			Map.Entry<String, Value> new_entry = i;
			symTableCopy.put(new_entry.getKey(), new_entry.getValue());
		}
		
		
		PrgState forked = new PrgState(forkedStack, symTableCopy, state.getList(), state.getFileTable(), state.getHeap());
		return forked;
	}

}
