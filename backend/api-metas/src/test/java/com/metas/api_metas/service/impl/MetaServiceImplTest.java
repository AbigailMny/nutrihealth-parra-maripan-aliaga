package com.metas.api_metas.service.impl;

import com.metas.api_metas.dto.MetaRequest;
import com.metas.api_metas.dto.MetaResponse;
import com.metas.api_metas.exception.ResourceNotFoundException;
import com.metas.api_metas.model.Meta;
import com.metas.api_metas.repository.MetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MetaServiceImplTest {

    @Mock
    private MetaRepository metaRepository;

    @InjectMocks
    private MetaServiceImpl metaService;

    private Meta meta;
    private MetaRequest metaRequest;

    @BeforeEach
    void setUp() {
        meta = Meta.builder()
                .id(1L)
                .pacienteId(100L)
                .nombreMeta("Perder Peso")
                .valorObjetivo(new BigDecimal("70.0"))
                .unidadMedida("kg")
                .build();

        metaRequest = new MetaRequest();
        metaRequest.setPacienteId(100L);
        metaRequest.setNombreMeta("Perder Peso");
        metaRequest.setValorObjetivo(new BigDecimal("70.0"));
        metaRequest.setUnidadMedida("kg");
    }

    @Test
    void createMeta_Success() {
        when(metaRepository.save(any(Meta.class))).thenReturn(meta);

        MetaResponse response = metaService.createMeta(metaRequest);

        assertNotNull(response);
        assertEquals(meta.getId(), response.getId());
        assertEquals(meta.getNombreMeta(), response.getNombreMeta());
        verify(metaRepository, times(1)).save(any(Meta.class));
    }

    @Test
    void getMetaById_Success() {
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));

        MetaResponse response = metaService.getMetaById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Perder Peso", response.getNombreMeta());
    }

    @Test
    void getMetaById_NotFound() {
        when(metaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> metaService.getMetaById(99L));
    }

    @Test
    void getMetasByPacienteId_Success() {
        when(metaRepository.findByPacienteId(100L)).thenReturn(List.of(meta));

        List<MetaResponse> responses = metaService.getMetasByPacienteId(100L);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Perder Peso", responses.get(0).getNombreMeta());
    }

    @Test
    void updateMeta_Success() {
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));
        when(metaRepository.save(any(Meta.class))).thenReturn(meta);

        metaRequest.setValorObjetivo(new BigDecimal("68.0"));
        MetaResponse response = metaService.updateMeta(1L, metaRequest);

        assertNotNull(response);
        verify(metaRepository, times(1)).save(meta);
    }

    @Test
    void updateMeta_NotFound() {
        when(metaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> metaService.updateMeta(99L, metaRequest));
        verify(metaRepository, never()).save(any());
    }

    @Test
    void deleteMeta_Success() {
        when(metaRepository.findById(1L)).thenReturn(Optional.of(meta));
        doNothing().when(metaRepository).delete(meta);

        assertDoesNotThrow(() -> metaService.deleteMeta(1L));
        verify(metaRepository, times(1)).delete(meta);
    }

    @Test
    void deleteMeta_NotFound() {
        when(metaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> metaService.deleteMeta(99L));
        verify(metaRepository, never()).delete(any());
    }
}
