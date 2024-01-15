/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.thomasp.b1662096.ica.element2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thomas P
 */
public class Main {

  /**
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException {
    // TODO code application logic here

    // Read the stock items from the CSV file once the application starts
    ArrayList<ASCStockItem> ascStockCollection = ASCStockItem.readASCStockItemCSV();
    ArrayList<MSMStockItem> msmStockCollection = MSMStockItem.readMSMStockItemCSV();

    // When running the application, print all the available stock items
    System.out.println("----");
    System.out.println("Asher's Sport Collective - Available Stock");
    System.out.println("----");
    for (ASCStockItem ascStockItem : ascStockCollection) {
      System.out.println("Product Code: " + ascStockItem.getProductCode());
      System.out.println("Product Title: " + ascStockItem.getProductTitle());
      System.out.println("Product Description: " + ascStockItem.getProductDescription());
      System.out.println("Unit Price (Pounds): " + ascStockItem.getUnitPricePounds());
      System.out.println(
          "Unit Price (Pence): " + Double.toString(ascStockItem.getUnitPricePence()));
      System.out.println("Quantity In Stock: " + ascStockItem.getQuantityInStock());
    }

    System.out.println("----");
    System.out.println("Mengdas Sporty Mart - Available Stock");
    System.out.println("----");
    for (MSMStockItem msmStockItem : msmStockCollection) {
      System.out.println("Product Department ID: " + msmStockItem.getProductDepartmentID());
      System.out.println("Product Code: " + msmStockItem.getProductCode());
      System.out.println("Product Title: " + msmStockItem.getProductTitle());
      System.out.println("Product Description: " + msmStockItem.getProductDescription());
      System.out.println("Unit Price: " + msmStockItem.getHumanFriendlyUnitPrice());
      System.out.println("Quantity In Stock: " + msmStockItem.getQuantityInStock());
    }

    writeSalesTransactionsFile();

  }

  public static void writeSalesTransactionsFile() throws IOException {

    // Read the stock items from the CSV files once this method starts
    ArrayList<ASCStockItem> ascStockCollection = ASCStockItem.readASCStockItemCSV();
    ArrayList<MSMStockItem> msmStockCollection = MSMStockItem.readMSMStockItemCSV();

    // Find the absolute file path where the written file will be
    File absoluteFilePath = new File("./data/sales_transactions.txt");

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(absoluteFilePath, true))) {
      absoluteFilePath.getAbsolutePath();

      String[] ascStringWritingArray = new String[ascStockCollection.size()];
      for (ASCStockItem ascStockItem : ascStockCollection) {
        ascStockItem.sellStockFromCollection(ascStockItem);
        ascStringWritingArray[0] = ascStockItem.getProductCode();
        ascStringWritingArray[1] = String.valueOf(ascStockItem.getQuantityInStock());
        ascStringWritingArray[2] = ascStockItem.getHumanFriendlyUnitPrice();
      }

      String[] msmStringWritingArray = new String[msmStockCollection.size()];
      for (MSMStockItem msmStockItem : msmStockCollection) {
        msmStockItem.sellStockFromCollection(msmStockItem);
        msmStringWritingArray[0] = msmStockItem.getProductCode();
        msmStringWritingArray[1] = String.valueOf(msmStockItem.getQuantityInStock());
        msmStringWritingArray[2] = msmStockItem.getHumanFriendlyUnitPrice();
      }

      LocalDateTime currentDateTime = LocalDateTime.now();
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
      String formattedDateTime = currentDateTime.format(dateTimeFormatter);

      String ascProductCode = ascStringWritingArray[0];
      int ascQuantitySold = Integer.parseInt(ascStringWritingArray[1]);
      int ascUnitPrice = Integer.valueOf(ascStringWritingArray[2]);
      String msmProductCode = msmStringWritingArray[0];
      int msmQuantitySold = Integer.valueOf(msmStringWritingArray[1]);
      int msmUnitPrice = Integer.valueOf(msmStringWritingArray[2]);

      String ascFormattedString =
          "Current Date and Time: " + formattedDateTime + "\n" + "Product Code: " + ascProductCode
              + "\n" + "Quantity Sold: " + ascQuantitySold + "\n" + "Unit Price: " + ascUnitPrice
              + "\n";

      String msmFormattedString =
          "Current Date and Time: " + formattedDateTime + "\n" + "Product Code: " + msmProductCode
              + "\n" + "Quantity Sold: " + msmQuantitySold + "\n" + "Unit Price: " + msmUnitPrice
              + "\n";
      bufferedWriter.write(ascFormattedString);
      bufferedWriter.write(msmFormattedString);
    } catch (ArrayStoreException arrayStoreException) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, arrayStoreException);
      System.out.println(
          "Element Type Mismatch: Can not cast one of the elements of Object[] to the destination array, String[]");
      System.exit(1);
    } catch (FileNotFoundException fileException) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, fileException);
      System.out.println("File doesn't exist");
      System.exit(1);
    }
  }
}
