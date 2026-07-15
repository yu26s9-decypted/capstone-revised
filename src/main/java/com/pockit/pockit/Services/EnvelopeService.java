package com.pockit.pockit.Services;

import com.pockit.pockit.Models.Envelope;
import com.pockit.pockit.Repositories.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EnvelopeService {

    private final EnvelopeRepository envelopeRepository;

    @Autowired
    public EnvelopeService(EnvelopeRepository envelopeRepository) {
        this.envelopeRepository = envelopeRepository;
    }

    public List<Envelope> getAllEnvelopes() {
        //todo return every envelope for the dashboard grid
        return List.of();
    }

    public Optional<Envelope> getEnvelopeById(Long id) {
        //todo
        return Optional.empty();
    }

    public Envelope createEnvelope(Envelope envelope) {
        //todo a new envelope starts allocated at 0.00, not at whatever the client sent
        return null;
    }

    public Optional<Envelope> updateEnvelope(Long id, Envelope updated) {
        //todo name / icon / target only -- allocation changes go through allocate()
        return Optional.empty();
    }

    public boolean deleteEnvelopeById(Long id) {
        //todo deleting frees its allocation back to unallocated
        return false;
    }

    public Optional<Envelope> allocate(Long id, BigDecimal amount) {
        //todo move money in (positive) or back out (negative)
        //todo reject if it would push allocatedAmount below 0
        //todo reject if it would push total allocations above the account balance
        return Optional.empty();
    }

    public BigDecimal getTotalAllocated() {
        //todo sum of allocatedAmount across all envelopes -- the "In envelopes" figure
        return BigDecimal.ZERO;
    }

    public BigDecimal getAccountBalance() {
        //todo derived, not stored: sum of CREDIT transactions minus sum of DEBIT transactions
        //todo needs TransactionRepository injected here, or a shared BalanceService
        return BigDecimal.ZERO;
    }

    public BigDecimal getUnallocated() {
        //todo getAccountBalance() minus getTotalAllocated() -- the "To spend" figure
        return BigDecimal.ZERO;
    }
}
