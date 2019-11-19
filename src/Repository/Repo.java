package Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Exceptions.MyException;
import Model.ADTs.PrgState;

public class Repo implements MyIRepo{

	PrgState mainState;
	String logFilePath = "";
	
	public Repo(PrgState state, String path)
	{
		mainState=state;
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
	public PrgState getCrtPrg() {
		return mainState;
	}
	
	public void setCrtPrg(PrgState state) {
		mainState=state;
	}

	@Override
	public void logPrgStateExec() throws MyException {
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
}
