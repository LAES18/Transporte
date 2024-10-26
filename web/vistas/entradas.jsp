<%-- 
    Document   : Entradas
    Created on : 25/10/2024, 7:53:30 a. m.
    Author     : hennr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>


        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">


                        <h2>Formulario de Manejo de Entras y Salidas</h2>

                        <form action="../NewServlet_intOut" method="post">

                            <label for="Movimiento">Movimiento</label>
                            <select name="Movimiento" id="Movimiento" required>
                                <option value="">Seleccione un movimiento</option>
                                <option value="Entrada">Entrada</option>
                                <option value="Salida">Salida</option>

                            </select><br><br>

                            <label for="predioOrigen">Predio de Origen:</label>
                            <select name="predioOrigen" id="predioOrigen" required>
                                <option value="">Seleccione un predio</option>
                            </select><br><br>

                            <label for="predioIngreso">Predio de Ingreso:</label>
                            <select name="predioIngreso" id="predioIngreso" required>
                                <option value="">Seleccione un predio</option>
                            </select><br><br>

                            <label for="placaCamion">Placa del camión:</label>
                            <select name="placaCamion" id="placaCamion" required>
                                <option value="">Seleccione un movimiento</option>
                                <option value="Entrada">Entrada</option>
                                <option value="Salida">Salida</option>
                            </select><br><br>
                           
                             <label for="producto">Tipo de producto:</label>
                            <select name="producto" id="producto" required>
                                <option value="">Seleccione un predio</option>
                            </select><br><br>
  
                             <label for="piloto">Id del piloto</label>
                             <input><br><br>
                               
                             
                            <input type="submit" value="Enviar">
                        </form>



                    </div>
                </div>
            </div>
        </div>




        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="../Js.js"></script>
        <script src="../jsproductos.js"></script>

    </body>
</html>
