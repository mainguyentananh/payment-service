package service.impl;

import common.Utils;
import model.TransactionHistory;
import repository.InitialRepository;
import service.TransactionHistoryService;

import java.util.Map;

public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Override
    public void getAllTransactionHistory() {
        System.out.printf("%-4s%-10s%-15s%-12s%-8s%n","No.","Amount", "Payment Date", "State", "Bill Id");
        System.out.println("----------------------------------------------");
        for (Map.Entry<Long, TransactionHistory> entry : InitialRepository.transactionHistoryRepo.entrySet()) {
            Long id = entry.getKey();
            TransactionHistory transactionHistory = entry.getValue();
            System.out.printf("%-4s%-10s%-15s%-12s%-8s%n", id, transactionHistory.getAmount(), transactionHistory.getPaymentDate(), transactionHistory.getState(), transactionHistory.getBillId());
        }
    }

    @Override
    public void create(TransactionHistory transactionHistory) {
        InitialRepository.transactionHistoryRepo.put(Utils.generatePrimaryKey(), transactionHistory);
    }

}
