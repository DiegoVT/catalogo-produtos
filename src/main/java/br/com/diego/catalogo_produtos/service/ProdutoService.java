package br.com.diego.catalogo_produtos.service;

import br.com.diego.catalogo_produtos.dto.produto.ProdutoCreateRequest;
import br.com.diego.catalogo_produtos.dto.produto.ProdutoResponse;
import br.com.diego.catalogo_produtos.exception.NotFoundException;
import br.com.diego.catalogo_produtos.model.Categoria;
import br.com.diego.catalogo_produtos.model.Marca;
import br.com.diego.catalogo_produtos.model.Produto;
import br.com.diego.catalogo_produtos.repository.CategoriaRepository;
import br.com.diego.catalogo_produtos.repository.MarcaRepository;
import br.com.diego.catalogo_produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

  private final ProdutoRepository produtoRepository;
  private final MarcaRepository marcaRepository;
  private final CategoriaRepository categoriaRepository;

  public ProdutoService(
      ProdutoRepository produtoRepository,
      MarcaRepository marcaRepository,
      CategoriaRepository categoriaRepository
  ) {
    this.produtoRepository = produtoRepository;
    this.marcaRepository = marcaRepository;
    this.categoriaRepository = categoriaRepository;
  }

  // CREATE
  @Transactional
  public ProdutoResponse criar(ProdutoCreateRequest req) {
    Marca marca = marcaRepository.findById(req.getMarcaId())
        .orElseThrow(() -> new NotFoundException("Marca não encontrada"));

    Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
        .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

    Produto produto = new Produto();
    produto.setNome(req.getNome());
    produto.setDescricao(req.getDescricao());
    produto.setPreco(req.getPreco());
    produto.setEstoque(req.getEstoque());
    produto.setMarca(marca);
    produto.setCategoria(categoria);

    Produto salvo = produtoRepository.save(produto);
    return toResponse(salvo);
  }

  // UPDATE (PUT)
  @Transactional
  public ProdutoResponse atualizar(Long id, ProdutoCreateRequest req) {
    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

    Marca marca = marcaRepository.findById(req.getMarcaId())
        .orElseThrow(() -> new NotFoundException("Marca não encontrada"));

    Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
        .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

    produto.setNome(req.getNome());
    produto.setDescricao(req.getDescricao());
    produto.setPreco(req.getPreco());
    produto.setEstoque(req.getEstoque());
    produto.setMarca(marca);
    produto.setCategoria(categoria);

    Produto salvo = produtoRepository.save(produto);
    return toResponse(salvo);
  }

  // DELETE
  @Transactional
  public void deletar(Long id) {
    if (!produtoRepository.existsById(id)) {
      throw new NotFoundException("Produto não encontrado");
    }
    produtoRepository.deleteById(id);
  }

  // DETALHE + VIEWS
  @Transactional
  public ProdutoResponse detalheIncrementandoViews(Long id) {
    // 1) incrementa no banco
    produtoRepository.incrementViews(id);

    // 2) busca atualizado (garante retorno com valor correto)
    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

    return toResponse(produto);
  }

  // LISTAR + BUSCAR
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
    r.setId(p.getId());
    r.setNome(p.getNome());
    r.setDescricao(p.getDescricao());
    r.setPreco(p.getPreco());
    r.setEstoque(p.getEstoque());
    r.setViews(p.getViews());

    r.setMarcaId(p.getMarca().getId());
    r.setMarcaNome(p.getMarca().getNome());

    r.setCategoriaId(p.getCategoria().getId());
    r.setCategoriaNome(p.getCategoria().getNome());

    return r;
  }
}
