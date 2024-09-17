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
import org.springframework.boot.test.mock.mockito.MockBean;

import urna.com.app.entity.Eleitor;
import urna.com.app.repository.EleitorRepository;
import urna.com.app.service.EleitorService;

public class EleitorServiceTests {

	@Mock
	private EleitorRepository eleitorRepository;

	@InjectMocks
	private EleitorService eleitorService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSalvarEleitorComDadosIncompletos() {
		// Mock para o cliente a ser salvo
		Eleitor eleitor = new Eleitor();
		eleitor.setNome("João da Silva");

		// Define o comportamento do mock para o método save
		when(eleitorRepository.save(eleitor)).thenReturn(eleitor);

		// Chama o método a ser testado
		Eleitor resultado = eleitorService.salvarEleitor(eleitor);

		// Verifica se o resultado é o esperado
		assertEquals(Eleitor.Status.PENDENTE, resultado.getStatus());
		verify(eleitorRepository, times(1)).save(eleitor);
	}

	@Test
	public void testSalvarEleitorComDadosCompletos() {
		// Mock para o cliente a ser salvo
		Eleitor eleitor = new Eleitor();
		eleitor.setNome("João da Silva");
		eleitor.setCpf("12345678901");
		eleitor.setProfissao("Engenheiro");
		eleitor.setTelefoneCelular("(11) 99999-9999");
		eleitor.setEmail("joaoteste@gmail.com");

		// Define o comportamento do mock para o método save
		when(eleitorRepository.save(eleitor)).thenReturn(eleitor);

		// Chama o método a ser testado
		Eleitor resultado = eleitorService.salvarEleitor(eleitor);

		// Verifica se o resultado é o esperado
		assertEquals(Eleitor.Status.APTO, resultado.getStatus());
		verify(eleitorRepository, times(1)).save(eleitor);
	}

	@Test
	public void testListarTodos() {
		// Mock para a lista de eleitores
		Eleitor eleitor1 = new Eleitor();
		eleitor1.setStatus(Eleitor.Status.APTO);
		Eleitor eleitor2 = new Eleitor();
		eleitor2.setStatus(Eleitor.Status.INATIVO);
		Eleitor eleitor3 = new Eleitor();
		eleitor3.setStatus(Eleitor.Status.BLOQUEADO);
		List<Eleitor> eleitores = Arrays.asList(eleitor1, eleitor2, eleitor3);

		// Define o comportamento do mock para o método findByStatusNot
		when(eleitorRepository.findByStatusNot(Eleitor.Status.INATIVO)).thenReturn(eleitores);

		// Chama o método a ser testado
		List<Eleitor> resultado = eleitorService.listarTodos();

		// Verifica se o resultado é o esperado
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(eleitor1));
		assertTrue(resultado.contains(eleitor3));
	}

	
}
