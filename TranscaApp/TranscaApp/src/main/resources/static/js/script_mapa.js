// Dropdown para menú usuario (tu código actual)
document.getElementById('userDropdown').addEventListener('click', function() {
    var dropdownMenu = document.getElementById('dropdownMenu');
    dropdownMenu.style.display = dropdownMenu.style.display === 'none' ? 'block' : 'none';
});

// Cerrar el dropdown si se hace clic fuera de él
window.onclick = function(event) {
    if (!event.target.matches('.user-name')) {
        var dropdowns = document.getElementsByClassName('dropdown-content');
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.style.display === 'block') {
                openDropdown.style.display = 'none';
            }
        }
    }
};

// Variables globales para el mapa y servicios
let map;
let directionsService;
let directionsRenderer;
let markers = [];

let rutaSeleccionadaGlobal = null;
let origenSeleccionadoGlobal = null;
let destinoSeleccionadoGlobal = null;
let estacionesIntermediasGlobal = [];

// Inicializar mapa
function iniciarMap() {
    var coord = {lat: 10.410793, lng: -75.518633};
    map = new google.maps.Map(document.getElementById('map'),{
        zoom: 13,
        center: coord
    });

    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer({map: map});
}

// Limpiar marcadores y ruta previa
function clearMap() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
    directionsRenderer.set('directions', null);
}

// Mostrar ruta con origen, destino y estaciones intermedias (waypoints)
// Además calcula el tiempo estimado y lo muestra en input con id "tiempoEstimado"
function mostrarRuta(origen, destino, estacionesIntermedias) {
    clearMap();

    rutaSeleccionadaGlobal = null; // Reseteamos selección global, si aplicara
    origenSeleccionadoGlobal = origen;
    destinoSeleccionadoGlobal = destino;
    estacionesIntermediasGlobal = estacionesIntermedias;

    // Crear marcadores para origen y destino con etiquetas A y B
    markers.push(new google.maps.Marker({position: origen, map: map, label: "A"}));
    markers.push(new google.maps.Marker({position: destino, map: map, label: "B"}));

    // Crear waypoints para estaciones intermedias (sin incluir origen y destino)
    const waypts = estacionesIntermedias.map(est => ({
        location: est,
        stopover: true
    }));

    directionsService.route({
        origin: origen,
        destination: destino,
        waypoints: waypts,
        travelMode: google.maps.TravelMode.TRANSIT
    }, (result, status) => {
        if (status === 'OK') {
            directionsRenderer.setDirections(result);

            // Cálculo distancia total en metros
            const route = result.routes[0];
            let distanciaTotal = 0;
            const path = [];

            // Extraemos todos los puntos del recorrido (legs y steps)
            route.legs.forEach(leg => {
                leg.steps.forEach(step => {
                    step.path.forEach(point => {
                        path.push(point);
                    });
                });
            });

            // Sumamos distancia entre cada par de puntos consecutivos
            for (let i = 0; i < path.length - 1; i++) {
                distanciaTotal += google.maps.geometry.spherical.computeDistanceBetween(path[i], path[i+1]);
            }

            // Parámetros para cálculo de tiempo
            const velocidadKmH = 30; // velocidad promedio Transcaribe
            const velocidadKmMin = velocidadKmH / 60; // km/min
            const tiempoPorSemaforo = 1.5; // min
            const cantidadSemaforos = 10; // cantidad semáforos en la ruta (fijo)

            // Convertir distancia a km
            const distanciaKm = distanciaTotal / 1000;

            // Calcular tiempo estimado
            const tiempoEstimado = (distanciaKm / velocidadKmMin) + (cantidadSemaforos * tiempoPorSemaforo);

            // Actualizar campo de tiempo estimado en la UI
            const inputTiempo = document.getElementById('tiempoEstimado');
            if(inputTiempo){
                inputTiempo.value = Math.round(tiempoEstimado) + ' min';
            } else {
                console.log('Tiempo estimado:', Math.round(tiempoEstimado), 'min');
            }

            // Guardamos globalmente el tiempo estimado para usarlo en el guardado
            window.tiempoEstimadoGlobal = Math.round(tiempoEstimado);

        } else {
            alert("No se pudo calcular la ruta: " + status);
        }
    });
}

// Función para enviar el registro de ruta al backend
function guardarRegistroRuta() {
    if (!origenSeleccionadoGlobal || !destinoSeleccionadoGlobal) {
        alert("Debe calcular una ruta primero.");
        return;
    }

    // Construir array con nombres de estaciones: origen + intermedias + destino
    let estacionesRecorridas = [origenSeleccionadoGlobal];
    estacionesIntermediasGlobal.forEach(e => estacionesRecorridas.push(e));
    estacionesRecorridas.push(destinoSeleccionadoGlobal);

    // Mapear sólo nombres para enviar
    const estacionesNombres = estacionesRecorridas.map(est => est.nombre ? est.nombre : `${est.lat},${est.lng}`);

    const registro = {
        // Aquí debes completar con id/nombre si tienes disponibles en tu lógica
        rutaId: null, // Si tienes un id en tu lógica, asigna aquí
        rutaNombre: "Ruta personalizada", // Ajusta o envía según tu selección
        origen: origenSeleccionadoGlobal.nombre || null,
        destino: destinoSeleccionadoGlobal.nombre || null,
        estacionesRecorridas: estacionesNombres,
        tiempoEstimado: window.tiempoEstimadoGlobal || 0
    };

    fetch('/api/rutas/registro', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(registro)
    })
    .then(res => {
        if(res.ok){
            alert('Registro guardado exitosamente');
        } else {
            alert('Error guardando el registro');
        }
    })
    .catch(err => alert('Error al conectar con el servidor'));
}

// Ejemplo para probar (debes adaptar estos datos según tu selección real)
function ejemploDeUso() {
    const origen = {lat: 10.395375, lng: -75.472822, nombre:"Portal"};
    const destino = {lat: 10.419834, lng: -75.551683, nombre:"La Bodeguita"};
    const estacionesIntermedias = [
        {lat: 10.394998, lng: -75.478884, nombre:"Madre Bernarda"},
        {lat: 10.394437, lng: -75.486037, nombre:"La Castellana"},
        {lat: 10.406490, lng: -75.502638, nombre:"Cuatro Vientos"},
        {lat: 10.409010, lng: -75.515796, nombre:"Maria Auxiliadora"}
    ];

    mostrarRuta(origen, destino, estacionesIntermedias);
}

// Llama iniciarMap al cargar la página
window.onload = function() {
    iniciarMap();

    // ejemploDeUso(); // Descomenta para probar la ruta de ejemplo al iniciar

    // Asumiendo que tienes un botón confirmar ruta con id 'btnConfirmarRuta'
    const btnConfirmar = document.getElementById('btnConfirmarRuta');
    if(btnConfirmar){
        btnConfirmar.addEventListener('click', guardarRegistroRuta);
    }
};
