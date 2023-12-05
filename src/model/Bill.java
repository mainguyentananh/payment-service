package model;


import common.Enum;
import common.Utils;

import java.util.Date;

public class Bill {
    private Long id;
    private Enum.Type type;
    private Long amount;
    private Date dueDate;
    private String provider;
    private Enum.State state;

    public Bill() {
    }

    public Bill(Enum.Type type, Long amount, Date dueDate, String provider, Enum.State state) {
        this.id = Utils.generatePrimaryKey();
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.provider = provider;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum.Type getType() {
        return type;
    }

    public void setType(Enum.Type type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String string) {
        this.provider = string;
    }

    public Enum.State getState() {
        return state;
    }

    public void setState(Enum.State state) {
        this.state = state;
    }

}
