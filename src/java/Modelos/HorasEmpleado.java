/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Lester
 */

public class HorasEmpleado {
    private String nombreEmpleado;
    private int mes;
    private int horasTrabajadas;

    public HorasEmpleado(String nombreEmpleado, int mes, int horasTrabajadas) {
        this.nombreEmpleado = nombreEmpleado;
        this.mes = mes;
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public int getMes() {
        return mes;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
}
