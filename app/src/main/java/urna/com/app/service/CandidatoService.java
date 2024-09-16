package urna.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import urna.com.app.entity.Candidato;
import urna.com.app.entity.Candidato.StatusCandidato;
import urna.com.app.repository.CandidatoRepository;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato salvarCandidato(Candidato candidato) {
        if (candidato.getStatus() == null) {
            candidato.setStatus(StatusCandidato.ATIVO);
        }
        return candidatoRepository.save(candidato);
    }

    public Candidato buscarPorId(Long id) {
        return candidatoRepository.findById(id).orElse(null);
    }

    public void deletarCandidato(Long id) {
        Candidato candidato = candidatoRepository.findById(id).orElse(null);
        if (candidato != null) {
            candidato.setStatus(StatusCandidato.INATIVO);
            candidatoRepository.save(candidato);
        }
    }
}

