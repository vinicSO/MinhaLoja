package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Categoria;
import br.com.minhaloja.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> find(@PathVariable Integer id) {
        AjaxResponseBody<Categoria> result = new AjaxResponseBody<Categoria>();
        Categoria obj = categoriaService.find(id);
        result.setObj(obj);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value= "/list", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findAll() {
        AjaxResponseBody<Categoria> result = new AjaxResponseBody<Categoria>();
        List<Categoria> list = categoriaService.findAll();
        result.setResult(list);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AjaxResponseBody> update(@RequestBody Categoria obj, @PathVariable Integer id) {
        AjaxResponseBody<Categoria> result = new AjaxResponseBody<Categoria>();
        obj.setId(id);
        obj = categoriaService.update(obj);
        result.setObj(obj);
        return ResponseEntity.ok().body(result);
    }
}
