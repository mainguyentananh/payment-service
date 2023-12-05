package model;

import common.Enum;


public class TransactionHistory {
    private Long amount;
    private String paymentDate;
    private Enum.State state;
    private Long billId;

    public TransactionHistory() {
    }

    public TransactionHistory(Long amount, String paymentDate, Enum.State state, Long billId) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.state = state;
        this.billId = billId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Enum.State getState() {
        return state;
    }

    public void setState(Enum.State state) {
        this.state = state;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }
}
