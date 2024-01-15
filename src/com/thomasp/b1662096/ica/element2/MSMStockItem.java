/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thomasp.b1662096.ica.element2;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas P
 */
public class MSMStockItem implements StockItemManager, StockListener {

  //
  // Initial variables for each stock item
  //
  private String productDepartmentID;
  private String productCode, productTitleAndDescription;
  private int unitPrice;
  private int quantityInStock;

  // ArrayList containing MSMStockItem objects
  private static final List<MSMStockItem> msmStockCollection = new ArrayList<>();

  // ArrayList containing StockListeners
  public List<StockListener> stockListeners = new ArrayList<>();

  public MSMStockItem(String productDepartmentID, String productCode,
                      String productTitleAndDescription, int unitPrice, int quantityInStock) {
    this.productDepartmentID = productDepartmentID;
    this.productCode = productCode;
    this.productTitleAndDescription = productTitleAndDescription;
    this.unitPrice = unitPrice;
    this.quantityInStock = quantityInStock;
  }

  public String getProductDepartmentID() {
    return productDepartmentID;
  }

  public String getProductCode() {
    return productCode;
  }

  public String getProductTitle() {
    return productTitleAndDescription.substring(0, 59).replace("\u00a0", "").stripTrailing();
  }


  public String getProductDescription() {
    return productTitleAndDescription.substring(60, productTitleAndDescription.length());
  }

  public int getUnitPricePounds() {
    return this.unitPrice / 100;
  }

  public int getUnitPricePence() {
    return this.unitPrice % 100;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  public String getHumanFriendlyUnitPrice() {
    final int pounds = getUnitPrice() / 100;
    final int pence = getUnitPrice() % 100;
    return String.format("%d%02d", pounds, pence);
  }

  public int getQuantityInStock() {
    return quantityInStock;
  }

  public void setQuantityInStock(int updatedQuantity) {
    this.quantityInStock = updatedQuantity;
    checkStockQuantity();
  }

  public boolean isSold() {
    if (!productCode.isEmpty()) {
      return true;
    }
    return false;
  }

  public void checkStockQuantity() {
    if (quantityInStock < 5) {
      notifyStockItemListeners();
    }
  }

  public void sellStockFromCollection(MSMStockItem msmStockItem) {
    msmStockCollection.remove(msmStockItem);
    isSold();
  }


  @Override
  public String toString() {
    return String.format("%s - %s - %s - %s - UNIT PRICE: £%s - QTY: %d",
        getProductDepartmentID(),
        getProductCode(),
        getProductTitle(),
        getProductDescription(),
        getHumanFriendlyUnitPrice(),
        getQuantityInStock());
  }

  private static MSMStockItem findMSMStockInList(String productCode,
                                                 ArrayList<MSMStockItem> msmStockCollection) {
    for (MSMStockItem stockItem : msmStockCollection) {
      if (stockItem.getProductCode().equals(productCode)) {
        return stockItem;
      }
    }
    System.out.println("Product - " + productCode + " cannot be not found in stock");
    return null;
  }

  public static ArrayList<MSMStockItem> readMSMStockItemCSV() {
    ArrayList<MSMStockItem> msmStockCollection = new ArrayList<>();

    try (final CSVReader csvReader = new CSVReader(
        new FileReader("./data/MengdasSportyMart.csv"))) {
      String[] csvColumns;
      while ((csvColumns = csvReader.readNext()) != null) {

        for (String cell : csvColumns) {
          System.out.println("DEBUG Raw Data: " + cell);
        }

        if (csvColumns.length < 4) {
          System.err.println("Insufficient Stock Item Data.");
          continue;
        }

        // Initalise a new stock item to be read from the CSV and added to an ArrayList
        MSMStockItem csvStockItem = new MSMStockItem(
            csvColumns[0],
            csvColumns[1],
            csvColumns[2],
            Integer.parseInt(csvColumns[3]),
            Integer.parseInt(csvColumns[4]));
        msmStockCollection.add(csvStockItem);
      }
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException: Invalid input string");
    } catch (IOException | CsvException e) {
      throw new RuntimeException(e);
    }
    return msmStockCollection;
  }

  public ArrayList<MSMStockItem> updateMSMStockItemCSV(ArrayList<MSMStockItem> msmStockCollection) {

    ArrayList<MSMStockItem> csvReaderList = readMSMStockItemCSV();

    try (final CSVWriter csvWriter = new CSVWriter(
        new FileWriter("./data/AshersSportsCollective.csv"))) {
      String[] msmColumns = new String[csvReaderList.size()];
      msmColumns = csvReaderList.toArray(msmColumns);
      while (msmColumns != null) {

        for (String ascStockItem : msmColumns) {
          System.out.println("DEBUG Raw Data: " + ascStockItem);
        }

        if (msmColumns.length < 4) {
          System.err.println("Insufficient Stock Item Data.");
          continue;
        }
        csvWriter.writeNext(msmColumns);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return csvReaderList;
  }

  @Override
  public void addStockItemListener(StockListener stockListener) {
    stockListeners.add(stockListener);
  }

  @Override
  public void removeStockItemListener(StockListener stockListener) {
    stockListeners.remove(stockListener);
  }

  @Override
  public void notifyStockItemListeners() {
    for (StockListener stockListener : stockListeners) {
      stockListener.updateStockQuantity(productCode, quantityInStock);
    }
  }

  @Override
  public void updateStockQuantity(String updatedProductCode, int updatedStockQuantity) {
    this.productCode = updatedProductCode;
    this.quantityInStock = updatedStockQuantity;
    checkStockQuantity();
  }

}
