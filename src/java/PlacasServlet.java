/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/PlacasServlet")
public class PlacasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String predioId = request.getParameter("predioId");
        if (predioId == null || predioId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"error\": \"El parámetro predioId es requerido\"}");
            return;
        }

        try {
            String url = "jdbc:mysql://localhost:3306/transporte";
            String user = "root";
            String password = "Enty";
            Connection conn = DriverManager.getConnection(url, user, password);

            // Cambia el nombre de la tabla a 'camion' y usa las columnas correctas
            String sql = "SELECT idcamion, placa FROM camion WHERE transporte_idtransporte = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(predioId)); // Asegúrate de que sea el tipo correcto
            ResultSet resultSet = statement.executeQuery();

            JSONArray placasArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject placaJson = new JSONObject();
                placaJson.put("idcamion", resultSet.getInt("idcamion")); // Cambia 'idtransporte' por 'idcamion'
                placaJson.put("placa", resultSet.getString("placa"));
                placasArray.put(placaJson);
            }

            out.print(placasArray.toString());
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\": \"Error en el servidor: " + e.getMessage() + "\"}");
        } finally {
            out.close();
        }
    }
}