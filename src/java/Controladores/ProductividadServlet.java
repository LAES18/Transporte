/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import Modelos.DatabaseConnection;
import Modelos.HorasEmpleado;
import Modelos.IngresoUsuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ProductividadServlet")
public class ProductividadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listas para almacenar los reportes
        List<IngresoUsuario> ingresosPorUsuario = obtenerIngresosPorUsuario();
        List<HorasEmpleado> horasMensuales = obtenerHorasTrabajadasMensualmente();

        // Agregar los datos como atributos en la solicitud
        request.setAttribute("ingresosPorUsuario", ingresosPorUsuario);
        request.setAttribute("horasMensuales", horasMensuales);

        // Redirigir a la página de productividad
        request.getRequestDispatcher("productividad.jsp").forward(request, response);
    }

    // Método para obtener el reporte de ingresos por usuario
    private List<IngresoUsuario> obtenerIngresosPorUsuario() {
        List<IngresoUsuario> ingresosPorUsuario = new ArrayList<>();
        String query = "SELECT usuario.nombre AS nombreUsuario, COUNT(int_out.idint_out) AS cantidadIngresos "
                     + "FROM usuario "
                     + "JOIN session ON usuario.idusuario = session.usuario_idusuario "
                     + "JOIN int_out ON session.idsession = int_out.session_idsession "
                     + "GROUP BY usuario.idusuario";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombreUsuario");
                int cantidadIngresos = rs.getInt("cantidadIngresos");
                ingresosPorUsuario.add(new IngresoUsuario(nombreUsuario, cantidadIngresos));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingresosPorUsuario;
    }

    // Método para obtener el reporte de horas trabajadas mensualmente por empleado
    private List<HorasEmpleado> obtenerHorasTrabajadasMensualmente() {
        List<HorasEmpleado> horasMensuales = new ArrayList<>();
        String query = "SELECT usuario.nombre AS nombreEmpleado, MONTH(session.fecha_inicio_sesion) AS mes, "
                     + "SUM(TIMESTAMPDIFF(HOUR, session.fecha_inicio_sesion, session.fecha_salida_sesion)) AS horasTrabajadas "
                     + "FROM usuario "
                     + "JOIN session ON usuario.idusuario = session.usuario_idusuario "
                     + "GROUP BY usuario.idusuario, mes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreEmpleado = rs.getString("nombreEmpleado");
                int mes = rs.getInt("mes");
                int horasTrabajadas = rs.getInt("horasTrabajadas");
                horasMensuales.add(new HorasEmpleado(nombreEmpleado, mes, horasTrabajadas));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return horasMensuales;
    }
}

