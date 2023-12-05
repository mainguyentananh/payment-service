package service.impl;

import common.Enum;
import common.Utils;
import model.Bill;
import model.TransactionHistory;
import service.BillService;
import service.TransactionHistoryService;
import service.TransactionService;
import service.WalletService;

import java.time.LocalDate;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private BillService billService;
    private TransactionHistoryService transactionHistoryService;
    private WalletService walletService;

    public TransactionServiceImpl(BillService billService, TransactionHistoryService transactionHistoryService, WalletService walletService) {
        this.billService = billService;
        this.transactionHistoryService = transactionHistoryService;
        this.walletService = walletService;
    }

    @Override
    public void payment(List<Long> billIds) {
        if (billIds.size() == 1) {
            Bill bill = billService.findById(billIds.get(0));
            if (bill == null) {
                return;
            }
            handlePayment(bill, false);
        }
        for (Long billId : billIds) {
            Bill bill = billService.findById(billId);
            if (bill == null) {
                continue;
            }
        }
    }

    private void handlePayment(Bill bill, boolean isMultiplePayment){
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAmount(bill.getAmount());
        transactionHistory.setPaymentDate(Utils.parseLocalDateToString(LocalDate.now()));
        transactionHistory.setBillId(bill.getId());
        transactionHistory.setState(Enum.State.PENDING);
        if (isValidPayment(bill.getAmount())) {
            transactionHistory.setState(Enum.State.PROCESSED);
            bill.setState(Enum.State.PROCESSED);
            billService.update(bill.getId(), bill);
            walletService.deductBalance(bill.getAmount());
        }
        transactionHistoryService.create(transactionHistory);
    }

    private boolean isValidPayment(Long totalAmount) {
        return walletService.getCurrentBalance() >= totalAmount;
    }
}
