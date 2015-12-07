/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.universidad.Aula;
import model.universidad.Curso;
import model.universidad.Laboratorio;
import model.universidad.Profesor;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Joel
 */
public class LectorXML {
 
    private DataStack data;
    private File dataXML;
    
    public LectorXML() {
        data = new DataStack();
    }

    /**
     * Busca el archivo mediante el JFileChooser
     */
    public void encontrarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            dataXML = selectedFile;
        }
    }
    
    /**
     * Leer datos del archivo XML cargado y guardar en el DataStack
     */
    public void leerDatosXML() {
        try {
            if (dataXML != null) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                                                                .newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(dataXML);
                
                Element root = document.getDocumentElement();
                
                // Cursos
                NodeList nlCursos = root.getChildNodes().item(5).getChildNodes();
                extraerDataCursos(nlCursos);                

                // Profesores
                NodeList nlProfes = root.getChildNodes().item(1).getChildNodes();
                extraerDataProf(nlProfes);
                
                // Espacios
                NodeList nlEsp = root.getChildNodes().item(3).getChildNodes();
                extraerDataEsp(nlEsp);

            }
        }
        catch (ParserConfigurationException | SAXException | IOException ex) { }
    }
    
    /**
     * Extrae y almacena datos de la lista de nodos de Profesores
     * @param nlProfes 
     */
    private void extraerDataProf(NodeList nlProfes) {
        try {
            for (int i = 1; i < nlProfes.getLength(); i += 2) {
                Node actual = nlProfes.item(i);
                String nombre = actual.getChildNodes().item(1).getTextContent();
                ArrayList cursoGrup = new ArrayList();

                NodeList imparte = actual.getChildNodes().item(3).getChildNodes();
                for (int j = 1; j < imparte.getLength(); j += 2) {
                    Node curso = imparte.item(j);
                    String cod = curso.getChildNodes().item(1).getTextContent();
                    int grupos = Integer.parseInt(curso.getChildNodes().item(3).getTextContent());
                    Curso crs = data.obtenerCursoPorID(cod);
                    
                    for (int k = 0; k < grupos; k++) {
                        cursoGrup.add(crs);
                    }
                }
                // Crear objeto profesor
                Profesor prof = new Profesor();
                prof.setNombreProf(nombre);
                prof.setCursos(cursoGrup);
                
                // Agregar al DataStack
                data.agregarProfe(prof); 
            }    
        }
        catch (DOMException | NumberFormatException ex) { }
    }
    
    /**
     * Extrae y almacena datos de la lista de nodos de Espacios
     * @param nlEsp
     */
    private void extraerDataEsp(NodeList nlEsp) {
        try {
            for (int i = 1; i < nlEsp.getLength(); i += 2) {
                Node actual = nlEsp.item(i);
                
                // Crear aula
                if ("Aula".equals(actual.getNodeName())) {
                    Aula nueva = new Aula();
                    nueva.setNombre(actual.getTextContent());
                    data.agregarAula(nueva);
                }
                
                // Crear laboratorio
                else {
                    Laboratorio nuevo = new Laboratorio();
                    nuevo.setNombreLab(actual.getTextContent());
                    data.agregarLab(nuevo);
                }
            }    
        }
        catch (DOMException | NumberFormatException ex) { }
    }
    
    /**
     * Extrae y almacena datos de la lista de nodos de Cursos
     * @param nlProfes 
     */
    private void extraerDataCursos(NodeList nlCursos) {
        try {
            for (int i = 1; i < nlCursos.getLength(); i += 2) {
                Node actual = nlCursos.item(i);
                String cod = actual.getChildNodes().item(1).getTextContent();
                String nombre = actual.getChildNodes().item(3).getTextContent();
                double horas = Double.parseDouble(actual.getChildNodes().item(5).getTextContent());
                boolean ocupaLab = traducirBool(actual.getChildNodes().item(7).getTextContent());
                String tipo = actual.getChildNodes().item(9).getTextContent();
                
                Curso nuevo = new Curso();
                nuevo.setCodigo(cod);
                nuevo.setNombre(nombre);
                nuevo.setHoras(horas);
                nuevo.setOcupaLab(ocupaLab);
                nuevo.setTipo(tipo);
                
                data.agregarCurso(nuevo);
            }    
        }
        catch (DOMException | NumberFormatException ex) { }
    }    
    
    /**
     * Pasar un valor String a valor Boolean para una instancia de curso
     * @param str - valor que puede ser 1 o 0
     * @return true - si el valor es 1
     */
    private boolean traducirBool(String str) {
        return str.equals("1");
    }
    
    /**
     * Obtener el data asociado
     * @return data - datastack extraido del XML
     */
    public DataStack getData() {
        return data;
    }    
    
}
