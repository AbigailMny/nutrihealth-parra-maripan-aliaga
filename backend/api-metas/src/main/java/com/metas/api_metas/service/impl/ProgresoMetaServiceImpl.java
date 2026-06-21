package com.metas.api_metas.service.impl;

import com.metas.api_metas.dto.ProgresoMetaRequest;
import com.metas.api_metas.dto.ProgresoMetaResponse;
import com.metas.api_metas.exception.ResourceNotFoundException;
import com.metas.api_metas.model.Meta;
import com.metas.api_metas.model.ProgresoMeta;
import com.metas.api_metas.repository.MetaRepository;
import com.metas.api_metas.repository.ProgresoMetaRepository;
import com.metas.api_metas.service.ProgresoMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgresoMetaServiceImpl implements ProgresoMetaService {

    private final ProgresoMetaRepository progresoMetaRepository;
    private final MetaRepository metaRepository;

    @Override
    @Transactional
    public ProgresoMetaResponse addProgreso(ProgresoMetaRequest request) {
        Meta meta = metaRepository.findById(request.getMetaId())
                .orElseThrow(() -> new ResourceNotFoundException("Meta", "id", request.getMetaId()));

        ProgresoMeta progreso = ProgresoMeta.builder()
                .meta(meta)
                .fechaRegistro(request.getFechaRegistro())
                .valorAlcanzado(request.getValorAlcanzado())
                .build();

        ProgresoMeta savedProgreso = progresoMetaRepository.save(progreso);
        return mapToResponse(savedProgreso);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgresoMetaResponse> getProgresosByMetaId(Long metaId) {
        if (!metaRepository.existsById(metaId)) {
            throw new ResourceNotFoundException("Meta", "id", metaId);
        }
        
        return progresoMetaRepository.findByMetaId(metaId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProgreso(Long id) {
        ProgresoMeta progreso = progresoMetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgresoMeta", "id", id));
        progresoMetaRepository.delete(progreso);
    }

    private ProgresoMetaResponse mapToResponse(ProgresoMeta progreso) {
        return ProgresoMetaResponse.builder()
                .id(progreso.getId())
                .metaId(progreso.getMeta().getId())
                .fechaRegistro(progreso.getFechaRegistro())
                .valorAlcanzado(progreso.getValorAlcanzado())
                .build();
    }
}
