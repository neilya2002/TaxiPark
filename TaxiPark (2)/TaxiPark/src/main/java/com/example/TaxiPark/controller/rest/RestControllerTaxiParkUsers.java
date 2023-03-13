package com.example.TaxiPark.controller.rest;

import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.model.entyti.UserRole;
import com.example.TaxiPark.repository.UserRepos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("users")
public class RestControllerTaxiParkUsers {

  private final UserRepos userRepos;

  public RestControllerTaxiParkUsers(UserRepos userRepos) {
    this.userRepos = userRepos;
  }

  @GetMapping("/all")
  public List<User> getSomething() {
    return userRepos.findAll();
  }

  @GetMapping("/{id}")
  public User getSomething(@PathVariable Long id) {
    return userRepos.findById(id).get();
  }

  @GetMapping("/role/{role}")
  public List<User> getUserByRole(@PathVariable String role) {
    return userRepos.findAllByRole(UserRole.valueOf(role));
  }

  @GetMapping("address/{userId}")
  public String isUserHaveHomeAddress(@PathVariable Long userId) {
    return userRepos.findById(userId).get().getHomeAddress();
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
        .header("Content-Disposition", "attachment; filename=\"123.txt\"")
        .contentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE))
        .body(new InputStreamResource(targetStream));
  }

  @GetMapping("/change-address/{userId}")
  public String changeAddress(@PathVariable Long userId
      , @RequestParam String newAddress
  ) {
    userRepos.setHomeAddressById(newAddress, userId);
    return "OK";
  }
}
