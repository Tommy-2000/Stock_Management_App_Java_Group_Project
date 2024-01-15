/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.thomasp.b1662096.ica.element2;

/**
 * @author Thomas P
 */
interface StockListener {

  void updateStockQuantity(String productCode, int quantityInStock);

}

interface StockItemManager {
  void addStockItemListener(StockListener stockListener);

  void removeStockItemListener(StockListener stockListener);

  void notifyStockItemListeners();
}

interface StockNotification {
  String stockNotification();
}

