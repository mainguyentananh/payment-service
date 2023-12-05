package service.impl;

import common.Enum;
import common.Utils;
import model.Bill;
import repository.InitialRepository;
import service.BillService;
import java.util.*;

public class BillServiceImpl implements BillService {

    @Override
    public void getAllBill() {
        System.out.printf("%-8s%-15s%-10s%-12s%-10s%-15s%n",
                "Bill No.", "Type", "Amount", "Due Date", "State", "Provider");
        System.out.println("--------------------------------------------------------");
        for (Map.Entry<Long, Bill> entry : InitialRepository.billRepo.entrySet()) {
            Long id = entry.getKey();
            Bill bill = entry.getValue();
            System.out.printf("%-8d%-15s%-10d%-12s%-10s%-15s%n",
                    id, bill.getType(), bill.getAmount(), Utils.formatDate(bill.getDueDate()), bill.getState(), bill.getProvider());
        }
    }

    @Override
    public void create(Bill bill) {
        InitialRepository.billRepo.put(bill.getId(), bill);
        System.out.println("Bill created successfully.");
    }

    @Override
    public void update(Long id, Bill bill) {
        if (InitialRepository.billRepo.containsKey(id)) {
            InitialRepository.billRepo.put(id, bill);
            System.out.println("Bill updated successfully.");
        } else {
            System.out.println("Bill not found with ID: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        if (InitialRepository.billRepo.containsKey(id)) {
            InitialRepository.billRepo.remove(id);
            System.out.println("Bill deleted successfully.");
        } else {
            System.out.println("Bill not found with ID: " + id);
        }
    }

    @Override
    public Bill findById(Long id) {
        if (InitialRepository.billRepo.containsKey(id)) {
            return InitialRepository.billRepo.get(id);
        }
        System.out.println("Sorry! Not found a bill with such id: " + id);
        return null;
    }

    @Override
    public void searchByProvider(String provider) {
        System.out.printf("%-8s%-15s%-10s%-12s%-10s%-15s%n",
                "Bill No.", "Type", "Amount", "Due Date", "State", "Provider");
        System.out.println("--------------------------------------------------------");
        for (Map.Entry<Long, Bill> entry : InitialRepository.billRepo.entrySet()) {
            Long id = entry.getKey();
            Bill bill = entry.getValue();
            if (!bill.getProvider().equalsIgnoreCase(provider)) {
                continue;
            }
            System.out.printf("%-8d%-15s%-10d%-12s%-10s%-15s%n",
                    id, bill.getType(), bill.getAmount(), Utils.formatDate(bill.getDueDate()), bill.getState(), bill.getProvider());
        }
    }

    @Override
    public void getAllBillDueDate() {
        System.out.printf("%-8s%-15s%-10s%-12s%-10s%-15s%n",
                "Bill No.", "Type", "Amount", "Due Date", "State", "Provider");
        System.out.println("--------------------------------------------------------");
        for (Map.Entry<Long, Bill> entry : InitialRepository.billRepo.entrySet()) {
            Long id = entry.getKey();
            Bill bill = entry.getValue();
            if (bill.getState().equals(Enum.State.PROCESSED)) {
                continue;
            }
            System.out.printf("%-8d%-15s%-10d%-12s%-10s%-15s%n",
                    id, bill.getType(), bill.getAmount(), Utils.formatDate(bill.getDueDate()), bill.getState(), bill.getProvider());
        }
    }
}
