package urna.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import urna.com.app.entity.Eleitor;
import urna.com.app.repository.EleitorRepository;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepository;

    public Eleitor salvarEleitor(Eleitor eleitor) {
        if (eleitor.getCpf() == null || eleitor.getEmail() == null) {
            eleitor.setStatus(Eleitor.Status.PENDENTE);
        } else {
            eleitor.setStatus(Eleitor.Status.APTO);
        }
        return eleitorRepository.save(eleitor);
    }

    public List<Eleitor> listarTodos() {
        return eleitorRepository.findByStatusNot(Eleitor.Status.INATIVO);
    }

    public Optional<Eleitor> buscarPorId(Long id) {
        return eleitorRepository.findById(id);
    }

    public void deletarEleitor(Long id) {
        Optional<Eleitor> eleitorExistente = eleitorRepository.findById(id);
        if (eleitorExistente.isPresent()) {
            Eleitor eleitor = eleitorExistente.get();
            if (Eleitor.Status.VOTOU.equals(eleitor.getStatus())) {
                throw new RuntimeException("Usuário já votou. Não foi possível inativá-lo.");
            }
            eleitor.setStatus(Eleitor.Status.INATIVO);
            eleitorRepository.save(eleitor);
        }
    }

    public Eleitor atualizarEleitor(Long id, Eleitor eleitorAtualizado) {
        Optional<Eleitor> eleitorExistente = eleitorRepository.findById(id);
        if (eleitorExistente.isPresent()) {
            Eleitor eleitor = eleitorExistente.get();
            if (Eleitor.Status.INATIVO.equals(eleitor.getStatus())) {
                eleitor.setNomeCompleto(eleitorAtualizado.getNomeCompleto());
                eleitor.setCpf(eleitorAtualizado.getCpf());
                eleitor.setProfissao(eleitorAtualizado.getProfissao());
                eleitor.setTelefoneCelular(eleitorAtualizado.getTelefoneCelular());
                eleitor.setTelefoneFixo(eleitorAtualizado.getTelefoneFixo());
                eleitor.setEmail(eleitorAtualizado.getEmail());
                return eleitorRepository.save(eleitor);
            } else {
                if (eleitorAtualizado.getCpf() == null || eleitorAtualizado.getEmail() == null) {
                    eleitorAtualizado.setStatus(Eleitor.Status.PENDENTE);
                } else {
                    eleitorAtualizado.setStatus(Eleitor.Status.APTO);
                }
                eleitor.setNomeCompleto(eleitorAtualizado.getNomeCompleto());
                eleitor.setCpf(eleitorAtualizado.getCpf());
                eleitor.setProfissao(eleitorAtualizado.getProfissao());
                eleitor.setTelefoneCelular(eleitorAtualizado.getTelefoneCelular());
                eleitor.setTelefoneFixo(eleitorAtualizado.getTelefoneFixo());
                eleitor.setEmail(eleitorAtualizado.getEmail());
                eleitor.setStatus(eleitorAtualizado.getStatus());
                return eleitorRepository.save(eleitor);
            }
        } else {
            return null;
        }
    }

    public void votar(Long id) {
        Optional<Eleitor> eleitorOptional = eleitorRepository.findById(id);
        if (eleitorOptional.isPresent()) {
            Eleitor eleitor = eleitorOptional.get();
            if (Eleitor.Status.PENDENTE.equals(eleitor.getStatus())) {
                eleitor.setStatus(Eleitor.Status.BLOQUEADO);
                eleitorRepository.save(eleitor);
                throw new RuntimeException("Usuário com cadastro pendente tentou votar. O usuário será bloqueado!");
            } else if (Eleitor.Status.APTO.equals(eleitor.getStatus())) {
                eleitor.setStatus(Eleitor.Status.VOTOU);
                eleitorRepository.save(eleitor);
            } else {
                throw new RuntimeException("Usuário não está apto a votar.");
            }
        }
    }
}
