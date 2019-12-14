package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt{
	IStmt first;
	IStmt snd;
	
	public CompStmt(IStmt f, IStmt s)
	{
		first=f;
		snd=s;
	}
	
	@Override
	public String toString() {
		return "(" + first.toString() + ";" + snd.toString() + ")";
	}

	public PrgState execute(PrgState state) throws MyException{
		MyIStack<IStmt> stk = state.getStack();
		stk.push(snd);
		stk.push(first);
		state.setStack(stk);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		return snd.typecheck(first.typecheck(typeEnv));
	}

}
