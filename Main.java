public class Main {

    public static void main(String[] args) {
        
        Lock lock = new LockTwo();
        Lock lock2 = new LockOne();
        
        SharedCounter counter = new SharedCounter(lock);

        Worker t0 = new Worker(counter);
        Worker t1 = new Worker(counter);

        t0.start();
        t1.start();
    }
}