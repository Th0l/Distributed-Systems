public class banco{

  private double contas[];

  public banco(int n){
    contas = new double[n];
  }

  public synchronized double consultar(int n){
    return contas[n-1];
  }

  public synchronized void depositar(int n,int montante){
    contas[n-1] += montante;
  }

  public synchronized void levantar(int n,int montante){
    contas[n-1] -= montante;
  }

  public synchronized void transferencia(int giver,int receiver,int montante){
    contas[giver-1] -= montante;
    contas[receiver-1] += montante;
  }
}
