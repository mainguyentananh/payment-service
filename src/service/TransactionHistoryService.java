package service;

import model.TransactionHistory;

public interface TransactionHistoryService {
    void getAllTransactionHistory();
    void create(TransactionHistory transactionHistory);
}
