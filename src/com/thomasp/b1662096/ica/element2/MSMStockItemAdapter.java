/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thomasp.b1662096.ica.element2;

import java.util.List;

/**
 * @author Thomas P
 */
public class MSMStockItemAdapter extends ASCStockItem {

  private final MSMStockItem adapteeStockItem;

  public MSMStockItemAdapter(MSMStockItem adapteeStockItem) {
    super(adapteeStockItem.getProductCode(), adapteeStockItem.getProductTitle(),
        adapteeStockItem.getProductDescription(), adapteeStockItem.getUnitPricePounds(),
        adapteeStockItem.getUnitPricePence(), adapteeStockItem.getQuantityInStock());
    this.adapteeStockItem = adapteeStockItem;
  }

  @Override
  public String getProductCode() {
    return adapteeStockItem.getProductCode();
  }


  @Override
  public String getProductTitle() {
    return adapteeStockItem.getProductTitle();
  }

  @Override
  public String getProductDescription() {
    return adapteeStockItem.getProductDescription();
  }

  @Override
  public int getUnitPricePounds() {
    return adapteeStockItem.getUnitPrice() / 100;
  }

  @Override
  public int getUnitPricePence() {
    return adapteeStockItem.getUnitPrice() % 100;
  }

  @Override
  public int getQuantityInStock() {
    return adapteeStockItem.getQuantityInStock();
  }

  public static List<ASCStockItem> convertStockItems(List<ASCStockItem> ascStockCollection) {
    final List<MSMStockItem> msmStockItemCSVCollection = MSMStockItem.readMSMStockItemCSV();

    for (MSMStockItem msmStockItem : msmStockItemCSVCollection) {

      MSMStockItemAdapter MSMStockItemAdapter = new MSMStockItemAdapter(msmStockItem);

      ASCStockItem adaptedStockItem = new ASCStockItem(MSMStockItemAdapter.getProductCode(),
          MSMStockItemAdapter.getProductTitle(), MSMStockItemAdapter.getProductDescription(),
          MSMStockItemAdapter.getUnitPricePounds(), MSMStockItemAdapter.getUnitPricePence(),
          MSMStockItemAdapter.getQuantityInStock());
      ascStockCollection.add(adaptedStockItem);
    }
    System.out.println("Successfully adapted MSMStockItem to ASCStockItem.");
    return ascStockCollection;
  }
}
