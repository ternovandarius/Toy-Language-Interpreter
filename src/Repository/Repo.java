package Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Exceptions.MyException;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyILatchTable;
import Model.ADTs.MyIList;
import Model.ADTs.PrgState;
import Model.Values.StringValue;
import Model.Values.Value;

public class Repo implements MyIRepo{

	List<PrgState> list = new ArrayList<PrgState>();
	String logFilePath = "";
	
	public Repo(PrgState state, String path)
	{
		list.add(state);
		logFilePath=path;
	}
	
	/*public void setFilePath()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the file path.");
		logFilePath=scan.nextLine();
		scan.close();
	}*/
	
	@Override
	public List<PrgState> getPrgList()
	{
		return list;
	}
	
	@Override
	public void setPrgList(List<PrgState> list) 
	{
		this.list=list;
	}

	@Override
	public void logPrgStateExec(PrgState mainState) throws MyException {
		// TODO Auto-generated method stub
		try {
			PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
			logFile.write(mainState.toString());
			logFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MyIList<Value> getOutFromPrgState()
	{
		return this.list.get(0).getOut();
	}

	@Override
	public Set<StringValue> getFileTableKeys() {
		return this.list.get(0).getFileTableKeys();
	}
	
	@Override
	public MyIHeap<Integer, Value> getHeap(){
		return this.list.get(0).getHeap();
	}

	@Override
	public MyILatchTable<Integer, Integer> getLatchTable() {
		return this.list.get(0).getLatchTable();
	}
	
	
}
