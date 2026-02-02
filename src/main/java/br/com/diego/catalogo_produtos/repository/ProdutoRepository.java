package br.com.diego.catalogo_produtos.repository;

import br.com.diego.catalogo_produtos.model.Produto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  @Query(value = """
    SELECT p.*,
           MATCH(p.nome, p.descricao) AGAINST (:q IN NATURAL LANGUAGE MODE) AS score
    FROM produto p
    WHERE (:q IS NULL OR :q = '' OR MATCH(p.nome, p.descricao) AGAINST (:q IN NATURAL LANGUAGE MODE))
      AND (:categoriaId IS NULL OR p.categoria_id = :categoriaId)
      AND (:marcaId IS NULL OR p.marca_id = :marcaId)
    ORDER BY
      CASE WHEN :order = 'preco_asc' THEN p.preco END ASC,
      CASE WHEN :order = 'preco_desc' THEN p.preco END DESC,
      score DESC,
      p.id DESC
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
  List<Produto> searchFulltext(
      @Param("q") String q,
      @Param("categoriaId") Long categoriaId,
      @Param("marcaId") Long marcaId,
      @Param("order") String order,
      @Param("limit") int limit,
      @Param("offset") int offset
  );

  @Modifying
  @Query(value = "UPDATE produto SET views = views + 1 WHERE id = :id", nativeQuery = true)
  int incrementViews(@Param("id") Long id);
}
