package com.pockit.pockit.Services;

import com.pockit.pockit.Models.Envelope;
import com.pockit.pockit.Repositories.EnvelopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return envelopeRepository.findAll();
    }

    public Optional<Envelope> getEnvelopeById(Long id) {
        return envelopeRepository.findById(id);
    }

    public Envelope createEnvelope(Envelope envelope) {
        envelope.setId(null);
        envelope.setAllocatedAmount(BigDecimal.ZERO);
        return envelopeRepository.save(envelope);
    }

    public Optional<Envelope> updateEnvelope(Long id, Envelope updated) {
        return envelopeRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setIcon(updated.getIcon());
            existing.setTargetAmount(updated.getTargetAmount());
            return envelopeRepository.save(existing);
        });
    }

    public boolean deleteEnvelopeById(Long id) {
        if (envelopeRepository.existsById(id)) {
            envelopeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Envelope> allocate(Long id, BigDecimal amount) {
        return envelopeRepository.findById(id).map(existing -> {
            BigDecimal next = existing.getAllocatedAmount().add(amount);
            if (next.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Envelope %d only holds %s, cannot move out %s"
                        .formatted(id, existing.getAllocatedAmount(), amount.abs()));
            }
            existing.setAllocatedAmount(next);
            return envelopeRepository.save(existing);
        });
    }

    public BigDecimal getTotalAllocated() {
        return envelopeRepository.sumAllocatedAmount();
    }
}
