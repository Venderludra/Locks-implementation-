public class ThreadID {

    private static int nextID = 0;

    private static ThreadLocal<Integer> threadID =
            ThreadLocal.withInitial(() -> nextID++);

    public static int get() {
        return threadID.get();
    }
}