public class Main {//tests the implementation of the different locks

    public static void testLockConcurrent(Lock lock, String lockName) {
        ThreadID.reset();//resetting 
        System.out.println("\n========== Testing " + lockName + " (CONCURRENT) ==========\n");
        
        SharedCounter counter = new SharedCounter(lock);
        Worker t0 = new Worker(counter);
        Worker t1 = new Worker(counter);

        t0.start();
        t1.start();
        
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("========== " + lockName + " Complete ==========\n");
    }

    public static void testLockSequential(Lock lock, String lockName) {
        ThreadID.reset();//resetting
        System.out.println("\n========== Testing " + lockName + " (SEQUENTIAL) ==========\n");
        
        SharedCounter counter = new SharedCounter(lock);
        Worker t0 = new Worker(counter);
        Worker t1 = new Worker(counter);

        t0.start();
        try {
            t0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("========== " + lockName + " Complete ==========\n");
    }

    public static void main(String[] args) {
        //this shows LockOne in sequential order (how it's supposed to work)
        testLockSequential(new LockOne(), "LockOne"); //works
        
        //this shows then LockOne FAILING (due to the concurrent thread) - gotta get the ctrl+c ready because this will be an infinite loop/will hang
        // testLockConcurrent(new LockOne(), "LockOne");
        
        //testLockConcurrent(new LockTwo(), "LockTwo"); //produces that hand because it has no one to release it from being a victim
        testLockConcurrent(new PetersonLock(), "Peterson's Lock"); //work
    }
}