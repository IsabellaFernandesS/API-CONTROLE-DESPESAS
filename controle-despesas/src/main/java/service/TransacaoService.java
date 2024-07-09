package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Transacao;
import repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Transacao> getAllTransacoes() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> getTransacaoById(Long id) {
        return transacaoRepository.findById(id);
    }

    public Transacao saveTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public void deleteTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

}
