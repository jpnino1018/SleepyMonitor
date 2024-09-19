package src;

import java.util.concurrent.Semaphore;

public class Monitor extends Thread {
	
	private Semaphore available;
	private Semaphore studentQueue;
	
	public Monitor(Semaphore available, Semaphore studentQueue) {
		super();
		this.available = available;
        this.studentQueue = studentQueue;
	}
	
	public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		while (true) {
            if(studentQueue.availablePermits()<3){ //If the permits available in the queue are less than three it means there's people waiting in line
                try {
                    studentQueue.release(); //The queue releases a student when the monitor is done with the session
                    System.out.println("A student has started a session with the monitor");
                    sleep((int)(Math.random()+1)*5000);
                    System.out.println("** The session with the monitor has ended **");
                    available.release(); //The monitor also turns available when done with a session
                } 
                catch (InterruptedException e) {
                }
            }
            else{
                System.out.println("Sleeping. Please disturb!");
            }
			
		}
	}
}