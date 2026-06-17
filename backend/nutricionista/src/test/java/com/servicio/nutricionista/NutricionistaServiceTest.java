package com.servicio.nutricionista;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.servicio.nutricionista.dto.NutricionistaDTO;
import com.servicio.nutricionista.exception.ResourceNotFoundException;
import com.servicio.nutricionista.model.Nutricionista;
import com.servicio.nutricionista.repository.NutricionistaRepository;
import com.servicio.nutricionista.service.impl.NutricionistaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NutricionistaServiceTest {

    @Mock
    private NutricionistaRepository nutricionistaRepository;

    @InjectMocks
    private NutricionistaServiceImpl nutricionistaService;

    private Nutricionista nutricionista;
    private NutricionistaDTO nutricionistaDTO;

    @BeforeEach
    void setUp() {
        nutricionista = Nutricionista.builder()
                .id(1L)
                .nombres("Juan")
                .apellidos("Perez")
                .correo("juan@test.com")
                .telefono("123456789")
                .build();

        nutricionistaDTO = NutricionistaDTO.builder()
                .id(1L)
                .nombres("Juan")
                .apellidos("Perez")
                .correo("juan@test.com")
                .telefono("123456789")
                .build();
    }

    @Test
    @DisplayName("Debería retornar una lista de nutricionistas")
    void findAll_ReturnsListOfNutricionistaDTOs() {
        when(nutricionistaRepository.findAll()).thenReturn(Arrays.asList(nutricionista));

        List<NutricionistaDTO> result = nutricionistaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombres());
        verify(nutricionistaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería retornar un nutricionista cuando el ID existe")
    void findById_ExistingId_ReturnsNutricionistaDTO() {
        when(nutricionistaRepository.findById(1L)).thenReturn(Optional.of(nutricionista));

        NutricionistaDTO result = nutricionistaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getNombres());
        verify(nutricionistaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID no existe al buscar")
    void findById_NonExistingId_ThrowsResourceNotFoundException() {
        when(nutricionistaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> nutricionistaService.findById(99L));
        verify(nutricionistaRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Debería guardar un nutricionista y retornar el DTO guardado")
    void save_ValidDto_ReturnsSavedNutricionistaDTO() {
        when(nutricionistaRepository.save(any(Nutricionista.class))).thenReturn(nutricionista);

        NutricionistaDTO result = nutricionistaService.save(nutricionistaDTO);

        assertNotNull(result);
        assertEquals("Juan", result.getNombres());
        verify(nutricionistaRepository, times(1)).save(any(Nutricionista.class));
    }

    @Test
    @DisplayName("Debería actualizar un nutricionista cuando el ID existe")
    void update_ExistingIdAndValidDto_ReturnsUpdatedNutricionistaDTO() {
        when(nutricionistaRepository.findById(1L)).thenReturn(Optional.of(nutricionista));
        when(nutricionistaRepository.save(any(Nutricionista.class))).thenReturn(nutricionista);

        nutricionistaDTO.setNombres("Carlos");
        NutricionistaDTO result = nutricionistaService.update(1L, nutricionistaDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(nutricionistaRepository, times(1)).findById(1L);
        verify(nutricionistaRepository, times(1)).save(any(Nutricionista.class));
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID no existe al actualizar")
    void update_NonExistingId_ThrowsResourceNotFoundException() {
        when(nutricionistaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> nutricionistaService.update(99L, nutricionistaDTO));
        verify(nutricionistaRepository, times(1)).findById(99L);
        verify(nutricionistaRepository, never()).save(any(Nutricionista.class));
    }

    @Test
    @DisplayName("Debería eliminar un nutricionista cuando el ID existe")
    void delete_ExistingId_DeletesNutricionista() {
        when(nutricionistaRepository.findById(1L)).thenReturn(Optional.of(nutricionista));
        doNothing().when(nutricionistaRepository).delete(nutricionista);

        nutricionistaService.delete(1L);

        verify(nutricionistaRepository, times(1)).findById(1L);
        verify(nutricionistaRepository, times(1)).delete(nutricionista);
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException cuando el ID no existe al eliminar")
    void delete_NonExistingId_ThrowsResourceNotFoundException() {
        when(nutricionistaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> nutricionistaService.delete(99L));
        verify(nutricionistaRepository, times(1)).findById(99L);
        verify(nutricionistaRepository, never()).delete(any(Nutricionista.class));
    }
}
