package Repository;

import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.ADTs.PrgState;
import Model.Values.StringValue;
import Model.Values.Value;

import java.util.List;
import java.util.Set;

import Exceptions.MyException;

public interface MyIRepo {
	List<PrgState> getPrgList();
	void setPrgList(List<PrgState> list);
	void logPrgStateExec(PrgState mainState) throws MyException;
	public MyIList<Value> getOutFromPrgState();
	public Set<StringValue> getFileTableKeys();
	public MyIHeap<Integer, Value> getHeap();
}
