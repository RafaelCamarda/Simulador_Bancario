/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_arquivo_simuladorbancario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class MyServerSocket implements Runnable{
    
    private boolean connected = false;
    private ServerSocket serverSocket = null;
    private Socket connection = null;
    private TrataConexaoServidorArquivo trata = null;
    private String line;

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(9000);
            JOptionPane.showMessageDialog(null, "Servidor de ARQUIVO está executando");
            connected = true;
            
            while(connected == true){
                System.out.println("Esperando por conexões");
                connection = serverSocket.accept();
       
                trata = new TrataConexaoServidorArquivo(connection);
                Thread trataConexao = new Thread(trata);
                trataConexao.start();
               System.out.println("Thread para tratar conexão startada");
            }
            
        }
        catch(IOException e){
          
            JOptionPane.showMessageDialog(null, "Falha na conexão com o servidor!");
        }
    }
    
    public void disconnect(){
        try{
            if(serverSocket != null){
                serverSocket.close();
                connected = false;
                JOptionPane.showMessageDialog(null, "Servidor desconectado!");
            }
        }
        catch(IOException ex){
          
            JOptionPane.showMessageDialog(null, "Falha ao fechar conexão!");
        }
    }
   
    
}
