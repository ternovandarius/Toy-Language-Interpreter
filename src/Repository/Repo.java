package Repository;

import Model.ADTs.PrgState;

public class Repo implements MyIRepo{

	PrgState mainState;
	
	public Repo(PrgState state)
	{
		mainState=state;
	}
	
	@Override
	public PrgState getCrtPrg() {
		return mainState;
	}
	
	public void setCrtPrg(PrgState state) {
		mainState=state;
	}

}
