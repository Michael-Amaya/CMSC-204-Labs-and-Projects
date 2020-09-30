/** This Exception occurs when enqueue is used
 *  on a full queue
 * 
 * @author Michael Amaya
 *
 */
public class QueueOverflowException extends Exception {
	
	/** Default constructor to tell the user
	 *  that enqueue was used on a full queue */
	public QueueOverflowException() {
		this("The queue you used enqueue on is full!");
	}
	
	/** Constructor that allows for a custom
	 *  message if enqueue is used on a full queue
	 *  
	 *  @param msg The custom message to show if enqueue is used on a full queue
	 */
	public QueueOverflowException(String msg) {
		super(msg);
	}
}
