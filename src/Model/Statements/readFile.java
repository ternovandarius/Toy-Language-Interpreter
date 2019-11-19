package Model.Statements;

import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyITable;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

public class readFile implements IStmt{
	
	Exp exp;
	String var_name;

	public readFile(Exp e, String var)
	{
		exp=e;
		var_name=var;
	}
	
	@Override
	public String toString()
	{
		return "readFile("+exp.toString()+","+var_name+")";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyITable<StringValue, BufferedReader> FileTable =state.getFileTable();
		MyIDictionary<String, Value> symTable = state.getTable();
		
		int resValue;
		
		if(!symTable.containsKey(var_name))
		{
			throw new MyException("Variable is not declared!");
		}
		else
		{
			Value val=symTable.get(var_name);
			IntType intt= new IntType();
			if(!val.getType().equals(intt))
			{
				throw new MyException("Variable is not int!");
			}
			else
			{
				IntValue newVal = (IntValue)val;
				Value val2=exp.eval(symTable);
				StringType strt = new StringType();
				if(!val2.getType().equals(strt))
				{
					throw new MyException("Exp is not evaluated to a StringType!");
				}
				else
				{
					StringValue newVal2=(StringValue)val2;
					if(!FileTable.containsKey(newVal2))
					{
						throw new MyException("No such file is opened!");
					}
					else
					{
						BufferedReader reader=FileTable.get(newVal2);
						String line = null;
						try {
							line=reader.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(line==null || line.isEmpty())
						{
							resValue=0;
						}
						else
						{
							resValue=Integer.parseInt(line);
						}
						IntValue rezValue = new IntValue(resValue);
						symTable.put(var_name, rezValue);
					}
				}
			}
		}
		state.setTable(symTable);
		return state;
	}

}
