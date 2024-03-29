/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javascrollableresultset;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author om4rezz
 */
public class NewRecordDialog extends JDialog {

    private JButton jbtOK = new JButton("OK");
    private JButton jbtCancel = new JButton("Cancel");

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable jTable1 = new JTable(tableModel);
    private Vector newRecord;

    /**
     * Creates new form NewRecordDialog
     */
    public NewRecordDialog(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Insert a New Record");
        setModal(true);

        JPanel jPanel1 = new JPanel();
        jPanel1.add(jbtOK);
        jPanel1.add(jbtCancel);

        jbtOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });
        jbtCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newRecord = null;
                setVisible(false);
            }
        });

        add(jPanel1, BorderLayout.SOUTH);
        add(new JScrollPane(jTable1), BorderLayout.CENTER);
    }

    public NewRecordDialog() {
        this(null, true);
    }

    public Vector getNewRecord() {
        return newRecord;
    }

    /**
     * Display the table
     */
    void displayTable(Vector columnHeaderVector) {
        this.setSize(new Dimension(400, 100));

        tableModel.setColumnIdentifiers(columnHeaderVector);

        // Must create a new vector for a new record
        tableModel.addRow(newRecord = new Vector());
        setVisible(true);
    }
}
