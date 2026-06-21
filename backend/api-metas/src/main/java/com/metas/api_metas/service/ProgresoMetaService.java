package com.metas.api_metas.service;

import com.metas.api_metas.dto.ProgresoMetaRequest;
import com.metas.api_metas.dto.ProgresoMetaResponse;

import java.util.List;

public interface ProgresoMetaService {
    ProgresoMetaResponse addProgreso(ProgresoMetaRequest request);
    List<ProgresoMetaResponse> getProgresosByMetaId(Long metaId);
    void deleteProgreso(Long id);
}
