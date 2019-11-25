package Controller;

import Exceptions.MyException;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Statements.IStmt;
import Repository.MyIRepo;

public class Controller implements MyIController{
	MyIRepo repo;
	boolean step=true;
	
	public Controller(MyIRepo r)
	{
		repo=r;
	}

	@Override
	public PrgState oneStep(PrgState state) throws MyException {
		MyIStack<IStmt> stk = state.getStack();
		if(stk.isEmpty())
			throw new MyException("prgstate stack is empty");
		IStmt crtStmt = stk.pop();
		//repo.logPrgStateExec();
		return crtStmt.execute(state);
	}

	@Override
	public void allStep() {
		PrgState prg = repo.getCrtPrg();
		try {
			repo.logPrgStateExec();
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(prg.toString());
		while(!prg.getStack().isEmpty())
		{
			try {
				oneStep(prg);
				repo.logPrgStateExec();
				prg.getHeap().setContent(prg.unsafeGarbageCollector(
						 prg.getAddrFromSymTable(( prg.getTable()).getContent().values()),
						 prg.getHeap().getContent()));
				repo.logPrgStateExec();
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(step)
				System.out.println(prg.toString());
		}
		if(!step)
			System.out.println(prg.toString());
	}

	@Override
	public boolean displayCurrent() {
		if (step==false)
			step=true;
		else
			step=false;
		return step;
	}

	@Override
	public boolean getFlagState() {
		return step;
	}
	
	
}
