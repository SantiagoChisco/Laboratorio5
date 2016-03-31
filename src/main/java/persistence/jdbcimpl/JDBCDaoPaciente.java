/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jdbcimpl;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import persistence.DaoPaciente;
import persistence.PersistenceException;

/**
 *
 * @author 2100609
 */
public class JDBCDaoPaciente implements DaoPaciente  {
    Connection con;

    public JDBCDaoPaciente(Connection con) {
        this.con = con;
    }
        
    
    @Override
    public Paciente load(int idpaciente, String tipoid) throws PersistenceException {
        PreparedStatement ps;
        Set<Consulta> consultas = new LinkedHashSet<>();
        
        try {
            
            ps = con.prepareStatement("select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS, con.fecha_y_hora, con.resumen \n" +
"from PACIENTES as pac inner join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id \n" +
"where pac.id=? and pac.tipo_id=?");
            ps.setInt(1, idpaciente);
            ps.setString(2, tipoid);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){
                Paciente p = new Paciente(idpaciente, tipoid, rs.getString(1),rs.getDate(2));
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
                while(rs.next()){   
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
            }
                p.setConsultas(consultas);
                return p;

            }
            
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading "+idpaciente,ex);
        }
        throw new RuntimeException("No se ha implementado el metodo 'load' del DAOPAcienteJDBC");
    }

    @Override
    public void save(Paciente p) throws PersistenceException {
        System.out.println("Entro al save");
        PreparedStatement ps;
        PreparedStatement cs;
        try {
        ps = con.prepareStatement("INSERT into PACIENTES(id,tipo_id,nombre,fecha_nacimiento) values(?,?,?,?)");
            ps.setInt(1, p.getId());
            ps.setString(2, p.getTipo_id());
            ps.setString(3, p.getNombre());
            ps.setDate(4, p.getFechaNacimiento());

            ps.execute();
            
            
                 Iterator<Consulta> i= p.getConsultas().iterator();
            while(i.hasNext()){
                    Consulta a=i.next();
                    cs=con.prepareStatement("INSERT INTO CONSULTAS (fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                    
                    cs.setDate(1, a.getFechayHora());
                    cs.setString(2, a.getResumen());
                    cs.setInt(3, p.getId());
                    cs.setString(4, p.getTipo_id());
                    cs.execute(); 
                }  
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading a product.",ex);
        }
        
        //throw new RuntimeException("No se ha implementado el metodo 'load' del DAOPAcienteJDBC");

    }

    @Override
    public void update(Paciente p) throws PersistenceException {
        PreparedStatement ps;
        /*try {
            
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading a product.",ex);
        } */
        throw new RuntimeException("No se ha implementado el metodo 'load' del DAOPAcienteJDBC");
    }
    
}
