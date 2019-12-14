package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStmt implements IStmt{
	Exp exp;

	public PrintStmt(Exp exp){
		this.exp=exp;
	}
	
	@Override
	public String toString() {
		return "print("+exp.toString()+")";
	}
	
	public PrgState execute(PrgState state) throws MyException {
		MyIList<Value> out=state.getList();
		MyIDictionary<String, Value> symTbl = state.getTable();
		MyIHeap<Integer, Value> heap = state.getHeap();
		
		Value v=exp.eval(symTbl, heap);
		out.add(0, v);
		state.setList(out);
		return null;
	}

	@Override
	public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		exp.typecheck(typeEnv);
		return typeEnv;
	}
}
