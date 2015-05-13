/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_simuladorbancario;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class GUI extends javax.swing.JFrame {

    Thread t;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    Socket socket = null;
    Socket socketBorda = null;
    private String serverIp;
    private char operacao;
    private double valor;
    Random randomGenerator;

    private static final String ipBorda = "192.168.0.200";

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jCheckBox1 = new javax.swing.JCheckBox();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        btOperacao = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfValor = new javax.swing.JTextField();
        rbDeposito = new javax.swing.JRadioButton();
        rbSaque = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbSaldo = new javax.swing.JLabel();
        btLogin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbIP = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLIENTE");
        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel2.setText("SERVIDOR:");

        lbStatus.setForeground(new java.awt.Color(255, 0, 0));
        lbStatus.setText("OFFLINE");
        lbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStatusMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setText("Faça seu login:");

        tfNome.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tfNome.setText("Usuario");
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setText("Nome:");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel5.setText("Senha:");

        tfSenha.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tfSenha.setText("1234");

        btOperacao.setText("FAZER OPERAÇÃO");
        btOperacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btOperacaoMouseClicked(evt);
            }
        });
        btOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOperacaoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel10.setText("Selecione a operação:");

        tfValor.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tfValor.setText("0.0");

        buttonGroup1.add(rbDeposito);
        rbDeposito.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        rbDeposito.setText("Depósito");

        buttonGroup1.add(rbSaque);
        rbSaque.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        rbSaque.setText("Saque");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel11.setText("Valor:");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel12.setText("Seu saldo:");

        lbSaldo.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lbSaldo.setText("0.0");

        btLogin.setText("Login");
        btLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLoginMouseClicked(evt);
            }
        });
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setText("Menu de operações:");

        jLabel6.setText("IP do Servidor:");

        lbIP.setText("0.0.0.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfSenha)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btLogin)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(lbSaldo)))
                        .addContainerGap(133, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rbSaque)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbDeposito)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btOperacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel11)
                                    .addGap(38, 38, 38)
                                    .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(257, 257, 257))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIP)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btLogin)
                    .addComponent(jLabel12)
                    .addComponent(lbSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSaque)
                    .addComponent(rbDeposito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(btOperacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbStatus)
                    .addComponent(jLabel6)
                    .addComponent(lbIP))
                .addContainerGap())
        );

        jTabbedPane1.addTab("OPERAÇÕES DE CLIENTE", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOperacaoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btOperacaoActionPerformed

    private void btOperacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOperacaoMouseClicked
        // TODO add your handling code here:
        try {
            String nome, senha;
            nome = tfNome.getText();
            senha = tfSenha.getText();
            char operacao = 'x';
            double valor = Double.parseDouble(tfValor.getText());

            if (rbDeposito.isSelected() == true) {
                dos.writeChar('d');
                dos.writeUTF(nome);
                dos.writeDouble(valor);
                lbSaldo.setText(Double.toString(dis.readDouble()));
                if (dis.readChar() == 'w') {
                    JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar depósito");
                }

            } else if (rbSaque.isSelected() == true) {

                dos.writeChar('s');
                dos.writeUTF(nome);
                dos.writeDouble(valor);
                lbSaldo.setText(Double.toString(dis.readDouble()));
                if (dis.readChar() == 'w') {
                    JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Seu saldo não pode ficar negativo!");
                }

            }

            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btOperacaoMouseClicked

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void lbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStatusMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lbStatusMouseClicked

    private void btLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLoginMouseClicked
        // TODO add your handling code here:
        String nome, senha;
        nome = tfNome.getText();
        senha = tfSenha.getText();
        String ipServer = "";

        boolean sucesso = false;
        boolean tentarNovamente = true;

        boolean servDisponivel = false;

        //Conexão com o Servidor de borda
        try {
            socket = new Socket(ipBorda, 9020);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            dos.writeChar('i');
            servDisponivel = dis.readBoolean();

            if (servDisponivel == true) {
                ipServer = dis.readUTF();

                try {

                    socket = new Socket(ipServer, 8080);
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());

                    dos.writeChar('l');
                    dos.writeUTF(nome);
                    dos.writeUTF(senha);
                    sucesso = dis.readBoolean();
                    if (sucesso == true) {
                        lbSaldo.setText(Double.toString(dis.readDouble()));
                        JOptionPane.showMessageDialog(null, "Concectado com sucesso!");
                        lbStatus.setText("ONLINE");
                        lbStatus.setForeground(Color.green);
                        tentarNovamente = false;
                        lbIP.setText(ipServer);
                        dos.flush();
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado ou senha invalida!");
                        lbStatus.setText("OFFLINE");
                        lbStatus.setForeground(Color.red);
                        lbSaldo.setText("0.0");
                        tentarNovamente = false;

                    }
                } catch (IOException ex) {
                    if (JOptionPane.showConfirmDialog(null, "Servidor escolhido offline, "
                            + "tentar em outro servidor?", "WARNING",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        tentarNovamente = true;
                    } else {
                        tentarNovamente = false;
                    }
                } catch (IndexOutOfBoundsException ex) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Não há servidor disponível!");
            }

        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btLoginMouseClicked

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btOperacao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbIP;
    private javax.swing.JLabel lbSaldo;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JRadioButton rbDeposito;
    private javax.swing.JRadioButton rbSaque;
    private javax.swing.JTextField tfNome;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables

}