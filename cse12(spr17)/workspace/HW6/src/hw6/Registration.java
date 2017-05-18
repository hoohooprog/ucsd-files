package hw6;

public class Registration implements Comparable<Registration> {
	
	private Student student;
	private Course course;
	private int coins;
	private long timestamp;
	
	public Registration(Student student, Course course, int coins) {
		
		this.student = student;
		this.course = course;
		this.coins = coins;
	}
	
	
	public Student getStudent() {
		
		return student; 
	}
	
	
	public Course getCourse() {
		
		return course;
	}
	
	
	public int getCoins() {
		
		return coins;
	}
	
	
	/**
	 * Compares this Student with another Student, by comparing their 
	 * course coins
	 * If the coins of this is less, returns a negative integer. If the
	 * coins of the Student received is less, returns a positive integer.
	 * If both Students have the same number of coins, returns zero.
	 * @param o Student to be compared with
	 * @return Result of the comparison
	 */
	@Override
    public int compareTo( Registration o ) {
	    
		if (this.getCoins() < o.getCoins()){ 
		
		    return -1; 
		}
		else if (this.getCoins() > o.getCoins()){
			
			return 1;
		}
		else{
			return 0;
		}
	    
    }
	
	/**
	 * Sets the timestamp inside this registration to be the current time in nano seconds.
	 */
	public void setTimestamp() {
		timestamp = System.nanoTime();
	}

}
