package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class whileStmt implements IStmt{

	Exp exp;
	IStmt stmt;
	
	public whileStmt(Exp e, IStmt st)
	{
		exp=e;
		stmt=st;
	}
	
	@Override
	public String toString()
	{
		return "while ("+exp.toString()+") "+stmt.toString();
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stk = state.getStack();
		MyIHeap<Integer, Value> heap = state.getHeap();
		MyIDictionary<String, Value> symTbl = state.getTable();
		
		Value val = exp.eval(symTbl, heap);
		if(!val.getType().equals(new BoolType()))
		{
			throw new MyException("Invalid type!");
		}
		else
		{
			BoolValue newVal = (BoolValue) val;
			if(newVal.getValue())
			{
				whileStmt newWhile = new whileStmt(exp, stmt);
				stk.push(newWhile);
				stk.push(stmt);
			}
		}
		state.setStack(stk);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typexp=exp.typecheck(typeEnv);
		
		if (typexp.equals(new BoolType())) {
			stmt.typecheck(typeEnv);
			return typeEnv;
		}
		else
			throw new MyException("The condition of WHILE has not the type bool!"); 
	}
	
}
