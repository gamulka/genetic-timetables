/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.universidad;

import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class Profesor {
    
    private String nombreProf;
    private ArrayList cursos;
    
    public Profesor() {
        cursos = new ArrayList<>();
    }

    public String getNombreProf() {
        return nombreProf;
    }

    public void setNombreProf(String nombreProf) {
        this.nombreProf = nombreProf;
    }
    
    /**
     * Agregar curso + cantidad de grupos a impartir
     * @param cursoGrupo 
     */
    public void agregarCursos(Curso curso) {
        cursos.add(curso);
    }

    public ArrayList getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList cursos) {
        this.cursos = cursos;
    }
    
}
