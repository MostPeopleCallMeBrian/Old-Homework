//gonna write the raw data to a text file then calculate the stats based on it
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

//package Semaphores.boundedbuffer;

/**
 * This is the producer thread for the bounded buffer problem.
 */
import java.util.*;

public class Producer implements Runnable {
  static boolean j=false;
  static boolean i=false;
      public BoundedBuffer buffer;
  long programStartTime=System.currentTimeMillis();
//This needs to produce jobs into a buffer
    public Producer(BoundedBuffer b) {
        buffer = b;
        Job job;
    }

    public void run() {
        while (i==false) {
          if(j==false){
            programStartTime=System.currentTimeMillis();
            j=true;
          }
          //this needs to create the job, put it in the buffer, then sleep
        Job job = new Job();
        //make the producer only insert into the buffer if there's space for both consumers too
        if(buffer.count<=3){
        buffer.insert(job);
        //interarrival time
        job.create();
        long interArrivalTime=job.arrivalTime;
        }
        //sleeps here
        try {Thread.sleep(100);}
        catch (InterruptedException e) {}
        //10 minutes for testing
            if(System.currentTimeMillis()-programStartTime>300000){
              i=true;
            long programRunTime=System.currentTimeMillis()-programStartTime;
            System.out.println(programRunTime);
            try{
              File file = new File("C:\\Users\\Phillip\\statistics.txt");
            if (!file.exists()) {
            file.createNewFile();
          } 
              FileWriter fw = new FileWriter(file, true);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write("Total program run time: "+ programRunTime *100);
              bw.close();
              
              
            }
            
            catch(IOException e){
              e.printStackTrace();
            }
            
        }
}
    }
}