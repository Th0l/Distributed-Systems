/*
Implemente uma classe Banco que ofereca os metodos de consulta, credito e debito de
valores sobre um numero fixo de contas (com saldo inicial nulo). Utilize exclusao mutua ao
nıvel do objecto Banco.
**/

import java.lang.Runnable;

public class ex2 extends Thread{

  private int value;
  private banco bp;

  public ex2(int k){value=k;}
  public ex2(int k,banco b){value=k;bp=b;}

  public void run(){
    if((value & 1) == 0){
      for(int i=0;i<100000;i++)
        bp.depositar(1,5);
    }
    else{
      for(int i=0;i<100000;i++)
        bp.levantar(1,5);
    }
  }

  public void manager(int n){
    Thread myThreads[] = new Thread[n];
    banco bn = new banco(n);

    System.out.println("Start money: " + bn.consultar(1));

    //synchronized(bn){
      for(int k = 0; k < n ; k++)
      {
        myThreads[k] = new Thread(new ex2(k+1,bn));
        myThreads[k].start();
      }

      for(int k = 0; k < n ; k++)
      {
        try{
          myThreads[k].join();
        }catch(Exception e){System.out.println(e);}
      }
    //}

    System.out.println("End money: " + bn.consultar(1));
  }

  public static void main(String args[]){
    if(args.length != 0){
      ex2 test = new ex2(0);
      test.manager(Integer.parseInt(args[0]));
    }
    else{
        System.out.println("Please indicate a value for N on function call");
    }
  }

}
