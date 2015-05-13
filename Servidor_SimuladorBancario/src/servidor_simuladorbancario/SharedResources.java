/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_simuladorbancario;

import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class SharedResources {

    public static SharedResources shared = null;

    private ArrayList<Cliente> clientes;
    private ArrayList<String> servers;

    public ArrayList<String> getServers() {
        return servers;
    }

    public void setServers(ArrayList<String> servers) {
        this.servers = servers;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static SharedResources getInstance() {
        if (shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public static SharedResources getShared() {
        return shared;
    }

    public static void setShared(SharedResources shared) {
        SharedResources.shared = shared;
    }

    private SharedResources() {
        clientes = new ArrayList<Cliente>();
        servers = new ArrayList<String>();
        
        servers.add("192.168.0.200");
        //servers.add("172.16.103.52");
        //servers.add("192.168.0.113");
        //servers.add("192.168.0.114");
    }

}
