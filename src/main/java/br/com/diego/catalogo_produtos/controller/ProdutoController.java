package br.com.diego.catalogo_produtos.controller;

import br.com.diego.catalogo_produtos.dto.produto.ProdutoCreateRequest;
import br.com.diego.catalogo_produtos.dto.produto.ProdutoResponse;
import br.com.diego.catalogo_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

  private final ProdutoService produtoService;

  public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  // LISTAR + BUSCAR
  @GetMapping
  public List<ProdutoResponse> listar(
      @RequestParam(required = false) String q,
      @RequestParam(required = false) Long categoriaId,
      @RequestParam(required = false) Long marcaId,
      @RequestParam(required = false) String order,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    return produtoService.listarBuscar(q, categoriaId, marcaId, order, page, size);
  }

  // DETALHE + CONTADOR DE VISUALIZAÇÕES
  @GetMapping("/{id}")
  public ProdutoResponse detalhe(@PathVariable Long id) {
    return produtoService.detalheIncrementandoViews(id);
  }

  // CADASTRO
  @PostMapping
  public ProdutoResponse criar(@RequestBody @Valid ProdutoCreateRequest request) {
    return produtoService.criar(request);
  }
}
