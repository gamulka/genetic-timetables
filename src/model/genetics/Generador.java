/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import model.universidad.Grupo;
import java.util.ArrayList;
import model.xml.DataStack;

/**
 * Modelo generador de horarios mediante algoritmos genéticos
 * @author Joel
 */
public class Generador {

    private ArrayList<Individuo> poblacion;
    private ArrayList<Grupo> grupos;
    private DataStack datos;
    
    public Generador() {
        poblacion = new ArrayList<>();
        grupos = new ArrayList<>();
    }
    
    /**
     * Construir grupos a partir de la lista de profesores
     */
    public void construirGrupos() {
        GeneradorGrupos genGr = new GeneradorGrupos(datos);
        this.grupos = genGr.generarGrupos();
    }
    
    /**
     * Se inicializa la población con 1000 individuos
     */
    public void inicializarPoblacion() {
        GeneradorIndividuo genIn = new GeneradorIndividuo(datos, grupos);
        for (int i = 0; i < 10000; i++) {
            poblacion.add(genIn.generarIndividuo());
        }
    }
    
    /**
     * Llama e inicializa el método Iniciar de la clase AlgoritmoGenetico
     */
    public void llamarAlgoritmoGenetico() {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(poblacion, grupos);
        ag.iniciarAG();
    }
    
    /*********************
     * Getters & Setters *
     *********************/ 
    
    public void setDatos(DataStack dat) {
        this.datos = dat;
    }
    
}
