public class Worker extends Thread{
    private SharedCounter _counter;

    public Worker(SharedCounter counter){
        _counter = counter;
    }

    @Override
    public void run(){
        for(int i = 0 ; i < 5 ; i++){
            _counter.increment();
        }
    }
}