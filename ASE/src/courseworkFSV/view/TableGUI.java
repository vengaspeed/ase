package courseworkFSV.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import courseworkFSV.interfaces.Observer;
import courseworkFSV.model.Order;
import courseworkFSV.model.Tables;

/**
 * Table inferface represents a VIEW layer of table
 *
 */
public class TableGUI extends JFrame implements Observer{
	
	private Tables tables;
	private int id;
	private JTextArea textOrders;
	
    //list of item in table
    private List<Order> items;
	
	public TableGUI(int id, Tables tables){
		this.tables = tables;
		this.id = id;	
		tables.addObserver(this);		
		setTitle("Table " + id);
		items = new ArrayList<Order>();
		initialize();
	}	
	
	private void initialize(){		
		
		textOrders = new JTextArea();
		JScrollPane jScrollPaneOrders = new JScrollPane(textOrders);
		add(jScrollPaneOrders, BorderLayout.CENTER);
		setSize(300, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	@Override
	/**
	 * update function
	 */
	public void update() {
		if (tables.get(id) != null){
			//check and add order that has not yet in table
			//but delivered to this table
			for (Order order: tables.get(id)){
				boolean found = false;
				for (Order item: items){
					if (order.getOrderId() == item.getOrderId()){
						found = true;
						break;
					}
				}
				if (!found){
					items.add(order);
					textOrders.append(order.toString() + "\n");
				}
			}//end for
		}//end if
	}
}