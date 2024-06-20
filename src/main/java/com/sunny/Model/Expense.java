package com.sunny.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense extends BaseModel {

    private String description;
    private double amount;
    private Date expenseTime;
    private Currency currency;
    @ManyToOne
    private User addedBy;

    @OneToMany
    private List<UserExpense> userExpenses;
}