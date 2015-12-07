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
public class Curso {
    
    private String codigo;
    private String nombre;
    private double horas;
    private boolean ocupaLab;
    private String tipo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public boolean isOcupaLab() {
        return ocupaLab;
    }

    public void setOcupaLab(boolean ocupaLab) {
        this.ocupaLab = ocupaLab;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
