public class LockTwo implements Lock{
    private volatile int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        victim = i;

        while (victim == i) {
            // Busy wait
             System.out.println("Thread " + i + " waiting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + i + " acquired the lock");
    }
        
    @Override
    public void unlock() {
        // Nothing to do
        int i = ThreadID.get();
        System.out.println("Thread " + i + " released the lock");
    }
}