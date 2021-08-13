package br.com.minhaloja.services;

import br.com.minhaloja.domain.Categoria;
import br.com.minhaloja.domain.Pedido;
import br.com.minhaloja.domain.Produto;
import br.com.minhaloja.repositories.CategoriaRepository;
import br.com.minhaloja.repositories.ProdutoRepository;
import br.com.minhaloja.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()
        ));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto update(Produto obj) {
        Produto newObj = find(obj.getId());
        updateData(newObj, obj);
        return produtoRepository.save(newObj);
    }

    public void updateData(Produto newObj, Produto obj) {
        newObj.setLinkImagemS3(obj.getLinkImagemS3());
        newObj.setPreco(obj.getPreco());
        newObj.setNome(obj.getNome());
        newObj.setCategorias(obj.getCategorias());
        newObj.setItens(obj.getItens());
    }
}
