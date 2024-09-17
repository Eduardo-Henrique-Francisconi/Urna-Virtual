package app.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import urna.com.app.entity.Candidato;
import urna.com.app.entity.Eleitor;
import urna.com.app.repository.CandidatoRepository;
import urna.com.app.repository.EleitorRepository;
import urna.com.app.service.CandidatoService;
import urna.com.app.service.EleitorService;

public class CandidatoServiceTests {

	@Mock
	private CandidatoRepository candidatoRepository;

	@InjectMocks
	private CandidatoService candidatoService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * 
	 */
	@Test
	public void testSalvarCandidato () {
		// Mock para o candidato a ser salvo
		Candidato candidato = new Candidato();
		candidato.setNome("Candidato 1");
		candidato.setNumero("10");
		candidato.setFuncao(1);

		// Mock para o candidatoRepository
		when(candidatoRepository.save(candidato)).thenReturn(candidato);

		// Teste do método salvarCandidato
		Candidato candidatoSalvo = candidatoService.salvarCandidato(candidato);

		// Verificação do método salvarCandidato
		assertEquals(candidatoSalvo.getNome(), candidato.getNome());
		assertEquals(candidatoSalvo.getNumero(), candidato.getNumero());
		assertEquals(candidatoSalvo.getFuncao(), candidato.getFuncao());
	}



}


