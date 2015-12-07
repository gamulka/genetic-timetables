/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.genetics;
 
import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class Individuo implements Comparable<Individuo> {
    
    private ArrayList[][] horario;
    private ArrayList[][] aula;
    private ArrayList[][] lab;
    private double fitness;
    
    public Individuo() {
        horario = new ArrayList[6][4];
        aula    = new ArrayList[6][4];
        lab     = new ArrayList[6][4];
        llenarArrays();
    }
    
    private void llenarArrays() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                horario[i][j] = new ArrayList();
                aula[i][j] = new ArrayList();
                lab[i][j] = new ArrayList();
            }
        }
    }
    
    /**
     * Getters & Setters
     */
    
    public ArrayList[][] getHorario() {
        return horario;
    }

    public void setHorario(ArrayList[][] horario) {
        this.horario = horario;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public ArrayList[][] getAula() {
        return aula;
    }

    public void setAula(ArrayList[][] aula) {
        this.aula = aula;
    }

    public ArrayList[][] getLab() {
        return lab;
    }

    public void setLab(ArrayList[][] lab) {
        this.lab = lab;
    }

    /**
     * Asignar Comparable a Individuo
     */
    @Override
    public int compareTo(Individuo o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }
    
}
