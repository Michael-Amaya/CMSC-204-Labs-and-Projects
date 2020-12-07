import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	
	private Queue<Integer> queue;
	private Random random = new Random();

	public CarQueue() {
		queue = new LinkedList<>();
		
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
		queue.add(random.nextInt(4));
	}
	
	public void addToQueue() {
		class AddRandom implements Runnable {

			@Override
			public void run() {
				while (true) {
					queue.add(random.nextInt(4));
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		new Thread(new AddRandom()).start();
	}
	
	public int deleteQueue() {
		return queue.remove();
	}
}
