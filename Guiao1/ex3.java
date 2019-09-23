/*
Fazendo a thread principal escrever o valor do contador depois de as outras threads terem
terminado, corra varias vezes o programa, para diferentes valores de ´ N e I e observe o
resultado produzido, em ambas as versoes.
**/
import java.lang.Runnable;

public class ex3 extends Thread{

  static class helper{
    volatile int counter;

    public helper(){
      counter =  0;
    }

    public void increment(){
      counter++;
    }

    public int getCounter(){
      return counter;
    }
  }

  private int n;
  private int threadNum;
  private helper hlp;

  public ex3(int value,int tNum,helper hpi){
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
        myThreads[k] = new Thread(new ex3(increments,k,hp));
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
