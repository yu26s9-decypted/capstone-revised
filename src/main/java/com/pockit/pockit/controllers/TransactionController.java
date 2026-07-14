
package com.pockit.pockit.controllers;
import com.pockit.pockit.Models.Transaction;
import com.pockit.pockit.Services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Display all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Display deposits only
    @GetMapping("/deposits")
    public List<Transaction> getDeposits() {
        return transactionService.getDeposits();
    }

    // Display payments only
    @GetMapping("/payments")
    public List<Transaction> getPayments() {
        return transactionService.getPayments();
    }


    // Add a deposit
    @PostMapping("/deposits")
    public Transaction addDeposit(@RequestBody Transaction transaction) {
        return transactionService.addDeposit(transaction);
    }

    // Add a payment
    @PostMapping("/payments")
    public Transaction addPayment(@RequestBody Transaction transaction) {
        return transactionService.addPayment(transaction);
    }

    // Month to date report
    @GetMapping("/reports/month-to-date")
    public List<Transaction> getMonthToDate() {
        return transactionService.getMonthToDate();
    }

    // Previous month report
    @GetMapping("/reports/previous-month")
    public List<Transaction> getPreviousMonth() {
        return transactionService.getPreviousMonth();
    }

    // Year to date report
    @GetMapping("/reports/year-to-date")
    public List<Transaction> getYearToDate() {
        return transactionService.getYearToDate();
    }

    // Previous year report
    @GetMapping("/reports/previous-year")
    public List<Transaction> getPreviousYear() {
        return transactionService.getPreviousYear();
    }

    // Search by vendor
    @GetMapping("/reports/vendor")
    public List<Transaction> searchByVendor(
            @RequestParam String vendor
    ) {
        return transactionService.searchByVendor(vendor);
    }

    // Bonus custom search
    @GetMapping("/reports/search")
    public List<Transaction> customSearch(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(required = false)
            String description,

            @RequestParam(required = false)
            String vendor,

            @RequestParam(required = false)
            Double amount
    ) {
        return transactionService.customSearch(
                startDate,
                endDate,
                description,
                vendor,
                amount
        );
    }








}
