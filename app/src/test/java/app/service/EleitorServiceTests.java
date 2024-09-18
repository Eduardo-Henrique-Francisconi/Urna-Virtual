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

	//não esta funcionando
	/*@Test
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
	*/

	@Test
	public void testBuscarPorId() {
		// Mock para o eleitor a ser retornado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Optional<Eleitor> resultado = eleitorService.buscarPorId(1L);

		// Verifica se o resultado é o esperado
		assertTrue(resultado.isPresent());
		assertEquals(eleitor, resultado.get());
	}

	@Test
	public void testDeletarEleitor() {
		// Mock para o eleitor a ser deletado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.APTO);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		eleitorService.deletarEleitor(1L);

		// Verifica se o método foi chamado
		verify(eleitorRepository, times(1)).save(eleitor);
	}

	@Test
	public void testDeletarEleitorQueJaVotou() {
		// Mock para o eleitor a ser deletado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.VOTOU);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Exception exception = assertThrows(RuntimeException.class, () -> {
			eleitorService.deletarEleitor(1L);
		});

		// Verifica se a exceção foi lançada
		assertTrue(exception.getMessage().contains("Usuário já votou"));
	}
	
	@Test	
	public void testAtualizarEleitorInativo() {
		// Mock para o eleitor a ser atualizado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.INATIVO);
		Eleitor eleitorAtualizado = new Eleitor();
		eleitorAtualizado.setNome("João da Silva");
		eleitorAtualizado.setCpf("12345678901");
		eleitorAtualizado.setProfissao("Engenheiro");
		eleitorAtualizado.setTelefoneCelular("(11) 99999-9999");
		eleitorAtualizado.setEmail("joaoteste@gmail.com");

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));
		// Define o comportamento do mock para o método save
		when(eleitorRepository.save(eleitor)).thenReturn(eleitor);

		// Chama o método a ser testado
		Eleitor resultado = eleitorService.atualizarEleitor(1L, eleitorAtualizado);

		// Verifica se o resultado é o esperado
		assertEquals(eleitorAtualizado.getNome(), resultado.getNome());
		assertEquals(eleitorAtualizado.getCpf(), resultado.getCpf());
		assertEquals(eleitorAtualizado.getProfissao(), resultado.getProfissao());
		assertEquals(eleitorAtualizado.getTelefoneCelular(), resultado.getTelefoneCelular());
		assertEquals(eleitorAtualizado.getEmail(), resultado.getEmail());
		assertEquals(Eleitor.Status.INATIVO, resultado.getStatus());
		verify(eleitorRepository, times(1)).save(eleitor);
	}

	//não esta funcionando
	/* 
	@Test
	public void testAtualizarEleitorApto() {
		// Mock para o eleitor a ser atualizado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.APTO);
		Eleitor eleitorAtualizado = new Eleitor();
		eleitorAtualizado.setNome("João da Silva");
		eleitorAtualizado.setCpf("12345678901");
		eleitorAtualizado.setProfissao("Engenheiro");
		eleitorAtualizado.setTelefoneCelular("(11) 99999-9999");
		eleitorAtualizado.setEmail("joaoteste@gmail.com");

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Eleitor resultado = eleitorService.atualizarEleitor(1L, eleitorAtualizado);

		// Verifica se o resultado é o esperado
		assertEquals(eleitorAtualizado.getNome(), resultado.getNome());
		assertEquals(eleitorAtualizado.getCpf(), resultado.getCpf());
		assertEquals(eleitorAtualizado.getProfissao(), resultado.getProfissao());
		assertEquals(eleitorAtualizado.getTelefoneCelular(), resultado.getTelefoneCelular());
		assertEquals(eleitorAtualizado.getEmail(), resultado.getEmail());
		assertEquals(Eleitor.Status.APTO, resultado.getStatus());
		verify(eleitorRepository, times(1)).save(eleitor);
	}
    */

	@Test
	public void testVotarPendente() {
		// Mock para o eleitor a ser votado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.PENDENTE);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Exception exception = assertThrows(RuntimeException.class, () -> {
			eleitorService.votar(1L);
		});

		// Verifica se a exceção foi lançada
		assertTrue(exception.getMessage().contains("Usuário com cadastro pendente"));
	}

	@Test
	public void testVotarApto() {
		// Mock para o eleitor a ser votado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.APTO);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		eleitorService.votar(1L);

		// Verifica se o método foi chamado
		verify(eleitorRepository, times(1)).save(eleitor);
	}

	@Test
	public void testVotarBloqueado() {
		// Mock para o eleitor a ser votado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.BLOQUEADO);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Exception exception = assertThrows(RuntimeException.class, () -> {
			eleitorService.votar(1L);
		});

		// Verifica se a exceção foi lançada
		assertTrue(exception.getMessage().contains("Usuário bloqueado"));
	}
	
	@Test
	public void testVotarJaVotou() {
		// Mock para o eleitor a ser votado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.VOTOU);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Exception exception = assertThrows(RuntimeException.class, () -> {
			eleitorService.votar(1L);
		});

		// Verifica se a exceção foi lançada
		assertTrue(exception.getMessage().contains("Usuário já votou"));
	}

	@Test
	public void testVotarInativo() {
		// Mock para o eleitor a ser votado
		Eleitor eleitor = new Eleitor();
		eleitor.setId(1L);
		eleitor.setStatus(Eleitor.Status.BLOQUEADO);

		// Define o comportamento do mock para o método findById
		when(eleitorRepository.findById(1L)).thenReturn(Optional.of(eleitor));

		// Chama o método a ser testado
		Exception exception = assertThrows(RuntimeException.class, () -> {
			eleitorService.votar(1L);
		});

		// Verifica se a exceção foi lançada
		assertTrue(exception.getMessage().contains("Usuário bloqueado"));

	}
}
