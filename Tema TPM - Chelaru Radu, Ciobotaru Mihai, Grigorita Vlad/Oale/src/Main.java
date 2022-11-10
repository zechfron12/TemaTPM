import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static volatile int capacity = 3;
    public static volatile int maxCapacity = 3;
    public static volatile int people = 10;
    public static volatile int satisfiedPeople = 0;


    public static void main(String[] args) throws InterruptedException {

        ReentrantLock sharedLock = new ReentrantLock();
        List<MyThread> bastinasti = new ArrayList<>();
        Chef scarlatescu = new Chef(sharedLock);
        scarlatescu.start();

        for (int i = 0; i < people; i++) {
            MyThread bastinas = new MyThread(sharedLock);
            bastinasti.add(bastinas);
            bastinas.start();
        }

        bastinasti.forEach( bastinas -> {
            try {
                bastinas.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        scarlatescu.join();

        System.out.println(capacity);
    }
}