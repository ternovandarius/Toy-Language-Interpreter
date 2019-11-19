/*package View;

import java.util.Scanner;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.ADTs.PrgState;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.Statements.AssignStmt;
import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Statements.PrintStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import Repository.MyIRepo;
import Repository.Repo;

public class Main {
	
	 * TODO:
	 * size
	 
	public static void main(String[] args)
	{
		MyIStack<IStmt> stk;
		MyIDictionary<String, Value> symTbl = new MyDictionary<String, Value>();
		MyIList<Value> out = new MyList<Value>();
		IStmt ex;
		PrgState prg;
		MyIRepo repo = null;
		Controller c = null;
		boolean loaded=false;
		while(true)
		{
			Scanner myObj = new Scanner(System.in);
			System.out.print("1.Load a program.\n");
			if(loaded)
				System.out.println("2.Run program.");
			System.out.println("0.Exit.");
			String choice = myObj.nextLine();
			int result=Integer.parseInt(choice);
			if(result==0)
			{
				System.out.println("Goodbye!");
				myObj.close();
				break;
			}
			else if(result==1)
			{
				while(true)
				{
					System.out.println("Select your program:\n1.int v; v=2;Print(v)\n2.int a;int b; a=2+3*5;b=a+1;Print(b)\n3.bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
					String newChoice = myObj.nextLine();
					int newResult=Integer.parseInt(newChoice);
					if(newResult==1)
					{
						stk = new MyStack<IStmt>();
						ex=new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
						loaded=true;
						stk.push(ex);
						prg=new PrgState(stk, symTbl, out);
						repo=new Repo(prg);
						c=new Controller(repo);
						break;
					}
					else if (newResult==2)
					{
						stk = new MyStack<IStmt>();
						ex=new CompStmt( new VarDeclStmt("a",new IntType()),
								 new CompStmt(new VarDeclStmt("b",new IntType()),
										 new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
										 ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
										  new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
										 ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
						loaded=true;
						stk.push(ex);
						prg=new PrgState(stk, symTbl, out);
						repo=new Repo(prg);
						c=new Controller(repo);
						break;

					}
					else if(newResult==3)
					{
						stk = new MyStack<IStmt>();
						ex= new CompStmt(new VarDeclStmt("a",new BoolType()),
								 new CompStmt(new VarDeclStmt("v", new IntType()),
										 new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
										  new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
										 IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
										 VarExp("v"))))));
						loaded=true;
						stk.push(ex);
						prg=new PrgState(stk, symTbl, out);
						repo=new Repo(prg);
						c=new Controller(repo);
						break;
					}
					else {
						System.out.println("Invalid selection!");
					}
				}
			}
			else if(result==2 && loaded) {
				while(true){
					System.out.println("1.Run program step by step.\n2.Run program.\n3.Change flag status. Current Status:" + Boolean.toString(c.getFlagState()));
					String newerChoice = myObj.nextLine();
					int newerResult=Integer.parseInt(newerChoice);
					if(newerResult==1)
					{
						PrgState state = repo.getCrtPrg();
						while(!state.getStack().isEmpty())
						{
							System.out.println("1.Go forward one step.");
							String evenNewerChoice = myObj.nextLine();
							int evenNewerResult = Integer.parseInt(evenNewerChoice);
							if(evenNewerResult==1)
							{
								try {
									c.oneStep(state);
								}catch(Exception e) {}
								if(c.getFlagState())
									System.out.println(state.toString());
							}
							else
								System.out.println("Invalid selection. ");
						}
					}
					else if(newerResult==2)
					{
						c.allStep();
					}
					else if(newerResult==3)
					{
						System.out.print("Flag state changed! Current state: ");
						boolean boo = c.displayCurrent();
						System.out.println(Boolean.toString(boo));
					}
					else {
						System.out.println("Invalid selection.");
					}
				}
			}
			else {
				System.out.println("Invalid selection.");
			}
		}
	}
}*/
