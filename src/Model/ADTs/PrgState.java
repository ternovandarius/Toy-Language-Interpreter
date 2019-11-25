package Model.ADTs;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Model.Statements.IStmt;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;

public class PrgState {
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Value> symTable;
	MyIList<Value> out;
	MyITable<StringValue, BufferedReader> FileTable;
	MyIHeap<Integer, Value> heap;
	//IStmt originalProgram; to be implemented
	
	public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyITable<StringValue, BufferedReader> FilTbl, MyIHeap<Integer, Value> heap){
		exeStack = stk;
		symTable = symtbl;
		out = ot;
		FileTable=FilTbl;
		this.heap=heap;
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
	
	public void setHeap(MyIHeap<Integer, Value> heap)
	{
		this.heap=heap;
	}
	
	public MyIHeap<Integer, Value> getHeap()
	{
		return this.heap;
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
		String heapMsg ="Heap:\n";
		heapMsg+=heap.toString();
		String finalMsg=exeStackMsg+"\n"+symTableMsg+"\n"+outMsg+"\n"+fileTableMsg+"\n"+heapMsg;
		return finalMsg;
	}
	
	public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, HashMap<Integer,Value>
	heap){
	return heap.entrySet()
			.stream()
			.filter(e->symTableAddr.contains(e.getKey()))   
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	
	}
	
	public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heapValues){
		List<Integer> symTableList = symTableValues.stream()
													.filter(v-> v instanceof RefValue )
													.map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
													.collect(Collectors.toList());
		
		List<Integer> heapList = heapValues.stream()
				 .filter(v-> v instanceof RefValue )
				 .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
				 .collect(Collectors.toList());
		
		symTableList.addAll(heapList);
		return symTableList;
	}

	
}
