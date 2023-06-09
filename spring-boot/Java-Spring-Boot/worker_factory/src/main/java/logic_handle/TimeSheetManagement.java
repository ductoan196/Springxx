package logic_handle;

import entity.Factory;
import entity.TimeSheet;
import entity.TimeSheetDetail;
import entity.Worker;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TimeSheetManagement {

    private List<TimeSheet> timeSheets;
    private WorkerManagement workerManagement;
    private FactoryManagement factoryManagement;

    public TimeSheetManagement(WorkerManagement workerManagement, FactoryManagement factoryManagement) {
        this.timeSheets = new ArrayList<>();
        this.workerManagement = workerManagement;
    }

    public void logTimeSheet() {
        System.out.println("Muốn chấm công cho bao nhiêu công nhân: ");
        int workerNumber;
        do {
            try {
                workerNumber = new Scanner(System.in).nextInt();
                if (workerNumber >0) {
                    break;
                }
                System.out.println("Số lượng công nhân phải lớn hơn 0, nhập lại");
            } catch (InputMismatchException ex) {
                System.out.println("Vui lòng nhập số: ");
            }
        } while (true);

        for (int i = 0; i < workerNumber; i++) {
            System.out.println("Nhập ID của công nhân thứ " + (i + 1) + " mà bạn muốn chấm công: ");
            int workerId;
            Worker worker;
            do {
                try {
                    workerId = new Scanner(System.in).nextInt();
                    worker = workerManagement.findByID(workerId);
                    if (worker != null) {
                        break;
                    }
                    System.out.println("ID không tồn tại trong hệ thống");
                } catch (InputMismatchException ex) {
                    System.out.println("Vui lòng nhập số");
                }
            } while (true);

            System.out.println("Công nhân này làm ở bao nhiêu xưởng trong tháng vừa rồi: ");
            int factoryNumber;
            do {
                try {
                    factoryNumber = new Scanner(System.in).nextInt();
                    if (factoryNumber > 0) {
                        break;
                    }
                    System.out.println("Số lượng xưởng phải lớn hơn 0, vui lòng nhập lại: ");
                } catch (InputMismatchException ex) {
                    System.out.println("Vui lòng nhập số: ");
                }
            } while (true);


            List<TimeSheetDetail> timeSheetDetails = new ArrayList<>();
            for (int j = 0; j < factoryNumber; j++) {
                System.out.println("Nhập mã xưởng thứ " + (j + 1) + " mà ông này đã làm việc trong tháng vừa rồi: ");
                int factoryId;
                Factory factory;
                do {
                    try {
                        factoryId = new Scanner(System.in).nextInt();
                        factory = factoryManagement.findByID(factoryId);
                        if (factory != null) {
                            break;
                        }
                        System.out.println("ID không tồn tại trong hệ thống");
                    } catch (InputMismatchException ex) {
                        System.out.println("Vui lòng nhập số");
                    }
                } while (true);

                System.out.println("Công nhân này làm việc ở xưởng vừa nhập là bao nhiêu ngày: ");
                int workingDay;
                do {
                    try {
                        workingDay = new Scanner(System.in).nextInt();
                        if (workingDay > 0) {
                            break;
                        }
                        System.out.println("Số lượng xưởng phải lớn hơn 0, vui lòng nhập lại: ");
                    } catch (InputMismatchException ex) {
                        System.out.println("Vui lòng nhập số: ");
                    }
                } while (true);

                TimeSheetDetail timeSheetDetail = new TimeSheetDetail(factory, workingDay);
                timeSheetDetails.add(timeSheetDetail);
            }
            TimeSheet timeSheet = new TimeSheet(worker, timeSheetDetails);
        }

    }
    public void showInfo(){
        timeSheets.forEach(System.out::println);
    }
}
