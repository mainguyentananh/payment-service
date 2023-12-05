package test;
import common.Enum;
import common.Utils;
import model.Bill;
import org.junit.Test;
import service.*;
import service.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TransactionServiceImplTest {


    @Test
    public void testPayment() {
        WalletService walletService = new WalletServiceImpl();
        BillService billService = new BillServiceImpl();
        TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl(billService, transactionHistoryService, walletService);
        Bill bill1 = new Bill(Enum.Type.ELECTRIC, 200000L, Utils.parseToDate("25/10/2020"), "EVN HCMC", Enum.State.NOT_PAID);
        billService.create(bill1);
        Bill bill2 = new Bill(Enum.Type.INTERNET, 300000L, Utils.parseToDate("30/10/2020"), "SAVACO HCMC", Enum.State.NOT_PAID);
        billService.create(bill2);
        Bill bill3 = new Bill(Enum.Type.WATER, 40000L, Utils.parseToDate("30/11/2020"), "VNPT", Enum.State.NOT_PAID);
        billService.create(bill3);
        transactionService.payment(new ArrayList<>(Arrays.asList(1L, 2L, 3L)));
    }

}
