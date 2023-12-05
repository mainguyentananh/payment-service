package service;

import java.util.List;

public interface TransactionService {
    void payment(List<Long> billIds);
}
