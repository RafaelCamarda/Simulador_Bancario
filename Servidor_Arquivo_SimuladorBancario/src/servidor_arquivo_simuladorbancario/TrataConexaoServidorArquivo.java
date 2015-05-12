/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_arquivo_simuladorbancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class TrataConexaoServidorArquivo implements Runnable {

    private Socket connection;
    private Socket connectionServLog;
    private Thread tServLog;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String opcao = "";

    public TrataConexaoServidorArquivo(Socket conn) throws IOException {
        this.connection = conn;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");
        

    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.flush();
                char op = dis.readChar();
                boolean achado = false;

                if (op == 'l') {
                    String nome = dis.readUTF();
                    Double saldo = dis.readDouble();
                    String senha = dis.readUTF();

                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (c.getNome().equals(nome) == true) {
                            c.setSaldo(saldo);
                            InternalStorage.writeObject("arqClientes", SharedResources.getInstance().getClientes());
                            achado = true;
                            System.out.println("Atualizei cliente");
                            break;
                        }
                        
                        try {
                        connectionServLog = new Socket(SharedResources.getInstance().getServers().get(0), 8090);
                        TrataConexaoServidorLog trataServidoresLog;
                        trataServidoresLog = new TrataConexaoServidorLog(connectionServLog, "Servidor de Arquivo recebeu atualização de clientes\n");
                        tServLog = new Thread(trataServidoresLog);
                        tServLog.start();
                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                    } catch (IndexOutOfBoundsException ex) {

                    }
                    }

                    if (achado == false) {
                        Cliente c = new Cliente(nome, saldo, senha);
                        SharedResources.getInstance().getClientes().add(c);
                        InternalStorage.writeObject("arqClientes", SharedResources.getInstance().getClientes());
                    }
                    System.out.println("Recebi mano!");
                } else if (op == 'e') {
                    int qtd = SharedResources.getInstance().getClientes().size();
                    dos.writeInt(qtd);
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        dos.writeUTF(c.getNome());
                        dos.writeDouble(c.getSaldo());
                        dos.writeUTF(c.getSenha());
                    }

                    try {
                        connectionServLog = new Socket(SharedResources.getInstance().getServers().get(0), 8090);
                        TrataConexaoServidorLog trataServidoresLog;
                        trataServidoresLog = new TrataConexaoServidorLog(connectionServLog, "Servidor de Arquivo enviou lista de clientes\n");
                        tServLog = new Thread(trataServidoresLog);
                        tServLog.start();
                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                    } catch (IndexOutOfBoundsException ex) {

                    }
                }

            }

        } catch (IOException ex) {
            //Logger.getLogger(TrataConexaoServidorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
