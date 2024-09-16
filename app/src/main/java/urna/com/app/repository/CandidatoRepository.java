package urna.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import urna.com.app.entity.Candidato;
import urna.com.app.entity.Candidato.StatusCandidato;


import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    
    @Query("SELECT c FROM Candidato c WHERE c.status = :status AND c.funcao = :funcaoId")
    List<Candidato> findAtivosPorFuncao(@Param("status") StatusCandidato status, @Param("funcaoId") Integer funcaoId);
}