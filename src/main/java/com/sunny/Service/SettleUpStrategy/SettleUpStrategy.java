package com.sunny.Service.SettleUpStrategy;

import com.sunny.Model.Expense;
import com.sunny.Model.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransaction(List<Expense> expenses);
}
