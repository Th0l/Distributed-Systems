/*
Implemente uma classe Banco que ofereca os metodos de consulta, credito e debito de
valores sobre um numero fixo de contas (com saldo inicial nulo). Utilize exclusao mutua ao
nıvel do objecto Banco.
**/

import java.lang.Runnable;

public class ex3 extends Thread{

  private int value;
  private banco bp;

  public ex3(int k){value=k;}
  public ex3(int k,banco b){value=k;bp=b;}

  public void run(){
    if((value & 1) == 0){
        bp.levantar(2,1000);
    }
    else{
        bp.transferencia(1,2,1000);
    }
  }

  public void manager(int n){
    Thread myThreads[] = new Thread[n];
    banco bn = new banco(n);
    bn.depositar(1,1000);

    System.out.println("Acc1 start money: " + bn.consultar(1));
    System.out.println("Acc2 start money: " + bn.consultar(2));

    //synchronized(bn){
      for(int k = 0; k < n ; k++)
      {
        myThreads[k] = new Thread(new ex3(k+1,bn));
        myThreads[k].start();
      }

      for(int k = 0; k < n ; k++)
      {
        try{
          myThreads[k].join();
        }catch(Exception e){System.out.println(e);}
      }
    //}

    System.out.println("Acc1 end money: " + bn.consultar(1));
    System.out.println("Acc2 end money: " + bn.consultar(2));
  }

  public static void main(String args[]){
    if(args.length != 0){
      ex3 test = new ex3(0);
      test.manager(Integer.parseInt(args[0]));
    }
    else{
        System.out.println("Please indicate a value for N on function call");
    }
  }

}
