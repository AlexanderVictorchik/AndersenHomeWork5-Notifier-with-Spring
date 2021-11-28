package com.notificationservice;

import com.accountant.Report;
import com.accountant.TimeTrackerService;
import com.command.CommandService;
import com.comparator.CompareService;
import com.notifyservice.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationSender {

    @Autowired
    public NotificationSender() {
    }

    public void sendNotification() throws IOException {
        List<String> comm = CommandService.getNotAdminUsers();
        List<Report> acc = TimeTrackerService.getTimingReport();
        List<String> convertReport = new ArrayList<>();
        for (Report r : acc) {
            convertReport.add(String.valueOf(r.getUserId()));
        }
        List<String> differenceIdList = CompareService.compare(convertReport, comm);
        for (int i = 0; i < differenceIdList.size(); i++) {
            NotifyService.sendNotify(Long.valueOf(CommandService.getLeadByIdGroup(differenceIdList.get(i))),
                    "User with ID " + differenceIdList.get(i) + "\n" +
                            "Name : " + CommandService.getFirstNameById(differenceIdList.get(i)) + " "
                            + CommandService.getLastNameById(differenceIdList.get(i)) + "\n" +
                            "from " + CommandService.getGroupById(differenceIdList.get(i)) + " team" + "\n" +
                            "didn't send the report" + "\n" +
                            "on date " + LocalDate.now() + "\n" +
                            "==============================" + "\n");
        }
    }
}
