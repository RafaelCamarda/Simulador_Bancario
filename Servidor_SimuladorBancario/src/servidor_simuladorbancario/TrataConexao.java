/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_simuladorbancario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
class TrataConexao implements Runnable {

    private Socket connection;
    private Socket connectionServ;
    private Socket connectionServArq;
    private Socket connectionServLog;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private char sucesso = 'w';
    private char fracasso = 'q';
    private Thread tServ;
    private Thread tServArq;
    private Thread tServLog;
    private String resultIp = null;

    public TrataConexao(Socket conn) throws IOException {
        this.connection = conn;
        dos = new DataOutputStream(connection.getOutputStream());
        dis = new DataInputStream(connection.getInputStream());
        System.out.println("Obtenção dos streams");
        resultIp = GUI2.getCurrentIp().toString().replace("/", "");
    }

    @Override
    public void run() {
        System.out.println("Iniciou o run");

        try {

            while (true) {
                System.out.flush();
                char opcao = dis.readChar();

                if (opcao == 'l') {
                    int contador = 0;
                    String nome = dis.readUTF();
                    String senha = dis.readUTF();
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (c.getNome().equals(nome) == true && c.getSenha().equals(senha) == true) {
                            //SharedResources.getInstance().setIndexCliente(index);
                            dos.writeBoolean(true);
                            dos.writeDouble(c.getSaldo());
                            System.out.println("Entrei no if do server");

                            try {
                                connectionServLog = new Socket(SharedResources.getInstance().getServers().get(0), 8090);
                                TrataConexaoServidorLog trataServidoresLog;
                                trataServidoresLog = new TrataConexaoServidorLog(connectionServLog, "Cliente logado: " + c.getNome() + "\n");
                                tServLog = new Thread(trataServidoresLog);
                                tServLog.start();
                            } catch (IOException ex) {
                                System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                            } catch (IndexOutOfBoundsException ex) {

                            }
                            break;

                        } else {
                            contador++;
                            if (contador > (SharedResources.getInstance().getClientes().size() - 1)) {
                                dos.writeBoolean(false);
                            }

                        }
                    }

                }

                if (opcao == 's') {
                    int index = 0;
                    String nome = dis.readUTF();
                    double valor = dis.readDouble();
                    //int index = SharedResources.getInstance().getIndexCliente();
                    System.out.println("Saque");
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (nome.equals(c.getNome()) == true) {
                            if (SharedResources.getInstance().getClientes().get(index).
                                    saque(valor) == 0) {
                                System.out.println("Saque efetuado");

                                dos.writeDouble(SharedResources.getInstance().getClientes().get(index).
                                        getSaldo());
                                dos.writeChar(sucesso);

                                double saldo = SharedResources.getInstance().getClientes().get(index).
                                        getSaldo();

                                try {
                                    connectionServLog = new Socket(SharedResources.getInstance().getServers().get(0), 8090);
                                    TrataConexaoServidorLog trataServidoresLog;
                                    trataServidoresLog = new TrataConexaoServidorLog(connectionServLog, "Cliente " + SharedResources.getInstance().getClientes().get(index).getNome()
                                            + "   efetuou um saque no valor de: " + valor + "\n");
                                    tServLog = new Thread(trataServidoresLog);
                                    tServLog.start();
                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                                } catch (IndexOutOfBoundsException ex) {

                                }

                                try {
                                    connectionServArq = new Socket(SharedResources.getInstance().getServers().get(0), 9000);
                                    TrataConexaoServidorArquivo trataServidoresArq;
                                    trataServidoresArq = new TrataConexaoServidorArquivo(connectionServArq, SharedResources.getInstance().getClientes().get(index), "enviaCliente");
                                    tServArq = new Thread(trataServidoresArq);
                                    tServArq.start();
                                    System.out.println("Atualizei cliente!");
                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                                } catch (IndexOutOfBoundsException ex) {

                                }

                                try {
                                    if (resultIp.equals(SharedResources.getInstance().getServers().get(0)) == false) {
                                        connectionServ = new Socket(SharedResources.getInstance().getServers().get(0), 8080);
                                        TrataConexaoServidores trataServidores;
                                        trataServidores = new TrataConexaoServidores(connectionServ, "saque", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                                getNome());
                                        tServ = new Thread(trataServidores);
                                        tServ.start();
                                    }

                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                                } catch (IndexOutOfBoundsException ex) {

                                }

                                try {
                                    if (resultIp.equals(SharedResources.getInstance().getServers().get(1)) == false) {
                                        connectionServ = new Socket(SharedResources.getInstance().getServers().get(1), 8080);
                                        TrataConexaoServidores trataServidores;
                                        trataServidores = new TrataConexaoServidores(connectionServ, "saque", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                                getNome());
                                        tServ = new Thread(trataServidores);
                                        tServ.start();
                                    }

                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(1));
                                } catch (IndexOutOfBoundsException ex) {

                                }

                                try {
                                    if (resultIp.equals(SharedResources.getInstance().getServers().get(2)) == false) {
                                        connectionServ = new Socket(SharedResources.getInstance().getServers().get(2), 8080);
                                        TrataConexaoServidores trataServidores;
                                        trataServidores = new TrataConexaoServidores(connectionServ, "saque", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                                getNome());
                                        tServ = new Thread(trataServidores);
                                        tServ.start();
                                    }

                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(2));
                                } catch (IndexOutOfBoundsException ex) {

                                }

                                try {
                                    if (resultIp.equals(SharedResources.getInstance().getServers().get(3)) == false) {
                                        connectionServ = new Socket(SharedResources.getInstance().getServers().get(3), 8080);
                                        TrataConexaoServidores trataServidores;
                                        trataServidores = new TrataConexaoServidores(connectionServ, "saque", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                                getNome());
                                        tServ = new Thread(trataServidores);
                                        tServ.start();
                                    }

                                } catch (IOException ex) {
                                    System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(3));
                                } catch (IndexOutOfBoundsException ex) {

                                }
                            } else {
                                dos.writeDouble(SharedResources.getInstance().getClientes().get(index).
                                        getSaldo());
                                dos.writeChar(fracasso);
                            }
                            break;
                        }

                        index++;
                    }

                }

                if (opcao == 'd') {
                    int index = 0;
                    String nome = dis.readUTF();
                    System.out.println("deposito");
                    double valor = dis.readDouble();
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (nome.equals(c.getNome()) == true) {
                            SharedResources.getInstance().getClientes().get(index).deposito(valor);
                            break;
                        }
                        index++;
                    }

                    dos.writeDouble(SharedResources.getInstance().getClientes().get(index).
                            getSaldo());
                    dos.writeChar(sucesso);

                    double saldo = SharedResources.getInstance().getClientes().get(index).
                            getSaldo();

                    try {
                        connectionServLog = new Socket(SharedResources.getInstance().getServers().get(0), 8090);
                        TrataConexaoServidorLog trataServidoresLog;
                        trataServidoresLog = new TrataConexaoServidorLog(connectionServLog, "Cliente " + SharedResources.getInstance().getClientes().get(index).getNome()
                                + "  efetuou um deposito no valor de: " + valor + "\n");
                        tServLog = new Thread(trataServidoresLog);
                        tServLog.start();
                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                    try {
                        connectionServArq = new Socket(SharedResources.getInstance().getServers().get(0), 9000);
                        TrataConexaoServidorArquivo trataServidoresArq;
                        trataServidoresArq = new TrataConexaoServidorArquivo(connectionServArq, SharedResources.getInstance().getClientes().get(index), "enviaCliente");
                        tServArq = new Thread(trataServidoresArq);
                        tServArq.start();
                        System.out.println("Atualizei cliente!");
                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                    try {
                        if (resultIp.equals(SharedResources.getInstance().getServers().get(0)) == false) {
                            connectionServ = new Socket(SharedResources.getInstance().getServers().get(0), 8080);
                            TrataConexaoServidores trataServidores;
                            trataServidores = new TrataConexaoServidores(connectionServ, "deposito", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                    getNome());
                            tServ = new Thread(trataServidores);
                            tServ.start();
                        }

                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(0));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                    try {
                        if (resultIp.equals(SharedResources.getInstance().getServers().get(1)) == false) {
                            connectionServ = new Socket(SharedResources.getInstance().getServers().get(1), 8080);
                            TrataConexaoServidores trataServidores;
                            trataServidores = new TrataConexaoServidores(connectionServ, "deposito", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                    getNome());
                            tServ = new Thread(trataServidores);
                            tServ.start();
                        }

                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(1));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                    try {
                        if (resultIp.equals(SharedResources.getInstance().getServers().get(2)) == false) {
                            connectionServ = new Socket(SharedResources.getInstance().getServers().get(2), 8080);
                            TrataConexaoServidores trataServidores;
                            trataServidores = new TrataConexaoServidores(connectionServ, "deposito", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                    getNome());
                            tServ = new Thread(trataServidores);
                            tServ.start();
                        }

                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(2));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                    try {
                        if (resultIp.equals(SharedResources.getInstance().getServers().get(3)) == false) {
                            connectionServ = new Socket(SharedResources.getInstance().getServers().get(3), 8080);
                            TrataConexaoServidores trataServidores;
                            trataServidores = new TrataConexaoServidores(connectionServ, "deposito", index, saldo, SharedResources.getInstance().getClientes().get(index).
                                    getNome());
                            tServ = new Thread(trataServidores);
                            tServ.start();
                        }

                    } catch (IOException ex) {
                        System.out.println("Falha ao conectar ao servidor: " + SharedResources.getInstance().getServers().get(3));
                    } catch (IndexOutOfBoundsException ex) {

                    }

                }

                if (opcao == 'o') {
                    System.out.println("Atualização cliente");
                    String nome = dis.readUTF();
                    String senha = dis.readUTF();
                    double saldo = dis.readDouble();
                    Cliente c = new Cliente(nome, saldo, senha);
                    SharedResources.getInstance().getClientes().add(c);

                }

                if (opcao == 'm') {
                    int index = 0;
                    System.out.println("Atualização saque");
                    String nome = dis.readUTF();
                    double valor = dis.readDouble();
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (nome.equals(c.getNome()) == true) {
                            SharedResources.getInstance().getClientes().get(index).setSaldo(valor);
                            break;
                        }
                        index++;
                    }

                }

                if (opcao == 'n') {
                    int index = 0;
                    System.out.println("Atualização deposito");
                    String nome = dis.readUTF();
                    double valor = dis.readDouble();
                    for (Cliente c : SharedResources.getInstance().getClientes()) {
                        if (nome.equals(c.getNome()) == true) {
                            SharedResources.getInstance().getClientes().get(index).setSaldo(valor);
                            break;
                        }
                        index++;
                    }
                }
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

}
