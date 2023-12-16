package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.tenis.TenisDTO;
import br.unitins.topicos1.dto.tenis.TenisResponseDTO;
import jakarta.validation.Valid;

public interface TenisService {

    public TenisResponseDTO insert(@Valid TenisDTO dto);

    public TenisResponseDTO update(TenisDTO dto, Long id);

    public void delete(Long id);

    public TenisResponseDTO findById(Long id);

    public List<TenisResponseDTO> findByModelo(String modelo);

    public List<TenisResponseDTO> findByMarca(String marca);

    public List<TenisResponseDTO> findByAll(); 

    TenisResponseDTO updateImagem(Long id, String nomeImagem);
    
}
