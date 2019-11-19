package Model.ADTs;

import java.io.BufferedReader;

import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

public class PrgState {
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Value> symTable;
	MyIList<Value> out;
	MyITable<StringValue, BufferedReader> FileTable;
	//IStmt originalProgram; to be implemented
	
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyITable<StringValue, BufferedReader> FilTbl){
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		FileTable=FilTbl;
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
	
	public void setFileTable(MyITable<StringValue, BufferedReader> newTable)
	{
		FileTable=newTable;
	}
	
	public MyITable<StringValue, BufferedReader> getFileTable()
	{
		return FileTable;
	}
	
	public String toString()
	{
		String exeStackMsg="ExeStack:\n";
		exeStackMsg+=exeStack.toString();
		String symTableMsg="SymTable:\n";
		symTableMsg+=symTable.toString();
		String outMsg="Out:\n";
		outMsg+=out.toString();
		String fileTableMsg ="FileTable:\n";
		fileTableMsg+=FileTable.toString();
		String finalMsg=exeStackMsg+"\n"+symTableMsg+"\n"+outMsg+"\n"+fileTableMsg+"\n";
		return finalMsg;
	}
}
