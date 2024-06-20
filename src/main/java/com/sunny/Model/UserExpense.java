package com.sunny.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserExpense extends BaseModel {

    @ManyToOne
    private User user;
    private double amount;

    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;


}
