package com.pockit.pockit.Repositories;

import com.pockit.pockit.Models.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface EnvelopeRepository extends JpaRepository<Envelope, Long> {

    @Query("SELECT COALESCE(SUM(e.allocatedAmount), 0) FROM Envelope e")
    BigDecimal sumAllocatedAmount();
}
