package Repository;

import Model.ADTs.PrgState;
import Exceptions.MyException;

public interface MyIRepo {
	PrgState getCrtPrg();
	void logPrgStateExec() throws MyException;
}
