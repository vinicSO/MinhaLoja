package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Categoria;
import br.com.minhaloja.domain.Pedido;
import br.com.minhaloja.dto.CategoriaDTO;
import br.com.minhaloja.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> find(@PathVariable Integer id) {
        AjaxResponseBody<Pedido> result = new AjaxResponseBody();
        Pedido obj = pedidoService.find(id);
        result.setObj(obj);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AjaxResponseBody> insert(@Valid @RequestBody Pedido obj) {
        AjaxResponseBody<Pedido> result = new AjaxResponseBody<Pedido>();
        obj = pedidoService.insert(obj);
        result = find(obj.getId()).getBody();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        AjaxResponseBody<Page<Pedido>> result = new AjaxResponseBody<>();
        Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
        result.setObj(list);
        return ResponseEntity.ok().body(result);
    }
}
