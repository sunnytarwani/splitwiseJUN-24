package com.sunny.Service;

import com.sunny.Model.Expense;
import com.sunny.Model.SettlementTransaction;
import com.sunny.Respository.GroupRepository;
import com.sunny.Service.SettleUpStrategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<SettlementTransaction> settleUp(Long groupId) {
        List<Expense> expenses = groupRepository.findGroupById(groupId);
        return settleUpStrategy.getSettlementTransaction(expenses);
    }

}
