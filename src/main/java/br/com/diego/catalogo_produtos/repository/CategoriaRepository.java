package br.com.diego.catalogo_produtos.repository;

import br.com.diego.catalogo_produtos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}


