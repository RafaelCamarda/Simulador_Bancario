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

/**
 *
 * @author rafael
 */
public class TrataConexaoServidores implements Runnable {

    private Socket connection;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String opcao = "";

    public TrataConexaoServidores(Socket conn) throws IOException {
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
                
                //Detecta que um servidor se conectou
                if (op == 'o') {
                    System.out.println("entrei aqui no borda");
                    if (dis.readUTF().equals("online") == true) {
                        String ip = dis.readUTF();
                        SharedResources.getInstance().getMapServers().put(ip, 0);

                        if (GUI_BORDA.getLbIP1().getText().equals("0.0.0.0") == true) {
                            GUI_BORDA.getLbIP1().setText(ip);
                            GUI_BORDA.getLbQTD1().setText("0");
                        } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") == true) {
                            GUI_BORDA.getLbIP2().setText(ip);
                            GUI_BORDA.getLbQTD2().setText("0");
                        } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") == true) {
                            GUI_BORDA.getLbIP2().setText(ip);
                            GUI_BORDA.getLbQTD2().setText("0");
                        } else if (GUI_BORDA.getLbIP2().getText().equals("0.0.0.0") == true) {
                            GUI_BORDA.getLbIP2().setText(ip);
                            GUI_BORDA.getLbQTD2().setText("0");
                        }
                    }
                
                //Detecta em qual servidor cliente se conectou, e incrementa o valor.
                } else if (op == 'c') {
                    String ip = dis.readUTF();
                    System.out.println("Entrei pra aumentar cliente");
                    if (GUI_BORDA.getLbIP1().getText().equals(ip) == true) {
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD1().getText());
                        qtd++;
                        GUI_BORDA.getLbQTD1().setText(Integer.toString(qtd));
                    } else if (GUI_BORDA.getLbIP2().getText().equals(ip) == true) {
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD2().getText());
                        qtd++;
                        GUI_BORDA.getLbQTD2().setText(Integer.toString(qtd));
                    } else if (GUI_BORDA.getLbIP3().getText().equals(ip) == true) {
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD3().getText());
                        qtd++;
                        GUI_BORDA.getLbQTD3().setText(Integer.toString(qtd));
                    } else if (GUI_BORDA.getLbIP4().getText().equals(ip) == true) {
                        int qtd = Integer.parseInt(GUI_BORDA.getLbQTD4().getText());
                        qtd++;
                        GUI_BORDA.getLbQTD4().setText(Integer.toString(qtd));
                    }

                }
            }

        } catch (IOException ex) {
            //Logger.getLogger(TrataConexaoServidorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
