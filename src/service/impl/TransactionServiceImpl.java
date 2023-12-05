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
import java.util.ArrayList;
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
            handleSinglePayment(billIds.get(0));
        } else {
            processMultiplePayments(billIds);
        }
    }

    private void handleSinglePayment(Long billId) {
        Bill bill = billService.findById(billId);
        if (bill != null) {
            handleSinglePayment(bill);
        }
    }

    private void processMultiplePayments(List<Long> billIds) {
        Long totalAmount = 0L;
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();

        for (Long billId : billIds) {
            Bill bill = billService.findById(billId);
            if (bill != null) {
                TransactionHistory transactionHistory = createTransactionHistory(bill);
                transactionHistoryList.add(transactionHistory);
                totalAmount += bill.getAmount();
            }
        }

        if (isValidPayment(totalAmount)) {
            processValidPayment(transactionHistoryList, totalAmount);
        } else {
            processInvalidPayment(transactionHistoryList);
        }
    }

    private TransactionHistory createTransactionHistory(Bill bill) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAmount(bill.getAmount());
        transactionHistory.setPaymentDate(Utils.parseLocalDateToString(LocalDate.now()));
        transactionHistory.setBillId(bill.getId());
        transactionHistory.setState(Enum.State.PENDING);
        return transactionHistory;
    }

    private void processValidPayment(List<TransactionHistory> transactionHistoryList, Long totalAmount) {
        for (TransactionHistory tra : transactionHistoryList) {
            tra.setState(Enum.State.PROCESSED);
            transactionHistoryService.create(tra);

            Bill bill = billService.findById(tra.getBillId());
            bill.setState(Enum.State.PROCESSED);
            billService.update(bill.getId(), bill);
        }

        walletService.deductBalance(totalAmount);
    }

    private void processInvalidPayment(List<TransactionHistory> transactionHistoryList) {
        for (TransactionHistory tra : transactionHistoryList) {
            transactionHistoryService.create(tra);
        }
    }


    private void handleSinglePayment(Bill bill){
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
