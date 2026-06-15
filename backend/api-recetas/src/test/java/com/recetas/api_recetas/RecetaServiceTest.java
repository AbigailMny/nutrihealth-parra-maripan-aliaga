package com.recetas.api_recetas;

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

import com.recetas.api_recetas.model.Receta;
import com.recetas.api_recetas.repository.RecetaRepository;
import com.recetas.api_recetas.service.RecetaService;

@ExtendWith(MockitoExtension.class)
public class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    private Receta ejemploReceta;

    @BeforeEach
    void setUp() {
        ejemploReceta = new Receta();
        ejemploReceta.setId_receta(1L);
        ejemploReceta.setId_paciente(1L);
        ejemploReceta.setNombre_plato("Ensalada Griega");
        ejemploReceta.setPreparacion("Mezclar ingredientes frescos");
        ejemploReceta.setEstado("Activa");
        ejemploReceta.setAnotaciones("Sin sal");
    }

    @Test
    @DisplayName("Mostrar todas las recetas")
    void mostrarRecetas() {
        when(recetaRepository.findAll()).thenReturn(List.of(ejemploReceta));

        List<Receta> resultado = recetaService.mostrarRecetas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ensalada Griega", resultado.get(0).getNombre_plato());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Buscar receta por id")
    void buscarId() {
        when(recetaRepository.findById(1L)).thenReturn(Optional.of(ejemploReceta));

        Receta resultado = recetaService.buscarId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_receta());
        assertEquals("Ensalada Griega", resultado.getNombre_plato());
        verify(recetaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Crear una receta")
    void crearReceta() {
        when(recetaRepository.save(ejemploReceta)).thenReturn(ejemploReceta);

        Receta resultado = recetaService.crearReceta(ejemploReceta);

        assertNotNull(resultado);
        assertEquals("Ensalada Griega", resultado.getNombre_plato());
        verify(recetaRepository, times(1)).save(ejemploReceta);
    }

    @Test
    @DisplayName("Buscar receta fallido - id no existe")
    void buscarIdFallido() {
        when(recetaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            recetaService.buscarId(999L);
        });

        verify(recetaRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Eliminar una receta")
    void eliminarReceta() {
        doNothing().when(recetaRepository).deleteById(1L);

        recetaService.eliminarReceta(1L);

        verify(recetaRepository, times(1)).deleteById(1L);
    }
}

