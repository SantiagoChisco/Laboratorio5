/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DaoFactory;
import persistence.DaoPaciente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.*;

/**
 *
 * @author 2100609
 */
public class PacientePersistenceTest {
     public PacientePersistenceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void databaseConnectionTest() throws IOException, PersistenceException{
            System.out.println("Realizando prueba");
             InputStream input = null;
             input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
             Properties properties=new Properties();
             properties.load(input);
             
             DaoFactory daof=DaoFactory.getInstance(properties);
             
             daof.beginSession(); 
             System.out.println("Inicio sesion");
             //Prueba 1
             DaoPaciente dp = daof.getDaoPaciente();
             Paciente  p = new Paciente(987, "CC", "Juan Sanchez", java.sql.Date.valueOf("1995-01-01"));
             Set<Consulta> consultas = new LinkedHashSet<>();
             Consulta c = new Consulta(java.sql.Date.valueOf("2000-10-01"), "Consulta general");
             Consulta c1 = new Consulta(java.sql.Date.valueOf("2001-01-01"), "Consulta de control");
             Consulta c3 = new Consulta(java.sql.Date.valueOf("2002-01-01"), "Consulta de control");
             consultas.add(c);
             consultas.add(c1);
             consultas.add(c3);
             p.setConsultas(consultas);
             dp.save(p);
             Paciente res = dp.load(987, "CC");
             //assertEquals(java.lang.Object p, java.lang.Object res);
             assertEquals(p.getId(), res.getId());
             assertEquals(3, res.getConsultas().size());
             
             //Prueba 2
             Paciente  p1 = new Paciente(9876, "TI", "David Lopez", java.sql.Date.valueOf("2005-01-01"));
             dp.save(p1);
             Paciente res1 = dp.load(9876, "TI");
             assertEquals(p1.getNombre(), res1.getNombre());
             //assertEquals(0, res1.getConsultas().size());
             
             //Prueba 3
             Paciente p2 = new Paciente(999, "CC", "David Chisco", java.sql.Date.valueOf("2006-05-06"));
             Set<Consulta> consultas2 = new LinkedHashSet<>();
             Consulta c2 = new Consulta(java.sql.Date.valueOf("2002-05-04"), "Consulta por urgencias");
             consultas2.add(c2);
             p2.setConsultas(consultas2);
             dp.save(p2);
             Paciente respu = dp.load(999, "CC");
             assertEquals(1, respu.getConsultas().size());
             
             //Prueba 4
             Paciente res3 = dp.load(987, "CC");
             if (res3.getConsultas().size() <= 1) {
                 fail();
             }
             
             //IMPLEMENTACION DE LAS PRUEBAS
             //fail("Pruebas no implementadas");
             
             
             daof.commitTransaction();        
             daof.endSession();
       
    }
    
}
