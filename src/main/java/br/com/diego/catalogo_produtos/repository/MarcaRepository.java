package br.com.diego.catalogo_produtos.repository;

import br.com.diego.catalogo_produtos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {}
