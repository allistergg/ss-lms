package com.smoothstack.borrower.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_borrower")
public class Borrower implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cardNo")
  private Integer cardNo;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 40)
  private String address;

  @Column(nullable = false, length = 20)
  private Long phone;
  
  @OneToMany(mappedBy = "borrowerid.cardNo", fetch = FetchType.LAZY)
  @JsonIgnoreProperties("borrower")
  private List<Loans> loans;

  /**
   * @return the cardNo
   */
  public Integer getCardNo() {
    return cardNo;
  }

  /**
   * @param cardNo the cardNo to set
   */
  public void setCardNo(Integer cardNo) {
    this.cardNo = cardNo;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return the phone
   */
  public Long getPhone() {
    return phone;
  }

  /**
   * @param phone the phone to set
   */
  public void setPhone(Long phone) {
    this.phone = phone;
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, cardNo, name, phone);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Borrower other = (Borrower) obj;
    return Objects.equals(address, other.address) && Objects.equals(cardNo, other.cardNo)
        && Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
  }


  @Override
  public String toString() {
    return "Borrower [cardNo=" + cardNo + ", name=" + name + ", address=" + address + ", phone="
        + phone + "]";
  }

  /**
   * @param cardNo
   * @param name
   * @param address
   * @param phone
   */
  public Borrower(Integer cardNo, String name, String address, Long phone) {
    super();
    this.cardNo = cardNo;
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  /**
   * 
   */
  public Borrower() {
    super();
    // TODO Auto-generated constructor stub
  }

}
