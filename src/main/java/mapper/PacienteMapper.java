/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author santiago-chisco
 */
public interface PacienteMapper {
    
     /**
     * Cargar un paciente por ID
     * @param id del paciente
     * @param tipoid tipo de id
     * @return 
     */
    public Paciente loadPacienteById(@Param("idpaciente")int id,@Param("tipopaciente")String tipoid);

    /**
     * Insertar un nuevo paciente
     * @param p el objeto paciente
     */
    public void insertPaciente(@Param("pacien")Paciente p);
    
    /**
     * Insertar la consulta asociada a un paciente
     * @param con la nueva consulta
     * @param id el identificador del PACIENTE
     * @param tipoid el tipo de ID del PACIENTE
     */
    public void insertConsulta(@Param("consul")Consulta con,@Param("fk_idpacien")int id, @Param("fk_tipopacien")String tipoid);
    
}
