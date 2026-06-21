package com.metas.api_metas.service.impl;

import com.metas.api_metas.dto.ProgresoMetaRequest;
import com.metas.api_metas.dto.ProgresoMetaResponse;
import com.metas.api_metas.exception.ResourceNotFoundException;
import com.metas.api_metas.model.Meta;
import com.metas.api_metas.model.ProgresoMeta;
import com.metas.api_metas.repository.MetaRepository;
import com.metas.api_metas.repository.ProgresoMetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProgresoMetaServiceImplTest {

    @Mock
    private ProgresoMetaRepository progresoMetaRepository;

    @Mock
    private MetaRepository metaRepository;

    @InjectMocks
    private ProgresoMetaServiceImpl progresoMetaService;

    private Meta meta;
    private ProgresoMeta progresoMeta;
    private ProgresoMetaRequest progresoMetaRequest;

    @BeforeEach
    void setUp() {
        meta = Meta.builder()
                .id(1L)
                .pacienteId(100L)
                .nombreMeta("Perder Peso")
                .valorObjetivo(new BigDecimal("70.0"))
                .unidadMedida("kg")
                .build();

        progresoMeta = ProgresoMeta.builder()
                .id(1L)
                .meta(meta)
                .fechaRegistro(LocalDate.now())
                .valorAlcanzado(new BigDecimal("75.0"))
                .build();

        progresoMetaRequest = new ProgresoMetaRequest();
        progresoMetaRequest.setMetaId(1L);
        progresoMetaRequest.setFechaRegistro(LocalDate.now());
        progresoMetaRequest.setValorAlcanzado(new BigDecimal("75.0"));
    }

    @Test
    void addProgreso_Success() {
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));
        when(progresoMetaRepository.save(any(ProgresoMeta.class))).thenReturn(progresoMeta);

        ProgresoMetaResponse response = progresoMetaService.addProgreso(progresoMetaRequest);

        assertNotNull(response);
        assertEquals(progresoMeta.getId(), response.getId());
        assertEquals(progresoMeta.getValorAlcanzado(), response.getValorAlcanzado());
        verify(progresoMetaRepository, times(1)).save(any(ProgresoMeta.class));
    }

    @Test
    void addProgreso_MetaNotFound() {
        when(metaRepository.findById(99L)).thenReturn(Optional.empty());

        progresoMetaRequest.setMetaId(99L);
        assertThrows(ResourceNotFoundException.class, () -> progresoMetaService.addProgreso(progresoMetaRequest));
        verify(progresoMetaRepository, never()).save(any());
    }

    @Test
    void getProgresosByMetaId_Success() {
        when(metaRepository.existsById(1L)).thenReturn(true);
        when(progresoMetaRepository.findByMetaId(1L)).thenReturn(List.of(progresoMeta));

        List<ProgresoMetaResponse> responses = progresoMetaService.getProgresosByMetaId(1L);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(new BigDecimal("75.0"), responses.get(0).getValorAlcanzado());
    }

    @Test
    void getProgresosByMetaId_MetaNotFound() {
        when(metaRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> progresoMetaService.getProgresosByMetaId(99L));
        verify(progresoMetaRepository, never()).findByMetaId(anyLong());
    }

    @Test
    void deleteProgreso_Success() {
        when(progresoMetaRepository.findById(1L)).thenReturn(Optional.of(progresoMeta));
        doNothing().when(progresoMetaRepository).delete(progresoMeta);

        assertDoesNotThrow(() -> progresoMetaService.deleteProgreso(1L));
        verify(progresoMetaRepository, times(1)).delete(progresoMeta);
    }

    @Test
    void deleteProgreso_NotFound() {
        when(progresoMetaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> progresoMetaService.deleteProgreso(99L));
        verify(progresoMetaRepository, never()).delete(any());
    }
}
