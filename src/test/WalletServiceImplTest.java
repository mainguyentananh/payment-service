package test;
import common.Constant;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import repository.InitialRepository;
import service.WalletService;
import service.impl.WalletServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletServiceImplTest {
    @Test
    public void testTopUpWallet() {
        WalletService walletService = new WalletServiceImpl();
        Long initialBalance = walletService.getCurrentBalance();
        Long topUpAmount = 100L;
        walletService.topUpWallet(topUpAmount);
        Long expectedBalance = initialBalance + topUpAmount;
        assertEquals(expectedBalance, walletService.getCurrentBalance());
    }

    @Test
    public void testDeductBalance() {
        WalletService walletService = new WalletServiceImpl();
        Long initialBalance = walletService.getCurrentBalance();
        Long deductionAmount = 50L;
        walletService.deductBalance(deductionAmount);
        Long expectedBalance = initialBalance - deductionAmount;
        assertEquals(expectedBalance, walletService.getCurrentBalance());
    }
}
