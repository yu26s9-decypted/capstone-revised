package com.pockit.pockit.Controllers;

import com.pockit.pockit.Models.Envelope;
import com.pockit.pockit.Services.EnvelopeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/envelopes")
public class EnvelopeController {

    private final EnvelopeService envelopeService;

    public EnvelopeController(EnvelopeService envelopeService) {
        this.envelopeService = envelopeService;
    }

    @GetMapping
    public List<Envelope> getAllEnvelopes() {
        //todo backs the Envelopes grid
        return envelopeService.getAllEnvelopes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envelope> getEnvelopeById(@PathVariable Long id) {
        //todo
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Envelope> createEnvelope(@RequestBody Envelope envelope) {
        //todo backs "Add envelope" -- 201 with created envelope
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvelope(@PathVariable Long id) {
        //todo
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/allocate")
    public ResponseEntity<Envelope> allocate(@PathVariable Long id,
                                             @RequestBody BigDecimal amount) {
        //todo for Andy: backs "Move to envelope"
        //todo 409 when the allocation would exceed the balance or go negative.
        return ResponseEntity.notFound().build();
    }
}
