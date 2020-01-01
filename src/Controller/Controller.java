package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Exceptions.MyException;
import Model.ADTs.PrgState;
import Model.Values.Value;
import Repository.MyIRepo;

public class Controller implements MyIController{
	MyIRepo repo;
	ExecutorService executor;
	boolean step=true;
	
	public Controller(MyIRepo r)
	{
		repo=r;
	}

	public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException
	{
		/*prgList.forEach(prg ->{
			try {
				System.out.println(prg.toString());
				repo.logPrgStateExec(prg);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
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
		
		/*prgList.forEach(prg ->{
			try {
				System.out.println(prg.toString());
				repo.logPrgStateExec(prg);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
		repo.setPrgList(prgList);

	}
	
	@Override
	public void allStep()
	{
		executor = Executors.newFixedThreadPool(2);
		
		List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
		 while(prgList.size() > 0){
			 System.out.println("Nr. of active threads: "+Integer.toString(prgList.size()));
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
					System.out.println(prg.toString());
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
		 prgList=removeCompletedPrg(repo.getPrgList());
		}
		executor.shutdownNow();
		
		prgList.forEach(prg ->{
			try {
				System.out.println(prg.toString());
				repo.logPrgStateExec(prg);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		repo.setPrgList(prgList);
	}

	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
	{
		return inPrgList.stream()
				 .filter(p -> p.isNotCompleted())
				 .collect(Collectors.toList());
	}
	
	@Override
	public boolean displayCurrent() {
		if (step==false)
			step=true;
		else
			step=false;
		return step;
	}

	@Override
	public boolean getFlagState() {
		return step;
	}
	
	
}
