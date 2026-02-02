package br.com.diego.catalogo_produtos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 120)
  private String nome;

  public Long getId() { return id; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
}
