package service;

public interface WalletService {
    void topUpWallet(Long amount);
    Long getCurrentBalance();
    void deductBalance(Long amount);
}
