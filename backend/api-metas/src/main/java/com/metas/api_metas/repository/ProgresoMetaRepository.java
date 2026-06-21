package com.metas.api_metas.repository;

import com.metas.api_metas.model.ProgresoMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoMetaRepository extends JpaRepository<ProgresoMeta, Long> {
    List<ProgresoMeta> findByMetaId(Long metaId);
}
