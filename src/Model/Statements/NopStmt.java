package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Types.Type;

public class NopStmt implements IStmt{
	
	@Override
	public String toString()
	{
		return "Nop";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return typeEnv;
	}

}
