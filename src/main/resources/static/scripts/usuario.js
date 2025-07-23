const $ = window.$;

$(document).ready(function() {
    const tablaUsuarios = $('#tablaUsuarios').DataTable({
        responsive: true,
        language: {
            url: "//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json",
        },
        pageLength: 10,
        order: [[0, "asc"]],
        dom: 'lrtip'
    });

    $('#searchInputUsuarios').on('keyup', function() {
        tablaUsuarios.column(0).search(this.value).draw();
    });
});