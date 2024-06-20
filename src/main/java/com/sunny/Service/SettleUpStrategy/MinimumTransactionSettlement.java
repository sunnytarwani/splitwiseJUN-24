package com.sunny.Service.SettleUpStrategy;

import com.sunny.Dto.UserAmount;
import com.sunny.Model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MinimumTransactionSettlement implements SettleUpStrategy {


    @Override
    public List<SettlementTransaction> getSettlementTransaction(List<Expense> expenses) {
        HashMap<User , Double> map = getOutStandingBalances(expenses);


        //Comparator for min Heap(Ascending order)
        Comparator<UserAmount> minHeapComparator = Comparator.comparingDouble(UserAmount::getAmount);
        //Comparator for the max heap(Descending Order)
        Comparator<UserAmount> maxHeapComparator = Comparator.comparingDouble(UserAmount::getAmount).reversed();


        //max hap
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxHeapComparator);
        //min heap
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minHeapComparator);

        for(Map.Entry<User,Double> entry : map.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }
            else if (entry.getValue() > 0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }
            else {
                System.out.println("User doesn't need to participate in the Settle Up");
            }
        }

        List<SettlementTransaction> settlementTransactions = new ArrayList<>();

        while(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            UserAmount borrower = minHeap.poll();
            UserAmount lender = maxHeap.poll();

            if(Math.abs(borrower.getAmount()) > lender.getAmount()){
                //lender = 500 , borrowe = -1000 , borrower pays lender 500
                borrower.setAmount(borrower.getAmount() + lender.getAmount());
                minHeap.add(borrower);
                SettlementTransaction  settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser() , lender.getAmount());
                settlementTransactions.add(settlementTransaction);
            } else if (Math.abs(borrower.getAmount()) < lender.getAmount()) {

                //lender = 1000 , borrower = 500
                lender.setAmount(lender.getAmount() - borrower.getAmount());
                maxHeap.add(lender);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), Math.abs(borrower.getAmount()));
                settlementTransactions.add(settlementTransaction);
            }
            else{
                System.out.println("Do Nothing, Both are equal");
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                settlementTransactions.add(settlementTransaction);
            }
        }

        return settlementTransactions;
    }

    private HashMap<User , Double> getOutStandingBalances(List<Expense> expenses){
        HashMap<User , Double> hashMap = new HashMap<>();
        for(Expense expense: expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if(hashMap.containsKey(participant)){
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        hashMap.put(participant, hashMap.get(participant) + amount);
                    }
                    else{
                        hashMap.put(participant, hashMap.get(participant) - amount);
                    }
                }
                else{
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        hashMap.put(participant, 0+amount);
                    }
                    else {
                        hashMap.put(participant, 0-amount);
                    }
                }
            }
        }
        return hashMap;
    }

}


/*
       1. Go through all the expenses, and find the total outstanding for each person
       2. All borrowers will go to a min heap
       3. All lendors will go to a max heap
       4. pull min from minHeap and max from maxHeap, and create a transaction
       5. Update the balances, put them back in heap
       6. Keep doing until heap is empty.


       Lendor = 500, Borrower = -500
       make both of them zero, and keep both of them out of the heap

       Lendor = 1000, Borrower = -500
       borrower will become zero, lendor will become 500 and lendor will go inside the heap again

       Lendor = 500, Borrower = -1000
       Lendor will become zero, Borrower will become -500 and Borrower will go inside the heap again
 */