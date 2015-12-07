/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import java.util.ArrayList;
import model.universidad.Aula;
import model.universidad.Grupo;
import model.universidad.Laboratorio;


/**
 * 
 * @author Joel
 */
public class EvaluacionFitness {
    
    private final double CONSTANT = 6;
    
    public Individuo evaluarIndividuo(Individuo horario) {
        double valFitness;
        valFitness = reglasDuras(horario) * CONSTANT + reglasSuaves(horario);
        horario.setFitness(valFitness);
        
        // Mostrar lo que está sucediendo
        System.out.println("\nFITNESS INDIVIDUO " + horario.getClass() + " > " + horario.getFitness());
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                
                System.out.print( "Pos (" + i + ", " + j + "): ");
                
                if (!horario.getHorario()[i][j].isEmpty()) {
                    
                    for (int k = 0; k < horario.getHorario()[i][j].size(); k++) {
                        Grupo lol = (Grupo) horario.getHorario()[i][j].get(k);
                        System.out.print(lol.getCurso().getNombre() + " - " + 
                                         lol.getProfesor()+ " - " +
                                         lol.getCurso().getHoras() + " | ");
                    }
                    System.out.println();
                }
                else {
                    System.out.println("No hay cursos para este slot.");
                }
            }
        }

        // Retornar el horario con fitness asignado.
        return horario;
    }
    
    /**
     * Evaluar si las reglas inquebrantables se cumplen
     * @param hor
     * @return 1 si se cumplen; 0 sino 
     */
    private double reglasDuras(Individuo hor) {
        return evaluarValidez(hor);
    }
    
    /**
     * Evaluar las reglas favorables
     * @param hor
     * @return 
     */
    private double reglasSuaves(Individuo hor) {
        return esFavorable(hor);
    }
    
    // **** Reglas Inquebrantables **** //
    
    /**
     * Ejecutar validaciones sobre el individuo
     */
    public double evaluarValidez(Individuo ind) {
        double res = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (chocanProfes(i, j, ind)) { res += 1; }
                if (chocanAulas(i, j, ind))  { res += 1; }
                if (chocanLabs(i, j, ind))   { res += 1; }
            }
        }
        return res / 3.0d;
    }
    
    // Buscar si un profesor fue metido varias veces en una celda
    public boolean chocanProfes(int i, int j, Individuo ind) {
        ArrayList encontrados = new ArrayList<>();
        
        for (Object horario1 : ind.getHorario()[i][j]) {
            Grupo hor = (Grupo) horario1;
            
            if (encontrados.contains(hor.getProfesor())) {
                return true;
            }
            else {
                encontrados.add(hor.getProfesor());
            }
        }
        return false;
    }
    
    // Buscar si un aula fue metida varias veces en una celda
    public boolean chocanAulas(int i, int j, Individuo ind) {
        ArrayList encontrados = new ArrayList<>();
        
        for (Object aul1 : ind.getAula()[i][j]) {
            Aula miAula = (Aula) aul1;
            
            if (encontrados.contains(miAula.getNombre())) {
                return true;
            }
            else {
                encontrados.add(miAula.getNombre());
            }
        }
        return false;
    }
    
    // Buscar si un laboratorio fue metido varias veces en una celda
    public boolean chocanLabs(int i, int j, Individuo ind) {
        ArrayList encontrados = new ArrayList<>();
        
        for (Object lab1 : ind.getLab()[i][j]) {
            Laboratorio miLab = (Laboratorio) lab1;
            
            if (encontrados.contains(miLab.getNombreLab())) {
                return true;
            }
            else {
                encontrados.add(miLab.getNombreLab());
            }
        }
        return false;
    }
    
    // **** Reglas Favorables **** //    
    
    /**
     * Analiza si el individuo tiene aspectos favorables
     * @return de 0 a 1 - 0 más favorable, 1 menos favorable
     */
    public double esFavorable(Individuo ind) {
        double res = 0;
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (!cursosAglomerados(i, j, ind)) { res += 1; }
                if (!deportivasEnLaTarde(i, j, ind)) { res += 1; }
                if (!cursosSeparados(i, j, ind)) { res += 1; }
            }
        }
        return res / 3.0d;
    }
    
    public boolean cursosAglomerados(int i, int j, Individuo ind) {
        if (i != 5) {
            return !ind.getHorario()[i + 1][j].isEmpty();
        }
        else {
            return !ind.getHorario()[i - 1][j].isEmpty();
        }
    }
    
    public boolean deportivasEnLaTarde(int i, int j, Individuo ind) {
        for (Object horario1 : ind.getHorario()[i][j]) {
            Grupo grp = (Grupo) horario1;
            if (grp.getCurso().getNombre().
                equals("Actividad cultural-deportiva")) {
                
                if ((i == 2 || i == 3) && (j == 1 || j == 2 )) {
                    return true;
                }
            }
        }
        return false;
    }  
    
    public boolean cursosSeparados(int i, int j, Individuo ind) {
        
        if (j < 2) {
            for (Object horario1 : ind.getHorario()[i][j]) {
                
                Grupo grp = (Grupo) horario1;
                String curso = grp.getCurso().getCodigo();
                
                for(int index = 0; index < 6; index ++){
                    for (Object horario2 : ind.getHorario()[index][j + 2]) {
                        Grupo grp2 = (Grupo) horario2;
                        if (curso.equals(grp2.getCurso().getCodigo()))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }    
    
}
