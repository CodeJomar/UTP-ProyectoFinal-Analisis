// Variables globales
let tablaAlumnos
let alumnosData = []

// Importaciones necesarias
const $ = window.$ // Assuming jQuery is available globally
const Swal = window.Swal // Assuming SweetAlert2 is available globally
const bootstrap = window.bootstrap // Assuming Bootstrap is available globally

// Datos de ejemplo
const datosEjemploAlumnos = [
    {
        id: 1,
        nombre: "Juan Carlos Pérez García",
        email: "juan.perez@email.com",
        telefono: "+51 967 654 321",
        dni: "72345678",
        estado: "ACTIVO",
    },
    {
        id: 2,
        nombre: "María Elena Rodríguez López",
        email: "maria.rodriguez@email.com",
        telefono: "+51 987 654 321",
        dni: "73456789",
        estado: "ACTIVO",
    },
    {
        id: 3,
        nombre: "Carlos Alberto Mendoza Silva",
        email: "carlos.mendoza@email.com",
        telefono: "+51 967 654 323",
        dni: "74567890",
        estado: "INACTIVO",
    },
    {
        id: 4,
        nombre: "Ana Sofía Vargas Torres",
        email: "ana.vargas@email.com",
        telefono: "+51 967 654 324",
        dni: "75678901",
        estado: "ACTIVO",
    },
    {
        id: 5,
        nombre: "Diego Fernando Castillo Ruiz",
        email: "diego.castillo@email.com",
        telefono: "+51 967 654 325",
        dni: "76789012",
        estado: "ACTIVO",
    },
]

// Inicialización cuando se carga la página
document.addEventListener("DOMContentLoaded", () => {
    inicializarDatos()
    inicializarTabla()
    configurarBusqueda()
})

function inicializarDatos() {
    alumnosData = [...datosEjemploAlumnos]
}

function inicializarTabla() {
    tablaAlumnos = $("#tablaAlumnos").DataTable({
        data: alumnosData,
        columns: [
            { data: "id" },
            { data: "nombre" },
            { data: "email" },
            { data: "telefono" },
            { data: "dni" },
            {
                data: "estado",
                render: (data, type, row) => {
                    const badgeClass = data === "ACTIVO" ? "bg-success" : "bg-warning"
                    return `<span class="badge ${badgeClass}">${data}</span>`
                },
            },
            {
                data: null,
                render: (data, type, row) => `
                        <button class="btn btn-outline-info btn-sm me-1" onclick="verDetalles(${row.id})" title="Ver detalles">
                            <i class="bi bi-eye"></i>
                        </button>
                        <button class="btn btn-outline-warning btn-sm me-1" onclick="editarAlumno(${row.id})" title="Editar">
                            <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn btn-outline-danger btn-sm" onclick="eliminarAlumno(${row.id})" title="Eliminar">
                            <i class="bi bi-trash"></i>
                        </button>
                    `,
            },
        ],
        responsive: true,
        language: {
            url: "//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json",
        },
        dom: "lrtip",
        pageLength: 10,
        order: [[0, "asc"]],
    })
}

function configurarBusqueda() {
    const searchInput = document.getElementById("searchInputAlumnos")
    searchInput.addEventListener("keyup", function () {
        tablaAlumnos.search(this.value).draw()
    })
}

function guardarAlumno() {
    const nombre = document.getElementById("nombreAlumno").value
    const email = document.getElementById("emailAlumno").value
    const telefono = document.getElementById("telefonoAlumno").value
    const dni = document.getElementById("dniAlumno").value
    const estado = document.getElementById("estadoAlumno").value

    if (!nombre || !email || !dni) {
        Swal.fire("Error", "Por favor complete todos los campos obligatorios", "error")
        return
    }

    const nuevoAlumno = {
        id: alumnosData.length + 1,
        nombre: nombre,
        email: email,
        telefono: telefono,
        dni: dni,
        estado: estado,
    }

    alumnosData.push(nuevoAlumno)
    tablaAlumnos.clear().rows.add(alumnosData).draw()

    // Limpiar y ocultar formulario
    document.getElementById("formAlumno").reset()
    document.getElementById("formularioAlumno").style.display = "none"

    Swal.fire("Éxito", "Alumno agregado correctamente", "success")
}

function verDetalles(id) {
    const alumno = alumnosData.find((a) => a.id === id)
    if (alumno) {
        Swal.fire({
            title: "Detalles del Alumno",
            html: `
                <div class="text-start">
                    <p><strong>ID:</strong> ${alumno.id}</p>
                    <p><strong>Nombre:</strong> ${alumno.nombre}</p>
                    <p><strong>Email:</strong> ${alumno.email}</p>
                    <p><strong>Teléfono:</strong> ${alumno.telefono}</p>
                    <p><strong>DNI:</strong> ${alumno.dni}</p>
                    <p><strong>Estado:</strong> <span class="badge ${alumno.estado === "ACTIVO" ? "bg-success" : "bg-warning"}">${alumno.estado}</span></p>
                </div>
            `,
            icon: "info",
            confirmButtonText: "Cerrar",
        })
    }
}

function editarAlumno(id) {
    const alumno = alumnosData.find((a) => a.id === id)
    if (alumno) {
        document.getElementById("nombreAlumno").value = alumno.nombre
        document.getElementById("emailAlumno").value = alumno.email
        document.getElementById("telefonoAlumno").value = alumno.telefono
        document.getElementById("dniAlumno").value = alumno.dni
        document.getElementById("estadoAlumno").value = alumno.estado

        document.getElementById("tituloFormulario").innerHTML = '<i class="bi bi-pencil me-2"></i>Editar Alumno'
        document.getElementById("formularioAlumno").style.display = "block"

        // Scroll suave al formulario
        document.getElementById("formularioAlumno").scrollIntoView({
            behavior: "smooth",
            block: "start",
        })
    }
}

function eliminarAlumno(id) {
    Swal.fire({
        title: "¿Eliminar alumno?",
        text: "Esta acción no se puede deshacer",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
    }).then((result) => {
        if (result.isConfirmed) {
            alumnosData = alumnosData.filter((a) => a.id !== id)
            tablaAlumnos.clear().rows.add(alumnosData).draw()
            Swal.fire("Eliminado", "El alumno ha sido eliminado", "success")
        }
    })
}

function cerrarSesion() {
    Swal.fire({
        title: "¿Cerrar sesión?",
        text: "¿Estás seguro de que quieres cerrar sesión?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, cerrar sesión",
        cancelButtonText: "Cancelar",
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "login.html"
        }
    })
}

function mostrarFormulario() {
    document.getElementById("formularioAlumno").style.display = "block"
    document.getElementById("tituloFormulario").innerHTML = '<i class="bi bi-mortarboard me-2"></i>Agregar Nuevo Alumno'
    document.getElementById("formAlumno").reset()

    // Scroll suave al formulario
    document.getElementById("formularioAlumno").scrollIntoView({
        behavior: "smooth",
        block: "start",
    })
}

function cancelarFormulario() {
    document.getElementById("formularioAlumno").style.display = "none"
    document.getElementById("formAlumno").reset()
}
