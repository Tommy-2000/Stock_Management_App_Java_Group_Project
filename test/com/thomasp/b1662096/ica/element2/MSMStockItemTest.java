package com.thomasp.b1662096.ica.element2;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MSMStockItemTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("Should Retrieve Product Department ID From Instance of MSMStockItem")
  void getProductDepartmentID() {
    try {
      System.out.println("getProductDepartmentID");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      String expResult = String.valueOf(2);
      String result = msmStockItem.getProductDepartmentID();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductDepartmentID failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Code From Instance of MSMStockItem")
  void getProductCode() {
    try {
      System.out.println("getProductCode");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      String expResult = "RUN1234567";
      String result = msmStockItem.getProductCode();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductCode failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Title From Instance of MSMStockItem")
  void getProductTitle() {
    try {
      System.out.println("getProductTitle");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      String expResult = "Cycle4ever Cycling jacket";
      String result = msmStockItem.getProductTitle();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductTitle failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Description From Instance of MSMStockItem")
  void getProductDescription() {
    try {
      System.out.println("getProductDescription");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      String expResult = "Super Hi-tech goggles for the professional and extreme environment swimmer";
      String result = msmStockItem.getProductDescription();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductDescription failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pounds From Instance of MSMStockItem")
  void getUnitPricePounds() {
    try {
      System.out.println("getUnitPricePounds");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      int expResult = 10;
      int result = msmStockItem.getUnitPricePounds();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePounds failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pence From Instance of MSMStockItem")
  void getUnitPricePence() {
    try {
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      int expResult = 0;
      int result = msmStockItem.getUnitPricePence();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePence failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Quantity In Stock From Instance of MSMStockItem")
  void getQuantityInStock() {
    try {
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      int expResult = 3;
      int result = msmStockItem.getQuantityInStock();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getQuantityInStock failed");
    }
  }


  @Test
  @DisplayName("Should Run checkStockQuantity Method From Instance of MSMStockItem")
  void checkStockQuantity() {
    try {
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      msmStockItem.checkStockQuantity();
    } catch (Exception e) {
      fail("Test method checkStockQuantity failed");
    }
  }
}