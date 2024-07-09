package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
