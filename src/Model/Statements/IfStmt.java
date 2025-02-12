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

public class IfStmt implements IStmt{

	Exp exp;
	IStmt thenS;
	IStmt elseS;
	
	public IfStmt(Exp e, IStmt t, IStmt el)
	{
		this.exp=e;
		this.thenS=t;
		this.elseS=el;
	}
	
	@Override
	public String toString()
	{
		return "(IF("+exp.toString()+")THEN("+thenS.toString()+")ELSE("+elseS.toString()+"))";
	}
	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> stk = state.getStack();
		MyIHeap<Integer, Value> heap = state.getHeap();
		MyIDictionary<String, Value> symTbl = state.getTable();
		
		Value val = exp.eval(symTbl, heap);
		Type typ = val.getType();
		BoolType boolTest = new BoolType();
		if(!typ.equals(boolTest))
			throw new MyException("Conditional expression is not a boolean!");
		else
		{
			BoolValue newVal = (BoolValue) exp.eval(symTbl, heap);
			if(newVal.getValue()) {
				stk.push(thenS);
			}
			else
				stk.push(elseS);
			state.setStack(stk);
		}
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typexp=exp.typecheck(typeEnv);
		
		if (typexp.equals(new BoolType())) {
			thenS.typecheck(typeEnv);
			elseS.typecheck(typeEnv);
			return typeEnv;
		}
		else
			throw new MyException("The condition of IF has not the type bool!"); 
	}

}
