package com.min.edu.vo;

public class KakaoVo {
   
   private String name   ;
   private String email  ;
   private int price  ;
   private String phone  ;
   private String address;
   public KakaoVo() {
      super();
      // TODO Auto-generated constructor stub
   }
   public KakaoVo(String name, String email, int price, String phone, String address) {
      super();
      this.name = name;
      this.email = email;
      this.price = price;
      this.phone = phone;
      this.address = address;
   }
   @Override
   public String toString() {
      return "KakaoVo [name=" + name + ", email=" + email + ", price=" + price + ", phone=" + phone + ", address="
            + address + "]";
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getAddress() {
      return address;
   }
   public void setAddress(String address) {
      this.address = address;
   }
   
   
   
   
   

}