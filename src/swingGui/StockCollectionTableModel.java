/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingGui;

import com.thomasp.b1662096.ica.element2.ASCStockItem;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thomas P
 */
public class StockCollectionTableModel extends AbstractTableModel
{

    protected List<ASCStockItem> convertedStockCollection;

    protected String[] columnNames = {"Product Code", "Product Title", "Product Description", "Unit Price (Pounds)", "Unit Price (Pence)", "Quantity In Stock"};



    public StockCollectionTableModel(List<ASCStockItem> convertedStockCollection)
    {
        this.convertedStockCollection = convertedStockCollection;
    }

    @Override
    public int getRowCount()
    {
        return convertedStockCollection.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ASCStockItem ascStockItem = convertedStockCollection.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return ascStockItem.getProductCode();
            }
            case 1 -> {
                return ascStockItem.getProductTitle();
            }
            case 2 -> {
                return ascStockItem.getProductDescription();
            }
            case 3 -> {
                return ascStockItem.getUnitPricePounds();
            }
            case 4 -> {
                return ascStockItem.getUnitPricePence();
            }
            case 5 -> {
                return ascStockItem.getQuantityInStock();
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

}
