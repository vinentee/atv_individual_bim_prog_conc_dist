package sist_bancario_v4;

public class Banco {
 public synchronized void transferencia(Conta origem, Conta destino, int valor) {
     if (origem.sacar(valor)) {
         destino.depositar(valor);
     }
 }

 public synchronized void pagarFuncionario(Loja loja, Funcionario funcionario) {
     loja.pagarFuncionario(funcionario);
 }
}

