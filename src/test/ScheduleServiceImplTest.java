package test;

import repository.InitialRepository;
import service.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


class ScheduleServiceImplTest {
    @Test
    void testSchedule() {
        ScheduleServiceImpl scheduleService = new ScheduleServiceImpl();
        Long billId = 123L;
        Date timePayment = new Date();
        scheduleService.schedule(billId, timePayment);
        assertTrue(InitialRepository.scheduleRepo.containsKey(billId));
        assertNotNull(InitialRepository.scheduleRepo.get(billId));
    }
}
