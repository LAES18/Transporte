<%-- 
    Document   : adminDashboard
    Created on : 23 oct 2024, 4:17:05 p.m.
    Author     : Lester
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Admin Dashboard</h1>
        <p class="text-center">Bienvenido, <%= session.getAttribute("userName") %> (Administrador)</p>

        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Gestión de Usuarios</h5>
                        <p class="card-text">Accede a la administración de usuarios y otras funciones administrativas.</p>
                        <a href="#" class="btn btn-primary">Administrar Usuarios</a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Gestión de Predios</h5>
                        <a href="agregarPredio.jsp" class="btn btn-primary">Agregar Predios</a>
                        <a href="agregarBodega.jsp" class="btn btn-primary">Agregar Bodegas</a>
                        <a href="agregarProducto.jsp" class="btn btn-primary">Productos</a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Gestión de Transportes</h5>
                        <a href="agregarTransporte.jsp" class="btn btn-primary">Agregar Transporte</a>
                        <a href="" class="btn btn-primary">Eliminar Transporte</a>
                    </div>
                    <div class="card-body">
                        <a href="agregarCamion.jsp" class="btn btn-primary">Agregar Camion</a>
                        <a href="agregarPiloto.jsp" class="btn btn-primary">Agregar Piloto</a>
                        
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Gestion de operaciones</h5>
                        <p class="card-text">Accede a la administración de entradas y salidalas de camiones.</p>
                        <a href="vistas/entradas.jsp" class="btn btn-primary">Administrar Movimientos</a>
                    </div>
                </div>
            </div>
        </div>
        
        
        
        <div class="text-center mt-4">
            <a href="LogoutServlet" class="btn btn-danger">Cerrar sesión</a>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


