package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADTs.FileTable;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.ADTs.MyITable;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.ADTs.PrgState;
import Model.Expressions.ArithExp;
import Model.Expressions.RelExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.Expressions.readHeap;
import Model.Statements.AssignStmt;
import Model.Statements.CompStmt;
import Model.Statements.IStmt;
import Model.Statements.IfStmt;
import Model.Statements.PrintStmt;
import Model.Statements.VarDeclStmt;
import Model.Statements.closeRFile;
import Model.Statements.condStmt;
import Model.Statements.forkStmt;
import Model.Statements.newStmt;
import Model.Statements.openRFile;
import Model.Statements.readFile;
import Model.Statements.whileStmt;
import Model.Statements.writeHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.MyIRepo;
import Repository.Repo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class mainFXController implements Initializable{

	@FXML
	private Button exitButton;
	
	@FXML
	private Button executeButton;
	
	@FXML
	private ListView<String> table;
	
	private boolean hasChild = false;
	
	IStmt ex1;
	IStmt ex2;
	IStmt ex3;
	IStmt compStat;
	IStmt compSt;
	IStmt compWst;
	IStmt compHst;
	IStmt compIst;
	IStmt compJst;
	IStmt compKst;
	
	Controller ctr1;
	Controller ctr2;
	Controller ctr3;
	Controller ctr4;
	Controller ctr5;
	Controller ctr6;
	Controller ctr7;
	Controller ctr8;
	Controller ctr9;
	Controller ctr10;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ex1=new CompStmt(new VarDeclStmt("v", new IntType()), 
				new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
		MyIDictionary<String, Value> symTbl1 = new MyDictionary<String, Value>();
		MyIList<Value> out1 = new MyList<Value>();
		MyIStack<IStmt> stk1 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl1 = new FileTable<StringValue, BufferedReader>();
		MyIDictionary<String, Type> typeEnv1 = new MyDictionary<String, Type>();
		try {
			ex1.typecheck(typeEnv1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk1.push(ex1);
		MyIHeap<Integer, Value> heap1 = new MyHeap<Integer, Value>();
		PrgState prg1 = new PrgState(stk1, symTbl1, out1, FilTbl1, heap1);
		MyIRepo repo1 = new Repo(prg1,"log1.txt");
		ctr1 = new Controller(repo1);
		
		ex2=new CompStmt( new VarDeclStmt("a",new IntType()),
				 new CompStmt(new VarDeclStmt("b",new IntType()),
						 new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
						 ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
						  new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
						 ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
		MyIDictionary<String, Value> symTbl2 = new MyDictionary<String, Value>();
		MyIList<Value> out2 = new MyList<Value>();
		MyIStack<IStmt> stk2 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl2 = new FileTable<StringValue, BufferedReader>();
		MyIDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
		try {
			ex2.typecheck(typeEnv2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk2.push(ex2);
		MyIHeap<Integer, Value> heap2 = new MyHeap<Integer, Value>();
		PrgState prg2 = new PrgState(stk2, symTbl2, out2, FilTbl2, heap2);
		MyIRepo repo2 = new Repo(prg2,"log2.txt");
		ctr2 = new Controller(repo2);
		
		ex3=new CompStmt(new VarDeclStmt("a",new BoolType()),
				 new CompStmt(new VarDeclStmt("v", new IntType()),
						 new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
						  new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
						 IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
						 VarExp("v"))))));
		MyIDictionary<String, Value> symTbl3 = new MyDictionary<String, Value>();
		MyIList<Value> out3 = new MyList<Value>();
		MyIStack<IStmt> stk3 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl3 = new FileTable<StringValue, BufferedReader>();
		MyIDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
		try {
			ex3.typecheck(typeEnv3);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk3.push(ex3);
		MyIHeap<Integer, Value> heap3 = new MyHeap<Integer, Value>();
		PrgState prg3 = new PrgState(stk3, symTbl3, out3, FilTbl3, heap3);
		MyIRepo repo3 = new Repo(prg3,"log3.txt");
		ctr3 = new Controller(repo3);
		
		MyIDictionary<String, Value> symTbl4 = new MyDictionary<String, Value>();
		MyIList<Value> out4 = new MyList<Value>();
		MyIStack<IStmt> stk4 = new MyStack<IStmt>();
		IStmt stat1=new VarDeclStmt("a", new IntType());
		IStmt stat2=new AssignStmt("a", new ValueExp(new IntValue(3)));
		IStmt stat3=new VarDeclStmt("b", new IntType());
		IStmt stat4=new AssignStmt("b", new ValueExp(new IntValue(10)));
		IStmt stat5=new IfStmt(new RelExp(new VarExp("a"), new VarExp("b"), "<"), new PrintStmt(new VarExp("b")), new PrintStmt(new VarExp("a")));
		compStat=new CompStmt(stat1, new CompStmt(stat2, new CompStmt(stat3, new CompStmt(stat4, stat5))));
		MyIDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
		try {
			compStat.typecheck(typeEnv4);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk4.push(compStat);
		MyITable<StringValue, BufferedReader> FilTbl4 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap4 = new MyHeap<Integer, Value>();
		PrgState prg4 = new PrgState(stk4, symTbl4, out4, FilTbl4, heap4);
		MyIRepo repo4 = new Repo(prg4,"log4.txt");
		ctr4 = new Controller(repo4);
		
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
		compSt=new CompStmt(st1, new CompStmt(st2, new CompStmt(st3, new CompStmt(st4,
						new CompStmt(st5, new CompStmt(st6, st7))))));
		MyIDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
		try {
			compSt.typecheck(typeEnv5);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk5.push(compSt);
		MyITable<StringValue, BufferedReader> FilTbl5 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap5 = new MyHeap<Integer, Value>();
		PrgState prg5 = new PrgState(stk5, symTbl5, out5, FilTbl5, heap5);
		MyIRepo repo5 = new Repo(prg5,"log5.txt");
		ctr5 = new Controller(repo5);
		
		MyIDictionary<String, Value> symTbl6 = new MyDictionary<String, Value>();
		MyIList<Value> out6 = new MyList<Value>();
		MyIStack<IStmt> stk6 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl6 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap6 = new MyHeap<Integer, Value>();
		IStmt wst1 = new VarDeclStmt("i", new IntType());
		IStmt wst2 = new AssignStmt("i", new ValueExp(new IntValue(0)));
		IStmt wst3 = new whileStmt(new RelExp(new VarExp("i"), new ValueExp(new IntValue(3)), "<="), new CompStmt(new PrintStmt(new VarExp("i")), new AssignStmt("i", new ArithExp('+', new VarExp("i"), new ValueExp(new IntValue(1))))));
		compWst = new CompStmt(wst1, new CompStmt(wst2, wst3));
		MyIDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
		try {
			compWst.typecheck(typeEnv6);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk6.push(compWst);
		PrgState prg6 = new PrgState(stk6, symTbl6, out6, FilTbl6, heap6);
		MyIRepo repo6 = new Repo(prg6,"log6.txt");
		ctr6 = new Controller(repo6);
		
		MyIDictionary<String, Value> symTbl7 = new MyDictionary<String, Value>();
		MyIList<Value> out7 = new MyList<Value>();
		MyIStack<IStmt> stk7 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl7 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap7 = new MyHeap<Integer, Value>();
		IStmt hst1 = new VarDeclStmt("v", new RefType(new IntType()));
		IStmt hst2 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(20)));
		IStmt hst3 = new PrintStmt(new readHeap(new VarExp("v")));
		IStmt hst4 = new writeHeap("v", new ValueExp(new IntValue(5)));
		IStmt hst5 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
		IStmt hst6 = new newStmt(new StringValue("a"), new VarExp("v"));
		IStmt hst7 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(30)));
		IStmt hst8 = new PrintStmt(new readHeap(new readHeap(new VarExp("a"))));
		compHst = new CompStmt(hst1, new CompStmt(hst2, new CompStmt(hst3, new CompStmt(hst4, 
						new CompStmt(hst5, new CompStmt(hst6, new CompStmt(hst7, hst8)))))));
		MyIDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
		try {
			compHst.typecheck(typeEnv7);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk7.push(compHst);
		PrgState prg7 = new PrgState(stk7, symTbl7, out7, FilTbl7, heap7);
		MyIRepo repo7 = new Repo(prg7,"log7.txt");
		ctr7 = new Controller(repo7);	
		
		MyIDictionary<String, Value> symTbl8 = new MyDictionary<String, Value>();
		MyIList<Value> out8 = new MyList<Value>();
		MyIStack<IStmt> stk8 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl8 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap8 = new MyHeap<Integer, Value>();
		IStmt ist1 = new VarDeclStmt("v", new IntType());
		IStmt ist2 = new VarDeclStmt("a", new RefType(new IntType()));
		IStmt ist3 = new AssignStmt("v", new ValueExp(new IntValue(10)));
		IStmt ist4 = new newStmt(new StringValue("a"), new ValueExp(new IntValue(22)));
		IStmt ist6 = new AssignStmt("v", new ValueExp(new IntValue(32)));
		IStmt ist7 = new PrintStmt(new VarExp("v"));
		IStmt ist8 = new PrintStmt(new readHeap(new VarExp("a")));
		IStmt ist5 = new forkStmt(new CompStmt(new writeHeap("a", new ValueExp(new IntValue(30))), new CompStmt(ist6, new CompStmt(ist7, ist8))));
		IStmt ist9 = new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new readHeap(new VarExp("a"))));
		compIst = new CompStmt(ist1, new CompStmt(ist2, new CompStmt(ist3, new CompStmt(ist4, 
						new CompStmt(ist5, ist9)))));
		MyIDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
		try {
			compIst.typecheck(typeEnv8);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk8.push(compIst);
		PrgState prg8 = new PrgState(stk8, symTbl8, out8, FilTbl8, heap8);
		MyIRepo repo8 = new Repo(prg8, "log8.txt");
		ctr8 = new Controller(repo8);
		
		MyIDictionary<String, Value> symTbl9 = new MyDictionary<String, Value>();
		MyIList<Value> out9 = new MyList<Value>();
		MyIStack<IStmt> stk9 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl9 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap9 = new MyHeap<Integer, Value>();
		
		IStmt jst1 = new VarDeclStmt("v", new RefType(new IntType()));
		IStmt jst2 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(20)));
		IStmt jst3 = new PrintStmt(new readHeap(new VarExp("v")));
		IStmt jst4 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
		IStmt jst5 = new newStmt(new StringValue("a"), new VarExp("v"));
		IStmt jst6 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(30)));
		IStmt jst7 = new forkStmt(new CompStmt(jst6, new CompStmt(new newStmt(new StringValue("a"), new VarExp("v")), new PrintStmt(new readHeap(new readHeap(new VarExp("a")))))));
		IStmt jst8 = new newStmt(new StringValue("v"), new ValueExp(new IntValue(40)));
		IStmt jst9 = new PrintStmt(new readHeap(new readHeap(new VarExp("a"))));
		IStmt jst10 = new writeHeap("v", new ValueExp(new IntValue(5)));
		compJst = new CompStmt(jst1, new CompStmt(jst2, new CompStmt(jst3, new CompStmt(jst4, 
						new CompStmt(jst5, new CompStmt(jst7, new CompStmt(jst8, new CompStmt(jst9, jst10))))))));
		MyIDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
		try {
			compJst.typecheck(typeEnv9);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stk9.push(compJst);
		PrgState prg9 = new PrgState(stk9, symTbl9, out9, FilTbl9, heap9);
		MyIRepo repo9 = new Repo(prg9, "log9.txt");
		ctr9 = new Controller(repo9);
		
		MyIDictionary<String, Value> symTbl10 = new MyDictionary<String, Value>();
		MyIList<Value> out10 = new MyList<Value>();
		MyIStack<IStmt> stk10 = new MyStack<IStmt>();
		MyITable<StringValue, BufferedReader> FilTbl10 = new FileTable<StringValue, BufferedReader>();
		MyIHeap<Integer, Value> heap10 = new MyHeap<Integer, Value>();
		IStmt kst1= new VarDeclStmt("a", new RefType(new IntType()));
		IStmt kst2= new VarDeclStmt("b", new RefType(new IntType()));
		IStmt kst3= new VarDeclStmt("v", new IntType());
		IStmt kst4= new newStmt(new StringValue("a"), new ValueExp(new IntValue(0)));
		IStmt kst5= new newStmt(new StringValue("b"), new ValueExp(new IntValue(0)));
		IStmt kst6= new writeHeap("a", new ValueExp(new IntValue(1)));
		IStmt kst7= new writeHeap("b", new ValueExp(new IntValue(2)));
		IStmt kst8= new condStmt("v", new RelExp(new readHeap(new VarExp("a")), new readHeap(new VarExp("b")), "<"),
				new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200)));
		IStmt kst9= new PrintStmt(new VarExp("v"));
		IStmt kst10= new condStmt("v", new RelExp(new ArithExp('-', new readHeap(new VarExp("b")), new ValueExp(new IntValue(2))), new readHeap(new VarExp("a")), ">"),
				new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200)));
		IStmt kst11= new PrintStmt(new VarExp("v"));
		compKst=new CompStmt(kst1, new CompStmt(kst2, new CompStmt(kst3, new CompStmt(kst4,
				new CompStmt(kst5, new CompStmt(kst6, new CompStmt(kst7, new CompStmt(kst8,
						new CompStmt (kst9, new CompStmt(kst10, kst11))))))))));
		MyIDictionary<String, Type> typeEnv10 = new MyDictionary<String, Type>();
		try {
			compKst.typecheck(typeEnv10);
		} catch (MyException e) {
			e.printStackTrace();
		}
		stk10.push(compKst);
		PrgState prg10 = new PrgState(stk10, symTbl10, out10, FilTbl10, heap10);
		MyIRepo repo10 = new Repo(prg10, "log10.txt");
		ctr10 = new Controller(repo10);
		
		ObservableList<String> str_list = FXCollections.observableArrayList();
		StringProperty sp_ex1=new SimpleStringProperty(ex1.toString());
		StringProperty sp_ex2=new SimpleStringProperty(ex2.toString());
		StringProperty sp_ex3=new SimpleStringProperty(ex3.toString());
		StringProperty sp_compStat=new SimpleStringProperty(compStat.toString());
		StringProperty sp_compSt=new SimpleStringProperty(compSt.toString());
		StringProperty sp_compWst=new SimpleStringProperty(compWst.toString());
		StringProperty sp_compHst=new SimpleStringProperty(compHst.toString());
		StringProperty sp_compIst=new SimpleStringProperty(compIst.toString());
		StringProperty sp_compJst=new SimpleStringProperty(compJst.toString());
		StringProperty sp_compKst=new SimpleStringProperty(compKst.toString());
		str_list.add(ex1.toString());
		str_list.add(ex2.toString());
		str_list.add(ex3.toString());
		str_list.add(compStat.toString());
		str_list.add(compSt.toString());
		str_list.add(compWst.toString());
		str_list.add(compHst.toString());
		str_list.add(compIst.toString());
		str_list.add(compJst.toString());
		str_list.add(compKst.toString());
		
		table.setItems(str_list);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void exitButtonClicked(ActionEvent event)
	{
		Platform.exit();
	}
	
	public void executeButtonClicked(ActionEvent event)
	{
		String oldSelection=table.getSelectionModel().getSelectedItem();
		if (oldSelection==null)
			return;
		else
			if (!hasChild)
			{
				hasChild=true;
				}
				FXMLLoader loader = new FXMLLoader(getClass().getResource("second.fxml"));
				try {
					AnchorPane newWindow = (AnchorPane) loader.load();
					Stage stage = new Stage();
					Controller cont = null;
					String selection=table.getSelectionModel().getSelectedItem();
					if (selection.equals(ex1.toString()))
						cont=ctr1;
					else if (selection.equals(ex2.toString()))
						cont=ctr2;
					else if (selection.equals(ex3.toString()))
						cont=ctr3;
					else if (selection.equals(compStat.toString()))
						cont=ctr4;
					else if (selection.equals(compSt.toString()))
						cont=ctr5;
					else if (selection.equals(compWst.toString()))
						cont=ctr6;
					else if (selection.equals(compHst.toString()))
						cont=ctr7;
					else if (selection.equals(compIst.toString()))
						cont=ctr8;
					else if (selection.equals(compJst.toString()))
						cont=ctr9;
					else if (selection.equals(compKst.toString()))
					{
						cont=ctr10;
					}
					else
					{
						cont=null;
						Platform.exit();
					}
					secondController newCont =loader.<secondController>getController();
					newCont.setController(cont);
					stage.setScene(new Scene(newWindow));
					stage.setTitle("Program execution");
					stage.show();
					((Node)(event.getSource())).getScene().getWindow().hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
