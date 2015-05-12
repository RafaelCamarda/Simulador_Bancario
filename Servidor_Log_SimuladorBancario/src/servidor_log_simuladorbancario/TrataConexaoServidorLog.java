/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_log_simuladorbancario;

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
public class TrataConexaoServidorLog implements Runnable{
    
    private Socket connection;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private String opcao = "";

    
    public TrataConexaoServidorLog(Socket conn) throws IOException{
        this.connection = conn;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");
            
    }

    @Override
    public void run() {
        try {
            String msg = dis.readUTF();
            GUI_LOG.getTaLog2().append(msg);
            
            
        } catch (IOException ex) {
            Logger.getLogger(TrataConexaoServidorLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
