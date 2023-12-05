package service;

import model.Bill;

public interface BillService {
    void getAllBill();
    void create(Bill bill);
    void update(Long id, Bill bill);
    void delete(Long id);
    Bill findById(Long id);
    void searchByProvider(String provider);
    void getAllBillDueDate();
}
