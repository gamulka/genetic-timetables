/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.genetics;

import java.util.ArrayList;
import java.util.Collections;
import model.universidad.Curso;
import model.universidad.Grupo;
import model.universidad.Profesor;
import model.xml.DataStack;

/**
 *
 * @author Joel
 */
public class GeneradorGrupos {

    private DataStack datos;
    
    public GeneradorGrupos(DataStack ds) {
        this.datos = ds;
    }
    
    /**
     * Construir grupos a partir de la lista de profesores
     */
    public ArrayList<Grupo> generarGrupos() {
        ArrayList<Grupo> result = new ArrayList<>();
        
        ArrayList agregados = new ArrayList<>();
        ArrayList lProf = datos.getListaProfes();

        for (Object prof : lProf) {
            Profesor profe = (Profesor) prof;
            ArrayList listaCursos = profe.getCursos();
            
            for (Object course : listaCursos) {
                Curso crs = (Curso) course;
                agregados.add(course);
                int ocurrencias = Collections.frequency(agregados, course);
                Grupo nuevo = new Grupo();
                nuevo.setProfesor(profe.getNombreProf());
                nuevo.setCurso(crs);
                nuevo.setNumGrupo(ocurrencias);
                
                // Agregar el grupo una vez por cada hora de duración
                if (nuevo.getCurso().getHoras() == 3 ||
                    nuevo.getCurso().getHoras() == 2)
                {
                    result.add(nuevo);
                }
                else
                {
                    result.add(nuevo);
                    result.add(nuevo);
                }
            }
        }
        return result;
    }
    
}