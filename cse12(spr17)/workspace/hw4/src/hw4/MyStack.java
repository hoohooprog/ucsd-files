package hw4;

public class MyStack<T> implements Stack_QueueInterface<T> {

	private DoubleEndedLL<T> myStackLL;
	
	
	public MyStack(){
		
		myStackLL = new DoubleEndedLL();
	}
	
	
	@Override
	public boolean isEmpty() {
		
		return myStackLL.isEmpty();
	}
	
	
	@Override
	public void addElement(T newItem){
		
		myStackLL.addFirst(newItem);
	}
	
	
	@Override
	public T removeElement(){
		
		return myStackLL.removeFirst();
				
	}
	
	
	@Override
	public int size(){
		
		return myStackLL.size();
		
	}
	
	
	@Override
	public T peek(){
		
		return (T) myStackLL.getHead().getNext().getElement();
	}
	
	
}
