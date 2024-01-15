package com.thomasp.b1662096.ica.element2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ASCStockItemTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("Should Retrieve Product Code From Instance of ASCStockItem")
  void getProductCode() {
    try {
      System.out.println("getProductCode");
      ASCStockItem ascStockItem = new ASCStockItem("RUN1234567", "Run-Tech Running shorts",
          "High-quality running shorts", 10, 0, 10);
      String expResult = "RUN1234567";
      String result = ascStockItem.getProductCode();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductCode failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Title From Instance of ASCStockItem")
  void getProductTitle() {
    try {
      System.out.println("getProductTitle");
      ASCStockItem ascStockItem = new ASCStockItem("CYC1111111", "Cycle4ever Cycling jacket",
          "Super lightweight hi-vis jacket for the extreme cyclist", 50, 0, 20);
      String expResult = "Cycle4ever Cycling jacket";
      String result = ascStockItem.getProductTitle();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductTitle failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Description From Instance of ASCStockItem")
  void getProductDescription() {
    try {
      System.out.println("getProductDescription");
      ASCStockItem ascStockItem = new ASCStockItem("SWM2222222", "4Oceans Goggles",
          "Super Hi-tech goggles for the professional and extreme environment swimmer", 25, 15, 3);
      String expResult = "Super Hi-tech goggles for the professional and extreme environment swimmer";
      String result = ascStockItem.getProductDescription();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductDescription failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pounds From Instance of ASCStockItem")
  void getUnitPricePounds() {
    try {
      System.out.println("getUnitPricePounds");
      ASCStockItem ascStockItem = new ASCStockItem("RUN1234567", "Run-Tech Running shorts",
          "High-quality running shorts", 10, 0, 10);
      int expResult = 10;
      int result = ascStockItem.getUnitPricePounds();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePounds failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pence From Instance of ASCStockItem")
  void getUnitPricePence() {
    try {
      System.out.println("getUnitPricePence");
      ASCStockItem ascStockItem = new ASCStockItem("CYC1111111", "Cycle4ever Cycling jacket",
          "Super lightweight hi-vis jacket for the extreme cyclist", 50, 0, 20);
      int expResult = 0;
      int result = ascStockItem.getUnitPricePence();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePence failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Quantity In Stock From Instance of ASCStockItem")
  void getQuantityInStock() {
    try {
      System.out.println("getQuantityInStock");
      ASCStockItem ascStockItem = new ASCStockItem("SWM2222222", "4Oceans Goggles",
          "Super Hi-tech goggles for the professional and extreme environment swimmer", 25, 15, 3);
      int expResult = 3;
      int result = ascStockItem.getQuantityInStock();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getQuantityInStock failed");
    }
  }


  @Test
  void checkStockQuantity() {
    try {
    System.out.println("checkStockQuantity");
      ASCStockItem ascStockItem = new ASCStockItem("SWM2222222", "4Oceans Goggles",
          "Super Hi-tech goggles for the professional and extreme environment swimmer", 25, 15, 3);
      ascStockItem.checkStockQuantity();
    } catch (Exception e) {
      fail("Test method checkStockQuantity failed");
    }
  }

}