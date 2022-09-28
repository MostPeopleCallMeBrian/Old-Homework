/**
 * Auto Generated Java Class.
 */
import java.util.Random;
import java.lang.*;
import java.math.*;

  
public class Job implements Runnable{

  //Random snooze = new Random();
  //SleepUtilities task;
  long arrivalTime;
  long waitTime;
  long startTime;
  long turnaroundTime;
  long serviceTime;
  public double time = 90 * -Math.log(Math.random());
  public long wait = (long)time;
  
  //this needs to store time informations so that the turnaround time, arrival time, wait time, and end time can be calculated.
  
  //arrival time in our program is the time that the job was created.

  //start time-arrival time=wait time
  //just need to make it calculate the start time when the job is donez
  
  public void create(){
      //Job=this.Job;
      arrivalTime = System.currentTimeMillis();
      //return Job;
  }
  
  
    public void run(){
      
      //calc the wait time here, the start time is when the run() starts, start-arrival=wait
      startTime=System.currentTimeMillis();
      waitTime=startTime-arrivalTime;
  //The actual task is just to wait, and the prof's sleeputilities class convienently has a method!
  //SleepUtilities.nap();
      
             try {Thread.sleep(wait);}
            catch (InterruptedException e) {}
  //after it does the task, the turnaround time will be the time then, minus the start time, plus the wait time
  serviceTime =System.currentTimeMillis()-startTime;
  turnaroundTime=serviceTime+waitTime;

  }
}


