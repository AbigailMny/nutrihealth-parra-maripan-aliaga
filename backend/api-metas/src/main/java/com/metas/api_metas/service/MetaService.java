package com.metas.api_metas.service;

import com.metas.api_metas.dto.MetaRequest;
import com.metas.api_metas.dto.MetaResponse;

import java.util.List;

public interface MetaService {
    MetaResponse createMeta(MetaRequest request);
    MetaResponse getMetaById(Long id);
    List<MetaResponse> getMetasByPacienteId(Long pacienteId);
    List<MetaResponse> getAllMetas();
    MetaResponse updateMeta(Long id, MetaRequest request);
    void deleteMeta(Long id);
}
