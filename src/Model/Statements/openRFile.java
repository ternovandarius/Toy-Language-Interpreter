package Model.Statements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyITable;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

public class openRFile implements IStmt{

	Exp exp;
	
	public openRFile(Exp e)
	{
		exp=e;
	}
	
	@Override
	public String toString()
	{
		return "openRFile("+exp.toString()+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyITable<StringValue, BufferedReader> FileTable =state.getFileTable();
		MyIDictionary<String, Value> symTable = state.getTable();
		MyIHeap<Integer, Value> heap = state.getHeap();
		
		Value val = exp.eval(symTable, heap);
		StringType strt = new StringType();
		if(!val.getType().equals(strt))
		{
			throw new MyException("Invalid type!");
		}
		else
		{
			StringValue newVal=(StringValue)val;
			if(FileTable.containsKey(newVal))
			{
				throw new MyException("This file is already opened!");
			}
			else
			{
					try {
						BufferedReader reader = new BufferedReader(new FileReader(newVal.toString()));
						FileTable.put(newVal, reader);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		state.setFileTable(FileTable);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typexp = exp.typecheck(typeEnv);
		
		if (typexp.equals(new StringType()))
			return typeEnv;
		else
			throw new MyException("Exp is not string type!");
	}

}
