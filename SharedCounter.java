public class SharedCounter{
    private volatile int counter = 0;
    private Lock lock;
    
    public SharedCounter(Lock lock){
        this.lock = lock;
    }

    public void increment(){
        lock.lock();
        // ========Critical section ========
        try{
        counter++;
        System.out.println("Thread " + ThreadID.get() +
                           " incremented counter to " + counter);
        }
        // ====================================
        // Exit Section
        finally{
            lock.unlock();
        }
    }
}