package sist_bancario_v4;

public class Conta {
 private int saldo;

 public Conta(int saldo) {
     this.saldo = saldo;
 }

 public synchronized void depositar(int valor) {
     saldo += valor;
 }

 public synchronized boolean sacar(int valor) {
     if (saldo >= valor) {
         saldo -= valor;
         return true;
     }
     return false;
 }

 public synchronized int getSaldo() {
     return saldo;
 }
}

