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
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name="RegistroConsultaBean")
@SessionScoped
public class RegistroConsultaBean implements Serializable{
    //private final Map<Tupla<Integer,String>,Paciente> pacientes;
    private List<Paciente> pacientes;
    private static Paciente paciente;
    
    private int id;
    private String tipo_id;
    private String nombre;
    private Date fechaNacimiento;
    private Date fechayHora;
    private String fch_tmp;
    private String resumen;
    private Date fecha;
    private int idSeleccion=1;
    private String tipoIdSeleccion;
    
    ServiciosPacientes sp=ServiciosPacientes.getInstance();
    
    public List<Paciente> getPacientes(){
        pacientes = sp.getPacientes();
        return pacientes;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getFch_tmp() {
        return fch_tmp;
    }
    public void setFch_tmp(String fch_tmp) {
        this.fch_tmp = fch_tmp;
    }
    public void registrarNuevoPaciente(Paciente p){
        try {
            sp.registrarNuevoPaciente(p);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Paciente getTupac() {
        Date fechaNacimiento = Date.valueOf(fch_tmp);
        return new Paciente(id, tipo_id, nombre, fechaNacimiento);
        
    }
     public Paciente getSeleccion(){
        return paciente;
    }
    public void setSeleccion(Paciente p){
        paciente = p;
    }
   
    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public void agregarConsulta(){
        try {
            fechayHora = Date.valueOf(fch_tmp);
            Consulta c = new Consulta(fechayHora, getResumen());
            sp.agregarConsultaAPaciente(getSeleccion().getId(), getSeleccion().getTipo_id(), c);
        } catch (ExcepcionServiciosPacientes ex) {
            Logger.getLogger(RegistroConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Consulta> getConsultaLista(){
        Set<Consulta> list = paciente.getConsultas();
        List<Consulta> consultas = new ArrayList<>();
        Iterator<Consulta> i=list.iterator();
        while(i.hasNext()){
            Consulta c = i.next();
            consultas.add(c);
        }  
        
      return consultas;
    }
    public ServiciosPacientes getServicio(){
        return sp;
    }
     
     
   
    
    
    
}


