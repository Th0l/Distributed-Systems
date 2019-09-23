/*
Escreva um programa que crie N threads, em cada uma escreva os numeros de 1 a I, e
depois espere que estas terminem
**/
import java.lang.Runnable;

public class ex1 extends Thread{
  private int n;
  private int threadNum;

  public ex1(int value,int tNum){
    n = value;
    threadNum = tNum;
  }

  public void run(){
    for(int i=0;i<n;i++)
      System.out.println("Thread " + (threadNum+1) + " is on iteration " + (i+1));
  }

  public static void main(String args[])
  {
    if(args.length != 0){
      int oof = Integer.parseInt(args[0]);
      int iii = Integer.parseInt(args[1]);

      Thread myThreads[] = new Thread[oof];

      for(int k = 0; k < oof ; k++)
      {
        myThreads[k] = new Thread(new ex1(iii,k));
        myThreads[k].start();
      }

      for(int k = 0; k < oof ; k++)
      {
        try{
          myThreads[k].join();
        }catch(Exception e){System.out.println(e);}
      }
    }
    else
      System.out.println("Please indicate a value for N and I on function call");
  }
}
