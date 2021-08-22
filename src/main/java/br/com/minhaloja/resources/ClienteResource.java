package br.com.minhaloja.resources;

import br.com.minhaloja.domain.AjaxResponseBody;
import br.com.minhaloja.domain.Cliente;
import br.com.minhaloja.dto.CategoriaDTO;
import br.com.minhaloja.dto.ClienteDTO;
import br.com.minhaloja.dto.ClienteNewDTO;
import br.com.minhaloja.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> find(@PathVariable Integer id) {
        AjaxResponseBody<Cliente> result = new AjaxResponseBody();
        Cliente obj = clienteService.find(id);
        result.setObj(obj);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findAll() {
        AjaxResponseBody<ClienteDTO> result = new AjaxResponseBody<ClienteDTO>();
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        result.setResult(listDto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        AjaxResponseBody<Page<ClienteDTO>> result = new AjaxResponseBody<>();
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
        result.setObj(listDto);
        return ResponseEntity.ok().body(result);
    }
}
