package courseworkFSV.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextArea;

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
	 * Export the summary of the restaurant
	 */
	public void report() {
		restaurant.export("testExport.txt");
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
	
}
