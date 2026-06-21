package com.mediciones.api_mediciones;

import com.mediciones.api_mediciones.dto.MedicionRequest;
import com.mediciones.api_mediciones.dto.MedicionResponse;
import com.mediciones.api_mediciones.exception.ResourceNotFoundException;
import com.mediciones.api_mediciones.model.Mediciones;
import com.mediciones.api_mediciones.repository.MedicionRepository;
import com.mediciones.api_mediciones.service.impl.MedicionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicionServiceImplTest {

	@Mock
	private MedicionRepository medicionRepository;

	@InjectMocks
	private MedicionServiceImpl medicionService;

	private Mediciones medicion;
	private MedicionRequest request;

	@BeforeEach
	void setUp() {
		medicion = Mediciones.builder()
				.id(1L)
				.pacienteId(100L)
				.fechaMedicion(new Date())
				.pesoKg(new BigDecimal("80.50"))
				.tallaCm(new BigDecimal("175.00"))
				.grasaPorcentaje(new BigDecimal("20.5"))
				.musculoPorcentaje(new BigDecimal("35.0"))
				.build();

		request = MedicionRequest.builder()
				.pacienteId(100L)
				.fechaMedicion(new Date())
				.pesoKg(new BigDecimal("80.50"))
				.tallaCm(new BigDecimal("175.00"))
				.grasaPorcentaje(new BigDecimal("20.5"))
				.musculoPorcentaje(new BigDecimal("35.0"))
				.build();
	}

	@Test
	void createMedicion_Success() {
		when(medicionRepository.save(any(Mediciones.class))).thenReturn(medicion);

		MedicionResponse response = medicionService.createMedicion(request);

		assertNotNull(response);
		assertEquals(medicion.getId(), response.getId());
		assertEquals(medicion.getPacienteId(), response.getPacienteId());
		verify(medicionRepository, times(1)).save(any(Mediciones.class));
	}

	@Test
	void getMedicionById_Success() {
		when(medicionRepository.findById(1L)).thenReturn(Optional.of(medicion));

		MedicionResponse response = medicionService.getMedicionById(1L);

		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals(medicion.getPacienteId(), response.getPacienteId());
	}

	@Test
	void getMedicionById_NotFound() {
		when(medicionRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> medicionService.getMedicionById(99L));
	}

	@Test
	void getMedicionesByPacienteId_Success() {
		when(medicionRepository.findByPacienteId(100L)).thenReturn(List.of(medicion));

		List<MedicionResponse> responses = medicionService.getMedicionesByPacienteId(100L);

		assertNotNull(responses);
		assertEquals(1, responses.size());
		assertEquals(medicion.getPacienteId(), responses.get(0).getPacienteId());
	}

	@Test
	void updateMedicion_Success() {
		when(medicionRepository.findById(1L)).thenReturn(Optional.of(medicion));
		when(medicionRepository.save(any(Mediciones.class))).thenReturn(medicion);

		request.setPesoKg(new BigDecimal("78.00"));
		MedicionResponse response = medicionService.updateMedicion(1L, request);

		assertNotNull(response);
		verify(medicionRepository, times(1)).save(medicion);
	}

	@Test
	void updateMedicion_NotFound() {
		when(medicionRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> medicionService.updateMedicion(99L, request));
		verify(medicionRepository, never()).save(any());
	}

	@Test
	void deleteMedicion_Success() {
		when(medicionRepository.findById(1L)).thenReturn(Optional.of(medicion));
		doNothing().when(medicionRepository).delete(medicion);

		assertDoesNotThrow(() -> medicionService.deleteMedicion(1L));
		verify(medicionRepository, times(1)).delete(medicion);
	}

	@Test
	void deleteMedicion_NotFound() {
		when(medicionRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> medicionService.deleteMedicion(99L));
		verify(medicionRepository, never()).delete(any());
	}
}
