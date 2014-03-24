package courseworkFSV.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import courseworkFSV.interfaces.Observer;
import courseworkFSV.model.Hatch;
import courseworkFSV.model.Order;

/**
 * HatchGUI inferface represents a VIEW layer of Hatch
 * 
 */
public class HatchGUI extends JFrame implements Observer {

	private Hatch hatch;
	private JTextArea textOrders;

	// list of item in table
	private List<Order> items;

	public HatchGUI(Hatch hatch) {
		this.hatch = hatch;
		hatch.addObserver(this);
		setTitle("Hatch");
		items = new ArrayList<Order>();
		initialize();
	}

	private void initialize() {

		textOrders = new JTextArea();
		JScrollPane jScrollPaneOrders = new JScrollPane(textOrders);
		add(jScrollPaneOrders, BorderLayout.CENTER);
		setSize(550, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	/**
	 * update function
	 */
	public void update() {
		textOrders.setText("");
		if (hatch.size() != 0) {
			// check and add order that has not yet in table
			// but delivered to this table
			for (Order order : hatch) {
				textOrders.append(order.toString() + "\n");
			}// end for
		}// end if
	}
}