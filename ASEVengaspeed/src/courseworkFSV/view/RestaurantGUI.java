package courseworkFSV.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import courseworkFSV.controller.ExitListener;
import courseworkFSV.controller.ReportListener;
import courseworkFSV.interfaces.Observer;
import courseworkFSV.model.Order;
import courseworkFSV.model.Restaurant;

public class RestaurantGUI extends JFrame implements Observer {

	// Restaurant class oject required to access orders Map in that class
	private Restaurant restaurant;
	// define a new Map to copy the Map populated in Restaurant class
	private Map<Integer, List<Order>> ihm;
	// declaring gui objects to be used in functions later in this class
	private JComboBox<String> tableIdChoice;
	private JButton report;
	private JButton exitSystem;
	

	// declaring table GUI objects used for showing each table items delivered
	private TableGUI tableView1;
	private TableGUI tableView2;
	private TableGUI tableView3;
	
	private HatchGUI hatchView;

	// JFrame dimensions for using in JFrames created later
	public static final int FRAME_WIDTH = 550;
	public static final int FRAME_HEIGHT = 500;
	// Setting flow layout for JFrame
	FlowLayout flow = new FlowLayout(FlowLayout.LEFT);

	private JTextArea textOrders;

	/**
	 * 
	 * @param r
	 *            is a Restaurant object needed by the constructor to copy the
	 *            Map
	 */
	public RestaurantGUI(Restaurant r) {

		restaurant = r;
		// getOrders() function from Restaurant class called to populate the Map
		ihm = restaurant.getOrders();
		initialize();
	}

	/**
	 * initialize component
	 */
	public void initialize() {

		// Setting title, dimensions and default operation on close

		setTitle("Order Information for tables");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Using a label and setting the font style
		JLabel idlabel = new JLabel("Please select table id:");
		idlabel.setFont(new Font("Arial", Font.ITALIC, 16));

		// panel for better arrangements
		JPanel pnlNorth = new JPanel();

		// using a combobox
		tableIdChoice = new JComboBox<String>();

		// using the layout declared earlier
		pnlNorth.setLayout(flow);

		// adding GUI elements
		pnlNorth.add(idlabel);
		// adding choices to the combobox
		tableIdChoice.addItem("Tables: ");
		tableIdChoice.addItem("table 1");
		tableIdChoice.addItem("table 2");
		tableIdChoice.addItem("table 3");
		// adding combobox element
		pnlNorth.add(tableIdChoice);

		// button for displaying report summary
		report = new JButton("Do the report summary");
		
		pnlNorth.add(report);

		// red button for exiting system
		exitSystem = new JButton(" E X I T  System");
		exitSystem.setBackground(Color.RED);
		
		pnlNorth.add(exitSystem);

		// jpanel goes to north
		add(pnlNorth, BorderLayout.NORTH);

		// text area for displaying orders
		textOrders = new JTextArea();
		// making text area scrollable
		JScrollPane jScrollPaneAllOrders = new JScrollPane(textOrders);

		add(jScrollPaneAllOrders, BorderLayout.CENTER);

		// add observer
		restaurant.getKitchen().addObserver(this);
		// creating tableGUI objects for each table
		tableView1 = new TableGUI(1, restaurant.getTables());
		tableView2 = new TableGUI(2, restaurant.getTables());
		tableView3 = new TableGUI(3, restaurant.getTables());

		// set their screen location
		tableView1.setLocation(550, 0);
		tableView2.setLocation(550, 250);
		tableView3.setLocation(550, 500);
		
		//hatch
		hatchView = new HatchGUI(restaurant.getHatch());
		hatchView.setLocation(0, 500);
	}

	/**
	 * Event is triggered when user clicks on a combobox option, what to do next is in this function
	 */
	public void report() {

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
		tblId = this.getTableIdChoice();
		// }

		if (tblId == 0) {
			return;
		}

		int key = tblId;
		// if selected table is in the Map, then...
		if (this.getRestaurant().getOrders().containsKey(key)) {

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
			List<Order> tabl = this.getRestaurant().getOrders().get(key);
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

	
	@Override
	/**
	 * observer pattern update method
	 */
	public void update() {
		textOrders.setText("");
		for (Order order : restaurant.getKitchenOrders()) {
			textOrders.append("Table #" + order.getTableId() + ": "
					+ order.toString() + "\n");
		}
	}

	public void setListener(ReportListener reportListener) {
		// event is triggered by selecting it
		report.addActionListener(reportListener); 
		
		// when user selects combo box element, event is triggered
		tableIdChoice.addActionListener(reportListener);
		
		exitSystem.addActionListener(new ExitListener());
	}

	public int getTableIdChoice() {
		return tableIdChoice.getSelectedIndex();
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
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
