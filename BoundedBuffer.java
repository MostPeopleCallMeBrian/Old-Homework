//package Semaphores.boundedbuffer;

import java.util.concurrent.Semaphore;

/**
 * BoundedBuffer.java
 *
 * This program implements the bounded buffer with semaphores.
 * Note that the use of count only serves to output whether
 * the buffer is empty of full.
 */
public class BoundedBuffer implements Buffer {

    private static final int BUFFER_SIZE = 5;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    public static int count;
    public int in, out;
    public static Object[] buffer;

    public BoundedBuffer() {
        // buffer is initially empty
        count = 0;
        in = 0;
        out = 0;

        buffer = new Object[BUFFER_SIZE];

        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
    }

    // producer calls this method
    // takes an object as an argument so no need to change this to job
    public void insert(Object item) {
        try {
            empty.acquire();
            mutex.acquire();
        } catch (Exception e) {
          System.out.println(e);
        }
        // add an item to the buffer
        ++count;
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;

        if (count == BUFFER_SIZE) {
            System.out.println("Producer Entered " + item + " Buffer FULL");
        } else {
            System.out.println("Producer Entered " + item + " Buffer Size = " + count);
        }

        mutex.release();
        full.release();
    }

    // consumer calls this method
    public Object remove() {
        try {
            full.acquire();
            mutex.acquire();
        } catch (Exception e) {
        }

        // remove an item from the buffer
        --count;
        
        Object item = buffer[out];
        //buffer[out]=null;
        out = (out + 1) % BUFFER_SIZE;
        System.out.println(out);

        if (count == 0) {
            System.out.println("Consumer Consumed " + item + " Buffer EMPTY");
        } else {
            System.out.println("Consumer Consumed " + item + " Buffer Size = " + count);
        }

        mutex.release();
        empty.release();

        return item;
    }
}
