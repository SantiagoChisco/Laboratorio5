/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientesTest {
    
    public PacientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroPacienteTest(){
        
        
        ServiciosPacientes servicio = ServiciosPacientes.getInstance();
        Paciente p = new Paciente(111,"CC","Daniel Ayala",java.sql.Date.valueOf("1994-01-29"));
        Paciente res = null;
        try {
            servicio.registrarNuevoPaciente(p);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(PacientesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            res=servicio.consultarPaciente(11, "CC");
        } catch (ExcepcionServiciosPacientes ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PacientesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
            
        }
        assertEquals(p, res);
    }
    
  
}
