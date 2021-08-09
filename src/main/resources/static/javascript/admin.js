let adminAbas = ["adminHome","adminDashboard", "adminOrders", "adminProdutos", "adminCategorias", "adminCustomers"]

let abaAtual = adminAbas[0]

let ConteudoMain = "#content"

$(document).ready( function() {
    $(`#adminCategorias`).click(function() {
        getCategorias()
    })
    $(`#adminProdutos`).click(function () {
        getProdutos()
    })
    $(document).on(`click`, `.buttonDelete`, function (event) {
        let button = event.currentTarget

        let obj_id = button.getAttribute(`objId`)
        let obj_nome = button.getAttribute(`objNome`)
        let obj_tipo = button.getAttribute(`objTipo`)
        let obj_objetos_relacionados = button.getAttribute(`objProdutosRelacionados`)

        if (obj_objetos_relacionados == 0) {
            popupDelete(obj_id, obj_nome, obj_tipo, obj_objetos_relacionados)
        } else {
            if($(`#alert-danger`).hasClass(`fade`)) {
                $(`#alert-danger`).prepend(`Possui Dependências`)
                $(`#alert-danger`).removeClass(`fade`)
                setTimeout(() => {$(`#alert-danger`).toggleClass(`fade`); $(`#alert-danger`).empty();}, 5000)
            }
        }
    })
    $(document).on(`click`, `.buttonUpdate`, function (event) {
        let button = event.currentTarget

        let obj_id = button.getAttribute(`objId`)
        let obj_nome = button.getAttribute(`objNome`)
        let obj_tipo = button.getAttribute(`objTipo`)

        let obj_content = document.getElementById(`contentLineCategoria-${obj_id}`)
        let obj_input = document.getElementById(`inputLineCategoria-${obj_id}`)

        obj_content.hidden = true
        obj_input.hidden = false
        obj_input.focus()

        $(obj_input).focusout(function () {
            if($(obj_input).val() == "") {
                obj_content.hidden = false
                obj_input.hidden = true
            } else {
                updateCategoria(obj_id, $(obj_input).val(), obj_input, obj_content)
            }
        })
    })
    $(`#buttonConfirmDelete`).click(function () {
        let id = document.getElementById(`buttonConfirmDelete`).getAttribute(`objId`)
        deleteCategoria(id)
    })
})

function popupDelete(obj_id, obj_nome, obj_tipo, obj_objetos_relacionados) {
    document.getElementById(`buttonConfirmDelete`).setAttribute(`objId`, obj_id)
    $(`#popupDelete`).modal(`show`)
}

function limparContent() {
    $(ConteudoMain).empty()
    //$(ConteudoMain).removeClass()
}

function getCategorias() {
    let url = "/categorias/list"
    $.ajax({method: "GET", url})
        .done(function(response) {
            limparContent()
            listarCategorias(response.result)
            alterarAbaAtiva(adminAbas[4])
        })
        .fail(function() {
            alert("Erro na requisicao")
        })
        .always(function() {
        })
}

function getProdutos() {
    let url = "/produtos/list"
    $.ajax({method: "GET", url})
        .done(function (response) {
            limparContent()
            listarProdutos(response.result)
            alterarAbaAtiva(adminAbas[3])
        })
        .fail(function () {
            alert("Erro na requisicao")
        })
        .always(function () {
        })
}

function deleteCategoria(id) {
    let url = `/categorias/${id}`
    $.ajax({
        type: "DELETE",
        url: url
    })
        .done(function (response) {
            let categoria = document.getElementById(`lineCategoria-${id}`)
            if (categoria.parentNode) {
               categoria.parentNode.removeChild(categoria)
            }
            $(`#popupDelete`).modal(`hide`)
            $(`#alert-success`).prepend(`Categoria Excluída`)
            $(`#alert-success`).removeClass(`fade`)
            setTimeout(() => {$(`#alert-success`).toggleClass(`fade`); $(`#alert-success`).empty();}, 5000)
        })
        .fail(function (response) {
            alert("Ocorreu um erro")
        })
        .always(function (response) {
        })
}

function updateCategoria(id, newName, input_line, content_line) {
    let url = `/categorias/${id}`
    let categoria = {
        nome: newName
    }
    $.ajax({
        type: "PUT",
        contentType : "application/json",
        data: JSON.stringify(categoria),
        dataType: `json`,
        url: url
    })
        .done(function (response) {
            content_line.children[0].innerHTML = newName
            input_line.hidden = true
            content_line.hidden = false
        })
        .fail(function (response) {
            alert("Ocorreu um erro")
            input_line.hidden = true
            content_line.hidden = false
        })
        .always(function (response) {
        })
}

function alterarAbaAtiva(id) {
    adminAbas.forEach(element => {
        document.getElementById(element).classList.remove('active')
    })
    document.getElementById(id).classList.add('active')
    abaAtual = id
}

function listarCategorias(list) {
    //$(ConteudoMain).toggleClass(`album py-5 bg-light`)
    $(ConteudoMain).append(
        `<div id='conteudoCategoria' class='container'>
            <nav class='nav-options navbar navbar-expand-lg navbar-light bg-light'>
                <a class="m-nav-link" href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg>
                </a>
            </nav>
            <ul id='listCategoria' class='list-group'></ul>
        </div>`
    )

    list.forEach(element => {
        $('#listCategoria').append(
            `<li id='lineCategoria-${element.id}' class='list-group-item d-flex justify-content-between align-items-center'>
                <div id='contentLineCategoria-${element.id}'>
                    <span>${element.nome}</span>
                    <span class="badge bg-primary rounded-pill">${element.quantidadeProdutosRelacionados}</span>
                </div>
                <input id='inputLineCategoria-${element.id}' hidden>

                <span>
                    <button class="buttonUpdate badge bg-warning" type="button" objId="${element.id}" objNome="${element.nome}" objTipo="Categoria">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen-fill" viewBox="0 0 16 16">
                            <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
                        </svg>
                    </button>
                    <button class="buttonDelete badge bg-danger" type="button" objId="${element.id}" objNome="${element.nome}" objTipo="Categoria" objProdutosRelacionados="${element.quantidadeProdutosRelacionados}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                            <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                        </svg>
                    </button>
                </span>
            </li>`
        )
    });
}

function listarProdutos(list) {
    //$(ConteudoMain).toggleClass(`album py-5 bg-light`)
    $(ConteudoMain).append(
        `<div class="container">
            <div id='listProdutos' class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            </div>
        </div>`
    )

    list.forEach(element => {
        $('#listProdutos').append(
            ` <div class="col">
                <div class="card shadow-sm">
                    <figure>
                        <svg class="produto bd-placeholder-img card-img-top" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"></rect>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                        </svg>
                        <div class="card-body">
                            <p class="card-text">${element.nome}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </figure>
                </div>
            </div>`
        )
    })
}