/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.universidad;

/**
 *
 * @author Joel
 */
public class Grupo {
    
    private String profesor;
    private Curso curso;
    private int numGrupo;
    
    /**
     * Getters & Setters
     */
    public int getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }
    
    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
