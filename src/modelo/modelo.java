/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class modelo {
    private int id,tamano,sbloq;
    private String nombre,estado; 

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public modelo(int id, int tamano, int sbloq, String nombre) {
        this.id = id;
        this.tamano = tamano;
        this.sbloq = sbloq;
        this.nombre = nombre;
    }

    public int getSbloq() {
        return sbloq;
    }

    public void setSbloq(int sbloq) {
        this.sbloq = sbloq;
    }
    

    public modelo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public modelo(int id, int tamano, String nombre) {
        this.id = id;
        this.tamano = tamano;
        this.nombre = nombre;
    }
    
}
