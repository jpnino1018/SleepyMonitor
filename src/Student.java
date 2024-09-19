package src;
import java.util.concurrent.Semaphore;

public class Student extends Thread {
	
	private Semaphore available;    
	private Semaphore studentQueue;         
	private String name;             
	
	public Student(Semaphore available, Semaphore studentQueue, String name) {
		super();
		this.available= available;
		this.studentQueue = studentQueue;
        this.name = name;
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println(name + " has arrived to the waiting aisle");
                if(studentQueue.availablePermits()>0){ //If there are still chairs the student may join the queue
                    studentQueue.acquire();

                    System.out.println(name + " sat in the waiting aisle");
					while(true){
						try{
							available.acquire(); // The monitor's availability is acquired by one of the students
							System.out.println(" ** The monitor has started a session with " + name +" **");
							sleep(((int)Math.random()+1)*5000);
							break;
						}
						catch (InterruptedException e) {
						}
					}
                }
                else{ //If there are no permits available in the queue the student will leave and come back later
                    System.out.println(name + " didn't find any chairs in the waiting isle"); 
                }
                System.out.println(name + " left the building to work on something else...");
                sleep(((int)Math.random()+1)*30000);

			} catch (InterruptedException e) {
			}
		}
	}
}

