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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Thomas P
 */
public class ASCStockItem implements StockItemManager, StockListener {

  //
  // Initial variables for each stock item
  //
  private String productCode, productTitle, productDescription;
  private int unitPricePounds;
  private int unitPricePence;
  private int quantityInStock;

  // ArrayList containing ASCStockItem objects
  private final List<ASCStockItem> ascStockCollection = new ArrayList<>();

  // ArrayList containing StockListeners
  public List<StockListener> stockListeners = new ArrayList<>();

  //
  // Constructor method for ASCStockItem
  //
  public ASCStockItem(String productCode, String productTitle, String productDescription,
                      int unitPricePounds, int unitPricePence, int quantityInStock) {
    this.productCode = productCode;
    this.productTitle = productTitle;
    this.productDescription = productDescription;
    this.unitPricePounds = unitPricePounds;
    this.unitPricePence = unitPricePence;
    this.quantityInStock = quantityInStock;
  }

  public String getProductCode() {
    return productCode;
  }

  public String getProductTitle() {
    return productTitle;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public int getUnitPricePounds() {
    return unitPricePounds;
  }

  public int getUnitPricePence() {
    return unitPricePence;
  }

  public String getHumanFriendlyUnitPrice() {
    final int pounds = getUnitPricePounds();
    final int pence = getUnitPricePence();
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

  public void sellStockFromCollection(ASCStockItem ascStockItem) {
    ascStockCollection.remove(ascStockItem);
    isSold();
    notifyStockItemListeners();
  }


  @Override
  public String toString() {
    return String.format(
        "PRODUCT CODE: %s - PRODUCT TITLE: %s - PRODUCT DESCRIPTION: %s - UNIT PRICE: £%s %s - QUANTITY IN STOCK: %d",
        getProductCode(), getProductTitle(), getProductDescription(), getUnitPricePounds(),
        getUnitPricePence(), getQuantityInStock());
  }

  private static ASCStockItem findASCStockInList(String productCode,
                                                 ArrayList<ASCStockItem> ascStockCollection) {
    for (ASCStockItem stockItem : ascStockCollection) {
      if (stockItem.getProductCode().equals(productCode)) {
        return stockItem;
      }
    }
    System.out.println("Product - " + productCode + " cannot be not found in stock");
    return null;
  }

  public static ArrayList<ASCStockItem> readASCStockItemCSV() {
    ArrayList<ASCStockItem> ascStockCollection = new ArrayList<>();

    try (final CSVReader csvReader = new CSVReader(
        new FileReader("./data/AshersSportsCollective.csv"))) {
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
        ASCStockItem csvStockItem = new ASCStockItem(csvColumns[0], csvColumns[1], csvColumns[2],
            Integer.parseInt(csvColumns[3]), Integer.parseInt(csvColumns[4]),
            Integer.parseInt(csvColumns[5]));
        ascStockCollection.add(csvStockItem);
      }
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException: Invalid input string");
    } catch (IOException | CsvException e) {
      throw new RuntimeException(e);
    }
    return ascStockCollection;
  }


  public ArrayList<ASCStockItem> updateASCStockItemCSV(ArrayList<ASCStockItem> ascStockCollection) {

    ArrayList<ASCStockItem> csvReaderList = readASCStockItemCSV();

    try (final CSVWriter csvWriter = new CSVWriter(
        new FileWriter("./data/MengdasSportyMart.csv"))) {
      String[] ascColumns = new String[csvReaderList.size()];
      ascColumns = csvReaderList.toArray(ascColumns);
      while (ascColumns != null) {

        for (String ascStockItem : ascColumns) {
          System.out.println("DEBUG Raw Data: " + ascStockItem);
        }

        if (ascColumns.length < 4) {
          System.err.println("Insufficient Stock Item Data.");
          continue;
        }
        csvWriter.writeNext(ascColumns);
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
