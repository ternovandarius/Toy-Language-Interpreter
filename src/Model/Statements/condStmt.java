package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.Value;

public class condStmt implements IStmt{

	String id;
	Exp exp1;
	Exp exp2;
	Exp exp3;
	
	public condStmt(String cid, Exp cexp1, Exp cexp2, Exp cexp3)
	{
		id=cid;
		exp1=cexp1;
		exp2=cexp2;
		exp3=cexp3;
	}
	
	@Override
	public String toString()
	{
		return id+"="+exp1.toString()+"?"+exp2.toString()+":"+exp3.toString();
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> exeStack = state.getStack();
		IStmt vis2 = new AssignStmt(id, exp2);
		IStmt vis3 = new AssignStmt(id, exp3);
		IStmt ifst = new IfStmt(exp1, vis2, vis3);
		exeStack.push(ifst);
		state.setStack(exeStack);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typexp=exp1.typecheck(typeEnv);
		
		if (typexp.equals(new BoolType()))
		{
			Type typevar = typeEnv.lookup(id);
			Type typexp2=exp2.typecheck(typeEnv);
			Type typexp3=exp3.typecheck(typeEnv);
			
			if (typevar.equals(typexp2) && typevar.equals(typexp3) && typexp2.equals(typexp3))
			{
				return typeEnv;
			}
			else {
				throw new MyException("Types of v, exp2 and exp3 are not equal!");
			}
		}
		else {
			throw new MyException("Type of exp1 is not bool!");
		}
	}

}
