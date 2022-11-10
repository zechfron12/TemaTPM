import java.util.concurrent.locks.ReentrantLock;

public class Chef extends Thread{
    public ReentrantLock lock = new ReentrantLock();

    public Chef(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while(Main.satisfiedPeople < Main.people){
            lock.lock();
            fillOala();
            lock.unlock();
        }
    }

    public void fillOala() {
        if(Main.capacity == 0) {
            Main.capacity = Main.maxCapacity;
            System.out.println("Scarlatescu umple oala");
        }
    }
}
