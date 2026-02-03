package br.com.diego.catalogo_produtos.dto.produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoCreateRequest {

  @NotBlank
  private String nome;

  private String descricao;

  @NotNull
  private BigDecimal preco;

  @NotNull
  @Min(0)
  private Integer estoque;

  @NotNull
  private Long marcaId;

  @NotNull
  private Long categoriaId;

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public String getDescricao() { return descricao; }
  public void setDescricao(String descricao) { this.descricao = descricao; }

  public BigDecimal getPreco() { return preco; }
  public void setPreco(BigDecimal preco) { this.preco = preco; }

  public Integer getEstoque() { return estoque; }
  public void setEstoque(Integer estoque) { this.estoque = estoque; }

  public Long getMarcaId() { return marcaId; }
  public void setMarcaId(Long marcaId) { this.marcaId = marcaId; }

  public Long getCategoriaId() { return categoriaId; }
  public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
