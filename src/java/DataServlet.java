
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Configuración de la conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/transporte";
            String user = "root";
            String password = "Enty";
            Connection conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para obtener los predios
            String sql = "SELECT nombre FROM predio";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Convertir los resultados en un JSONArray
            JSONArray prediosArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject predio = new JSONObject();
                predio.put("nombre", resultSet.getString("nombre"));
                prediosArray.put(predio);
            }

            out.print(prediosArray.toString());  // Enviar JSON al cliente
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            out.close();
        }
    }
}
