/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingGui;

import com.thomasp.b1662096.ica.element2.ASCStockItem;
import com.thomasp.b1662096.ica.element2.MSMStockItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.SpinnerListModel;

/**
 * @author Thomas P
 */
public class StockCollectionSpinnerModel extends SpinnerListModel {


  // Constructor method for SpinnerModel containing a List of ASCStockItem
  public StockCollectionSpinnerModel(List<ASCStockItem> ascStockCollection) {
    super(createEntries(ascStockCollection));
  }

  private static String[] createEntries(List<ASCStockItem> ascStockCollection) {

    ascStockCollection = ASCStockItem.readASCStockItemCSV();

    String[] stockItemNames = new String[ascStockCollection.size()];

    for (int stockIdx = 0; stockIdx < ascStockCollection.size(); stockIdx++) {
      final ASCStockItem ascStockItem = ascStockCollection.get(stockIdx);
      stockItemNames[stockIdx] = ascStockItem.getProductTitle();
    }

    return stockItemNames;
  }

}
