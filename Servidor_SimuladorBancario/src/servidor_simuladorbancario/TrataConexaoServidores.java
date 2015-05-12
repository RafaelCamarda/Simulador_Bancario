/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_simuladorbancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class TrataConexaoServidores implements Runnable{
    
    private Socket connection;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String opcao = "";
    private String nome = "";
    private int indexCliente = 0;
    private double valorCliente = 0.0;
    
    
    public TrataConexaoServidores(Socket conn, String op,int index, double valor, String nome) throws IOException{
        this.connection = conn;
        this.opcao = op;
        this.indexCliente = index;
        this.valorCliente = valor;
        this.nome = nome;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");
            
    }

    @Override
    public void run() {
        try {             
            if(opcao.equals("saque") == true){
                dos.writeChar('m');
                dos.writeUTF(nome);
                dos.writeDouble(valorCliente);
            }
            
            if(opcao.equals("deposito") == true){
                dos.writeChar('n');
                dos.writeUTF(nome);
                dos.writeDouble(valorCliente);
            }
            
            if(opcao.equals("cliente") == true){
                Cliente c = SharedResources.getInstance().
                        getClientes().get(indexCliente);
                dos.writeChar('o');
                dos.writeUTF(c.getNome());
                dos.writeUTF(c.getSenha());
                dos.writeDouble(c.getSaldo());
                
            }
            dos.flush();
            
            if(dis != null){
            dis.close();
            }
            if(dos != null){
                dos.close();
            }
            if(connection != null){
               connection.close();
        } 
         
       }
            catch (IOException ex) {
            Logger.getLogger(TrataConexaoServidores.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
