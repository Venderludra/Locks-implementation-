public class LockOne implements Lock {
    private volatile boolean[] flag = new boolean[2];
    //the flag shows whether or not a thread is interested in entering the resources - 0: false; 1: true

    @Override
    public void lock() {
        int i = ThreadID.get(); //get the threadID of the current thread
        int j = 1-i; //get the OTHER thread's threadID
        flag[i] = true; //the current thread (should) show that it's interested in entering the critical section
        
        while (flag[j]) { //but while the other thread is interested
            System.out.println("Thread " + i + " waiting..."); //show that the current thread is interested (i.e wait)
            try {
                Thread.sleep(100);
                /* Thread.sleep() is not required for lock correctness
                    what this basically does is that it pauses the thread for 100ms so you can see the waiting output and reduce CPU usage during the busy-wait */
            } catch (InterruptedException e) {
                //this part of the code is needed jsut incase the Thread.sleep() needs to sleep early
                e.printStackTrace();
                //this is prints the error details and where the exception occurred
            }
        }
    }

    @Override
    public void unlock() {//this is the part now when we remove the lock 
        int i = ThreadID.get(); //look at the current thread 
        flag[i] = false; //this is the part where we set the current thread to indicate that it isn't fully  the interest or  
        System.out.println("Thread " + i + " released the lock");
    }
}