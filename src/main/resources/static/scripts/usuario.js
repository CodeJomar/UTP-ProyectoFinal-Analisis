// Usuarios JavaScript - Vanilla JS

// Import SweetAlert2
const Swal = window.Swal

// Import jQuery
const $ = window.$

// Datos de ejemplo para alumnos
let alumnos = [
  {
    id: 1,
    nombre: "Juan Carlos Pérez García",
    email: "juan.perez@email.com",
    telefono: "+51 987 654 321",
    dni: "72345678",
    estado: "activo",
  },
  {
    id: 2,
    nombre: "María Elena Rodríguez López",
    email: "maria.rodriguez@email.com",
    telefono: "+51 987 654 322",
    dni: "73456789",
    estado: "activo",
  },
  {
    id: 3,
    nombre: "Carlos Alberto Mendoza Silva",
    email: "carlos.mendoza@email.com",
    telefono: "+51 987 654 323",
    dni: "74567890",
    estado: "inactivo",
  },
  {
    id: 4,
    nombre: "Ana Sofía Vargas Torres",
    email: "ana.vargas@email.com",
    telefono: "+51 987 654 324",
    dni: "75678901",
    estado: "activo",
  },
  {
    id: 5,
    nombre: "Diego Fernando Castillo Ruiz",
    email: "diego.castillo@email.com",
    telefono: "+51 987 654 325",
    dni: "76789012",
    estado: "activo",
  },
]

// Datos de ejemplo para docentes
let docentes = [
  {
    id: 1,
    nombre: "Dr. García López",
    email: "garcia.lopez@udemy.edu",
    especialidad: "Matemáticas Avanzadas",
    telefono: "+51 987 111 111",
    fechaIngreso: "2020-03-01",
    estado: "activo",
  },
  {
    id: 2,
    nombre: "Ing. María Rodríguez",
    email: "maria.rodriguez@udemy.edu",
    especialidad: "Desarrollo Web",
    telefono: "+51 987 111 112",
    fechaIngreso: "2021-08-15",
    estado: "activo",
  },
  {
    id: 3,
    nombre: "Prof. Ana Martínez",
    email: "ana.martinez@udemy.edu",
    especialidad: "Diseño Gráfico",
    telefono: "+51 987 111 113",
    fechaIngreso: "2019-01-10",
    estado: "activo",
  },
  {
    id: 4,
    nombre: "Dr. Luis Fernández",
    email: "luis.fernandez@udemy.edu",
    especialidad: "Inteligencia Artificial",
    telefono: "+51 987 111 114",
    fechaIngreso: "2022-06-01",
    estado: "activo",
  },
  {
    id: 5,
    nombre: "Lic. Carmen Vega",
    email: "carmen.vega@udemy.edu",
    especialidad: "Gestión de Proyectos",
    telefono: "+51 987 111 115",
    fechaIngreso: "2021-11-20",
    estado: "inactivo",
  },
]

// Variables para DataTables
let tablaAlumnos, tablaDocentes

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

// Funciones para navegación interna
function mostrarAlumnos() {
  // Ocultar contenido de docentes
  document.getElementById("contenidoDocentes").style.display = "none"
  // Mostrar contenido de alumnos
  document.getElementById("contenidoAlumnos").style.display = "block"

  // Actualizar botones activos
  document.getElementById("btnGestionarAlumnos").classList.add("active")
  document.getElementById("btnGestionarAlumnos").classList.remove("btn-outline-primary")
  document.getElementById("btnGestionarAlumnos").classList.add("btn-primary")

  document.getElementById("btnGestionarDocentes").classList.remove("active")
  document.getElementById("btnGestionarDocentes").classList.remove("btn-success")
  document.getElementById("btnGestionarDocentes").classList.add("btn-outline-success")
}

function mostrarDocentes() {
  // Ocultar contenido de alumnos
  document.getElementById("contenidoAlumnos").style.display = "none"
  // Mostrar contenido de docentes
  document.getElementById("contenidoDocentes").style.display = "block"

  // Actualizar botones activos
  document.getElementById("btnGestionarDocentes").classList.add("active")
  document.getElementById("btnGestionarDocentes").classList.remove("btn-outline-success")
  document.getElementById("btnGestionarDocentes").classList.add("btn-success")

  document.getElementById("btnGestionarAlumnos").classList.remove("active")
  document.getElementById("btnGestionarAlumnos").classList.remove("btn-primary")
  document.getElementById("btnGestionarAlumnos").classList.add("btn-outline-primary")
}

// Función para ver detalles del alumno
function verAlumno(id) {
  const alumno = alumnos.find((a) => a.id === id)
  if (alumno) {
    Swal.fire({
      title: "Detalles del Alumno",
      html: `
                <div class="text-start">
                    <p><strong>Nombre:</strong> ${alumno.nombre}</p>
                    <p><strong>Email:</strong> ${alumno.email}</p>
                    <p><strong>Teléfono:</strong> ${alumno.telefono}</p>
                    <p><strong>DNI:</strong> ${alumno.dni}</p>
                    <p><strong>Estado:</strong> <span class="badge bg-${alumno.estado === "activo" ? "success" : "warning"}">${alumno.estado.toUpperCase()}</span></p>
                </div>
            `,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para ver detalles del docente
function verDocente(id) {
  const docente = docentes.find((d) => d.id === id)
  if (docente) {
    Swal.fire({
      title: "Detalles del Docente",
      html: `
                <div class="text-start">
                    <p><strong>Nombre:</strong> ${docente.nombre}</p>
                    <p><strong>Email:</strong> ${docente.email}</p>
                    <p><strong>Especialidad:</strong> ${docente.especialidad}</p>
                    <p><strong>Teléfono:</strong> ${docente.telefono}</p>
                    <p><strong>Fecha de Ingreso:</strong> ${formatearFecha(docente.fechaIngreso)}</p>
                    <p><strong>Estado:</strong> <span class="badge bg-${docente.estado === "activo" ? "success" : "warning"}">${docente.estado.toUpperCase()}</span></p>
                </div>
            `,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para editar alumno
function editarAlumno(id) {
  const alumno = alumnos.find((a) => a.id === id)
  if (alumno) {
    Swal.fire({
      title: "Editar Alumno",
      text: `Editar datos de: ${alumno.nombre}`,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para editar docente
function editarDocente(id) {
  const docente = docentes.find((d) => d.id === id)
  if (docente) {
    Swal.fire({
      title: "Editar Docente",
      text: `Editar datos de: ${docente.nombre}`,
      icon: "info",
      confirmButtonColor: "#4f46e5",
    })
  }
}

// Función para eliminar alumno
function eliminarAlumno(id) {
  const alumno = alumnos.find((a) => a.id === id)
  if (alumno) {
    Swal.fire({
      title: "¿Eliminar Alumno?",
      text: `¿Estás seguro de que deseas eliminar a ${alumno.nombre}?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#dc3545",
      cancelButtonColor: "#6c757d",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        alumnos = alumnos.filter((a) => a.id !== id)
        tablaAlumnos
          .clear()
          .rows.add(
            alumnos.map((alumno) => [
              alumno.id,
              alumno.nombre,
              alumno.email,
              alumno.telefono,
              alumno.dni,
              `<span class="badge bg-${alumno.estado === "activo" ? "success" : "warning"}">${alumno.estado.toUpperCase()}</span>`,
              generarBotonesAccionAlumno(alumno.id),
            ]),
          )
          .draw()
        actualizarContadores()
        Swal.fire({
          title: "Eliminado",
          text: "El alumno ha sido eliminado exitosamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        })
      }
    })
  }
}

// Función para eliminar docente
function eliminarDocente(id) {
  const docente = docentes.find((d) => d.id === id)
  if (docente) {
    Swal.fire({
      title: "¿Eliminar Docente?",
      text: `¿Estás seguro de que deseas eliminar a ${docente.nombre}?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#dc3545",
      cancelButtonColor: "#6c757d",
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        docentes = docentes.filter((d) => d.id !== id)
        tablaDocentes
          .clear()
          .rows.add(
            docentes.map((docente) => [
              docente.id,
              docente.nombre,
              docente.email,
              docente.especialidad,
              docente.telefono,
              formatearFecha(docente.fechaIngreso),
              `<span class="badge bg-${docente.estado === "activo" ? "success" : "warning"}">${docente.estado.toUpperCase()}</span>`,
              generarBotonesAccionDocente(docente.id),
            ]),
          )
          .draw()
        actualizarContadores()
        Swal.fire({
          title: "Eliminado",
          text: "El docente ha sido eliminado exitosamente",
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
  const opciones = { year: "numeric", month: "long", day: "numeric" }
  return new Date(fecha).toLocaleDateString("es-ES", opciones)
}

// Función para generar botones de acción para alumnos
function generarBotonesAccionAlumno(id) {
  return `
        <div class="btn-group btn-group-sm" role="group">
            <button type="button" class="btn btn-outline-primary" onclick="verAlumno(${id})" title="Ver detalles">
                <i class="bi bi-eye"></i>
            </button>
            <button type="button" class="btn btn-outline-secondary" onclick="editarAlumno(${id})" title="Editar">
                <i class="bi bi-pencil"></i>
            </button>
            <button type="button" class="btn btn-outline-danger" onclick="eliminarAlumno(${id})" title="Eliminar">
                <i class="bi bi-trash"></i>
            </button>
        </div>
    `
}

// Función para generar botones de acción para docentes
function generarBotonesAccionDocente(id) {
  return `
        <div class="btn-group btn-group-sm" role="group">
            <button type="button" class="btn btn-outline-primary" onclick="verDocente(${id})" title="Ver detalles">
                <i class="bi bi-eye"></i>
            </button>
            <button type="button" class="btn btn-outline-secondary" onclick="editarDocente(${id})" title="Editar">
                <i class="bi bi-pencil"></i>
            </button>
            <button type="button" class="btn btn-outline-danger" onclick="eliminarDocente(${id})" title="Eliminar">
                <i class="bi bi-trash"></i>
            </button>
        </div>
    `
}

// Función para actualizar contadores
function actualizarContadores() {
  const totalAlumnos = alumnos.length
  const totalDocentes = docentes.length
  const alumnosActivos = alumnos.filter((a) => a.estado === "activo").length
  const docentesActivos = docentes.filter((d) => d.estado === "activo").length
  const usuariosActivos = alumnosActivos + docentesActivos

  // Actualizar contadores en tabs
  document.getElementById("contadorAlumnos").textContent = totalAlumnos
  document.getElementById("contadorDocentes").textContent = totalDocentes

  // Actualizar tarjetas de estadísticas principales
  document.getElementById("totalAlumnos").textContent = alumnosActivos
  document.getElementById("totalDocentes").textContent = docentesActivos

  // Actualizar nueva tarjeta de usuarios activos
  document.getElementById("usuariosActivos").textContent = usuariosActivos

  // Calcular nuevos registros del mes (simulado)
  const fechaActual = new Date()
  const inicioMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth(), 1)

  const nuevosAlumnos = alumnos.filter((a) => new Date(a.fechaRegistro) >= inicioMes).length
  const nuevosDocentes = docentes.filter((d) => new Date(d.fechaIngreso) >= inicioMes).length
  const nuevosRegistros = nuevosAlumnos + nuevosDocentes

  document.getElementById("nuevosRegistros").textContent = nuevosRegistros

  // Actualizar contadores en navegación interna
  document.getElementById("contadorAlumnosNav").textContent = totalAlumnos
  document.getElementById("contadorDocentesNav").textContent = totalDocentes
}

// Función para simular estadísticas dinámicas
function actualizarEstadisticasEnTiempoReal() {
  // Simular cambios en porcentajes cada 30 segundos
  setInterval(() => {
    const porcentajes = ["+12%", "+15%", "+18%", "+22%", "+8%", "+25%"]
    const porcentajeAleatorio = porcentajes[Math.floor(Math.random() * porcentajes.length)]

    const elementoPorcentaje = document.getElementById("porcentajeUsuarios")
    if (elementoPorcentaje) {
      elementoPorcentaje.textContent = `${porcentajeAleatorio} vs mes anterior`
    }
  }, 30000)
}

// Inicializar cuando se carga la página
document.addEventListener("DOMContentLoaded", () => {
  console.log("Página de usuarios cargada")

  // Inicializar DataTable para alumnos
  tablaAlumnos = $("#tablaAlumnos").DataTable({
    data: alumnos.map((alumno) => [
      alumno.id,
      alumno.nombre,
      alumno.email,
      alumno.telefono,
      alumno.dni,
      `<span class="badge bg-${alumno.estado === "activo" ? "success" : "warning"}">${alumno.estado.toUpperCase()}</span>`,
      generarBotonesAccionAlumno(alumno.id),
    ]),
    columns: [
      { title: "ID", width: "5%" },
      { title: "Nombre Completo", width: "25%" },
      { title: "Email", width: "20%" },
      { title: "Teléfono", width: "15%" },
      { title: "DNI", width: "15%" },
      { title: "Estado", width: "10%" },
      { title: "Acciones", width: "10%", orderable: false },
    ],
    responsive: true,
    language: {
      url: "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json",
    },
    pageLength: 10,
    order: [[0, "asc"]],
    lengthChange: false, // Ocultar el selector "Mostrar X registros"
    dom: "rtip", // Ocultar controles innecesarios, solo mostrar tabla, info y paginación
  })

  // Inicializar DataTable para docentes
  tablaDocentes = $("#tablaDocentes").DataTable({
    data: docentes.map((docente) => [
      docente.id,
      docente.nombre,
      docente.email,
      docente.especialidad,
      docente.telefono,
      formatearFecha(docente.fechaIngreso),
      `<span class="badge bg-${docente.estado === "activo" ? "success" : "warning"}">${docente.estado.toUpperCase()}</span>`,
      generarBotonesAccionDocente(docente.id),
    ]),
    columns: [
      { title: "ID", width: "5%" },
      { title: "Nombre Completo", width: "20%" },
      { title: "Email", width: "20%" },
      { title: "Especialidad", width: "15%" },
      { title: "Teléfono", width: "15%" },
      { title: "Fecha Ingreso", width: "10%" },
      { title: "Estado", width: "8%" },
      { title: "Acciones", width: "7%", orderable: false },
    ],
    responsive: true,
    language: {
      url: "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json",
    },
    pageLength: 10,
    order: [[0, "asc"]],
    lengthChange: false, // Ocultar el selector "Mostrar X registros"
    dom: "rtip", // Ocultar controles innecesarios, solo mostrar tabla, info y paginación
  })

  // Actualizar contadores
  actualizarContadores()

  // Configurar SweetAlert2 defaults
  Swal.mixin({
    customClass: {
      confirmButton: "btn btn-primary me-2",
      cancelButton: "btn btn-secondary",
    },
    buttonsStyling: false,
  })

  // Iniciar actualización de estadísticas en tiempo real
  actualizarEstadisticasEnTiempoReal()
})
