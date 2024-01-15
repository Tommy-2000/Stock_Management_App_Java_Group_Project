package com.thomasp.b1662096.ica.element2;

public class PurchasingDepartment implements StockListener, StockNotification {


  private String productCode;
  private int quantityInStock;
  private ASCStockItem ascStockItem;

  public PurchasingDepartment(ASCStockItem ascStockItem) {
    ascStockItem.addStockItemListener(this);
  }


  @Override
  public void updateStockQuantity(String productCode, int quantityInStock) {
    this.productCode = productCode;
    this.quantityInStock = quantityInStock;
  }

  @Override
  public String stockNotification() {
    if (productCode == null) {
      return "No Stock Notifications";
    } else {
      System.out.println(String.format("%s is low and only %d remains", productCode, quantityInStock));
      return "Low Stock Notification";
    }
  }
}
