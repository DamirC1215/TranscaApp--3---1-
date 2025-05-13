document.querySelector('.registro-link').addEventListener('click', function() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'block';
    document.querySelector('.wrapper').classList.add('active');
});

document.querySelector('.login-link').addEventListener('click', function() {
    document.getElementById('registerForm').style.display = 'none';
    document.getElementById('loginForm').style.display = 'block';
    document.querySelector('.wrapper').classList.remove('active');
});

const iconClose = document.querySelector('.icon-close');
iconClose.addEventListener('click', function() {
    document.querySelector('.wrapper').style.display = 'none'; 
    
    document.getElementById('loginForm').style.display = 'block'; // Restablece el formulario de inicio de sesión
    document.getElementById('registerForm').style.display = 'none'; // Oculta el formulario de registro

    document.querySelectorAll('#loginForm input').forEach(input => input.value = '');
    document.querySelectorAll('#registerForm input').forEach(input => input.value = '');
});

const loginButton = document.querySelector('.btn');

    // Añade el evento de click al botón
    loginButton.addEventListener('click', function() {
        // Simular que la autenticación es exitosa
        window.location.href = "usuario.html"; // Redirigir a la página del mapa
    });
