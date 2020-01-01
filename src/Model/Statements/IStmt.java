package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Types.Type;

public interface IStmt{
	 PrgState execute(PrgState state) throws MyException;
	 MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
	 @Override
	public String toString();
} 
