package br.com.diego.catalogo_produtos.dto.produto;

import br.com.diego.catalogo_produtos.model.Produto;

public class ProdutoMapper {

  private ProdutoMapper() {}

  public static ProdutoResponse toResponse(Produto p) {
   
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
