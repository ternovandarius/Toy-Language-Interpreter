package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.PrgState;

public interface IStmt{
	 PrgState execute(PrgState state) throws MyException; //which is the execution method for a statement.
	 public String toString();
} 
