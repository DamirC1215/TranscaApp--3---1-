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
function mostrarRuta(origen, destino, estacionesIntermedias) {
    clearMap();

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
        } else {
            alert("No se pudo calcular la ruta: " + status);
        }
    });
}

// Ejemplo para probar (debes adaptar estos datos según tu selección real)
function ejemploDeUso() {
    const origen = {lat: 10.395375, lng: -75.472822};  // Portal
    const destino = {lat: 10.419834, lng: -75.551683}; // La Bodeguita
    const estacionesIntermedias = [
        {lat: 10.394998, lng: -75.478884}, // Madre Bernarda
        {lat: 10.394437, lng: -75.486037}, // La Castellana
        {lat: 10.406490, lng: -75.502638}, // Cuatro Vientos
        {lat: 10.409010, lng: -75.515796}  // Maria Auxiliadora
    ];

    mostrarRuta(origen, destino, estacionesIntermedias);
}

// Llama iniciarMap al cargar la página
window.onload = function() {
    iniciarMap();
    // ejemploDeUso(); // Descomenta para probar la ruta de ejemplo al iniciar
};
