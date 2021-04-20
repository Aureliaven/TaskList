import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;


import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TaskList {

  static int selectedRow = -1;

public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(1400, 800);
    String data[][]={{"Computer Science Project","Use Java Swing to create a task list","4/20/21"},
    {"Laundry","Remember to separate colors and whites","4/22/21"},
    {"Coffee date","Wear a mask","4/25/21"}};
    String column[]={"TITLE","DETAILS","DUE DATE"};
    DefaultTableModel model = new DefaultTableModel(data, column);
    JTable table = new JTable(model);
    table.setPreferredSize(new Dimension(800,800));
    table.setAutoCreateRowSorter(true);
    JScrollPane pane = new JScrollPane(table);
    pane.setPreferredSize(new Dimension(800,800));
    frame.getContentPane().add(pane, BorderLayout.CENTER);
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(400,800));
    JTextArea field1 = new JTextArea();
    field1.setPreferredSize(new Dimension(300, 100));
    field1.setFont(new Font("Calibri", Font.PLAIN, 18));
    JTextArea field2 = new JTextArea();
    field2.setPreferredSize(new Dimension(300, 300));
    field2.setFont(new Font("Calibri", Font.PLAIN, 18));
    JTextArea field3 = new JTextArea();
    field3.setPreferredSize(new Dimension(300, 100));
    field3.setFont(new Font("Calibri", Font.PLAIN, 18));
    JButton add = new JButton("Add");
    add.setPreferredSize(new Dimension(100,60));
    add.setFont(new Font("Calibri", Font.PLAIN, 18));
    JButton edit = new JButton("Edit");
    edit.setPreferredSize(new Dimension(100,60));
    edit.setFont(new Font("Calibri", Font.PLAIN, 18));

    panel.add(field1);
    panel.add(field2);
    panel.add(field3);
    panel.add(add);
    panel.add(edit);

    frame.getContentPane().add(panel, BorderLayout.EAST);

    add.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          model.addRow(new Object[]{field1.getText(), field2.getText(), field3.getText()});
        }
    });
    edit.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRow == -1) {
              return;
            } else {
              model.setValueAt(field1.getText(), selectedRow, 0);
              model.setValueAt(field2.getText(), selectedRow, 1);
              model.setValueAt(field3.getText(), selectedRow, 2);
            }
        }
    });
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent event) {
      selectedRow = table.getSelectedRow();
      field1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
      field2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
      field3.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
    }
    });

    table.getTableHeader().addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      int col = table.columnAtPoint(e.getPoint());
      String name = table.getColumnName(col);
      System.out.println("Column index selected " + col + " " + name);
    }
    });

    frame.pack();
    frame.setVisible(true);
}

}
