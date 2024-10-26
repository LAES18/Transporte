/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    const productoSelect = document.getElementById("producto");

    // Función para cargar los productos desde el servidor
    function cargarProductos() {
        fetch('/WebApplication3/ProductoServlet')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud al servidor');
                }
                return response.json();
            })
            .then(data => {
                // Limpia las opciones actuales del <select>
                productoSelect.innerHTML = '<option value="">Seleccione un producto</option>';
                
                // Añade cada producto como una nueva opción
                data.forEach(item => {
                    const option = document.createElement("option");
                    option.value = item.idproducto;  // Usa el ID como valor
                    option.textContent = item.nombre; // Usa el nombre como texto visible
                    productoSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error:', error));
    }

    // Llama a la función para cargar los productos al cargar la página
    cargarProductos();
});
