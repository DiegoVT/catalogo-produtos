package br.com.diego.catalogo_produtos.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;
}
