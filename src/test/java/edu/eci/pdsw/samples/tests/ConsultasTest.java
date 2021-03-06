/*
 * Copyright (C) 2015 hcadavid
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

import edu.eci.pdsw.samples.entities.Consulta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class ConsultasTest {
    
    public ConsultasTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroPacienteTest(){
        Consulta c = new Consulta(java.sql.Date.valueOf("1994-01-29"),"consulta General");
        String res = "("+-1+","+"1994-01-29" +","+"consulta General)";
        assertEquals(res, c.toString());
        
    }
    
    
}
