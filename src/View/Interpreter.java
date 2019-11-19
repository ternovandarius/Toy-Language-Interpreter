package View;

import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.*;
import Model.Types.*;
import Model.Expressions.*;
import Model.Values.*;
import Repository.*;
import Controller.*;

import java.io.BufferedReader;

import Model.ADTs.*;

class Interpreter {
	public static void main(String[] args) {
		IStmt ex1=new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
		MyIDictionary<String, Value> symTbl1 = new MyDictionary<String, Value>();
		MyIList<Value> out1 = new MyList<Value>();
		MyIStack<IStmt> stk1 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl1 = new FileTable<StringValue, BufferedReader>();
		stk1.push(ex1);
		PrgState prg1 = new PrgState(stk1, symTbl1, out1, FilTbl1);
		MyIRepo repo1 = new Repo(prg1,"log1.txt");
		Controller ctr1 = new Controller(repo1);
		
		IStmt ex2=new CompStmt( new VarDeclStmt("a",new IntType()),
				 new CompStmt(new VarDeclStmt("b",new IntType()),
						 new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
						 ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
						  new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
						 ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
		MyIDictionary<String, Value> symTbl2 = new MyDictionary<String, Value>();
		MyIList<Value> out2 = new MyList<Value>();
		MyIStack<IStmt> stk2 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl2 = new FileTable<StringValue, BufferedReader>();
		stk2.push(ex2);
		PrgState prg2 = new PrgState(stk2, symTbl2, out2, FilTbl2);
		MyIRepo repo2 = new Repo(prg2,"log2.txt");
		Controller ctr2 = new Controller(repo2);
		
		IStmt ex3=new CompStmt(new VarDeclStmt("a",new BoolType()),
				 new CompStmt(new VarDeclStmt("v", new IntType()),
						 new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
						  new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
						 IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
						 VarExp("v"))))));
		MyIDictionary<String, Value> symTbl3 = new MyDictionary<String, Value>();
		MyIList<Value> out3 = new MyList<Value>();
		MyIStack<IStmt> stk3 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl3 = new FileTable<StringValue, BufferedReader>();
		stk3.push(ex3);
		PrgState prg3 = new PrgState(stk3, symTbl3, out3, FilTbl3);
		MyIRepo repo3 = new Repo(prg3,"log3.txt");
		Controller ctr3 = new Controller(repo3);
		
		MyIDictionary<String, Value> symTbl4 = new MyDictionary<String, Value>();
		MyIList<Value> out4 = new MyList<Value>();
		MyIStack<IStmt> stk4 = new MyStack<IStmt>();
		IStmt stat1=new VarDeclStmt("a", new IntType());
		IStmt stat2=new AssignStmt("a", new ValueExp(new IntValue(3)));
		IStmt stat3=new VarDeclStmt("b", new IntType());
		IStmt stat4=new AssignStmt("b", new ValueExp(new IntValue(10)));
		IStmt stat5=new IfStmt(new RelExp(new VarExp("a"), new VarExp("b"), "<"), new PrintStmt(new VarExp("b")), new PrintStmt(new VarExp("a")));
		stk4.push(stat5);
		stk4.push(stat4);
		stk4.push(stat3);
		stk4.push(stat2);
		stk4.push(stat1);
		MyITable<StringValue, BufferedReader> FilTbl4 = new FileTable<StringValue, BufferedReader>();
		PrgState prg4 = new PrgState(stk4, symTbl4, out4, FilTbl4);
		MyIRepo repo4 = new Repo(prg4,"log4.txt");
		Controller ctr4 = new Controller(repo4);
		
		MyIDictionary<String, Value> symTbl5 = new MyDictionary<String, Value>();
		MyIList<Value> out5 = new MyList<Value>();
		MyIStack<IStmt> stk5 = new MyStack<IStmt>();
		IStmt st1=new VarDeclStmt("varf", new StringType());
		IStmt st2=new AssignStmt("varf", new ValueExp(new StringValue("test.in")));
		IStmt st3=new openRFile(new VarExp("varf"));
		IStmt st4=new VarDeclStmt("varc", new IntType());
		IStmt st5=new CompStmt(new readFile(new VarExp("varf"), "varc"), new PrintStmt(new VarExp("varc")));
		IStmt st6=new CompStmt(new readFile(new VarExp("varf"), "varc"), new PrintStmt(new VarExp("varc")));
		IStmt st7=new closeRFile(new VarExp("varf"));
		stk5.push(st7);
		stk5.push(st6);
		stk5.push(st5);
		stk5.push(st4);
		stk5.push(st3);
		stk5.push(st2);
		stk5.push(st1);
		MyITable<StringValue, BufferedReader> FilTbl5 = new FileTable<StringValue, BufferedReader>();
		PrgState prg5 = new PrgState(stk5, symTbl5, out5, FilTbl5);
		MyIRepo repo5 = new Repo(prg5,"log5.txt");
		Controller ctr5 = new Controller(repo5);
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
		menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
		menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
		menu.addCommand(new RunExample("4","Operator test",ctr4));
		menu.addCommand(new RunExample("5","File read test", ctr5));
		menu.show();
	}
}
