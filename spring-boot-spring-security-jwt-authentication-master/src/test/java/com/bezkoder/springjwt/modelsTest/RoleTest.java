package com.bezkoder.springjwt.modelsTest;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERoleTest name;

  public RoleTest() {

  }

  public RoleTest(ERoleTest name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ERoleTest getName() {
    return name;
  }

  public void setName(ERoleTest name) {
    this.name = name;
  }
}