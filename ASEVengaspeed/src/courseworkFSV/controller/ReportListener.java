package courseworkFSV.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import courseworkFSV.model.Order;
import courseworkFSV.view.RestaurantGUI;

public class ReportListener implements ActionListener {

	private RestaurantGUI restaurantGUI;
	
	public ReportListener(RestaurantGUI restaurantGUI) {
		this.restaurantGUI = restaurantGUI;
	}

	@Override
	/**
	 * Event is triggered when user clicks on a combobox option, what to do next is in this function
	 * @param e is ActionEvent object
	 */
	public void actionPerformed(ActionEvent e) {

		// event for button to exit the system
		if (e.getSource() instanceof JButton && 
				((JButton)e.getSource()).getText().equals(" E X I T  System")) {
			System.exit(1);
		}

		int tblId = 0;
		JFrame tableInfo;
		// we will use a table to show results, we declare first the column
		// names
		String[] columnNames = { "Item", "Amount", "Item Price",
				"Total Item Price" };
		// declaring table model
		DefaultTableModel table = new DefaultTableModel();
		// declaring JTable element
		JTable summary = new JTable(table);
		// adding table model
		summary.setModel(table);
		// attaching column names on table
		table.setColumnIdentifiers(columnNames);

		// get the index of JCombobox option selected by user
		// if (e.getSource() == tableIdChoice){
		tblId = restaurantGUI.getTableIdChoice();
		// }

		if (tblId == 0) {
			return;
		}

		int key = tblId;
		// if selected table is in the Map, then...
		if (restaurantGUI.getRestaurant().getOrders().containsKey(key)) {

			// using new JFrame for displaying results for only this table
			tableInfo = new JFrame("Summary for table " + key);
			tableInfo.setSize(RestaurantGUI.FRAME_WIDTH, RestaurantGUI.FRAME_HEIGHT);

			tableInfo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
			tableInfo.setLayout(flow);

			// using label to show which table is displayed
			JLabel tbl = new JLabel("TABLE " + key);
			// setting dimensions of JLabel and alignment
			tbl.setPreferredSize(new Dimension(490, 18));
			tbl.setHorizontalAlignment(JLabel.LEFT);
			// add label and Column names of table on the frame. Using a scroll
			// pane is essential
			tableInfo.add(tbl);
			tableInfo.add(summary.getTableHeader());
			tableInfo.add(new JScrollPane(summary));

			// calculating total cost, discount, discounted total, variables are
			// created to hold their initial values

			double totalcost = 0.00;
			double discount = 0.00;

			// All order for the selected table are stored in a list, each table
			// row is filled with a certain item orders
			List<Order> tabl = restaurantGUI.getRestaurant().getOrders().get(key);
			// for each one of the orders of an item for this table, details are
			// stored on a table row
			for (Order tableOrder : tabl) {
				Object[] a = new Object[4];
				a[0] = tableOrder.getMenuItemName();
				a[1] = tableOrder.getQuantity();
				a[2] = tableOrder.getItemPrice();
				a[3] = tableOrder.totalCost();
				table.addRow(a);

				// calculating cost and discount
				// if a product is ordered 3 or more times, there is a discount
				// of 10% in the total price
				totalcost += ((double) a[3]);
				if ((int) a[1] >= 3) {
					discount += (((double) a[3]) * 0.1);
				}
				totalcost = roundedTwoDecimals(totalcost);
				discount = roundedTwoDecimals(discount);

			}

			// write total, discount on Gui

			JLabel total = new JLabel(
					"Total for this table:                          "
							+ totalcost);
			JLabel discountAmount = new JLabel(
					"Discount:                                           "
							+ discount);
			JLabel discounted = new JLabel(
					"Discounted total:                             "
							+ roundedTwoDecimals(totalcost - discount));
			total.setPreferredSize(new Dimension(490, 18));
			discountAmount.setPreferredSize(new Dimension(490, 18));
			discounted.setPreferredSize(new Dimension(490, 18));
			total.setHorizontalAlignment(JLabel.LEFT);
			discountAmount.setHorizontalAlignment(JLabel.LEFT);
			discounted.setHorizontalAlignment(JLabel.LEFT);
			tableInfo.add(total);
			tableInfo.add(discountAmount);
			tableInfo.add(discounted);

			tableInfo.setVisible(true);
		}
	}
	/**
	 * Rounds double number to 2 decimal points
	 * 
	 * @param number
	 *            --> the number to be rounded to 2 decimal points
	 * @return number rounded
	 * 
	 */
	double roundedTwoDecimals(double number) {
		int a = (int) Math.round(number * 100);
		number = a / 100.0;

		return number;
	}

}
