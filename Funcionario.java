package sist_bancario_v4;

public class Funcionario extends Thread {
 private int id;
 private Conta salarioConta;
 private Conta investimentosConta;

 public Funcionario(int id, Conta salarioConta, Conta investimentosConta) {
     this.id = id;
     this.salarioConta = salarioConta;
     this.investimentosConta = investimentosConta;
 }

 public void receberSalario(int valor) {
     salarioConta.depositar(valor);
     int valorInvestimento = valor * 20 / 100;
     investimentosConta.depositar(valorInvestimento);
     System.out.println("Funcionário " + id + " depositou R$" + valorInvestimento + " na conta de investimentos");
 }

 @Override
 public void run() {
     while (true) {
         synchronized (salarioConta) {
             if (salarioConta.getSaldo() >= 1400) {
                 receberSalario(1400);
                 try {
                     Thread.sleep(2000); // Simula intervalo entre pagamentos de funcionários
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
 }
}

