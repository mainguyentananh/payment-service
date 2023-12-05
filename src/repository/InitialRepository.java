package repository;

import model.Bill;
import model.TransactionHistory;

import java.util.HashMap;

public class InitialRepository {
    public static HashMap<Long, String> scheduleRepo = new HashMap<>();
    public static HashMap<Long, Bill> billRepo = new HashMap<>();
    public static HashMap<Long, TransactionHistory> transactionHistoryRepo = new HashMap<>();
    public static HashMap<String, Long> walletRepo = new HashMap<>();
}
