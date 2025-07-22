// Eventos JavaScript - Vanilla JS

// Import SweetAlert2
const Swal = window.Swal

// Import jQuery
const $ = window.$

// Datos de ejemplo para eventos
let eventos = [
  {
    id: 1,
    nombre: "Conferencia de Inteligencia Artificial",
    tipo: "conferencia",
    descripcion: "Conferencia sobre las últimas tendencias en IA y Machine Learning",
    responsable: "Dr. García López",
    fecha: "2024-12-15",
    hora: "09:00",
    ubicacion: "Auditorio Principal",
    capacidad: 150,
    inscritos: 120,
    estado: "programado",
  },
  {
    id: 2,
    nombre: "Workshop de React y Next.js",
    tipo: "workshop",
    descripcion: "Taller práctico de desarrollo con React y Next.js",
    responsable: "Ing. María Rodríguez",
    fecha: "2024-12-18",
    hora: "14:00",
    ubicacion: "Laboratorio de Computación",
    capacidad: 30,
    inscritos: 28,
    estado: "programado",
  },
  {
    id: 3,
    nombre: "Feria de Proyectos Estudiantiles",
    tipo: "feria",
    descripcion: "Exposición de proyectos finales de estudiantes",
    responsable: "Prof. Ana Martínez",
    fecha: "2024-12-20",
    hora: "10:00",
    ubicacion: "Patio Central",
    capacidad: 200,
    inscritos: 85,
    estado: "programado",
  },
  {
    id: 4,
    nombre: "Seminario de DevOps",
    tipo: "seminario",
    descripcion: "Seminario sobre implementación de DevOps en empresas",
    responsable: "Lic. Carmen Vega",
    fecha: "2024-11-25",
    hora: "16:00",
    ubicacion: "Sala de Conferencias",
    capacidad: 80,
    inscritos: 75,
    estado: "finalizado",
  },
  {
    id: 5,
    nombre: "Hackathon de Desarrollo Web",
    tipo: "workshop",
    descripcion: "Competencia de desarrollo web de 48 horas",
    responsable: "Dr. Luis Fernández",
    fecha: "2025-01-10",
    hora: "08:00",
    ubicacion: "Centro de Innovación",
    capacidad: 60,
    inscritos: 45,
    estado: "programado",
  },
]

// Variable para DataTable
let tablaEventos

// Función para limpiar formulario
function limpiarFormularioEvento() {
  document.getElementById("formNuevoEvento").reset()
  $(".is-invalid").removeClass("is-invalid")
}

// Función para aplicar filtros personalizados de eventos
function aplicarFiltrosEventos() {
  if (!tablaEventos) return

  const filtroTipo = $("#filtroTipo").val()
  const filtroEstado = $("#filtroEstado").val()
  const buscarEvento = $("#buscarEvento").val()

  // Aplicar búsqueda general
  tablaEventos.search(buscarEvento).draw()

  // Aplicar filtros por columna
  tablaEventos
    .column(2) // Columna de tipo
    .search(filtroTipo ? filtroTipo : "", true, false)
    .column(8) // Columna de estado
    .search(filtroEstado ? filtroEstado : "", true, false)
    .draw()
}

// Función para limpiar filtros de eventos
function limpiarFiltrosEventos() {
  $("#filtroTipo").val("")
  $("#filtroEstado").val("")
  $("#buscarEvento").val("")

  if (tablaEventos) {
    tablaEventos.search("").columns().search("").draw()
  }
}

// Función para actualizar tabla de eventos
function actualizarTablaEventos() {
  if (tablaEventos) {
    tablaEventos
      .clear()
      .rows.add(
        eventos.map((evento) => [
          evento.id,
          evento.nombre,
          evento.tipo.toUpperCase(),
          evento.responsable,
          formatearFecha(evento.fecha),
          evento.hora,
          evento.ubicacion,
          `${evento.inscritos}/${evento.capacidad}`,
          `<span class="badge bg-${evento.estado === "programado" ? "primary" : evento.estado === "finalizado" ? "secondary" : "warning"}">${evento.estado.toUpperCase()}</span>`,
          generarBotonesAccionEvento(evento.id),
        ]),
      )
      .draw()
  }
}

// Función para ver detalles del evento
function verEvento(id) {
  const evento = eventos.find((e) => e.id === id)
  if (evento) {
    Swal.fire({
      title: "Detalles del Evento",
      html: `
        <div class="text-start">
          <p><strong>Nombre:</strong> ${evento.nombre}</p>
          <p><strong>Tipo:</strong> ${evento.tipo.toUpperCase()}</p>
          <p><strong>Responsable:</strong> ${evento.responsable}</p>
          <p><strong>Fecha:</strong> ${formatearFecha(evento.fecha)}</p>
          <p><strong>Hora:</strong> ${evento.hora}</p>
          <p><strong>Ubicación:</strong> ${evento.ubicacion}</p>
          <p><strong>Capacidad:</strong> ${evento.inscritos}/${evento.capacidad} personas</p>
          <p><strong>Estado:</strong> <span class="badge bg-${evento.estado === "programado" ? "primary" : evento.estado === "finalizado" ? "secondary" : "warning"}">${evento.estado.toUpperCase()}</span></p>
          <p><strong>Descripción:</strong> ${evento.descripcion}</p>
        </div>
      `,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para editar evento
function editarEvento(id) {
  const evento = eventos.find((e) => e.id === id)
  if (evento) {
    Swal.fire({
      title: "Editar Evento",
      text: `Editar datos de: ${evento.nombre}`,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para eliminar evento
function eliminarEvento(id) {
  const evento = eventos.find((e) => e.id === id)
  if (evento) {
    Swal.fire({
      title: "¿Eliminar Evento?",
      text: `¿Estás seguro de que deseas eliminar el evento "${evento.nombre}"?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#dc3545",
      cancelButtonColor: "#6c757d",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        eventos = eventos.filter((e) => e.id !== id)
        actualizarTablaEventos()
        Swal.fire({
          title: "Eliminado",
          text: "El evento ha sido eliminado exitosamente",
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

// Función para generar botones de acción para eventos
function generarBotonesAccionEvento(id) {
  return `
    <div class="btn-group btn-group-sm" role="group">
      <button type="button" class="btn btn-outline-primary" onclick="verEvento(${id})" title="Ver detalles">
        <i class="bi bi-eye"></i>
      </button>
      <button type="button" class="btn btn-outline-secondary" onclick="editarEvento(${id})" title="Editar">
        <i class="bi bi-pencil"></i>
      </button>
      <button type="button" class="btn btn-outline-danger" onclick="eliminarEvento(${id})" title="Eliminar">
        <i class="bi bi-trash"></i>
      </button>
    </div>
  `
}

// Función para validar el formulario de evento
function validarFormularioEvento() {
  let esValido = true
  const campos = [
    "nombreEvento",
    "fechaEvento",
    "tipoEventoForm",
    "horaEvento",
    "responsableEvento",
    "capacidadEvento",
    "ubicacionEvento",
    "descripcionEvento",
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

  // Validar que la fecha no sea en el pasado
  const fechaEvento = new Date($("#fechaEvento").val())
  const hoy = new Date()
  hoy.setHours(0, 0, 0, 0)

  if (fechaEvento < hoy) {
    $("#fechaEvento").addClass("is-invalid")
    mostrarNotificacion("La fecha del evento no puede ser en el pasado", "danger")
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

  // Inicializar DataTable para eventos
  tablaEventos = $("#tablaEventos").DataTable({
    data: eventos.map((evento) => [
      evento.id,
      evento.nombre,
      evento.tipo.toUpperCase(),
      evento.responsable,
      formatearFecha(evento.fecha),
      evento.hora,
      evento.ubicacion,
      `${evento.inscritos}/${evento.capacidad}`,
      `<span class="badge bg-${evento.estado === "programado" ? "primary" : evento.estado === "finalizado" ? "secondary" : "warning"}">${evento.estado.toUpperCase()}</span>`,
      generarBotonesAccionEvento(evento.id),
    ]),
    columns: [
      { title: "ID", width: "5%", searchable: true },
      { title: "Nombre del Evento", width: "25%", searchable: true },
      { title: "Tipo", width: "10%", searchable: true },
      { title: "Responsable", width: "15%", searchable: true },
      { title: "Fecha", width: "10%", searchable: true },
      { title: "Hora", width: "8%", searchable: false },
      { title: "Ubicación", width: "12%", searchable: true },
      { title: "Inscritos", width: "8%", searchable: false },
      { title: "Estado", width: "7%", searchable: true },
      { title: "Acciones", width: "0%", orderable: false, searchable: false },
    ],
    responsive: true,
    language: idiomaDataTables,
    pageLength: 10,
    order: [[0, "asc"]],
    lengthChange: false,
    dom: "rtip",
    searching: true,
  })

  // Event listeners para filtros personalizados de eventos
  $("#filtroTipo, #filtroEstado").on("change", aplicarFiltrosEventos)
  $("#buscarEvento").on("keyup", aplicarFiltrosEventos)
  $("#limpiarFiltrosEventos").on("click", limpiarFiltrosEventos)

  // Event listener para limpiar formulario
  $("#limpiarFormularioEvento").on("click", limpiarFormularioEvento)

  // Manejar el envío del formulario de nuevo evento
  $("#guardarEvento").on("click", () => {
    if (validarFormularioEvento()) {
      const nuevoEvento = {
        id: eventos.length + 1,
        nombre: $("#nombreEvento").val(),
        tipo: $("#tipoEventoForm").val(),
        descripcion: $("#descripcionEvento").val(),
        responsable: $("#responsableEvento").val(),
        fecha: $("#fechaEvento").val(),
        hora: $("#horaEvento").val(),
        ubicacion: $("#ubicacionEvento").val(),
        capacidad: Number.parseInt($("#capacidadEvento").val()),
        inscritos: 0,
        estado: "programado",
      }

      eventos.push(nuevoEvento)
      actualizarTablaEventos()
      limpiarFormularioEvento()
      mostrarNotificacion("Evento creado exitosamente", "success")
    }
  })
})
