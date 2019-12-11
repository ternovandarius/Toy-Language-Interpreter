package Repository;

import Model.ADTs.PrgState;

import java.util.List;

import Exceptions.MyException;

public interface MyIRepo {
	List<PrgState> getPrgList();
	void setPrgList(List<PrgState> list);
	void logPrgStateExec(PrgState mainState) throws MyException;
}
