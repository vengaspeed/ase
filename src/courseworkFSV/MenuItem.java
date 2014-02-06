package courseworkFSV;



public class MenuItem implements Comparable<MenuItem>{
	
	private String name;
	private double price;
	private Category category;
	
	public MenuItem(String n, double p, Category c){
		name = n;
		price = p;
		category = c;
	}
	
	
	public boolean equals(Object other){
		return false;
		
	}
	
	public int hashCode(){
		return 0;
		 
	}
	@Override
	public int compareTo(MenuItem arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
