package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Categoria;
import br.com.minhaloja.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value= "/list", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findAll() {
        AjaxResponseBody result = new AjaxResponseBody();
        List<Categoria> list = categoriaService.findAll();
        result.setResult(list);
        return ResponseEntity.ok().body(result);
    }
}
