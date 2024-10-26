/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import org.json.JSONArray;
import org.json.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Realiza la consulta a la base de datos para obtener la lista de productos
        List<Producto> productos = obtenerProductosDesdeBD();
        
        // Convierte la lista de productos a un JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Producto producto : productos) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idproducto", producto.getIdproducto());
            jsonObject.put("nombre", producto.getNombre());
            jsonArray.put(jsonObject);
        }

        // Escribe el JSONArray como respuesta
        response.getWriter().write(jsonArray.toString());
    }

    private List<Producto> obtenerProductosDesdeBD() {
        List<Producto> productos = new ArrayList<>();
        
        try {
            // Conexión a la base de datos (ajusta los valores según tu configuración)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transporte", "root", "Enty");
            String sql = "SELECT idproducto, nombre_producto FROM producto";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            // Itera sobre los resultados y añade cada producto a la lista
            while (rs.next()) {
                int idProducto = rs.getInt("idproducto");
                String nombre = rs.getString("nombre_producto");
                productos.add(new Producto(idProducto, nombre));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return productos;
    }
    
    // Clase interna para representar cada producto
    private class Producto {
        private int idproducto;
        private String nombre_producto;

        public Producto(int idproducto, String nombre) {
            this.idproducto = idproducto;
            this.nombre_producto = nombre;
        }
        
        public int getIdproducto() {
            return idproducto;
        }
        
        public String getNombre() {
            return nombre_producto;
        }
    }
}

