/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import java.util.ArrayList;
import java.util.Random;
import model.universidad.Aula;
import model.universidad.Grupo;
import model.universidad.Laboratorio;
import model.xml.DataStack;

/**
 *
 * @author Joel
 */
public class GeneradorIndividuo {
    
    private DataStack datos;
    private ArrayList<Grupo> grupos;
    
    public GeneradorIndividuo(DataStack ds, ArrayList<Grupo> gr) {
        this.datos = ds;
        this.grupos = gr;
    }

    public Individuo generarIndividuo() {
        
        Random rnd = new Random(); // Random para aleatorizar
        
        Individuo nuevo = new Individuo();

        int fila;
        int col;
        ArrayList[][] grp = nuevo.getHorario();
        ArrayList[][] aul = nuevo.getAula();
        ArrayList[][] lab = nuevo.getLab();

        for (Grupo grupo : grupos) {

            col = rnd.nextInt(4);
            
            if (grupo.getCurso().getTipo().equals("Diurno")) {
                fila = rnd.nextInt(4);
            }
            else {
                fila = 4 + rnd.nextInt(2);
            }
            
            if (grupo.getCurso().getHoras() == 3 && grupo.getCurso().getTipo().equals("Diurno")) {
                fila = 1;
            }
            
            if (grupo.getCurso().isOcupaLab()) {
                grp[fila][col].add(grupo);
                lab[fila][col].add(datos.getListaLabs()
                        .get(rnd.nextInt(datos.getListaLabs().size())));
            }
            else {
                grp[fila][col].add(grupo);
                aul[fila][col].add(datos.getListaAulas()
                        .get(rnd.nextInt(datos.getListaAulas().size())));
            }
        }
        nuevo.setHorario(grp);
        nuevo.setAula(aul);
        nuevo.setLab(lab);
        nuevo.setFitness(0.0f);
        return nuevo;
    }

}
