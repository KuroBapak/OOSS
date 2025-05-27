package model;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name, department, address, payCard, empType;
    private BigDecimal salary;

    public Employee(int id, String name, String department,
        BigDecimal salary, String address, String payCard, String empType) {
      this.id         = id;
      this.name       = name;
      this.department = department;
      this.salary     = salary;
      this.address    = address;
      this.payCard    = payCard;
      this.empType    = empType;
    }
    // getters/setters...
}
