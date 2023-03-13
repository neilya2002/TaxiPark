package com.example.TaxiPark.controller;

import com.example.TaxiPark.model.CommentDto;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.model.entyti.Report;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.repository.ReportRepos;
import com.example.TaxiPark.service.OrderService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ManagerController {

  private final OrderService orderService;
  private final ReportRepos reportRepos;

  public ManagerController(OrderService orderService, ReportRepos reportRepos) {
    this.orderService = orderService;
    this.reportRepos = reportRepos;
  }

  @GetMapping(
      value = "/getFile/{file-name}"
  )
  public ResponseEntity<InputStreamResource> getFile(@PathVariable("file-name") String fileName) throws IOException {

    File report = new File(
        "C:\\Users\\andrey.golovin\\kz\\TaxiPark (2)\\TaxiPark\\src\\main\\resources\\report\\"
            + fileName);

    InputStream targetStream = new FileInputStream(report);

    return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=\"somefile.txt\"")
        .contentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE))
        .body(new InputStreamResource(targetStream));
  }

  @GetMapping("manager")
  public String manager(@AuthenticationPrincipal User user, Model model) {

    List<CommentDto> commentDto = orderService.getAllOrders().stream()
        .filter(order -> order.getStatus().equals(OrderStatus.COMPLETE))
        .map(order -> new CommentDto(order.getComment(), order.getUser().getName()))
        .collect(Collectors.toList());

    List<Report> reports = reportRepos.findAll();

    model.addAttribute("reports", reports);
    model.addAttribute("commentDto", commentDto);

    return "/manager";
  }
}
