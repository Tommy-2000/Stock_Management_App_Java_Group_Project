package swingGui;

import com.thomasp.b1662096.ica.element2.ASCStockItem;
import com.thomasp.b1662096.ica.element2.MSMStockItem;

import com.thomasp.b1662096.ica.element2.MSMStockItemAdapter;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class LowStockAlertJDialog extends JDialog {
    private JPanel contentJPanel;

    private JPanel buttonPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel contentPane;

    // Retrieve ArrayList from the read CSV file from each class
    private List<ASCStockItem> ascStockCollection = ASCStockItem.readASCStockItemCSV();

    private List<MSMStockItem> msmStockCollection = MSMStockItem.readMSMStockItemCSV();

    private List<ASCStockItem> convertedStockCollection = new ArrayList<>();


    public LowStockAlertJDialog() {
        getRootPane().setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Convert the MSMStockItems in the collection into ASCStockItems
        // Use the adapter to convert and add the MSMStockItems in the ArrayList into ASCStockItems
        MSMStockItem msmStockItem = msmStockCollection.get(0);
        MSMStockItemAdapter msmStockItemAdapter = new MSMStockItemAdapter(msmStockItem);
        convertedStockCollection = msmStockItemAdapter.convertStockItems(ascStockCollection);




        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentJPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        LowStockAlertJDialog dialog = new LowStockAlertJDialog();
        dialog.pack();
        System.exit(0);
    }
}
