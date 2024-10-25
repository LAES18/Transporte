<%-- 
    Document   : agregarCamion
    Created on : 25/10/2024, 2:23:55 p. m.
    Author     : mmora
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Camion</title>
    <script>
        function cargarTransportes() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'TransportesJSONServlet', true);
            xhr.onload = function() {
                if (this.status === 200) {
                    var transportes = JSON.parse(this.responseText);
                    var select = document.getElementById("transporte");
                    transportes.forEach(function(transporte) {
                        var option = document.createElement("option");
                        option.value = transporte.idtransporte;
                        option.text = transporte.nombre;
                        select.add(option);
                    });
                }
            };
            xhr.send();
        }
        window.onload = cargarTransportes;
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Agregar Camion</h1>
        
        <form action="RegistrarCamion" method="post" class="mt-4">
            
            <div class="mb-3">
                <label for="transporte" class="form-label">Seleccione el Transporte</label>
                <select name="transporte" id="transporte"></select>
            </div>
            
            <div class="mb-3">
                <label for="placa" class="form-label">Placa del Camion:</label>
                <input type="text" name="placa" id="placa" required>
            </div>
            
            <div class="mb-3">
                <label for="corto" class="form-label">Nombre corto del transporte</label>
                <input type="text" name="corto" id="corto" required>
            </div>
            
            <input type="submit" class="btn btn-primary" value="Agregar Camion">
        </form>
   </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
