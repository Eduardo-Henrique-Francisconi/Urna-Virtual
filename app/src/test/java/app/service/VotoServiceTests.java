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
	}

    





   
}
