package sist_bancario_v4;

public class Main {
 public static void main(String[] args) {
     // Criar o banco
     Banco banco = new Banco();

     // Criar contas para lojas
     Conta contaLoja1 = new Conta(0);
     Conta contaLoja2 = new Conta(0);

     // Criar contas para funcionários
     Conta contaSalarioFuncionario1 = new Conta(0);
     Conta contaInvestimentosFuncionario1 = new Conta(0);
     Conta contaSalarioFuncionario2 = new Conta(0);
     Conta contaInvestimentosFuncionario2 = new Conta(0);

     // Criar contas para clientes
     Conta[] contasClientes = new Conta[5];
     for (int i = 0; i < contasClientes.length; i++) {
         contasClientes[i] = new Conta(1000);
     }

     // Criar lojas
     Loja loja1 = new Loja(1, contaLoja1);
     Loja loja2 = new Loja(2, contaLoja2);

     // Criar funcionários
     Funcionario funcionario1 = new Funcionario(1, contaSalarioFuncionario1, contaInvestimentosFuncionario1);
     Funcionario funcionario2 = new Funcionario(2, contaSalarioFuncionario2, contaInvestimentosFuncionario2);

     // Iniciar threads das lojas
     Thread threadLoja1 = new Thread(() -> {
         while (true) {
             banco.pagarFuncionario(loja1, funcionario1);
             try {
                 Thread.sleep(2000); // Simula intervalo entre pagamentos de funcionários
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });

     Thread threadLoja2 = new Thread(() -> {
         while (true) {
             banco.pagarFuncionario(loja2, funcionario2);
             try {
                 Thread.sleep(2000); // Simula intervalo entre pagamentos de funcionários
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     });

     threadLoja1.start();
     threadLoja2.start();

     // Iniciar threads dos clientes
     Thread[] threadsClientes = new Thread[contasClientes.length];
     for (int i = 0; i < threadsClientes.length; i++) {
         final int clienteId = i + 1;
         threadsClientes[i] = new Thread(() -> {
             Cliente cliente = new Cliente(clienteId, contasClientes[clienteId - 1], new Loja[]{loja1, loja2});
             cliente.run();
         });
         threadsClientes[i].start();
     }
     try {
         Thread.sleep(10000); // Tempo suficiente para as threads terminarem suas tarefas
     } catch (InterruptedException e) {
         e.printStackTrace();
     }

     // Imprimir saldo final das contas
     System.out.println("Saldo final da conta da Loja 1: R$" + contaLoja1.getSaldo());
     System.out.println("Saldo final da conta da Loja 2: R$" + contaLoja2.getSaldo());
     System.out.println("Saldo final da conta do Funcionário 1: R$" + contaSalarioFuncionario1.getSaldo());
     System.out.println("Saldo final da conta de investimentos do Funcionário 1: R$" + contaInvestimentosFuncionario1.getSaldo());
     System.out.println("Saldo final da conta do Funcionário 2: R$" + contaSalarioFuncionario2.getSaldo());
     System.out.println("Saldo final da conta de investimentos do Funcionário 2: R$" + contaInvestimentosFuncionario2.getSaldo());

     for (int i = 0; i < contasClientes.length; i++) {
         System.out.println("Saldo final da conta do Cliente " + (i + 1) + ": R$" + contasClientes[i].getSaldo());
     }
 }
}

