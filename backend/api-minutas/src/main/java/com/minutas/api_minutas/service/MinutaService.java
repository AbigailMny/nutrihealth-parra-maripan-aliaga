package com.minutas.api_minutas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minutas.api_minutas.client.ExternalServiceClient;
import com.minutas.api_minutas.dto.MinutaDto;
import com.minutas.api_minutas.model.Minuta;
import com.minutas.api_minutas.repository.MinutaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MinutaService {

    @Autowired
    private MinutaRepository minutaRepository;

    @Autowired
    private ExternalServiceClient externalServiceClient;

    public List<MinutaDto> mostrarMinutas() {
        return minutaRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MinutaDto buscarId(Long id) {
        Minuta minuta = minutaRepository.findById(id).orElseThrow();
        return mapToDto(minuta);
    }

    public MinutaDto crearMinuta(MinutaDto minutaDto) {
        Minuta minuta = mapToEntity(minutaDto);
        Minuta saved = minutaRepository.save(minuta);
        return mapToDto(saved);
    }

    public MinutaDto actualizarMinuta(Long id, MinutaDto minutaDto) {
        minutaDto.setId(id);
        return crearMinuta(minutaDto);
    }

    public void eliminarMinuta(Long id) {
        minutaRepository.deleteById(id);
    }

    private MinutaDto mapToDto(Minuta minuta) {
        MinutaDto dto = new MinutaDto();
        dto.setId(minuta.getId());
        dto.setPaciente_id(minuta.getPaciente_id());
        dto.setNutricionista_id(minuta.getNutricionista_id());
        dto.setFecha_inicio(minuta.getFecha_inicio());
        dto.setFecha_fin(minuta.getFecha_fin());
        dto.setUrl_archivo(minuta.getUrl_archivo());
        dto.setEstado(minuta.getEstado());
        dto.setPaciente(externalServiceClient.obtenerPaciente(minuta.getPaciente_id()));
        dto.setNutricionista(externalServiceClient.obtenerNutricionista(minuta.getNutricionista_id()));
        return dto;
    }

    private Minuta mapToEntity(MinutaDto dto) {
        Minuta minuta = new Minuta();
        if (dto.getId() != null) {
            minuta.setId(dto.getId());
        }
        minuta.setPaciente_id(dto.getPaciente_id());
        minuta.setNutricionista_id(dto.getNutricionista_id());
        minuta.setFecha_inicio(dto.getFecha_inicio());
        minuta.setFecha_fin(dto.getFecha_fin());
        minuta.setUrl_archivo(dto.getUrl_archivo());
        minuta.setEstado(dto.getEstado());
        return minuta;
    }
}

