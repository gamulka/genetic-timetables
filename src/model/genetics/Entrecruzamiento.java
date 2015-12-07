/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import java.util.ArrayList;
import model.universidad.Grupo;

/**
 *
 * @author Joel
 */
public class Entrecruzamiento {
    
    private ArrayList<Grupo> indice = new ArrayList<>();
    
    public Individuo cruzarIndividuos(Individuo ind1, Individuo ind2) {
        
        Individuo hijo = new Individuo();
        
        Individuo derecha = ind1; //borrarMitadIzquierda(ind2);
        Individuo izquierda = ind2; //borrarMitadDerecha(ind1);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 2; j < 4; j++) {
                hijo.getHorario()[i][j] = derecha.getHorario()[i][j];
                hijo.getAula()[i][j] = derecha.getAula()[i][j];
                hijo.getLab()[i][j] = derecha.getLab()[i][j];
            }
        }        
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                hijo.getHorario()[i][j] = izquierda.getHorario()[i][j];
                hijo.getAula()[i][j] = izquierda.getAula()[i][j];
                hijo.getLab()[i][j] = izquierda.getLab()[i][j];
            }
        }
        indice.clear();
        return hijo;
    }
    
    private Individuo borrarMitadIzquierda(Individuo ind) {
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                
                for (Object grupo : ind.getHorario()[i][j]) {
                    Grupo grupoAct = (Grupo) grupo;
                    indice.add(grupoAct);
                    ind = borrarOcurrencias(ind, grupoAct);
                }
            }
        }        
        return ind;
    }
    
    private Individuo borrarMitadDerecha(Individuo ind) {
        
        for (int i = 0; i < 6; i++) {
            for (int j = 2; j < 4; j++) {
                
                for (Object horario1 : ind.getHorario()[i][j]) {
                    Grupo grupoAct = (Grupo) horario1;
                    ind = borrarOcurrencias(ind, grupoAct);
                }
            }
        }        
        return ind;
    }    
    
    private Individuo borrarOcurrencias(Individuo ind, Grupo grp) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {

                for (Object grupo : ind.getHorario()[i][j]) {
                    Grupo grupoAct = (Grupo) grupo;
                    if (grupoAct.getCurso().equals(grp.getCurso())) {
                        ind.getHorario()[i][j] = new ArrayList();
                        ind.getAula()[i][j] = new ArrayList();
                        ind.getLab()[i][j] = new ArrayList();
                    }
                }
            }
        }
        return ind;
    }
    
}
