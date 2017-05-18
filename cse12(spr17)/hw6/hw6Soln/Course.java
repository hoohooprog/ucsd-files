package hw6;

import java.util.LinkedList;
import java.util.List;

public class Course implements Course_Interface {
	
	private String courseName;
	private String courseCode;
	private MyPriorityQueue<Registration> waitlistQueue;
	private List<Student> roster;
	private int maxCapacity;
	
	public Course(String name, String code, int capacity) {
	
		courseName = name;
		courseCode = code;
		maxCapacity = capacity;
		roster = new LinkedList<Student>();
		waitlistQueue = new MyPriorityQueue<Registration>();
		
	}
	
	
	@Override
	public String toString() {
		return courseCode;
	}
	
	/**
	 * Getter for course name
	 * @return course name
	 */
	public String getCourseName(){
		
		return courseName;
	}
	
	
	/**
	 * Getter for course code
	 * @return course code
	 */
	public String getCourseCode(){
		
		return courseCode;
	}
	
	
	/**
	 * Getter for course capacity
	 * @return course capacity
	 */
	public int getCourseCapacity(){
		
		return maxCapacity;
	}
	
	
	/**
	 * Getter for Course Roster
	 * @return course roster
	 */
	public List<Student> getCourseRoster(){
		
		return roster;
	}
	
	
	/**
	 * Checks whether the course enrollment has reached its capacity
	 * @return Returns true if the number of enrolled students is equal to 
	 * capacity, false otherwise
	 */
	public boolean isFull(){
		
	    if(roster.size() == maxCapacity){
	    	return true;
	    }
	    else{
	    	return false;
	    }
	}
	
		
	/**
	 * Enqueues the student to the waitlist for this course and deducts 
	 * coins from the course coins of the student
	 * @param student Student to be added to the waitlist
	 * @param coins Number of coins the student offers for this course
	 *
	 */
	public void addToWaitlist(Registration r){
		
		if (r.getStudent().getmyWaitlist().contains(this)){
			return;
		}
		else{
		    waitlistQueue.offer(r);
		    r.getStudent().deductCoins(r.getCoins());
		    r.getStudent().waitlistCourse(this);
		}
	}
	
	
	/**
	 * Enrolls the next student in the waitlist to the course.
	 * Does nothing if the waitlist is empty
	 */
	public Registration processWaitlist(){
		
		if (waitlistQueue.peek() == null){
			return null;
		}
		
		Registration studentRegist = waitlistQueue.poll();
		
		roster.add(studentRegist.getStudent());
		
		studentRegist.getStudent().enrollCourse(this);
		
		return (studentRegist);
		
	}
	
	
}
