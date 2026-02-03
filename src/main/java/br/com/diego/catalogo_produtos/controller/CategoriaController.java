package br.com.diego.catalogo_produtos.controller;

import br.com.diego.catalogo_produtos.dto.categoria.CategoriaRequest;
import br.com.diego.catalogo_produtos.dto.categoria.CategoriaResponse;
import br.com.diego.catalogo_produtos.model.Categoria;
import br.com.diego.catalogo_produtos.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

  private final CategoriaRepository categoriaRepository;

  public CategoriaController(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @GetMapping
  public List<CategoriaResponse> listar() {
    return categoriaRepository.findAll().stream().map(c -> {
      CategoriaResponse r = new CategoriaResponse();
      r.setId(c.getId());
      r.setNome(c.getNome());
      return r;
    }).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoriaResponse criar(@RequestBody @Valid CategoriaRequest request) {
    Categoria c = new Categoria();
    c.setNome(request.getNome());

    Categoria salvo = categoriaRepository.save(c);

    CategoriaResponse r = new CategoriaResponse();
    r.setId(salvo.getId());
    r.setNome(salvo.getNome());
    return r;
  }
}
