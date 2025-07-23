// Variables globales
let tablaDocentes
let docentesData = []
const $ = window.$ // Declare the $ variable
const Swal = window.Swal // Declare the Swal variable
const bootstrap = window.bootstrap // Declare the bootstrap variable

// Datos de ejemplo
const datosEjemploDocentes = [
    {
        id: 1,
        nombre: "Dr. Roberto Martínez Sánchez",
        email: "roberto.martinez@email.com",
        especialidad: "Matemáticas",
        telefono: "+51 987 654 321",
        fechaIngreso: "2023-01-15",
        estado: "ACTIVO",
    },
    {
        id: 2,
        nombre: "Dra. Carmen López Herrera",
        email: "carmen.lopez@email.com",
        especialidad: "Física",
        telefono: "+51 987 654 322",
        fechaIngreso: "2023-02-20",
        estado: "ACTIVO",
    },
    {
        id: 3,
        nombre: "Mg. Luis García Morales",
        email: "luis.garcia@email.com",
        especialidad: "Química",
        telefono: "+51 987 654 323",
        fechaIngreso: "2023-03-10",
        estado: "INACTIVO",
    },
    {
        id: 4,
        nombre: "Dra. Patricia Ruiz Vega",
        email: "patricia.ruiz@email.com",
        especialidad: "Biología",
        telefono: "+51 987 654 324",
        fechaIngreso: "2023-04-05",
        estado: "ACTIVO",
    },
    {
        id: 5,
        nombre: "Mg. Fernando Torres Castro",
        email: "fernando.torres@email.com",
        especialidad: "Historia",
        telefono: "+51 987 654 325",
        fechaIngreso: "2023-05-12",
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
    docentesData = [...datosEjemploDocentes]
}

function inicializarTabla() {
    tablaDocentes = $("#tablaDocentes").DataTable({
        data: docentesData,
        columns: [
            { data: "id" },
            { data: "nombre" },
            { data: "email" },
            { data: "especialidad" },
            { data: "telefono" },
            { data: "fechaIngreso" },
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
                        <button class="btn btn-outline-warning btn-sm me-1" onclick="editarDocente(${row.id})" title="Editar">
                            <i class="bi bi-pencil"></i>
                        </button>
                        <button class="btn btn-outline-danger btn-sm" onclick="eliminarDocente(${row.id})" title="Eliminar">
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
    const searchInput = document.getElementById("searchInputDocentes")
    searchInput.addEventListener("keyup", function () {
        tablaDocentes.search(this.value).draw()
    })
}

function guardarDocente() {
    const nombre = document.getElementById("nombreDocente").value
    const email = document.getElementById("emailDocente").value
    const especialidad = document.getElementById("especialidadDocente").value
    const telefono = document.getElementById("telefonoDocente").value
    const fechaIngreso = document.getElementById("fechaIngreso").value
    const estado = document.getElementById("estadoDocente").value

    if (!nombre || !email || !especialidad) {
        Swal.fire("Error", "Por favor complete todos los campos obligatorios", "error")
        return
    }

    const nuevoDocente = {
        id: docentesData.length + 1,
        nombre: nombre,
        email: email,
        especialidad: especialidad,
        telefono: telefono,
        fechaIngreso: fechaIngreso,
        estado: estado,
    }

    docentesData.push(nuevoDocente)
    tablaDocentes.clear().rows.add(docentesData).draw()

    // Limpiar y ocultar formulario
    document.getElementById("formDocente").reset()
    document.getElementById("formularioDocente").style.display = "none"

    Swal.fire("Éxito", "Docente agregado correctamente", "success")
}

function verDetalles(id) {
    const docente = docentesData.find((d) => d.id === id)
    if (docente) {
        Swal.fire({
            title: "Detalles del Docente",
            html: `
                <div class="text-start">
                    <p><strong>ID:</strong> ${docente.id}</p>
                    <p><strong>Nombre:</strong> ${docente.nombre}</p>
                    <p><strong>Email:</strong> ${docente.email}</p>
                    <p><strong>Especialidad:</strong> ${docente.especialidad}</p>
                    <p><strong>Teléfono:</strong> ${docente.telefono}</p>
                    <p><strong>Fecha de Ingreso:</strong> ${docente.fechaIngreso}</p>
                    <p><strong>Estado:</strong> <span class="badge ${docente.estado === "ACTIVO" ? "bg-success" : "bg-warning"}">${docente.estado}</span></p>
                </div>
            `,
            icon: "info",
            confirmButtonText: "Cerrar",
        })
    }
}

function editarDocente(id) {
    const docente = docentesData.find((d) => d.id === id)
    if (docente) {
        document.getElementById("nombreDocente").value = docente.nombre
        document.getElementById("emailDocente").value = docente.email
        document.getElementById("especialidadDocente").value = docente.especialidad
        document.getElementById("telefonoDocente").value = docente.telefono
        document.getElementById("fechaIngreso").value = docente.fechaIngreso
        document.getElementById("estadoDocente").value = docente.estado

        document.getElementById("tituloFormulario").innerHTML = '<i class="bi bi-pencil me-2"></i>Editar Docente'
        document.getElementById("formularioDocente").style.display = "block"

        // Scroll suave al formulario
        document.getElementById("formularioDocente").scrollIntoView({
            behavior: "smooth",
            block: "start",
        })
    }
}

function eliminarDocente(id) {
    Swal.fire({
        title: "¿Eliminar docente?",
        text: "Esta acción no se puede deshacer",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
    }).then((result) => {
        if (result.isConfirmed) {
            docentesData = docentesData.filter((d) => d.id !== id)
            tablaDocentes.clear().rows.add(docentesData).draw()
            Swal.fire("Eliminado", "El docente ha sido eliminado", "success")
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
    document.getElementById("formularioDocente").style.display = "block"
    document.getElementById("tituloFormulario").innerHTML =
        '<i class="bi bi-person-workspace me-2"></i>Agregar Nuevo Docente'
    document.getElementById("formDocente").reset()

    // Scroll suave al formulario
    document.getElementById("formularioDocente").scrollIntoView({
        behavior: "smooth",
        block: "start",
    })
}

function cancelarFormulario() {
    document.getElementById("formularioDocente").style.display = "none"
    document.getElementById("formDocente").reset()
}

