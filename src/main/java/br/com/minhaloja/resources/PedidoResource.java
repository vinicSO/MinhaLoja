package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Pedido;
import br.com.minhaloja.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> find(@PathVariable Integer id) {
        AjaxResponseBody result = new AjaxResponseBody();
        Pedido obj = pedidoService.find(id);
        result.setObj(obj);
        return ResponseEntity.ok().body(result);
    }
}
