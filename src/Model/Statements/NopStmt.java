package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.PrgState;

public class NopStmt implements IStmt{
	
	@Override
	public String toString()
	{
		return "Nop";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		return state;
	}

}
