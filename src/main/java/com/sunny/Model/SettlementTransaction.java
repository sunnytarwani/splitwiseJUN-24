package com.sunny.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SettlementTransaction extends BaseModel {
    @ManyToOne
    private User borrower;

    @ManyToOne
    private User lender;

    private double amount;
    private Currency currency;

    public SettlementTransaction(User borrower, User lender, double amount) {
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
    }
}
