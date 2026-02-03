package br.com.diego.catalogo_produtos.controller;

import br.com.diego.catalogo_produtos.dto.marca.MarcaRequest;
import br.com.diego.catalogo_produtos.dto.marca.MarcaResponse;
import br.com.diego.catalogo_produtos.model.Marca;
import br.com.diego.catalogo_produtos.repository.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "*")
public class MarcaController {

  private final MarcaRepository marcaRepository;

  public MarcaController(MarcaRepository marcaRepository) {
    this.marcaRepository = marcaRepository;
  }

  @GetMapping
  public List<MarcaResponse> listar() {
    return marcaRepository.findAll().stream().map(this::toResponse).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MarcaResponse criar(@RequestBody @Valid MarcaRequest req) {
    Marca m = new Marca();
    m.setNome(req.getNome());
    Marca salvo = marcaRepository.save(m);
    return toResponse(salvo);
  }

  private MarcaResponse toResponse(Marca m) {
    MarcaResponse r = new MarcaResponse();
    r.setId(m.getId());
    r.setNome(m.getNome());
    return r;
  }
}
