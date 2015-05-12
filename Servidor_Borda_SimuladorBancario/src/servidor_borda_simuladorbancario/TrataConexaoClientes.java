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

                if (op == 'i') {
                    if (GUI_BORDA.getLbIP1().getText().equals("0.0.0.0") != true) {
                        System.out.println("Entrei no 1");
                        System.out.println(GUI_BORDA.getLbIP1().getText());
                        String ipserv = GUI_BORDA.getLbIP1().getText();
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD1().getText());
                        qtd++;
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        GUI_BORDA.getLbQTD1().setText(Integer.toString(qtd));
                        break;
                    } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") != true) {
                        String ipserv = GUI_BORDA.getLbIP2().getText();
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD2().getText());
                        qtd++;
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        GUI_BORDA.getLbQTD1().setText(Integer.toString(qtd));
                        break;
                    } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") == true) {
                        String ipserv = GUI_BORDA.getLbIP3().getText();
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD3().getText());
                        qtd++;
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        GUI_BORDA.getLbQTD1().setText(Integer.toString(qtd));
                        break;
                    } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") == true) {
                        String ipserv = GUI_BORDA.getLbIP4().getText();
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD4().getText());
                        qtd++;
                        dos.writeBoolean(true);
                        dos.writeUTF(ipserv);
                        GUI_BORDA.getLbQTD1().setText(Integer.toString(qtd));
                        break;
                    } else {
                        System.out.println("Enviei falso");
                        dos.writeBoolean(false);
                    }

                } else if (op == 'c') {
                    
                }
            }

        } catch (IOException ex) {
            //Logger.getLogger(TrataConexaoServidorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
