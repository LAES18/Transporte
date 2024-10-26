import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/NewServlet_intOut")
public class NewServlet_intOut extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Configura el juego de caracteres de la solicitud
            request.setCharacterEncoding("UTF-8");

            // Obtén y valida los datos del formulario
            String movimiento = request.getParameter("Movimiento");
            String predioOrigen = request.getParameter("predioOrigen");
            String predioIngreso = request.getParameter("predioIngreso");
            String placaCamion = request.getParameter("placaCamion");
            String producto = request.getParameter("producto");
            String piloto = request.getParameter("piloto");

            // Validación de campos
            if (movimiento == null || predioOrigen == null || predioIngreso == null || placaCamion == null || producto == null || piloto == null) {
                out.write("Error: Todos los campos son requeridos.");
                return;
            }

            // Guarda en la base de datos y responde
            if (guardarDatosEnBD(movimiento, predioOrigen, predioIngreso, placaCamion, producto, piloto)) {
                out.write("Datos guardados correctamente.");
            } else {
                out.write("Error al guardar los datos en la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("Error en el procesamiento de datos: " + e.getMessage());
        }
    }

    private boolean guardarDatosEnBD(String movimiento, String predioOrigen, String predioIngreso, String placaCamion, String producto, String piloto) {
        boolean exito = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Configuración de conexión (ajusta los valores de acuerdo con tu base de datos)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transporte", "root", "Enty");

            // Consulta SQL para insertar en la tabla `int_out`
            String sql = "INSERT INTO int_out (movimiento, origen, destino, predio_idpredio, camion_idcamion, piloto_idpiloto) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Configura los parámetros de la consulta SQL
            stmt.setString(1, movimiento);
            stmt.setString(2, predioOrigen);
            stmt.setString(3, predioIngreso);
            stmt.setInt(4, Integer.parseInt(producto));  // ID del producto
            stmt.setInt(5, Integer.parseInt(placaCamion));  // ID del camión
            stmt.setInt(6, Integer.parseInt(piloto));  // ID del piloto

            // Ejecuta la consulta y verifica si fue exitosa
            int filasInsertadas = stmt.executeUpdate();
            exito = filasInsertadas > 0;

        } catch (NumberFormatException nfe) {
            System.err.println("Error de formato numérico en uno de los parámetros: " + nfe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return exito;
    }
}
