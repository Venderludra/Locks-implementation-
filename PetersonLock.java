public class PetersonLock implements Lock {
    private volatile boolean[] flag = new boolean[2]; //same as LockOne - tracks who is interested
    private volatile int victim; //same as LockTwo - tracks who should wait

    @Override
    public void lock() {
        int i = ThreadID.get();      //get current thread's ID
        int j = 1 - i;               //get the OTHER thread's ID

        flag[i] = true;              //current thread expresses its interest of acquiring the lock
        victim = i;                  //but it'll let the other thread go first (aka making itself the victim - LOckTwo property)

        while (flag[j] && victim == i) {
            //remember, we wait ONLY IF the other thread is interested AND I'm (current thread is) still the victim
            System.out.println("Thread " + i + " waiting...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + i + " acquired the lock");
    }

    @Override
    public void unlock() {
        int i = ThreadID.get();
        flag[i] = false;             //current thread is no longer interested in entering the CS
        System.out.println("Thread " + i + " released the lock");
    }
}