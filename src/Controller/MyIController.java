package Controller;

import Exceptions.MyException;
import Model.ADTs.PrgState;

public interface MyIController {
	PrgState oneStep(PrgState state) throws MyException;
	void allStep();
	boolean displayCurrent();
	boolean getFlagState();
}
