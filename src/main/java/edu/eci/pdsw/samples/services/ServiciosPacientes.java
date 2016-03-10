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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public abstract class ServiciosPacientes {
     int id;
     String tipo_id;
     String nombre;
     Date fechaNacimiento;
     Date fechayHora;
     String fch_tmp;
     String resumen;
    private static ServiciosPacientes instance=new ServiciosPacientesStub();
    Paciente tupac;

    public Paciente getTupac() {
        return tupac;
    }

    public void setFch_tmp(String fch_tmp) {
        this.fch_tmp = fch_tmp;
    }

    public void setTupac(Paciente tupac) {
        this.tupac = tupac;
    }
     public int getId() {
        return id;
    }

    public String getFch_tmp() {
        return fch_tmp;
    }

    public void setId(int id) {
        this.id = id;
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
    
    protected ServiciosPacientes(){        

    }
    
    public static ServiciosPacientes getInstance() throws RuntimeException{        
        return instance;
    }

    /**
     * Consultar un paciente dado su identificador.
     * @param idPaciente identificador del paciente
     * @param tipoid tipo de identificación del paciente
     * @return el paciente con el identificador dado
     * @throws ExcepcionServiciosPacientes si  el paciente no esta registrado.
     */
    public abstract Paciente consultarPaciente(int idPaciente,String tipoid) throws ExcepcionServiciosPacientes;
    
    
    /**
     * Registrar un nuevo PACIENTE en el sistema.
     * @param p El nuevo paciente
     * @throws ExcepcionServiciosPacientes si se presenta algún error de persistencia o si el paciente ya existe.
     */
    public abstract void registrarNuevoPaciente(Paciente p) throws ExcepcionServiciosPacientes;
    
    /**
     * Agrega una consulta a un paciente ya registrado
     * @param idPaciente el identificador del paciente
     * @param tipoid el tipo de identificación
     * @param c la consulta a ser agregada
     * @throws ExcepcionServiciosPacientes si se presenta algún error de persistencia o si el paciente no existe.
     */
    public abstract void agregarConsultaAPaciente(int idPaciente,String tipoid,Consulta c) throws ExcepcionServiciosPacientes;
    
    public abstract List<Paciente> getPacientes();
    
    public Date getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(Date fechayHora) {
        this.fechayHora = fechayHora;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public abstract void agregarConsulta();
    
    
}
