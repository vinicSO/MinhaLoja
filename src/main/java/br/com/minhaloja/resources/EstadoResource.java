package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Cidade;
import br.com.minhaloja.domain.Estado;
import br.com.minhaloja.dto.CidadeDTO;
import br.com.minhaloja.dto.EstadoDTO;
import br.com.minhaloja.services.CidadeService;
import br.com.minhaloja.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findAll() {
        AjaxResponseBody<EstadoDTO> result = new AjaxResponseBody<EstadoDTO>();
        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        result.setResult(listDto);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/{estadoId}/cidades")
    public ResponseEntity<AjaxResponseBody> findCidades(@PathVariable Integer estadoId) {
        AjaxResponseBody<CidadeDTO> result = new AjaxResponseBody<CidadeDTO>();
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        result.setResult(listDto);
        return ResponseEntity.ok().body(result);
    }
}
