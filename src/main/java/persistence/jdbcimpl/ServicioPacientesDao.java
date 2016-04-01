/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.jdbcimpl;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import persistence.PersistenceException;

/**
 *
 * @author 2100609
 */
public class ServicioPacientesDao extends ServiciosPacientes{
    Connection con;

    public ServicioPacientesDao(Connection con) {
        this.con=con;
    }
    
    

    @Override
    public Paciente consultarPaciente(int idPaciente, String tipoid) throws ExcepcionServiciosPacientes {
        PreparedStatement ps;
          Set<Consulta> consultas = new LinkedHashSet<>();
          System.out.println(idPaciente+"  "+ tipoid);
        
        try {
            
            ps = con.prepareStatement("select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS, con.fecha_y_hora, con.resumen \n" +
"from PACIENTES as pac left join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id \n" +
"where pac.id=? and pac.tipo_id=?");
            ps.setInt(1, idPaciente);
            ps.setString(2, tipoid);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){
                Paciente p = new Paciente(idPaciente, tipoid, rs.getString(1),rs.getDate(2));
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
                while(rs.next()){   
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
                }
                p.setConsultas(consultas);
                return p;

            }
        } catch (SQLException ex) {
            throw new ExcepcionServiciosPacientes("Fallo",new PersistenceException("An error ocurred while loading "+idPaciente,ex));
        }
        throw new RuntimeException("No se encontro paciente"+idPaciente);
  
    }

    @Override
    public void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes {
        PreparedStatement ps;
        PreparedStatement cs;
        System.out.println(p.getId());
        try {
            System.out.println("Registro al Paciente");
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
            throw new ExcepcionServiciosPacientes("Fallo",new PersistenceException("An error ocurred while loading a product.",ex));
        }
        
    }

    @Override
    public void agregarConsultaAPaciente(int idPaciente, String tipoid, Consulta c) throws ExcepcionServiciosPacientes {
         PreparedStatement ps;
         PreparedStatement cs;
          Set<Consulta> consultas = new LinkedHashSet<>();
        
        try {
            
            ps = con.prepareStatement("select pac.nombre, pac.fecha_nacimiento, con.idCONSULTAS, con.fecha_y_hora, con.resumen \n" +
"from PACIENTES as pac left join CONSULTAS as con on con.PACIENTES_id=pac.id and con.PACIENTES_tipo_id=pac.tipo_id \n" +
"where pac.id=? and pac.tipo_id=?");
            ps.setInt(1, idPaciente);
            ps.setString(2, tipoid);
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()){
                Paciente p = new Paciente(idPaciente, tipoid, rs.getString(1),rs.getDate(2));
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
                while(rs.next()){   
                consultas.add(new Consulta(rs.getDate(4),rs.getString(5)));
            }
                p.setConsultas(consultas);
                p.getConsultas().add(c);
                System.out.println("Agrego la consulta");
                cs=con.prepareStatement("INSERT INTO CONSULTAS (fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                cs.setDate(1, c.getFechayHora());
                    cs.setString(2, c.getResumen());
                    cs.setInt(3, p.getId());
                    cs.setString(4, p.getTipo_id());
                    cs.execute(); 
            }
            
            
        } catch (SQLException ex) {
            throw new ExcepcionServiciosPacientes("Fallo",new PersistenceException("An error ocurred while loading "+idPaciente,ex));
        }
        throw new RuntimeException("No se encontro paciente");
    }

    @Override
    public List<Paciente> getPacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarConsulta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consulta> getConsultaLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
