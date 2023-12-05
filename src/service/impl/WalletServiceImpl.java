package service.impl;

import common.Constant;
import repository.InitialRepository;
import service.WalletService;
import common.Message;

public class WalletServiceImpl implements WalletService {
    public WalletServiceImpl() {
        InitialRepository.walletRepo.put(Constant.MOMO, 0L);
    }
    @Override
    public void topUpWallet(Long amount) {
        InitialRepository.walletRepo.put(Constant.MOMO, this.getCurrentBalance() + amount);
        checkBalance();
    }
    @Override
    public Long getCurrentBalance() {
        return InitialRepository.walletRepo.get(Constant.MOMO);
    }

    @Override
    public void deductBalance(Long amount) {
        InitialRepository.walletRepo.put(Constant.MOMO, this.getCurrentBalance() - amount);
        checkBalance();
    }

    private void checkBalance() {
        System.out.println(String.format(Message.CASH_IN, InitialRepository.walletRepo.get(Constant.MOMO)));
    }
}
