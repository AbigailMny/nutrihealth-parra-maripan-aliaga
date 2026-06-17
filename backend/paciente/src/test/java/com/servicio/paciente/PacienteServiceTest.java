package com.servicio.paciente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.servicio.paciente.dto.PacienteDTO;
import com.servicio.paciente.dto.TipoDietaDTO;
import com.servicio.paciente.model.Paciente;
import com.servicio.paciente.model.TipoDieta;
import com.servicio.paciente.repository.PacienteRepository;

import com.servicio.paciente.repository.TipoDietaRepository;
import com.servicio.paciente.service.PacienteService;
import com.servicio.paciente.service.impl.PacienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    @Mock
    private PacienteRepository pacienteRepository;
    @Mock
    private TipoDietaRepository tipoDietaRepository;
    @InjectMocks
    private PacienteServiceImpl pacienteService;

    private Paciente pacienteEjemplo;
    private TipoDieta tipoDietaEjemplo;
    private PacienteDTO pacienteDTOEjemplo;

    @BeforeEach
    void setUp() {
        tipoDietaEjemplo = new TipoDieta();
        tipoDietaEjemplo.setId(1L);
        tipoDietaEjemplo.setNombre("Keto");
        tipoDietaEjemplo.setDescripcion("Dieta cetogénica");
        pacienteEjemplo = new Paciente();
        pacienteEjemplo.setId(1L);
        pacienteEjemplo.setRun("12345678-9");
        pacienteEjemplo.setNombres("Juan");
        pacienteEjemplo.setApellidos("Perez");
        pacienteEjemplo.setCorreo("juan.perez@example.com");
        pacienteEjemplo.setTelefono("123456789");
        pacienteEjemplo.setTipoDieta(tipoDietaEjemplo);
        pacienteEjemplo.setFechaRegistro(LocalDate.now());
        pacienteDTOEjemplo = PacienteDTO.builder()
                .id(1L)
                .run("12345678-9")
                .nombres("Juan")
                .apellidos("Perez")
                .correo("juan.perez@example.com")
                .telefono("123456789")
                .tipoDietaId(1L)
                .fechaRegistro(LocalDate.now())
                .build();
    }

    @Test
    @DisplayName("Debería retornar una lista con todos los pacientes")
    void testFindAll() {
        when(pacienteRepository.findAll()).thenReturn(List.of(pacienteEjemplo));
        List<PacienteDTO> pacientesObtenidos = pacienteService.findAll();
        assertNotNull(pacientesObtenidos);
        assertEquals(1, pacientesObtenidos.size());
        assertEquals("Juan", pacientesObtenidos.get(0).getNombres());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería buscar por nombre y apellido")
    void testSearchByNombreApellido_Ambos() {
        when(pacienteRepository.findByNombresContainingIgnoreCaseAndApellidosContainingIgnoreCase("Juan", "Perez"))
                .thenReturn(List.of(pacienteEjemplo));
        List<PacienteDTO> result = pacienteService.searchByNombreApellido("Juan", "Perez");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pacienteRepository, times(1))
                .findByNombresContainingIgnoreCaseAndApellidosContainingIgnoreCase("Juan", "Perez");
    }

    @Test
    @DisplayName("Debería buscar solo por nombre")
    void testSearchByNombreApellido_SoloNombre() {
        when(pacienteRepository.findByNombresContainingIgnoreCase("Juan"))
                .thenReturn(List.of(pacienteEjemplo));
        List<PacienteDTO> result = pacienteService.searchByNombreApellido("Juan", null);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pacienteRepository, times(1)).findByNombresContainingIgnoreCase("Juan");
    }

    @Test
    @DisplayName("Debería buscar solo por apellido")
    void testSearchByNombreApellido_SoloApellido() {
        when(pacienteRepository.findByApellidosContainingIgnoreCase("Perez"))
                .thenReturn(List.of(pacienteEjemplo));
        List<PacienteDTO> result = pacienteService.searchByNombreApellido("", "Perez");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pacienteRepository, times(1)).findByApellidosContainingIgnoreCase("Perez");
    }

    @Test
    @DisplayName("Debería retornar lista vacía si no se envía nombre ni apellido")
    void testSearchByNombreApellido_Ninguno() {
        List<PacienteDTO> result = pacienteService.searchByNombreApellido(null, " ");
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verifyNoInteractions(pacienteRepository);
    }

    @Test
    @DisplayName("Debería retornar un paciente por su ID")
    void testFindById_Success() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEjemplo));
        PacienteDTO obtenido = pacienteService.findById(1L);
        assertNotNull(obtenido);
        assertEquals("12345678-9", obtenido.getRun());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debería lanzar excepción si no encuentra el paciente por ID")
    void testFindById_NotFound() {
        when(pacienteRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.findById(2L);
        });
        assertTrue(exception.getMessage().contains("Paciente no encontrado con id: 2"));
    }

    @Test
    @DisplayName("Debería guardar un paciente correctamente")
    void testSave() {
        when(tipoDietaRepository.findById(1L)).thenReturn(Optional.of(tipoDietaEjemplo));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteEjemplo);
        PacienteDTO guardado = pacienteService.save(pacienteDTOEjemplo);
        assertNotNull(guardado);
        assertEquals("Juan", guardado.getNombres());
        verify(tipoDietaRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Debería actualizar un paciente correctamente")
    void testUpdate_Success() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEjemplo));
        when(tipoDietaRepository.findById(1L)).thenReturn(Optional.of(tipoDietaEjemplo));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteEjemplo);
        PacienteDTO actualizado = pacienteService.update(1L, pacienteDTOEjemplo);
        assertNotNull(actualizado);
        assertEquals("Juan", actualizado.getNombres());
        verify(pacienteRepository, times(1)).findById(1L);
        verify(tipoDietaRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Debería lanzar excepción al actualizar un paciente inexistente")
    void testUpdate_NotFound() {
        when(pacienteRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.update(2L, pacienteDTOEjemplo);
        });
        assertTrue(exception.getMessage().contains("Paciente no encontrado con id: 2"));
    }

    @Test
    @DisplayName("Debería eliminar un paciente correctamente")
    void testDelete_Success() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEjemplo));
        doNothing().when(pacienteRepository).delete(pacienteEjemplo);
        assertDoesNotThrow(() -> pacienteService.delete(1L));
        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).delete(pacienteEjemplo);
    }

    @Test
    @DisplayName("Debería lanzar excepción al intentar eliminar un paciente inexistente")
    void testDelete_NotFound() {
        when(pacienteRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.delete(2L);
        });
        assertTrue(exception.getMessage().contains("Paciente no encontrado con id: 2"));
    }
}