import common.Enum;
import common.Utils;
import model.Bill;
import service.*;
import service.impl.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WalletService walletService = new WalletServiceImpl();
        BillService billService = new BillServiceImpl();
        ScheduleService scheduleService = new ScheduleServiceImpl();
        TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl(billService, transactionHistoryService, walletService);


        //master data
        Bill bill1 = new Bill(Enum.Type.ELECTRIC,200000L, Utils.parseToDate("25/10/2020"),"EVN HCMC", Enum.State.NOT_PAID);
        billService.create(bill1);
        Bill bill2 = new Bill(Enum.Type.INTERNET,300000L, Utils.parseToDate("30/10/2020"),"SAVACO HCMC", Enum.State.NOT_PAID);
        billService.create(bill2);
        Bill bill3 = new Bill(Enum.Type.WATER,40000L, Utils.parseToDate("30/11/2020"),"VNPT", Enum.State.NOT_PAID);
        billService.create(bill3);

        while (true) {
            String command = scanner.nextLine();
            String[] params = command.split(" ");
            try {
                switch (params[0].toUpperCase()) {
                    case "CASH_IN":
                        walletService.topUpWallet(Long.valueOf(params[1]));
                        break;
                    case "LIST_BILL":
                        billService.getAllBill();
                        break;
                    case "PAY":
                        if (params.length < 2) {
                            System.out.println("Invalid command. Try again.");
                        }
                        List<Long> listBillId = Arrays.stream(params, 1, params.length)
                                .map(Long::valueOf)
                                .collect(Collectors.toList());
                        transactionService.payment(listBillId);
                        break;
                    case "DUE_DATE":
                        billService.getAllBillDueDate();
                        break;
                    case "SCHEDULE":
                        scheduleService.schedule(Long.valueOf(params[1]), Utils.parseToDate(params[2]));
                        break;
                    case "LIST_PAYMENT":
                        transactionHistoryService.getAllTransactionHistory();
                        break;
                    case "SEARCH_BILL_BY_PROVIDER":
                        billService.searchByProvider(params[1]);
                        break;
                    case "EXIT":
                        System.out.println("Good bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid command. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid command. Try again.");
            }

        }
    }
}