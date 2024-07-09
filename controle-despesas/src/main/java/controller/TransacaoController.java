package controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Transacao;
import service.TransacaoService;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public List<Transacao> getAllTransacoes() {
        return transacaoService.getAllTransacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.getTransacaoById(id);
        if (transacao.isPresent()) {
            return ResponseEntity.ok(transacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Transacao createTransacao(@RequestBody Transacao transacao) {
        return transacaoService.saveTransacao(transacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> updateTransacao(@PathVariable Long id, @RequestBody Transacao transacaoDetails) {
        Optional<Transacao> transacao = transacaoService.getTransacaoById(id);
        if (transacao.isPresent()) {
            Transacao transacaoToUpdate = transacao.get();
            transacaoToUpdate.setDescricao(transacaoDetails.getDescricao());
            transacaoToUpdate.setValor(transacaoDetails.getValor());
            transacaoToUpdate.setData(transacaoDetails.getData());
            transacaoToUpdate.setTipo(transacaoDetails.getTipo());
            return ResponseEntity.ok(transacaoService.saveTransacao(transacaoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        if (transacaoService.getTransacaoById(id).isPresent()) {
            transacaoService.deleteTransacao(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
