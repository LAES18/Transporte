/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Lester
 */

public class IngresoUsuario {
    private String nombreUsuario;
    private int cantidadIngresos;

    public IngresoUsuario(String nombreUsuario, int cantidadIngresos) {
        this.nombreUsuario = nombreUsuario;
        this.cantidadIngresos = cantidadIngresos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getCantidadIngresos() {
        return cantidadIngresos;
    }
}

