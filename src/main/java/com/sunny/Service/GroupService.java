package com.sunny.Service;

import com.sunny.Model.SettlementTransaction;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface GroupService {

    List<SettlementTransaction> settleUp(Long groupId);
}
