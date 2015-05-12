/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_borda_simuladorbancario;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
class MyServerSocketCliente extends MyServerSocket implements Runnable {

    private boolean connected = false;
    private ServerSocket clientesSocket = null;
    private Socket connectionClientes = null;
    private TrataConexaoClientes trataClientes = null;
    private String line;

    @Override
    public void run() {
        try {
            clientesSocket = new ServerSocket(9020);
            JOptionPane.showMessageDialog(null, "Servidor de Borda está executando");
            connected = true;

            while (connected == true) {
                System.out.println("Esperando por conexões");
                
                connectionClientes = clientesSocket.accept();

               
                trataClientes = new TrataConexaoClientes(connectionClientes);

                

                Thread trataConexaoClientes = new Thread(trataClientes);
                trataConexaoClientes.start();

                System.out.println("Thread para tratar conexão startada");
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha na conexão com o servidor!");
        }

    }

    public void disconnect() {
        try {
            if (clientesSocket != null) {
                clientesSocket.close();
                connected = false;
                JOptionPane.showMessageDialog(null, "Servidor desconectado!");
            }
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Falha ao fechar conexão!");
        }
    }

}
