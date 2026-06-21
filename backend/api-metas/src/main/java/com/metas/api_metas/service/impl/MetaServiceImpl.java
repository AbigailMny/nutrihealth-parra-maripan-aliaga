package com.metas.api_metas.service.impl;

import com.metas.api_metas.dto.MetaRequest;
import com.metas.api_metas.dto.MetaResponse;
import com.metas.api_metas.dto.ProgresoMetaResponse;
import com.metas.api_metas.exception.ResourceNotFoundException;
import com.metas.api_metas.model.Meta;
import com.metas.api_metas.repository.MetaRepository;
import com.metas.api_metas.service.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetaServiceImpl implements MetaService {

    private final MetaRepository metaRepository;

    @Override
    @Transactional
    public MetaResponse createMeta(MetaRequest request) {
        Meta meta = Meta.builder()
                .pacienteId(request.getPacienteId())
                .nombreMeta(request.getNombreMeta())
                .valorObjetivo(request.getValorObjetivo())
                .unidadMedida(request.getUnidadMedida())
                .build();
        
        Meta savedMeta = metaRepository.save(meta);
        return mapToResponse(savedMeta);
    }

    @Override
    @Transactional(readOnly = true)
    public MetaResponse getMetaById(Long id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meta", "id", id));
        return mapToResponse(meta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetaResponse> getMetasByPacienteId(Long pacienteId) {
        return metaRepository.findByPacienteId(pacienteId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetaResponse> getAllMetas() {
        return metaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MetaResponse updateMeta(Long id, MetaRequest request) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meta", "id", id));

        meta.setNombreMeta(request.getNombreMeta());
        meta.setValorObjetivo(request.getValorObjetivo());
        meta.setUnidadMedida(request.getUnidadMedida());
        
        Meta updatedMeta = metaRepository.save(meta);
        return mapToResponse(updatedMeta);
    }

    @Override
    @Transactional
    public void deleteMeta(Long id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meta", "id", id));
        metaRepository.delete(meta);
    }

    private MetaResponse mapToResponse(Meta meta) {
        List<ProgresoMetaResponse> progresos = meta.getProgresos() != null 
            ? meta.getProgresos().stream()
                .map(p -> ProgresoMetaResponse.builder()
                        .id(p.getId())
                        .metaId(p.getMeta().getId())
                        .fechaRegistro(p.getFechaRegistro())
                        .valorAlcanzado(p.getValorAlcanzado())
                        .build())
                .collect(Collectors.toList())
            : Collections.emptyList();

        return MetaResponse.builder()
                .id(meta.getId())
                .pacienteId(meta.getPacienteId())
                .nombreMeta(meta.getNombreMeta())
                .valorObjetivo(meta.getValorObjetivo())
                .unidadMedida(meta.getUnidadMedida())
                .progresos(progresos)
                .build();
    }
}
