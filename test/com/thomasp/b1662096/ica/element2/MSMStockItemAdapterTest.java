package com.thomasp.b1662096.ica.element2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MSMStockItemAdapterTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }


  @Test
  @DisplayName("Should Retrieve Product Code From Instance of MSMStockItemAdapter")
  void getProductCode() {
    try {
      System.out.println("getProductCode");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      String expResult = "RUN1234567";
      String result = msmStockItemAdapter.getProductCode();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductCode failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Title From Instance of MSMStockItemAdapter")
  void getProductTitle() {
    try {
      System.out.println("getProductTitle");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      String expResult = "Cycle4ever Cycling jacket";
      String result = msmStockItemAdapter.getProductTitle();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductTitle failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Product Description From Instance of MSMStockItemAdapter")
  void getProductDescription() {
    try {
      System.out.println("getProductDescription");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      String expResult = "Super Hi-tech goggles for the professional and extreme environment swimmer";
      String result = msmStockItemAdapter.getProductDescription();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getProductDescription failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pounds From Instance of MSMStockItemAdapter")
  void getUnitPricePounds() {
    try {
      System.out.println("getUnitPricePounds");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      int expResult = 10;
      int result = msmStockItemAdapter.getUnitPricePounds();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePounds failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Unit Price Pence From Instance of MSMStockItemAdapter")
  void getUnitPricePence() {
    try {
      System.out.println("getUnitPricePence");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      int expResult = 99;
      int result = msmStockItemAdapter.getUnitPricePence();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getUnitPricePence failed");
    }
  }

  @Test
  @DisplayName("Should Retrieve Quantity In Stock From Instance of MSMStockItemAdapter")
  void getQuantityInStock() {
    try {
      System.out.println("getQuantityInStock");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      int expResult = 3;
      int result = msmStockItemAdapter.getQuantityInStock();
      assertEquals(expResult, result);
    } catch (Exception e) {
      fail("Test method getQuantityInStock failed");
    }
  }


  @Test
  void checkStockQuantity() {
    try {
      System.out.println("checkStockQuantity");
      MSMStockItem msmStockItem = new MSMStockItem(String.valueOf(2), "SWM-123456-MSM",
          "LakeTech Swimhat                    General purpose latex swimhat suitable for beginner and intermediate swimmers\n",
          1099, 10);
      MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
      msmStockItemAdapter.checkStockQuantity();
    } catch (Exception e) {
      fail("Test method checkStockQuantity failed");
    }
  }
}