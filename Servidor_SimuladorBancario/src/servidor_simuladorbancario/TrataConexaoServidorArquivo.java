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
public class TrataConexaoServidorArquivo implements Runnable {

    private Socket connection;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String text = "";
    private Cliente c = null;
    private String op;

    public TrataConexaoServidorArquivo(Socket conn, Cliente c, String op) throws IOException {
        this.connection = conn;
        this.text = text;
        this.c = c;
        this.op = op;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");

    }

    @Override
    public void run() {
       try {
            
           if(op.equals("enviaCliente") == true){
               dos.writeChar('l');
               System.out.println("Enviei l");
               dos.writeUTF(c.getNome());
               dos.writeDouble(c.getSaldo());
               dos.writeUTF(c.getSenha());
               System.out.println("TO enviando mano!");
           }
           else if(op.equals("recebeCliente") == true){
               dos.writeChar('e');
               int qtd = dis.readInt();
               
               for(int i = 0 ; i < qtd; i++){
                   String nome = dis.readUTF();
                   Double saldo = dis.readDouble();
                   String senha = dis.readUTF();
                   
                   Cliente c = new Cliente(nome, saldo, senha);
                   
                   SharedResources.getInstance().getClientes().add(c);
                   System.out.println("Recebi o cliente  " + c.getNome());
               }
           }
           
            dos.flush();

            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TrataConexaoServidorLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
