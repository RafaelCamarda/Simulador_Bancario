/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_arquivo_simuladorbancario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */

//Classe estática para ler e gravar arquivos
public class InternalStorage {
     public static void writeObject(String name, Object object) {
         try {
             FileOutputStream fos = new FileOutputStream(name);
	     ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(object);
	     oos.close();
	     fos.close();
         } catch (IOException ex) {
             Logger.getLogger(InternalStorage.class.getName()).log(Level.SEVERE, null, ex);
         }
	     
	   }
     
     public static Object  readObject(String name){
         try {
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            fis.close();
            ois.close();
            return object;
         } catch (FileNotFoundException ex) {
            
         } catch (IOException ex) {
            ex.printStackTrace();
         } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }
	 
	return null; 
     }
}
