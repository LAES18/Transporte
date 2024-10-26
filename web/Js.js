document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM completamente cargado y analizado.");

    fetch("/WebApplication3/DataServlet")
        .then(response => response.json())
        .then(data => {
            const selectOrigen = document.getElementById("predioOrigen");
            const selectIngreso = document.getElementById("predioIngreso");

            // Agregar opciones a ambos selects
            data.forEach((predio, index) => {
                const optionOrigen = document.createElement("option");
                const optionIngreso = document.createElement("option");

                optionOrigen.value = index + 1; // Valor incremental para cada predio
                optionOrigen.textContent = predio.nombre; // Nombre del predio
                optionIngreso.value = index + 1; // Valor incremental para cada predio
                optionIngreso.textContent = predio.nombre; // Nombre del predio

                selectOrigen.appendChild(optionOrigen);
                selectIngreso.appendChild(optionIngreso);
            });

            // Función para validar selección y cargar placas
            function validarSeleccion() {
                const origenSeleccionado = selectOrigen.value;

                // Cargar placas si se selecciona un origen
                if (origenSeleccionado) {
                    console.log("Predio de Origen seleccionado:", origenSeleccionado); // Verificar el valor aquí
                    cargarPlacas(origenSeleccionado);
                } else {
                    console.error("No se ha seleccionado un predio de origen válido.");
                }
            }

            // Función para cargar las placas según el predio seleccionado
            function cargarPlacas(origenSeleccionado) {
                const selectPlaca = document.getElementById("placaCamion");
                selectPlaca.innerHTML = '<option value="">Seleccione una placa</option>'; // Reiniciar select

                // Hacer la petición al servlet para obtener las placas
                 fetch(`/WebApplication3/PlacasServlet?predioId=${parseInt(origenSeleccionado)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: ${response.status}`);
                }
                return response.json();
            })
            .then(placas => {
                console.log("Placas recibidas:", placas); // Verifica las placas recibidas

                if (Array.isArray(placas) && placas.length > 0) {
                    placas.forEach(placa => {
                        const optionPlaca = document.createElement("option");
                        optionPlaca.value = placa.idcamion; // Usar el ID del camión como valor
                        optionPlaca.textContent = placa.placa; // Mostrar la placa
                        selectPlaca.appendChild(optionPlaca); // Agregar la opción al select
                    });
                    console.log("Opciones de placa agregadas al select."); // Mensaje de depuración
                } else {
                    console.error("No se recibieron placas válidas.");
                }
            })
            .catch(error => {
                console.error("Hubo un problema con la solicitud fetch:", error);
            });
            }

            // Escuchar cambios en el select de origen
            selectOrigen.addEventListener("change", validarSeleccion);
        })
        .catch(error => console.error("Error al cargar los predios:", error));
});