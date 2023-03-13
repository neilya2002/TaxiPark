package com.example.TaxiPark.service;

import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.Report;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.repository.ReportRepos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FileService {

  private final ReportRepos reportRepos;

  public FileService(ReportRepos reportRepos) {
    this.reportRepos = reportRepos;
  }

  public void generateFile(List<Order> orders, User user) {

    String absolutePath = "C:\\Users\\andrey.golovin\\kz\\TaxiPark (2)\\TaxiPark\\src\\main\\resources\\report\\";

    String fileName = "report per " + LocalDate.now() + " from " + user.getName() + "__" + UUID.randomUUID() + ".txt";

    StringBuilder report = new StringBuilder(getStringFromUser(user));

    for (Order order : orders) {
      report.append(getStringFromOrder(order));
    }
    report.append("Конец отчета");
    File file = new File(absolutePath + fileName);
    try {
      file.createNewFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      writer.write(report.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    Report reportFromDb = new Report();

    reportFromDb.setPathToFile(fileName);
    reportFromDb.setOrders(orders);
    reportRepos.save(reportFromDb);
  }

  private String getStringFromUser(User user) {
    return "Диспетчер " + user.getName() + "\n" + "Начало отчета" + "\n" + "\n";
  }

  private String getStringFromOrder(Order order) {

    return "Номер заказа " + order.getId()
        + "\n"
        + "Место старта "
        + order.getStartAddress()
        + "\n"
        + "Место окончания поездки "
        + order.getEndAddress()
        + "\n"
        + "Время в пути "
        + order.getTime()
        + "\n"
        + "Цена "
        + order.getPrice()
        + "\n"
        + "Заказчик "
        + order.getUser().getName()
        + "\n"
        + "\n";
  }
}
