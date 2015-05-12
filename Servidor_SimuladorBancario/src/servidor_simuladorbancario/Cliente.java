/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_simuladorbancario;

/**
 *
 * @author rafael
 */
public class Cliente {
    
    private String nome;
    private String senha;
    private double saldo;
    
    
    public Cliente(String nome, double saldo, String senha) {
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void deposito(double valor){
        this.saldo += valor;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int saque(double valor){
        if((this.saldo - valor) < 0){
            return 1;
        }
        else{
            this.saldo -= valor;
            return 0;
        }
    } 
    
}
