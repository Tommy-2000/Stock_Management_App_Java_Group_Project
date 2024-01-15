/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package swingGui;


import com.thomasp.b1662096.ica.element2.ASCStockItem;
import com.thomasp.b1662096.ica.element2.MSMStockItem;
import com.thomasp.b1662096.ica.element2.MSMStockItemAdapter;
import com.thomasp.b1662096.ica.element2.PurchasingDepartment;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Thomas P
 */
public class MainJFrame extends javax.swing.JFrame {

  private List<ASCStockItem> ascStockCollection = new ArrayList<>();

  private List<MSMStockItem> msmStockCollection = new ArrayList<>();
  private List<ASCStockItem> convertedStockCollection = new ArrayList<>();
  protected List<ASCStockItem> filteredStockCollection;

  private static JFrame mainJFrame;
  private JPanel contentJPanel;
  private JButton clearFilterJButton;
  private JLabel filterJLabel;
  private JSpinner filterJSpinner;
  private JScrollPane tableJScrollPane;
  private JTable stockCollectionJTable;
  private JButton sellJButton;

  public MainJFrame() {
    init();
  }

  public void init() {
    // Initialise the mainJFrame once the program runs
    JFrame mainJFrame = new JFrame();
    // Add the content panel to the mainJFrame
    mainJFrame.add(contentJPanel);
    // Set the default look and feel of the mainJFrame
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    JFrame.setDefaultLookAndFeelDecorated(true);
    mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Show the mainJFrame
    mainJFrame.setVisible(true);

    // Initialise the readASCStockItemCSV method for each List
    ascStockCollection = ASCStockItem.readASCStockItemCSV();
    // Initialise the readMSMStockItemCSV method for each List
    msmStockCollection = MSMStockItem.readMSMStockItemCSV();

    // Convert the MSMStockItems in the collection into ASCStockItems
    // Use the adapter to convert and add the MSMStockItems in the ArrayList into ASCStockItems
    MSMStockItem msmStockItem = msmStockCollection.get(0);
    MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
    convertedStockCollection = msmStockItemAdapter.convertStockItems(ascStockCollection);

    // Initialise the PurchasingDepartment to check stock quantity on the full collection of stock
    PurchasingDepartment purchasingDepartment = new PurchasingDepartment(
        convertedStockCollection.get(0));

    // Bind the filteredStockCollection method to the List
    this.filteredStockCollection = filteredStockCollection();

    // stockCollection contains instances of ASCStockItem and converted MSMStockItem instances

    // Set the Model for the jTable using the stockCollectionTableModel
    StockCollectionTableModel stockCollectionTableModel = new StockCollectionTableModel(
        convertedStockCollection);
    stockCollectionJTable.setModel(stockCollectionTableModel);

    // Set the TableRowSorter using the stockCollectionTableModel
    TableRowSorter<TableModel> stockCollectionSorter = new TableRowSorter<>(
        stockCollectionTableModel);
    stockCollectionJTable.setRowSorter(stockCollectionSorter);

    // Set the Model for the jSpinner using the StockCollectionSpinnerModel
    StockCollectionSpinnerModel stockCollectionSpinnerModel = new StockCollectionSpinnerModel(
        convertedStockCollection);
    filterJSpinner.setModel(stockCollectionSpinnerModel);
    filterJSpinner.setMinimumSize(filterJSpinner.getPreferredSize());
    // If the list is filtered, update the UI
    filterJSpinner.updateUI();

    // Filter JSpinner Change Listener
    filterJSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent evt) {

        final String productName = ((String) filterJSpinner.getValue()).substring(0, 15);

        System.out.println("==> " + productName);

        stockCollectionSorter.setRowFilter(RowFilter.regexFilter(productName));
      }
    });

    // Set the clear filter button
    clearFilterJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg) {
        stockCollectionSorter.setRowFilter(null);
        stockCollectionJTable.updateUI();
      }
    });

    // Set the sell item button
    sellJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg) {
        int selectedItem = stockCollectionJTable.getSelectedRow();

        if (selectedItem != -1) {
          ASCStockItem stockItem = convertedStockCollection.get(selectedItem);

          // Pass the converted item through the purchasing department
          PurchasingDepartment purchasingDepartment = new PurchasingDepartment(stockItem);

          // Get the current stock quantity available
          int currentQuantity = stockItem.getQuantityInStock();

          try {
            // Set the quantity of stock that is sold
            int soldQuantity = Integer.parseInt(
                JOptionPane.showInputDialog(MainJFrame.this, "Only " + currentQuantity
                        + " items left. \n Enter the number of stock you wish to sell",
                    "Sell " + stockItem.getProductCode(), JOptionPane.PLAIN_MESSAGE));

            if (soldQuantity <= currentQuantity) {
              int remainderQuantity = currentQuantity - soldQuantity;

              stockItem.setQuantityInStock(remainderQuantity);

              // Whenever the quantityInStock is low, show the JDialog
              if (purchasingDepartment.stockNotification().equals("Low Stock Notification")) {
                // Show the message of the JDialog if stock in convertedStockCollection is low
                JOptionPane.showMessageDialog(MainJFrame.this, msmStockItemAdapter.getProductCode(),
                    " Quantity Left: " + msmStockItemAdapter.getQuantityInStock(),
                    JOptionPane.WARNING_MESSAGE);
              }

              // ALWAYS UPDATE THE UI BASED ON ANY CHANGES MADE
              stockCollectionJTable.updateUI();

            }
          } catch (NumberFormatException | HeadlessException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
    });

  }

  private List<ASCStockItem> filteredStockCollection() {
    List<ASCStockItem> filteredStockCollection = new ArrayList<>();
    for (ASCStockItem stockItem : convertedStockCollection) {
      if (stockItem.getQuantityInStock() >= 1) {
        filteredStockCollection.add(stockItem);
      } else {
        System.err.println("No stock available for item in stock collection");
      }
    }
    return filteredStockCollection;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Create and display the form */
    new MainJFrame();
  }

}
