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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import urna.com.app.entity.Eleitor;
import urna.com.app.entity.Voto;
import urna.com.app.entity.Candidato;
import urna.com.app.entity.Candidato.StatusCandidato;
import urna.com.app.entity.Apuracao;
import urna.com.app.repository.CandidatoRepository;
import urna.com.app.repository.EleitorRepository;
import urna.com.app.repository.VotoRepository;
import urna.com.app.service.EleitorService;

public class VotoServiceTests {

    @Mock
	private EleitorRepository eleitorRepository;
    private VotoRepository votoRepository;
    private CandidatoRepository candidatoRepository;
    
    

	@InjectMocks
	private EleitorService eleitorService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		candidatoRepository = Mockito.mock(CandidatoRepository.class);
	}

	/*
	@Test
	public void testarVotar() {
		// Mock para o cliente a ser salvo
		Eleitor eleitor = new Eleitor();
		eleitor.setCpf("12345678901");
		eleitor.setEmail("teste@teste.com");
		eleitor.setNome("Teste");
		eleitor.setStatus(Eleitor.Status.APTO);

		// Mock para o candidato a ser votado
		Candidato candidato = new Candidato();
		candidato.setNome("Teste");
		candidato.setCpf("12345678901");
		candidato.setNumero("12");
		candidato.setFuncao(1);
		candidato.setStatus(StatusCandidato.ATIVO);

		// Mock para o voto
		Voto voto = new Voto();
		voto.setCandidatoPrefeito(candidato);
		voto.setCandidatoVereador(candidato);

		//definindo o metodo buscarPorId para retornar o eleitor mockado
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		//definindo o metodo findById para retornar o candidato mockado
		when(candidatoRepository.findById(1L)).thenReturn(Optional.of(candidato));
		
		//definindo o metodo salvarEleitor para retornar o eleitor mockado
		when(eleitorService.salvarEleitor(eleitor)).thenReturn(eleitor);

		//definindo o metodo votar para retornar o hash do voto
		when(eleitorService.votar(1L, voto)).thenReturn("hash");

		//verificando se o metodo votar foi chamado

		assertEquals("hash", eleitorService.votar(1L, voto));
		

	}	
		*/

   
}
