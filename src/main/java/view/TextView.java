/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import persistence.DaoFactory;
import persistence.PersistenceException;

/**
 *
 * @author 2100609
 */
public class TextView {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, PersistenceException {
        
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        /**
         * OPERACIONES CON LOS DAO
         */
        
        
        daof.beginSession();
        daof.commitTransaction();
        daof.endSession();
        
        
    }
    
}
