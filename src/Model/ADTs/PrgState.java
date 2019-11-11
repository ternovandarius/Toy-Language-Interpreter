package Model.ADTs;

import Model.Statements.IStmt;
import Model.Values.Value;

public class PrgState {
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Value> symTable;
	MyIList<Value> out;
	//IStmt originalProgram; to be implemented
	
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot){
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		//originalProgram=deepCopy(prg);
		//stk.push(prg);
	}
	
	public void setStack(MyIStack<IStmt> newStack)
	{
		exeStack=newStack;
	}
	
	public MyIStack<IStmt> getStack()
	{
		return exeStack;
	}
	
	public void setTable(MyIDictionary<String, Value> newTable)
	{
		symTable=newTable;
	}
	
	public MyIDictionary<String, Value> getTable()
	{
		return symTable;
	}
	
	public void setList(MyIList<Value> newList)
	{
		out=newList;
	}
	
	public MyIList<Value> getList()
	{
		return out;
	}
	
	public String toString()
	{
		String exeStackMsg="ExeStack= {";
		exeStackMsg+=exeStack.toString()+"}";
		String symTableMsg="SymTable= {";
		symTableMsg+=symTable.toString()+"}";
		String outMsg="Out= {";
		outMsg+=out.toString()+"}";
		String finalMsg=exeStackMsg+"\n"+symTableMsg+"\n"+outMsg+"\n";
		return finalMsg;
	}
}
