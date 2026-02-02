package br.com.diego.catalogo_produtos.dto.produto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoCreateRequest {

  @NotBlank
  @Size(max = 200)
  public String nome;

  public String descricao;

  @NotNull
  @DecimalMin("0.00")
  public BigDecimal preco;

  @NotNull
  @Min(0)
  public Integer estoque;

  @NotNull
  public Long marcaId;

  @NotNull
  public Long categoriaId;
}

