package br.com.diego.catalogo_produtos.dto.marca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MarcaRequest {
  @NotBlank
  @Size(max = 120)
  private String nome;

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
}
