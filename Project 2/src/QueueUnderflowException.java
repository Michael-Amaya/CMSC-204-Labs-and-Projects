
/** Exception that is thrown when 
 *  dequeue is called on an empty queue
 * 
 * @author Michael Amaya
 *
 */
public class QueueUnderflowException extends Exception {
	
	/** Default constructor to show a default message
	 *  if dequeue is used on an empty queue */
	public QueueUnderflowException() {
		this("The queue you're using dequeue on is empty!");
	}
	
	/** Constructor that allows for a custom message if
	 *  dequeue is used on an empty queue
	 *  
	 *  @param msg The message to show if dequeue is used on an empty queue
	 */
	public QueueUnderflowException(String msg) {
		super(msg);
	}
}
