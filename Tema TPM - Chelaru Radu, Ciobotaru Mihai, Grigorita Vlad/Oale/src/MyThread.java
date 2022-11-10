import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread{

    public ReentrantLock lock;
    public boolean satisfied = false;

    public MyThread(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while(!satisfied){
            lock.lock();
            getPortion();
            lock.unlock();
        }
    }

    public void getPortion() {
        if(Main.capacity != 0) {
            Main.capacity -= 1;
            satisfied = true;
            Main.satisfiedPeople++;
            System.out.println(this.getName() + " I am satisfied");
        }
    }
}
