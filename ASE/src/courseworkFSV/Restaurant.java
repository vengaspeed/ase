package courseworkFSV;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Restaurant {
	private Map<Integer, List<Order>> orders;
	private Menu menu;
	
	public Restaurant(){
		orders = new HashMap<Integer, List<Order>>();
		menu = new Menu();
	}
	
	public void importMenu(String filename){
		
	}
	
	public void importOrders(String filename){
		
	}
	
	public void export(String filename){
		
	}
	
	public String getTableSummary(int tableId){
		return null;
		
	}
	
	public String getFrequencyReport(){
		return null;
		
	}
}
