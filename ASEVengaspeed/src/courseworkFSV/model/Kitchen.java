package courseworkFSV.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import courseworkFSV.interfaces.Observable;
import courseworkFSV.interfaces.Observer;

public class Kitchen extends ArrayList<Order> implements Observable {
	/** Observers of the tables */
	private Set<Observer> observers;
	private boolean finished = false;

	/** Log of the kitchen */
	private String kitchenLog = "";

	/** return the kitchen log */
	public String getKitchenLog() {
		return kitchenLog;
	}

	/** set the kitchen log */
	public void setKitchenLog(String action) {
		kitchenLog += action + "\n";
	}

	/**
	 * Set up the structure : LinkedList<Order>
	 */
	public Kitchen() {
		super();
		observers = new HashSet<Observer>();
	}

	public void setFinished() {
		finished = true;
	}

	public boolean getFinished() {
		return finished;
	}

	@Override
	public synchronized Order get(int index) {
		return super.get(index);
	}

	@Override
	public synchronized boolean add(Order o) {		
		boolean result = super.add(o);
		notifyObservers();
		return result;
	}

	@Override
	public synchronized Order remove(int index) {
		Order o = this.get(index);
		Order order = super.remove(index);
		notifyObservers();
		return order;
	}

	/**
	 * Notify the observers that something change.
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}

	}

	/**
	 * Attaches an observer
	 * 
	 * @param o
	 *            Observer to attach.
	 */
	public void addObserver(Observer o) {
		observers.add(o);

	}

	/**
	 * Detaches an observer
	 * 
	 * @param o
	 *            Observer to detach.
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);

	}

}
