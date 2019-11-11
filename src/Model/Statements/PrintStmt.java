package Model.Statements;

import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.ADTs.PrgState;
import Model.Expressions.Exp;
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
		Value v=exp.eval(symTbl);
		out.add(0, v);
		state.setList(out);
		return state;
	}
}
