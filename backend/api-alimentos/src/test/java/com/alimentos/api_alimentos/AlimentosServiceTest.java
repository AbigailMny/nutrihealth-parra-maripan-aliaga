package com.alimentos.api_alimentos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alimentos.api_alimentos.model.Alimento;
import com.alimentos.api_alimentos.model.Categoria;
import com.alimentos.api_alimentos.repository.AlimentoRepository;
import com.alimentos.api_alimentos.service.AlimentoService;

@ExtendWith(MockitoExtension.class)
public class AlimentosServiceTest {

    @Mock
    private AlimentoRepository alimentoRepository;

    @InjectMocks
    private AlimentoService alimentoService;

    private Alimento ejemploAlimento;

    @BeforeEach
    void setUp() {
        Categoria categoria = new Categoria();
        categoria.setId_categoria(1L);
        categoria.setNombre("Frutas");

        ejemploAlimento = new Alimento();
        ejemploAlimento.setId_alimento(1L);
        ejemploAlimento.setNombre("Manzana");
        ejemploAlimento.setProteinaG(0.3);
        ejemploAlimento.setGrasaG(0.2);
        ejemploAlimento.setCarbohidratoG(14.0);
        ejemploAlimento.setCalorias(52.0);
        ejemploAlimento.setCategoria(categoria);
    }

    @Test
    @DisplayName("Mostrar todos los alimentos")
    void mostrarAlimento() {
        when(alimentoRepository.findAll()).thenReturn(List.of(ejemploAlimento));

        List<Alimento> resultado = alimentoService.mostrarAlimento();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Manzana", resultado.get(0).getNombre());
        verify(alimentoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar alimento por id")
    void buscarId() {
        when(alimentoRepository.findById(1L)).thenReturn(Optional.of(ejemploAlimento));

        Alimento resultado = alimentoService.buscarId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_alimento());
        assertEquals("Manzana", resultado.getNombre());
        verify(alimentoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Crear un alimento")
    void crearAlimento() {
        when(alimentoRepository.save(ejemploAlimento)).thenReturn(ejemploAlimento);

        Alimento resultado = alimentoService.crearAlimento(ejemploAlimento);

        assertNotNull(resultado);
        assertEquals("Manzana", resultado.getNombre());
        verify(alimentoRepository, times(1)).save(ejemploAlimento);
    }

    @Test
    @DisplayName("Buscar alimentos por calorías máximas")
    void buscarPorCaloriasMaximas() {
        when(alimentoRepository.findByCaloriasLessThanEqual(100.0)).thenReturn(List.of(ejemploAlimento));

        List<Alimento> resultado = alimentoService.buscarPorCaloriasMaximas(100.0);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(alimentoRepository, times(1)).findByCaloriasLessThanEqual(100.0);
    }

    @Test
    @DisplayName("Buscar alimento fallido - id no existe")
    void buscarIdFallido() {
        when(alimentoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            alimentoService.buscarId(999L);
        });

        verify(alimentoRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Eliminar un alimento")
    void eliminarAlimento() {
        doNothing().when(alimentoRepository).deleteById(1L);

        alimentoService.eliminarAlimento(1L);

        verify(alimentoRepository, times(1)).deleteById(1L);
    }
}

