/*
Modifique o exercıcio anterior – incremento concorrente de um contador partilhado —- de
modo a garantir a execucao correcta do programa.
**/
import java.lang.Runnable;

public class ex1 extends Thread{

  static class helper{
    volatile int counter;

    public helper(){
      counter =  0;
    }

    public synchronized void increment(){
      counter++;
    }

    public synchronized int getCounter(){
      return counter;
    }
  }

  private int n;
  private int threadNum;
  private helper hlp;

  public ex1(int value,int tNum,helper hpi){
    n = value;
    threadNum = tNum;
    hlp = hpi;
  }

  public void run(){
    for(int i=0;i<n;i++)
      hlp.increment();
  }

  public static void main(String args[])
  {
    if(args.length != 0){
      int nThreads = Integer.parseInt(args[0]);
      int increments = Integer.parseInt(args[1]);

      Thread myThreads[] = new Thread[nThreads];
      helper hp = new helper();

      for(int k = 0; k < nThreads ; k++)
      {
        myThreads[k] = new Thread(new ex1(increments,k,hp));
        myThreads[k].start();
      }

      for(int k = 0; k < nThreads ; k++)
      {
        try{
          myThreads[k].join();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Counter value at the exit point of thread " + (k+1) + ": " + hp.getCounter());
      }
      System.out.println("Expected Final Counter value: " + (nThreads * increments));
      System.out.println("Actual Final Counter value: " + hp.getCounter());
    }
    else
      System.out.println("Please indicate a value for N and I on function call");
  }
}
