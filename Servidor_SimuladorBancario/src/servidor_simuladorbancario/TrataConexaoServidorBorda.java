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
public class TrataConexaoServidorBorda implements Runnable {

    private Socket connection;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String on = "";
    private String ip = "";
    private String op = "";

    public TrataConexaoServidorBorda(Socket conn, String ip, String op) throws IOException {
        this.connection = conn;
        this.ip = ip;
        this.op = op;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");

    }

    @Override
    public void run() {
        try {
            System.out.println("to no server local borda");
            if (op.equals("o") == true) {
                System.out.println("Enviarei ao servidor borda");
                on = "online";
                dos.writeChar('o');
                dos.writeUTF(on);

                dos.writeUTF(ip);
            }
            else if(op.equals("c") == true){
                
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
