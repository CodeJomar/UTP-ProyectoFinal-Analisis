// Dashboard JavaScript

// Función para cerrar sesión
function cerrarSesion() {
    if (confirm('¿Estás seguro de que deseas cerrar sesión?')) {
        // Aquí puedes agregar lógica para limpiar sesión
        localStorage.clear();
        sessionStorage.clear();
        
        // Redirigir a página de login (por ahora solo muestra alerta)
        alert('Sesión cerrada exitosamente');
        // window.location.href = 'login.html';
    }
}

// Función para actualizar estadísticas (simulada)
function actualizarEstadisticas() {
    const stats = {
        cursos: Math.floor(Math.random() * 10) + 20,
        talleres: Math.floor(Math.random() * 5) + 5,
        eventos: Math.floor(Math.random() * 8) + 10
    };
    
    // Actualizar los números en las tarjetas
    document.querySelector('.stats-card:nth-child(1) h2').textContent = stats.cursos;
    document.querySelector('.stats-card:nth-child(2) h2').textContent = stats.talleres;
    document.querySelector('.stats-card:nth-child(3) h2').textContent = stats.eventos;
}

// Función para simular actividad en tiempo real
function simularActividad() {
    const actividades = [
        'Nuevo estudiante registrado en Matemáticas Avanzadas',
        'Curso de React actualizado por Ing. López',
        'Evento de DevOps programado para mañana',
        'Taller de Python completado exitosamente'
    ];
    
    // Agregar nueva actividad cada 30 segundos
    setInterval(() => {
        const actividadAleatoria = actividades[Math.floor(Math.random() * actividades.length)];
        console.log('Nueva actividad:', actividadAleatoria);
        // Aquí podrías actualizar la UI con la nueva actividad
    }, 30000);
}

// Inicializar dashboard cuando se carga la página
document.addEventListener('DOMContentLoaded', function() {
    console.log('Dashboard cargado');
    
    // Actualizar estadísticas cada 5 minutos
    setInterval(actualizarEstadisticas, 300000);
    
    // Iniciar simulación de actividad
    simularActividad();
    
    // Agregar efecto hover a las tarjetas de estadísticas
    const statsCards = document.querySelectorAll('.stats-card');
    statsCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-5px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
});

// Función para mostrar notificaciones
function mostrarNotificacion(mensaje, tipo = 'info') {
    // Crear elemento de notificación
    const notificacion = document.createElement('div');
    notificacion.className = `alert alert-${tipo} alert-dismissible fade show position-fixed`;
    notificacion.style.top = '20px';
    notificacion.style.right = '20px';
    notificacion.style.zIndex = '9999';
    notificacion.innerHTML = `
        ${mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(notificacion);
    
    // Remover después de 5 segundos
    setTimeout(() => {
        if (notificacion.parentNode) {
            notificacion.parentNode.removeChild(notificacion);
        }
    }, 5000);
}