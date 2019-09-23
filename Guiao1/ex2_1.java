/*
Modifique o programa para as N threads terem acesso a um unico objecto de uma classe
Counter. Cada thread devera agora incrementar I vezes o contador. Escreva duas versoes:
uma em que cada thread invoca um metodo increment da classe Counter e outra em
que as threadas acedem directamente a uma variavel de instancia.
**/
import java.lang.Runnable;

public class ex2_1 extends Thread{

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

  public ex2_1(int value,int tNum,helper hpi){
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
        myThreads[k] = new Thread(new ex2_1(increments,k,hp));
        myThreads[k].start();
      }

      for(int k = 0; k < nThreads ; k++)
      {
        try{
          myThreads[k].join();
        }catch(Exception e){System.out.println(e);}
      }

      System.out.println("Expected Counter value:" + (nThreads * increments));
      System.out.println("Actual Counter value:" + hp.getCounter());
    }
    else
      System.out.println("Please indicate a value for N and I on function call");
  }
}
