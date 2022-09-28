//package Semaphores.boundedbuffer;

/**
 * This is the consumer thread for the bounded buffer problem.
 */
import java.util.*;
//gonna write the raw data to a text file then calculate the stats based on it
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class Consumer implements Runnable {
      public BoundedBuffer buffer;

    public Consumer(BoundedBuffer b) {
        buffer = b;

    }

    public void run() {

        while (true) {
         
             Job job = (Job) buffer.remove();

             if((job.wait)<=10){
              job.run();
            }
            
            else{
               System.out.println(job.wait);
               job.wait=job.wait-10;
               System.out.println(job.wait);
               try {Thread.sleep(10);}
               catch (InterruptedException e) {}
               buffer.insert(job);
            }

            try{
              File file = new File("C:\\Users\\Phillip\\statistics.txt");
            if (!file.exists()) {
            file.createNewFile();
         } 
              FileWriter fw = new FileWriter(file, true);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write(job + " Wait time: " +job.waitTime +'\n');
              bw.write(" Service time: " +job.serviceTime +'\n');
              bw.write(" Turnaround time: " +job.turnaroundTime +'\n');
              bw.close();
              
              
            }
            catch(IOException e){
              e.printStackTrace();
            }
            
            //i++;
            //System.out.println("Consumer received message:" + message);
        }
    }
}


