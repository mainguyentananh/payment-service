package service.impl;
import common.Message;
import common.Utils;
import repository.InitialRepository;
import service.ScheduleService;

import java.util.Date;

public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public void schedule(Long billId, Date timePayment) {
        String dateRunJob = Utils.formatDate(timePayment);
        InitialRepository.scheduleRepo.put(billId, dateRunJob);
        System.out.println(String.format(Message.SCHEDULE, billId, dateRunJob));
    }
}
