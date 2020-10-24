
public class CollisionException extends Exception {
	public CollisionException() {
		this("There was a collision!");
	}
	
	public CollisionException(String msg) {
		super(msg);
	}
}
