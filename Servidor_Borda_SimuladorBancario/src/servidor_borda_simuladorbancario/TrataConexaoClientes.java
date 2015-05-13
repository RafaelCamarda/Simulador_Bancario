/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_borda_simuladorbancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
class TrataConexaoClientes implements Runnable {

    private Socket connection;
    private Socket connectionServLog;
    private Thread tServLog;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String opcao = "";
    private ArrayList<Integer> qtd_clientes = new ArrayList<Integer>();

    public TrataConexaoClientes(Socket conn) throws IOException {
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

                //Verifica qual servidor está menos sobrecarregado e envia para cliente.
                //Só leva em consideração ips válidos.
                if (op == 'i') {
                    if (GUI_BORDA.getLbIP1().getText().equals("0.0.0.0") != true) {
                        qtd_clientes.add(Integer.parseInt(GUI_BORDA.getLbQTD1().getText()));

                    } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") != true) {
                        qtd_clientes.add(Integer.parseInt(GUI_BORDA.getLbQTD2().getText()));


                    } else if (GUI_BORDA.getLbIP3().getText().equals("0.0.0.0") != true) {
                        qtd_clientes.add(Integer.parseInt(GUI_BORDA.getLbQTD3().getText()));

                    } else if (GUI_BORDA.getLbIP4().getText().equals("0.0.0.0") != true) {
                        qtd_clientes.add(Integer.parseInt(GUI_BORDA.getLbQTD4().getText()));

                    } else {
                        dos.writeBoolean(false);
                        break;
                    }

                    int minIndex = qtd_clientes.indexOf(Collections.min(qtd_clientes));

                    if (minIndex == 0) {
                       
                        String ipserv = GUI_BORDA.getLbIP1().getText();
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        break;

                    } else if (minIndex == 1) {
                        String ipserv = GUI_BORDA.getLbIP2().getText();
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        break;

                    } else if (minIndex == 2) {

                        String ipserv = GUI_BORDA.getLbIP3().getText();
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        break;

                    } else if (minIndex == 3) {
                        String ipserv = GUI_BORDA.getLbIP4().getText();
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        break;

                    }

                } else if (op == 'c') {

                }
            }

        } catch (IOException ex) {
            //Logger.getLogger(TrataConexaoServidorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
