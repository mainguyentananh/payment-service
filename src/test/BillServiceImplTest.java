package test;

import common.Enum;
import model.Bill;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import repository.InitialRepository;
import service.BillService;
import service.impl.BillServiceImpl;

import java.util.Date;

import static org.junit.Assert.*;

public class BillServiceImplTest{
    @Test
    public void testCreate() {
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill(Enum.Type.ELECTRIC, 100L, new Date(), "XYZ Provider", Enum.State.PENDING);
        billService.create(bill);
        assertTrue(InitialRepository.billRepo.containsKey(bill.getId()));
        assertEquals(bill, InitialRepository.billRepo.get(bill.getId()));
    }

    @Test
    public void testUpdate() {
        BillService billService = new BillServiceImpl();
        Bill initialBill = new Bill(Enum.Type.ELECTRIC, 50L, new Date(), "XYZ Provider", Enum.State.PENDING);
        InitialRepository.billRepo.put(1L, initialBill);
        Bill updatedBill = new Bill(Enum.Type.ELECTRIC, 60L, new Date(), "XYZ Provider", Enum.State.PROCESSED);
        billService.update(1L, updatedBill);
        assertEquals(updatedBill, InitialRepository.billRepo.get(1L));
    }

    @Test
    public void testDelete() {
        BillService billService = new BillServiceImpl();
        Bill billToDelete = new Bill(Enum.Type.ELECTRIC, 60L, new Date(), "GHI Provider", Enum.State.PENDING);
        InitialRepository.billRepo.put(2L, billToDelete);
        billService.delete(2L);
        assertFalse(InitialRepository.billRepo.containsKey(2L));
    }

    @Test
    public void testFindById() {
        BillService billService = new BillServiceImpl();
        Bill billToFind = new Bill(Enum.Type.ELECTRIC, 60L, new Date(), "GHI Provider", Enum.State.PENDING);
        InitialRepository.billRepo.put(3L, billToFind);
        Bill foundBill = billService.findById(3L);
        assertEquals(billToFind, foundBill);
    }

    @Test
    public void testSearchByProvider() {
        BillService billService = new BillServiceImpl();
        Bill bill1 = new Bill(Enum.Type.ELECTRIC, 60L, new Date(), "GHI Provider", Enum.State.PENDING);
        Bill bill2 = new Bill(Enum.Type.INTERNET, 75L, new Date(), "GHI Provider", Enum.State.PENDING);
        InitialRepository.billRepo.put(4L, bill1);
        InitialRepository.billRepo.put(5L, bill2);
        billService.searchByProvider("GHI Provider");
    }

    @Test
    public void testGetAllBillDueDate() {
        BillService billService = new BillServiceImpl();
        Bill bill1 = new Bill(Enum.Type.ELECTRIC, 100L, new Date(), "XYZ Provider", Enum.State.PENDING);
        Bill bill2 = new Bill(Enum.Type.ELECTRIC, 100L, new Date(), "XYZ Provider", Enum.State.PROCESSED);
        InitialRepository.billRepo.put(6L, bill1);
        InitialRepository.billRepo.put(7L, bill2);
        billService.getAllBillDueDate();
    }
}
