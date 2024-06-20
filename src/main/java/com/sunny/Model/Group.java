package com.sunny.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel{

    private String name;
    @ManyToOne
    private User createdBy;
    private Date createdDate;
    private double totalAmountSpent;

    @ManyToMany(mappedBy = "groups")
    private List<User> members;

    @OneToMany
    private List<Expense> expenses;
    @OneToMany
    private List<SettlementTransaction> settlementTransactions;
}
