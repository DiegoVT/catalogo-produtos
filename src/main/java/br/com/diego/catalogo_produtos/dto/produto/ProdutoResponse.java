package br.com.diego.catalogo_produtos.dto.produto;

import java.math.BigDecimal;

public class ProdutoResponse {
  public Long id;
  public String nome;
  public String descricao;
  public BigDecimal preco;
  public Integer estoque;
  public Long views;

  public Long marcaId;
  public String marcaNome;

  public Long categoriaId;
  public String categoriaNome;
}