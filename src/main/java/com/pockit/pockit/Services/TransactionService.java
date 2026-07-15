package com.pockit.pockit.Services;

import com.pockit.pockit.Models.Transaction;
import com.pockit.pockit.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> updateTransaction(int id, Transaction updated) {
        return transactionRepository.findById(id).map(existing -> {
            existing.setDescription(updated.getDescription());
            existing.setVendor(updated.getVendor());
            existing.setAmount(updated.getAmount());
            existing.setTransactionType(updated.getTransactionType());
            existing.setDate(updated.getDate());
            return transactionRepository.save(existing);
        });
    }

    public boolean deleteTransactionById(int id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
