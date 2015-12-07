/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import java.util.ArrayList;
import java.util.Collections;
import model.universidad.Grupo;
import view.Horario;

/**
 *
 * @author Joel
 */
public class AlgoritmoGenetico {
    
    private final double FITNESS_GOAL = 12;
    private final double CONSTANTE_PRESERVACION = 0.495;
    private final int TAMANO_INIT;
    
    private ArrayList<Individuo> poblacion;
    private ArrayList<Grupo> grupos;
    
    public AlgoritmoGenetico(ArrayList<Individuo> pob,
                             ArrayList<Grupo> grp) {
        this.poblacion = pob;
        this.grupos = grp;
        this.TAMANO_INIT = poblacion.size();
    }
     
    /**
     * Método que toma la población inicializada y se encarga
     * de administrar los cruces, selección y la repetición del
     * proceso hasta encontrar un individuo bastante bueno.
     */
    public void iniciarAG() {
        
        // Banderas para continuar buscando
        boolean objetivoLogrado = false;
        double valMejorFitness = 100; // valor alto a reducir
        
        // Objetos propios del algoritmo genético
        EvaluacionFitness evaluar = new EvaluacionFitness();
        Entrecruzamiento cruzar = new Entrecruzamiento();
        
        while (!objetivoLogrado && poblacion.size() > TAMANO_INIT * 0.01) {

            for (Individuo indiv : poblacion) {
                evaluar.evaluarIndividuo(indiv);
            }
            
            // Ordenar cada horario de la población según su fitness
            Collections.sort(poblacion);

            // Recortar la mitad peor valorada
            ArrayList<Individuo> sobrevivientes = new ArrayList();
            for (int i = 0; i < poblacion.size() * CONSTANTE_PRESERVACION; i++) {
                sobrevivientes.add(poblacion.get(i));
            }
            poblacion = sobrevivientes;

            // Hacer cruces necesarios
            int numCruces = poblacion.size() - 1;
            for (int i = 0; i < numCruces; i++) {
                poblacion.add(cruzar.cruzarIndividuos(poblacion.get(i), 
                                                  poblacion.get(i + 1)));
            }
            
            System.out.println("Tamano pob.: " + poblacion.size());
            System.out.println("Fitness actual: " + valMejorFitness + "\n");
            
            // Actualizar fitness y verificar si se alcanzó el objetivo
            valMejorFitness = poblacion.get(0).getFitness();
            if (valMejorFitness < FITNESS_GOAL) {
                objetivoLogrado = true;
            }
        }
        
        // Ordenar cada horario de la población según su fitness
        Collections.sort(poblacion);
        
        System.out.println("Tamano pob.: " + poblacion.size());
        System.out.println("Fitness actual: " + valMejorFitness + "\n");
        
        Horario mostrarHor = new Horario();
        mostrarHor.setHorario(poblacion.get(0));
        mostrarHor.representarHorario();
    }
    
    
} // END