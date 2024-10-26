<%-- 
    Document   : productividad
    Created on : 26 oct 2024, 7:20:45 a.m.
    Author     : Lester
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- productividad.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporte de Productividad</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-primary mb-4">Reporte de Productividad</h1>

        <!-- Reporte de Ingresos por Usuario -->
        <div class="card mb-4">
            <div class="card-header bg-primary text-white">
                <h4>Ingresos Registrados por Usuario</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Usuario</th>
                            <th>Ingresos Registrados</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ingreso" items="${ingresosPorUsuario}">
                            <tr>
                                <td>${ingreso.nombreUsuario}</td>
                                <td>${ingreso.cantidadIngresos}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Reporte de Horas Trabajadas Mensuales por Empleado -->
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h4>Horas Trabajadas Mensualmente por Empleado</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Empleado</th>
                            <th>Mes</th>
                            <th>Horas Trabajadas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="horas" items="${horasMensuales}">
                            <tr>
                                <td>${horas.nombreEmpleado}</td>
                                <td>${horas.mes}</td>
                                <td>${horas.horasTrabajadas}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

