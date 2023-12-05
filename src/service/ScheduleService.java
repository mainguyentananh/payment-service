package service;

import java.util.Date;

public interface ScheduleService {
    void schedule(Long billId, Date timePayment);
}
