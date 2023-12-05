package test;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import service.impl.WalletServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletServiceImplTest {
    private WalletServiceImpl walletService;

    @BeforeEach
    public void setUp() {
        walletService = new WalletServiceImpl();
    }

    @Test
    public void testTopUpWallet() {
        Long initialBalance = walletService.getCurrentBalance();
        Long topUpAmount = 100L;
        walletService.topUpWallet(topUpAmount);
        Long expectedBalance = initialBalance + topUpAmount;
        assertEquals(expectedBalance, walletService.getCurrentBalance());
    }

    @Test
    public void testDeductBalance() {
        Long initialBalance = walletService.getCurrentBalance();
        Long deductionAmount = 50L;
        walletService.deductBalance(deductionAmount);
        Long expectedBalance = initialBalance - deductionAmount;
        assertEquals(expectedBalance, walletService.getCurrentBalance());
    }
}
