package com.pockit.pockit.Controllers;

import com.pockit.pockit.Models.Envelope;
import com.pockit.pockit.Services.EnvelopeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/envelopes")
public class EnvelopeController {

    public record AllocateRequest(BigDecimal amount) {
    }

    private final EnvelopeService envelopeService;

    public EnvelopeController(EnvelopeService envelopeService) {
        this.envelopeService = envelopeService;
    }

    @GetMapping
    public List<Envelope> getAllEnvelopes() {
        return envelopeService.getAllEnvelopes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envelope> getEnvelopeById(@PathVariable Long id) {
        return envelopeService.getEnvelopeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Envelope> createEnvelope(@RequestBody Envelope envelope) {
        Envelope created = envelopeService.createEnvelope(envelope);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvelope(@PathVariable Long id) {
        if (envelopeService.deleteEnvelopeById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/allocate")
    public ResponseEntity<Envelope> allocate(@PathVariable Long id,
                                             @RequestBody AllocateRequest request) {
        return envelopeService.allocate(id, request.amount())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidAllocation(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
