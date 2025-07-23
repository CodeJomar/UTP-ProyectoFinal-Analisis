// Cursos JavaScript - Vanilla JS

// Import SweetAlert2
const Swal = window.Swal

// Import jQuery
const $ = window.$

// Datos de ejemplo para cursos
let cursos = [
  {
    id: 1,
    nombre: "Desarrollo Web Full Stack",
    instructor: "Dr. García López",
    modalidad: "presencial",
    precio: 1500.0,
    estudiantes: 25,
    ciclo: "2024-1",
    fechaInicio: "2024-03-01",
    fechaFin: "2024-07-15",
    estado: "activo",
    descripcion: "Curso completo de desarrollo web con tecnologías modernas",
  },
  {
    id: 2,
    nombre: "React Avanzado",
    instructor: "Ing. María Rodríguez",
    modalidad: "virtual",
    precio: 1200.0,
    estudiantes: 30,
    ciclo: "2024-1",
    fechaInicio: "2024-02-15",
    fechaFin: "2024-06-30",
    estado: "activo",
    descripcion: "Curso avanzado de React con hooks y context",
  },
  {
    id: 3,
    nombre: "Python para Data Science",
    instructor: "Prof. Ana Martínez",
    modalidad: "hibrido",
    precio: 1800.0,
    estudiantes: 20,
    ciclo: "2024-2",
    fechaInicio: "2024-08-01",
    fechaFin: "2024-12-15",
    estado: "programado",
    descripcion: "Análisis de datos y machine learning con Python",
  },
  {
    id: 4,
    nombre: "Diseño UX/UI",
    instructor: "Dr. Luis Fernández",
    modalidad: "presencial",
    precio: 1350.0,
    estudiantes: 18,
    ciclo: "2024-1",
    fechaInicio: "2024-01-15",
    fechaFin: "2024-05-30",
    estado: "finalizado",
    descripcion: "Diseño de experiencias de usuario y interfaces",
  },
  {
    id: 5,
    nombre: "Ciberseguridad Empresarial",
    instructor: "Lic. Carmen Vega",
    modalidad: "virtual",
    precio: 2000.0,
    estudiantes: 15,
    ciclo: "2025-1",
    fechaInicio: "2025-01-10",
    fechaFin: "2025-05-25",
    estado: "programado",
    descripcion: "Seguridad informática para empresas",
  },
]

// Variable para DataTable
let tablaCursos

// Función para limpiar formulario
function limpiarFormulario() {
  document.getElementById("formNuevoCurso").reset()
  $(".is-invalid").removeClass("is-invalid")
}

// Función para aplicar filtros personalizados de cursos
function aplicarFiltrosCursos() {
  if (!tablaCursos) return

  const filtroCiclo = $("#filtroCiclo").val()
  const filtroModalidad = $("#filtroModalidad").val()
  const buscarCurso = $("#buscarCurso").val()

  // Aplicar búsqueda general
  tablaCursos.search(buscarCurso).draw()

  // Aplicar filtros por columna
  tablaCursos
    .column(6) // Columna de ciclo
    .search(filtroCiclo ? filtroCiclo : "", true, false)
    .column(3) // Columna de modalidad
    .search(filtroModalidad ? filtroModalidad : "", true, false)
    .draw()
}

// Función para limpiar filtros de cursos
function limpiarFiltrosCursos() {
  $("#filtroCiclo").val("")
  $("#filtroModalidad").val("")
  $("#buscarCurso").val("")

  if (tablaCursos) {
    tablaCursos.search("").columns().search("").draw()
  }
}

// Función para actualizar tabla de cursos
function actualizarTablaCursos() {
  if (tablaCursos) {
    tablaCursos
      .clear()
      .rows.add(
        cursos.map((curso) => [
          curso.id,
          curso.nombre,
          curso.instructor,
          curso.modalidad.toUpperCase(),
          `S/ ${curso.precio.toFixed(2)}`,
          curso.estudiantes,
          curso.ciclo,
          formatearFecha(curso.fechaInicio),
          `<span class="badge bg-${curso.estado === "activo" ? "success" : curso.estado === "programado" ? "primary" : "secondary"}">${curso.estado.toUpperCase()}</span>`,
          generarBotonesAccionCurso(curso.id),
        ]),
      )
      .draw()
  }
}

// Función para ver detalles del curso
function verCurso(id) {
  const curso = cursos.find((c) => c.id === id)
  if (curso) {
    Swal.fire({
      title: "Detalles del Curso",
      html: `
        <div class="text-start">
          <p><strong>Nombre:</strong> ${curso.nombre}</p>
          <p><strong>Instructor:</strong> ${curso.instructor}</p>
          <p><strong>Modalidad:</strong> ${curso.modalidad.toUpperCase()}</p>
          <p><strong>Precio:</strong> S/ ${curso.precio.toFixed(2)}</p>
          <p><strong>Estudiantes:</strong> ${curso.estudiantes}</p>
          <p><strong>Ciclo:</strong> ${curso.ciclo}</p>
          <p><strong>Fecha Inicio:</strong> ${formatearFecha(curso.fechaInicio)}</p>
          <p><strong>Fecha Fin:</strong> ${formatearFecha(curso.fechaFin)}</p>
          <p><strong>Estado:</strong> <span class="badge bg-${curso.estado === "activo" ? "success" : curso.estado === "programado" ? "primary" : "secondary"}">${curso.estado.toUpperCase()}</span></p>
          <p><strong>Descripción:</strong> ${curso.descripcion}</p>
        </div>
      `,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para editar curso
function editarCurso(id) {
  const curso = cursos.find((c) => c.id === id)
  if (curso) {
    Swal.fire({
      title: "Editar Curso",
      text: `Editar datos de: ${curso.nombre}`,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para eliminar curso
function eliminarCurso(id) {
  const curso = cursos.find((c) => c.id === id)
  if (curso) {
    Swal.fire({
      title: "¿Eliminar Curso?",
      text: `¿Estás seguro de que deseas eliminar el curso "${curso.nombre}"?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#dc3545",
      cancelButtonColor: "#6c757d",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        cursos = cursos.filter((c) => c.id !== id)
        actualizarTablaCursos()
        Swal.fire({
          title: "Eliminado",
          text: "El curso ha sido eliminado exitosamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        })
      }
    })
  }
}

// Función para formatear fecha
function formatearFecha(fecha) {
  const opciones = { year: "numeric", month: "short", day: "numeric" }
  return new Date(fecha).toLocaleDateString("es-ES", opciones)
}

// Función para generar botones de acción para cursos
function generarBotonesAccionCurso(id) {
  return `
    <div class="btn-group btn-group-sm" role="group">
      <button type="button" class="btn btn-outline-primary" onclick="verCurso(${id})" title="Ver detalles">
        <i class="bi bi-eye"></i>
      </button>
      <button type="button" class="btn btn-outline-secondary" onclick="editarCurso(${id})" title="Editar">
        <i class="bi bi-pencil"></i>
      </button>
      <button type="button" class="btn btn-outline-danger" onclick="eliminarCurso(${id})" title="Eliminar">
        <i class="bi bi-trash"></i>
      </button>
    </div>
  `
}

// Función para validar el formulario de curso
function validarFormularioCurso() {
  let esValido = true
  const campos = [
    "nombreCurso",
    "capacidadMaxima",
    "instructor",
    "cicloAcademicoForm",
    "modalidadForm",
    "fechaInicio",
    "precio",
    "fechaFin",
    "descripcionCurso",
  ]

  campos.forEach((campo) => {
    const elemento = $("#" + campo)
    if (!elemento.val() || elemento.val().trim() === "") {
      elemento.addClass("is-invalid")
      esValido = false
    } else {
      elemento.removeClass("is-invalid")
    }
  })

  // Validar que la fecha de fin sea posterior a la fecha de inicio
  const fechaInicio = new Date($("#fechaInicio").val())
  const fechaFin = new Date($("#fechaFin").val())

  if (fechaFin <= fechaInicio) {
    $("#fechaFin").addClass("is-invalid")
    mostrarNotificacion("La fecha de fin debe ser posterior a la fecha de inicio", "danger")
    esValido = false
  }

  if (!esValido) {
    mostrarNotificacion("Por favor, complete todos los campos requeridos", "danger")
  }

  return esValido
}

// Función para mostrar notificaciones
function mostrarNotificacion(mensaje, tipo = "info") {
  Swal.fire({
    title: tipo === "success" ? "¡Éxito!" : tipo === "danger" ? "Error" : "Información",
    text: mensaje,
    icon: tipo === "success" ? "success" : tipo === "danger" ? "error" : "info",
    timer: 3000,
    showConfirmButton: false,
    toast: true,
    position: "top-end",
  })
}

// Función para cerrar sesión
function cerrarSesion() {
  Swal.fire({
    title: "¿Cerrar Sesión?",
    text: "¿Estás seguro de que deseas cerrar sesión?",
    icon: "question",
    showCancelButton: true,
    confirmButtonColor: "#4f46e5",
    cancelButtonColor: "#6c757d",
    confirmButtonText: "Sí, cerrar sesión",
    cancelButtonText: "Cancelar",
  }).then((result) => {
    if (result.isConfirmed) {
      localStorage.clear()
      sessionStorage.clear()
      Swal.fire({
        title: "Sesión Cerrada",
        text: "Has cerrado sesión exitosamente",
        icon: "success",
        timer: 2000,
        showConfirmButton: false,
      })
      // window.location.href = 'login.html';
    }
  })
}

// Event listeners cuando se carga la página
$(document).ready(() => {
  // Configuración de idioma para DataTables
  const idiomaDataTables = {
    decimal: "",
    emptyTable: "No hay información disponible",
    info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
    infoEmpty: "Mostrando 0 a 0 de 0 registros",
    infoFiltered: "(filtrado de _MAX_ registros totales)",
    infoPostFix: "",
    thousands: ",",
    lengthMenu: "Mostrar _MENU_ registros",
    loadingRecords: "Cargando...",
    processing: "Procesando...",
    search: "Buscar:",
    zeroRecords: "No se encontraron registros coincidentes",
    paginate: {
      first: "Primero",
      last: "Último",
      next: "Siguiente",
      previous: "Anterior",
    },
    aria: {
      sortAscending: ": activar para ordenar la columna de manera ascendente",
      sortDescending: ": activar para ordenar la columna de manera descendente",
    },
  }

  // Inicializar DataTable para cursos
  tablaCursos = $("#tablaCursos").DataTable({
    data: cursos.map((curso) => [
      curso.id,
      curso.nombre,
      curso.instructor,
      curso.modalidad.toUpperCase(),
      `S/ ${curso.precio.toFixed(2)}`,
      curso.estudiantes,
      curso.ciclo,
      formatearFecha(curso.fechaInicio),
      `<span class="badge bg-${curso.estado === "activo" ? "success" : curso.estado === "programado" ? "primary" : "secondary"}">${curso.estado.toUpperCase()}</span>`,
      generarBotonesAccionCurso(curso.id),
    ]),
    columns: [
      { title: "ID", width: "5%", searchable: true },
      { title: "Nombre del Curso", width: "20%", searchable: true },
      { title: "Instructor", width: "15%", searchable: true },
      { title: "Modalidad", width: "10%", searchable: true },
      { title: "Precio", width: "10%", searchable: false },
      { title: "Estudiantes", width: "8%", searchable: false },
      { title: "Ciclo", width: "8%", searchable: true },
      { title: "Fecha Inicio", width: "10%", searchable: false },
      { title: "Estado", width: "8%", searchable: true },
      { title: "Acciones", width: "6%", orderable: false, searchable: false },
    ],
    responsive: true,
    language: idiomaDataTables,
    pageLength: 10,
    order: [[0, "asc"]],
    lengthChange: false,
    dom: "rtip",
    searching: true,
  })

  // Event listeners para filtros personalizados de cursos
  $("#filtroCiclo, #filtroModalidad").on("change", aplicarFiltrosCursos)
  $("#buscarCurso").on("keyup", aplicarFiltrosCursos)
  $("#limpiarFiltros").on("click", limpiarFiltrosCursos)

  // Event listener para limpiar formulario
  $("#limpiarFormulario").on("click", limpiarFormulario)

  // Manejar el envío del formulario de nuevo curso
  $("#guardarCurso").on("click", () => {
    if (validarFormularioCurso()) {
      const nuevoCurso = {
        id: cursos.length + 1,
        nombre: $("#nombreCurso").val(),
        instructor: $("#instructor").val(),
        modalidad: $("#modalidadForm").val(),
        precio: Number.parseFloat($("#precio").val()),
        estudiantes: Number.parseInt($("#capacidadMaxima").val()),
        ciclo: $("#cicloAcademicoForm").val(),
        fechaInicio: $("#fechaInicio").val(),
        fechaFin: $("#fechaFin").val(),
        estado: "programado",
        descripcion: $("#descripcionCurso").val(),
      }

      cursos.push(nuevoCurso)
      actualizarTablaCursos()
      limpiarFormulario()
      mostrarNotificacion("Curso creado exitosamente", "success")
    }
  })
})
