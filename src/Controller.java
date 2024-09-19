package src;
import java.util.concurrent.Semaphore;

public class Controller {
    public static void main(String[] args) {
        Semaphore available = new Semaphore(1); //Represents number of available monitors (just one...)
        Semaphore studentQueue = new Semaphore(3); //Number of chairs in the waiting isle

        Monitor monitor = new Monitor(available,studentQueue);
        Student student1 = new Student(available, studentQueue, "Student 1");
        Student student2 = new Student(available, studentQueue, "Student 2");
        Student student3 = new Student(available, studentQueue, "Student 3");
        Student student4 = new Student(available, studentQueue, "Student 4");

        monitor.start();
        student1.start();
        student2.start();
        student3.start();
        student4.start();

    }
}
