
package Controladores;


import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistrarCamion")
public class RegistrarCamion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/transporte";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    private static final String INSERT_USER_SQL = "INSERT INTO camion (transporte_idtransporte,placa,nombre_corto_transporte) VALUES (?, ?, ?)";

    public RegistrarCamion() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transporte = request.getParameter("transporte");
        String placa = request.getParameter("placa");
        String corto = request.getParameter("corto");
       

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, transporte);
            preparedStatement.setString(2, placa);
            preparedStatement.setString(3, corto);


            int result = preparedStatement.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("agregarCamion.jsp?success=registered");
            } else {
                response.sendRedirect("agregarCamion.jsp?error=registrationFailed");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=exception");
        }
    }

}