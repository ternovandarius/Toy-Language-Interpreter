package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.MyIRepo;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class secondController implements Initializable{

	@FXML
	private Button exitButton;
	
	@FXML
	private TextField prgStateCount;
	
	private Controller controller;
	
	@FXML
	private ListView<String> outputTable;
	
	@FXML
	private ListView<String> fileTable;
	
	@FXML
	private ListView<String> exeStackList;
	
	@FXML
	private TableView<TableEntry> heapTable;
	
	@FXML
	private TableColumn<TableEntry, String> addressCol;
	
	@FXML
	private TableColumn<TableEntry, String> valueCol;
	
	@FXML
	private ListView<String> prgStateTable;
	
	@FXML
	private TableView<TableEntry> symTable;
	
	@FXML
	private TableColumn<TableEntry, String> varName;
	
	@FXML
	private TableColumn<TableEntry, String> value;
	
	private int selectedID=-1;
	
	private MyIRepo repo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(() -> {
			addressCol.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("firstCol")); 
			valueCol.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("secondCol"));
			
			prgStateTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			prgStateTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal)
				{
						try {
							selectedID=Integer.parseInt(newVal);
						}
						catch (Exception e)
						{
							selectedID=-1;
						}
						setDependencies();
				}
			});
			
			varName.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("firstCol")); 
			value.setCellValueFactory(new PropertyValueFactory<TableEntry,String>("secondCol"));
			
			this.repo=this.controller.getRepo();
			
			this.updateWindow();
		});
	}

	public void setController(Controller newCont) {
		controller=newCont;
	}
	
	public void exitButtonClicked(ActionEvent event)
	{
		Platform.exit();
	}
	
	private void setPrgStateCount()
	{
		int appender=this.controller.getRepoCount();
		String prgStateText = "PrgState count: "+Integer.toString(appender);
		prgStateCount.setText(prgStateText);
	}
	
	private void setOutputList()
	{
		ObservableList<String> outList = FXCollections.observableArrayList();
		MyIList<Value> auxList = this.controller.getOutput();
		int i=auxList.size()-1;
		for(i=auxList.size()-1; i>=0; i--)
		{
			outList.add(auxList.get(i).toString());
		}
		outputTable.setItems(outList);
	}
	
	private void setFileTableList()
	{
		ObservableList<String> fileList = FXCollections.observableArrayList();
		Set<StringValue> auxSet = this.controller.getFileTableKeys();
		Iterator<StringValue> it = auxSet.iterator();
		while(it.hasNext())
		{
			fileList.add(it.next().getVal());
		}
		fileTable.setItems(fileList);
	}
	
	private void setHeapTable()
	{
		HashMap<Integer, Value> aux = this.controller.getHeap().getContent();
		ObservableList<TableEntry> tbl = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Value> cursor : aux.entrySet())
		{
			String first=cursor.getKey().toString();
			String second=cursor.getValue().toString();
			tbl.add(new TableEntry(first, second));
		}
		heapTable.setItems(tbl);
	}
	
	private void setPrgStateIDList()
	{
		ObservableList<String> prgStateList = FXCollections.observableArrayList();
		List<String> aux = this.controller.getPrgStateIDs();
		for (String i: aux)
		{
			prgStateList.add(i);
		}
		prgStateTable.setItems(prgStateList);
	}
	
	private void setDependencies()
	{
		if(selectedID!=-1)
		{
			List<PrgState> list = this.controller.getPrgStateList();
			for(PrgState i : list)
			{
				if (i.getId()==selectedID)
				{
					HashMap<String, Value> symTbl = i.getTable().getContent();
					ObservableList<TableEntry> tbl = FXCollections.observableArrayList();
					for (Map.Entry<String, Value> cursor : symTbl.entrySet())
					{
						String first=cursor.getKey();
						String second=cursor.getValue().toString();
						tbl.add(new TableEntry(first, second));
					}
					symTable.setItems(tbl);
					//now we do the exe stack
					
					Stack<IStmt> stack = i.getStack().getContents();
					ObservableList<String> stk = FXCollections.observableArrayList();
					ListIterator<IStmt> iterator = stack.listIterator(stack.size());
					while(iterator.hasPrevious())
					{
						stk.add(iterator.previous().toString());
					}
					exeStackList.setItems(stk);
				}
			}
		}
		else {
			symTable.setItems(null);
			exeStackList.setItems(null);
		}
	}
	
	private void updateWindow()
	{
		this.setPrgStateCount();
		this.setOutputList();
		this.setFileTableList();
		this.setHeapTable();
		this.setPrgStateIDList();
		this.setDependencies();
	}
	
	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
	{
		return inPrgList.stream()
				 .filter(p -> p.isNotCompleted())
				 .collect(Collectors.toList());
	}
	
	public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException
	{
		
		List<Callable<PrgState>> callList = prgList.stream()
				 .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
				 .collect(Collectors.toList());
		
		List<PrgState> newPrgList = executor.invokeAll(callList). stream()
				 . map(future -> { try {
					return future.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				 })
				 .filter(p -> p!=null)
				 .collect(Collectors.toList());

		prgList.addAll(newPrgList);
		
		repo.setPrgList(prgList);

	}
	
	ExecutorService executor;
	
	public void stepButtonClicked(ActionEvent event)
	{
		executor = Executors.newFixedThreadPool(8);
		
		List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
		
		if(prgList.size() > 0)
		{
			try {
				oneStepForAllPrg(prgList);
						
				//in some cases, the garbage collector only removes the references to unneeded addresses on one step, so we
				//need to use the garbage collector multiple times, until there's nothing else to collect
				HashMap<Integer,Value> lastHeap=new HashMap<Integer,Value>(prgList.get(0).getHeap().getContent());
				List<Integer> allSymTableAddrs = new ArrayList<Integer>();
				HashMap<Integer, Value> heapAddrs = prgList.get(0).getHeap().getContent();
				prgList.forEach((prg) -> allSymTableAddrs.addAll(prg.getAddrFromSymTable(prg.getTable().getContent().values(), prg.getHeap().getContent().values())));
				prgList.get(0).getHeap().setContent(prgList.get(0).unsafeGarbageCollector(
						 allSymTableAddrs,
						 heapAddrs));
				
				while(!lastHeap.equals(prgList.get(0).getHeap().getContent()))
				{
					lastHeap=new HashMap<Integer,Value>(prgList.get(0).getHeap().getContent());
					List<Integer> newSymTableAddrs = new ArrayList<Integer>();
					prgList.forEach((prg) -> newSymTableAddrs.addAll(prg.getAddrFromSymTable(prg.getTable().getContent().values(), prg.getHeap().getContent().values())));
					prgList.get(0).getHeap().setContent(prgList.get(0).unsafeGarbageCollector(
							 newSymTableAddrs,
							 lastHeap));
				}
				
				prgList.forEach(prg ->{
					try {
						repo.logPrgStateExec(prg);
					} catch (MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setOutputList();
		this.setFileTableList();
		this.setHeapTable();
		prgList=removeCompletedPrg(repo.getPrgList());
		this.setPrgStateCount();
		this.setPrgStateIDList();
		this.setDependencies();
		}
		executor.shutdownNow();
		
		repo.setPrgList(prgList);
		
		if(prgList.size()<=0)
		{
			prgStateCount.setText("PrgState count: 0");
		}
	}
	
}
