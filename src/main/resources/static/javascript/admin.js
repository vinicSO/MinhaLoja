let adminAbas = ["adminHome","adminDashboard", "adminOrders", "adminProdutos", "adminCategorias", "adminCustomers"]

$(document).ready( function() {
    $('#adminCategorias').click(function() {
        getCategorias()
    })
})

function getCategorias() {
    let url = "/categorias/list"
    $.ajax({method: "GET", url})
        .done(function(response) {
            
            listarCategorias(response.result)
            alterarAbaAtiva('adminCategorias')
        })
        .fail(function() {
            alert("Erro na requisicao")
        })
        .always(function() {
            alert("Requisicao feita")
        })
}

function alterarAbaAtiva(id) {
    adminAbas.forEach(element => {
        document.getElementById(element).classList.remove('active')
    })
    document.getElementById(id).classList.add('active')
}

function listarCategorias(list) {
    $('#content').toggleClass(`album py-5 bg-light`)
    $('#content').append(`<div id='conteudoCategoria' class='container'></div>`)
    $('#conteudoCategoria').append(
        `<nav class='nav-options navbar navbar-expand-lg navbar-light bg-light'>
            <a class="m-nav-link" href="#">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                </svg>
            </a>
        </nav>`
    )
    $('#conteudoCategoria').append(`<ul id='listCategoria' class='list-group'></ul>`)

    let aux = 0;

    list.forEach(element => {
        aux = aux + 1
        $('#listCategoria').append(
            `<li class='list-group-item d-flex justify-content-between align-items-center'>
                <div>
                    <span>${element.nome}</span>
                    <span class="badge bg-primary rounded-pill">${element.quantidadeProdutosRelacionados}</span>
                </div>

                <span>
                    <button class="badge bg-warning" type="button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen-fill" viewBox="0 0 16 16">
                            <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
                        </svg>
                    </button>
                    <button class="badge bg-danger" type="button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                            <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                        </svg>
                    </button>
                </span>
            </li>`
        )
    });
}