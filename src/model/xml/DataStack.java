/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.xml;

import java.util.ArrayList;
import model.universidad.Aula;
import model.universidad.Curso;
import model.universidad.Laboratorio;
import model.universidad.Profesor;

/**
 *
 * @author Joel
 */
public class DataStack {

    private ArrayList listaProfes;
    private ArrayList listaAulas;
    private ArrayList listaLabs;
    private ArrayList listaCursos;
    
    public DataStack() {
        listaProfes = new ArrayList<>();
        listaAulas = new ArrayList<>();
        listaLabs = new ArrayList<>();
        listaCursos = new ArrayList<>();
    }
    
    /**
     * Hallar objeto curso a partir de parámetro Código
     * @param id
     * @return 
     */
    public Curso obtenerCursoPorID(String id) {
        for (Object curso : listaCursos) {
            Curso crs = (Curso) curso;
            if (crs.getCodigo().equals(id)) {
                return crs;
            }
        }
        return null;
    }
    
    /**
     * "Adders" para agregar datos a las listas
     */
    
    public void agregarProfe(Profesor profe) {
        listaProfes.add(profe);
    }
    
    public void agregarAula(Aula aula) {
        listaAulas.add(aula);
    }
    
    public void agregarLab(Laboratorio lab) {
        listaLabs.add(lab);
    }
    
    public void agregarCurso(Curso curso) {
        listaCursos.add(curso);
    }    
    
    /**
     * Getters para las listas
     */

    public ArrayList getListaProfes() {
        return listaProfes;
    }

    public ArrayList getListaAulas() {
        return listaAulas;
    }

    public ArrayList getListaLabs() {
        return listaLabs;
    }

    public ArrayList getListaCursos() {
        return listaCursos;
    }
    
}
