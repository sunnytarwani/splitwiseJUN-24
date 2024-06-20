package com.sunny.Controller;

import com.sunny.Model.SettlementTransaction;
import com.sunny.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/splitwise")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/settleup/{groupId}")
    public ResponseEntity settleUp(@PathVariable("groupId") Long id){
        List<SettlementTransaction> settlementTransactions = groupService.settleUp(id);
        return ResponseEntity.ok(settlementTransactions);
    }
}
