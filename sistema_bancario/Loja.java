package sist_bancario_v4;

public class Loja {
 private int id;
 private Conta conta;

 public Loja(int id, Conta conta) {
     this.id = id;
     this.conta = conta;
 }

 public int getId() {
     return id;
 }

 public synchronized void receberPagamento(int valor) {
     conta.depositar(valor);
 }

 public synchronized void pagarFuncionario(Funcionario funcionario) {
     if (conta.getSaldo() >= 1400) {
         conta.sacar(1400);
         funcionario.receberSalario(1400);
         System.out.println("Loja " + id + " realizou o pagamento do funcionário " + funcionario.getId());
     }
 }
}

