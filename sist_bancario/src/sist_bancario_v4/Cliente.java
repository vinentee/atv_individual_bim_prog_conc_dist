package sist_bancario_v4;

public class Cliente extends Thread {
 private int id;
 private Conta conta;
 private Loja[] lojas;

 public Cliente(int id, Conta conta, Loja[] lojas) {
     this.id = id;
     this.conta = conta;
     this.lojas = lojas;
 }

 @Override
 public void run() {
     while (conta.getSaldo() > 0) {
         int valorCompra = Math.random() < 0.5 ? 100 : 200;
         Loja loja = lojas[(int) (Math.random() * lojas.length)];
         synchronized (loja) {
             if (conta.sacar(valorCompra)) {
                 loja.receberPagamento(valorCompra);
                 System.out.println("Cliente " + id + " realizou uma compra de R$" + valorCompra + " na loja " + loja.getId());
             }
         }
     }
 }
}

