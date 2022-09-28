//package Semaphores.boundedbuffer;

/**
 * This creates the buffer and the producer and consumer threads.
 *
 */
public class Factory {

    public static void main(String args[]) {
        //long programStartTime=System.currentTimeMillis();
        
        //while((programStartTime-System.currentTimeMillis())<120){
        BoundedBuffer server = new BoundedBuffer();

        // now create the producer and consumer threads
        Thread producerThread0 = new Thread(new Producer(server));
        Thread producerThread1 = new Thread(new Producer(server));
        Thread consumerThread0 = new Thread(new Consumer(server));
        Thread consumerThread1 = new Thread(new Consumer(server));
        
        producerThread0.start();
        //producerThread1.start();
        consumerThread0.start();
        //consumerThread1.start();


}
    }
