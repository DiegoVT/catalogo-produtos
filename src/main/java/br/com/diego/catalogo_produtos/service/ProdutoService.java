package br.com.diego.catalogo_produtos.service;

import br.com.diego.catalogo_produtos.dto.produto.ProdutoCreateRequest;
import br.com.diego.catalogo_produtos.dto.produto.ProdutoResponse;
import br.com.diego.catalogo_produtos.model.Produto;
import br.com.diego.catalogo_produtos.model.Marca;
import br.com.diego.catalogo_produtos.model.Categoria;
import br.com.diego.catalogo_produtos.repository.ProdutoRepository;
import br.com.diego.catalogo_produtos.repository.MarcaRepository;
import br.com.diego.catalogo_produtos.repository.CategoriaRepository;
import br.com.diego.catalogo_produtos.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

  private final ProdutoRepository produtoRepository;
  private final MarcaRepository marcaRepository;
  private final CategoriaRepository categoriaRepository;
  private final EntityManager entityManager;

  public ProdutoService(
      ProdutoRepository produtoRepository,
      MarcaRepository marcaRepository,
      CategoriaRepository categoriaRepository,
      EntityManager entityManager
  ) {
    this.produtoRepository = produtoRepository;
    this.marcaRepository = marcaRepository;
    this.categoriaRepository = categoriaRepository;
    this.entityManager = entityManager;
  }

  @Transactional
  public ProdutoResponse criar(ProdutoCreateRequest req) {
    Marca marca = marcaRepository.findById(req.marcaId)
        .orElseThrow(() -> new NotFoundException("Marca não encontrada"));

    Categoria categoria = categoriaRepository.findById(req.categoriaId)
        .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

    Produto produto = new Produto();
    produto.setNome(req.nome);
    produto.setDescricao(req.descricao);
    produto.setPreco(req.preco);
    produto.setEstoque(req.estoque);
    produto.setMarca(marca);
    produto.setCategoria(categoria);

    Produto salvo = produtoRepository.save(produto);
    return toResponse(salvo);
  }

  @Transactional
  public ProdutoResponse detalheIncrementandoViews(Long id) {
    produtoRepository.incrementViews(id);
    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    return toResponse(produto);
  }

  @Transactional(readOnly = true)
  public List<ProdutoResponse> listarBuscar(
      String q,
      Long categoriaId,
      Long marcaId,
      String order,
      int page,
      int size
  ) {
    int offset = page * size;
    return produtoRepository.searchFulltext(
        q == null ? "" : q,
        categoriaId,
        marcaId,
        order == null ? "" : order,
        size,
        offset
    ).stream().map(this::toResponse).toList();
  }

  private ProdutoResponse toResponse(Produto p) {
    ProdutoResponse r = new ProdutoResponse();
    r.id = p.getId();
    r.nome = p.getNome();
    r.descricao = p.getDescricao();
    r.preco = p.getPreco();
    r.estoque = p.getEstoque();
    r.views = p.getViews();

    r.marcaId = p.getMarca().getId();
    r.marcaNome = p.getMarca().getNome();
    r.categoriaId = p.getCategoria().getId();
    r.categoriaNome = p.getCategoria().getNome();
    return r;
  }
}
