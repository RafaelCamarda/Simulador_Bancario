/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_borda_simuladorbancario;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rafael
 */
public class SharedResources {

    public static SharedResources shared = null;
    private HashMap mapServers = null;

    public HashMap getMapServers() {
        return mapServers;
    }

    public void setMapServers(HashMap mapServers) {
        this.mapServers = mapServers;
    }


    public static SharedResources getInstance() {
        if (shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }


    public static SharedResources getShared() {
        return shared;
    }

    public static void setShared(SharedResources shared) {
        SharedResources.shared = shared;
    }

    private SharedResources() {
        mapServers = new HashMap();
        
    }

}
