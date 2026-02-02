package br.com.diego.catalogo_produtos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 200)
  private String nome;

  @Lob
  private String descricao;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal preco;

  @Column(nullable = false)
  private Integer estoque;

  @Column(nullable = false)
  private Long views = 0L;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "marca_id")
  private Marca marca;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  public Long getId() { return id; }

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public String getDescricao() { return descricao; }
  public void setDescricao(String descricao) { this.descricao = descricao; }

  public BigDecimal getPreco() { return preco; }
  public void setPreco(BigDecimal preco) { this.preco = preco; }

  public Integer getEstoque() { return estoque; }
  public void setEstoque(Integer estoque) { this.estoque = estoque; }

  public Long getViews() { return views; }
  public void setViews(Long views) { this.views = views; }

  public Marca getMarca() { return marca; }
  public void setMarca(Marca marca) { this.marca = marca; }

  public Categoria getCategoria() { return categoria; }
  public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
