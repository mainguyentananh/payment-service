package test;
import common.Enum;
import common.Utils;
import model.TransactionHistory;
import org.junit.jupiter.api.Test;
import repository.InitialRepository;
import service.impl.TransactionHistoryServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class TransactionHistoryServiceImplTest {
    @Test
    void testGetAllTransactionHistory() {
        TransactionHistoryServiceImpl transactionHistoryService = new TransactionHistoryServiceImpl();
        InitialRepository.transactionHistoryRepo.clear();
        String date = Utils.parseLocalDateToString(LocalDate.now());
        TransactionHistory history1 = new TransactionHistory(100L, date, Enum.State.PENDING, 123L);
        TransactionHistory history2 = new TransactionHistory(50L, date, Enum.State.PROCESSED, 456L);
        InitialRepository.transactionHistoryRepo.put(1L, history1);
        InitialRepository.transactionHistoryRepo.put(2L, history2);
        transactionHistoryService.getAllTransactionHistory();
    }

    @Test
    void testCreate() {
        TransactionHistoryServiceImpl transactionHistoryService = new TransactionHistoryServiceImpl();
        InitialRepository.transactionHistoryRepo.clear();
        String date = Utils.parseLocalDateToString(LocalDate.now());
        TransactionHistory history = new TransactionHistory(75L, date, Enum.State.PROCESSED, 789L);
        transactionHistoryService.create(history);
        assertEquals(1, InitialRepository.transactionHistoryRepo.size());
        assertTrue(InitialRepository.transactionHistoryRepo.containsValue(history));
    }
}
