package View;

import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.*;
import Model.Types.*;
import Model.Expressions.*;
import Model.Values.*;
import Repository.*;
import Controller.*;
import Exceptions.MyException;

import java.io.BufferedReader;

import Model.ADTs.*;

class Interpreter {
	public static void main(String[] args) throws MyException {
		IStmt ex1=new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
		MyIDictionary<String, Value> symTbl1 = new MyDictionary<String, Value>();
		MyIList<Value> out1 = new MyList<Value>();
		MyIStack<IStmt> stk1 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl1 = new FileTable<StringValue, BufferedReader>();
		stk1.push(ex1);
		MyIHeap<Integer, Value> heap1 = new MyHeap<Integer, Value>();
		PrgState prg1 = new PrgState(stk1, symTbl1, out1, FilTbl1, heap1);
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
		MyIHeap<Integer, Value> heap2 = new MyHeap<Integer, Value>();
		PrgState prg2 = new PrgState(stk2, symTbl2, out2, FilTbl2, heap2);
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
		MyIHeap<Integer, Value> heap3 = new MyHeap<Integer, Value>();
		PrgState prg3 = new PrgState(stk3, symTbl3, out3, FilTbl3, heap3);
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
		MyIHeap<Integer, Value> heap4 = new MyHeap<Integer, Value>();
		PrgState prg4 = new PrgState(stk4, symTbl4, out4, FilTbl4, heap4);
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
		MyIHeap<Integer, Value> heap5 = new MyHeap<Integer, Value>();
		PrgState prg5 = new PrgState(stk5, symTbl5, out5, FilTbl5, heap5);
		MyIRepo repo5 = new Repo(prg5,"log5.txt");
		Controller ctr5 = new Controller(repo5);
		
		MyIDictionary<String, Value> symTbl6 = new MyDictionary<String, Value>();
		MyIList<Value> out6 = new MyList<Value>();
		MyIStack<IStmt> stk6 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl6 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap6 = new MyHeap<Integer, Value>();
		IStmt wst1 = new VarDeclStmt("i", new IntType());
		IStmt wst2 = new AssignStmt("i", new ValueExp(new IntValue(0)));
		IStmt wst3 = new whileStmt(new RelExp(new VarExp("i"), new ValueExp(new IntValue(3)), "<="), new CompStmt(new PrintStmt(new VarExp("i")), new AssignStmt("i", new ArithExp('+', new VarExp("i"), new ValueExp(new IntValue(1))))));
		
		stk6.push(wst3);
		stk6.push(wst2);
		stk6.push(wst1);
		PrgState prg6 = new PrgState(stk6, symTbl6, out6, FilTbl6, heap6);
		MyIRepo repo6 = new Repo(prg6,"log6.txt");
		Controller ctr6 = new Controller(repo6);
		
		MyIDictionary<String, Value> symTbl7 = new MyDictionary<String, Value>();
		MyIList<Value> out7 = new MyList<Value>();
		MyIStack<IStmt> stk7 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl7 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap7 = new MyHeap<Integer, Value>();
		IStmt hst1 = new VarDeclStmt("v", new RefType(new IntType()));
		//IStmt hst2 = new AssignStmt("v", new ValueExp(new RefValue(0, new IntType())));
		IStmt hst3 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(20)));
		IStmt hst4 = new PrintStmt(new readHeap(new VarExp("v")));
		IStmt hst5 = new writeHeap("v", new ValueExp(new IntValue(5)));
		IStmt hst6 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
		IStmt hst7 = new newStmt(new StringValue("a"), new VarExp("v"));
		IStmt hst8 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(30)));
		IStmt hst9 = new PrintStmt(new readHeap(new readHeap(new VarExp("a"))));
		
		stk7.push(hst9);
		stk7.push(hst8);
		stk7.push(hst7);
		stk7.push(hst6);
		stk7.push(hst5);
		stk7.push(hst4);
		stk7.push(hst3);
		stk7.push(hst1);
		PrgState prg7 = new PrgState(stk7, symTbl7, out7, FilTbl7, heap7);
		MyIRepo repo7 = new Repo(prg7,"log7.txt");
		Controller ctr7 = new Controller(repo7);
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0", "exit"));
		menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
		menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
		menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
		menu.addCommand(new RunExample("4","Operator test",ctr4));
		menu.addCommand(new RunExample("5","File read test", ctr5));
		menu.addCommand(new RunExample("6", "While test", ctr6));
		menu.addCommand(new RunExample("7", "Heap test", ctr7));
		menu.show();
	}
}
