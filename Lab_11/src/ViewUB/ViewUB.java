package ViewUB;

import ModelUB.FileSupport;
import ModelUB.UtilityBill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ViewUB extends JFrame {
    private JPanel mainPanel;

    private final int WIDTH = 900;
    private final int HEIGHT = 700;

    public ViewUB() throws HeadlessException {
        // Intro settings
        JFrame selfFrame=this;

        DefaultListModel<UtilityBill> listModel = new DefaultListModel<>();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //      ******STATUS *****
        Status status = new Status();

        //Creating menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveItem = new JMenuItem("Save");

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        JMenu sortMenu=new JMenu("Sort");
        JMenuItem ascendingOrder=new JMenuItem("In ascending order");
        JMenuItem descendingOrder=new JMenuItem("In descending order");
        sortMenu.add(ascendingOrder);
        sortMenu.add(descendingOrder);

        menuBar.add(fileMenu);
        menuBar.add(sortMenu);
        setJMenuBar(menuBar);

//      ************* LOAD ********************
        loadItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser();
            int result= fileChooser.showOpenDialog(selfFrame);

            if(result!=JFileChooser.APPROVE_OPTION)
                return;

            File file= fileChooser.getSelectedFile();

            String fileName=file.getName();
                FileSupport fileDat=new FileSupport(fileName);
                try {
                    ArrayList<UtilityBill> bills = fileDat.readFile();
                    for(UtilityBill bill:bills)
                        listModel.addElement(bill);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.addStatus("New bills were loaded from file: "+fileName);
            }
        });


//      ************* SAVE ********************
        saveItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                int result= fileChooser.showSaveDialog(selfFrame);

                if(result!=JFileChooser.APPROVE_OPTION)
                    return;

                File file= fileChooser.getSelectedFile();

                String fileName=file.getName();
                FileSupport fileDat=new FileSupport(fileName);
                try {
                   fileDat.writeAllToFile(listModel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.addStatus("All bills were saved to file: "+fileName);
            }
        });

        //      *****ASCENDING*******
        ascendingOrder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<UtilityBill>data=new ArrayList<>();
                for(int i=0;i<listModel.size();++i)
                    data.add(listModel.get(i));
                Collections.sort(data,((one, two) ->{return one.getHomeNumber()-two.getHomeNumber(); }));
            listModel.clear();
            for(UtilityBill bill:data)
                listModel.addElement(bill);

                status.addStatus("All bills were sorted in ascending order!");
            }
        });

        //      *****DESCENDING*******
        descendingOrder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<UtilityBill>data=new ArrayList<>();
                for(int i=0;i<listModel.size();++i)
                    data.add(listModel.get(i));
                Collections.sort(data,((one, two) ->{return two.getHomeNumber()-one.getHomeNumber(); }));
                listModel.clear();
                for(UtilityBill bill:data)
                    listModel.addElement(bill);

                status.addStatus("All bills were sorted in descending order!");
            }
        });


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        //Creating listPanel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JLabel listLabel = new JLabel("The list of utility bills: ");
        listPanel.add(listLabel);

        JList listView = new JList(listModel);
        listPanel.add(listView);

        mainPanel.add(listPanel, BorderLayout.NORTH);

        //Creating adding a new bill part
        JPanel billPanel = new JPanel();
        billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));


        JTextField homeNumberText = new JTextField();
        JLabel homeNumberLabel = new JLabel("Home Number:");
        billPanel.add(homeNumberLabel);
        billPanel.add(homeNumberText);


        JTextField flatNumberText = new JTextField();
        JLabel flatNumberLabel = new JLabel("Flat Number:");
        billPanel.add(flatNumberLabel);
        billPanel.add(flatNumberText);

        JTextField addressText = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        billPanel.add(addressLabel);
        billPanel.add(addressText);

        JTextField nameText = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        billPanel.add(nameLabel);
        billPanel.add(nameText);

        JTextField paymentDateText = new JTextField();
        JLabel paymentDateLabel = new JLabel("Payment date (\"dd.MM.yyyy\"):");
        billPanel.add(paymentDateLabel);
        billPanel.add(paymentDateText);

        JTextField paymentAmountText = new JTextField();
        JLabel paymentAmountLabel = new JLabel("Payment amount:");
        billPanel.add(paymentAmountLabel);
        billPanel.add(paymentAmountText);

        JTextField finePercentText = new JTextField();
        JLabel finePercentLabel = new JLabel("Fine percent:");
        billPanel.add(finePercentLabel);
        billPanel.add(finePercentText);

        JTextField expirationDaysText = new JTextField();
        JLabel expirationDaysLabel = new JLabel("Expiration days:");
        billPanel.add(expirationDaysLabel);
        billPanel.add(expirationDaysText);

        //      SUBMIT
        JButton submit = new JButton("Submit!");
        billPanel.add(submit);
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int homeNumber = Integer.parseInt(homeNumberText.getText());
                    int flatNumber = Integer.parseInt(flatNumberText.getText());

                    String address = addressText.getText();
                    String name = nameText.getName();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Date paymentDate = sdf.parse(paymentDateText.getText());


                    double paymentAmount = Double.parseDouble(paymentAmountText.getText());
                    double finePercent = Double.parseDouble(finePercentText.getText());

                    int expirationDays = Integer.parseInt(expirationDaysText.getText());
                    listModel.addElement(new UtilityBill(homeNumber, flatNumber, address, name, paymentDate, paymentAmount, finePercent, expirationDays));
                    status.addStatus("A new bill was submitted!");
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //      ***** SOUTH&BOTTOM PANEL *****
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.add(billPanel);

        bottomPanel.add(southPanel);
        bottomPanel.add(status.getStatusPanel());

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        //BgColor and icon
        ImageIcon icon = new ImageIcon("index.png");
        setIconImage(icon.getImage());
        //***** Locate a programme in the center
        setLocationRelativeTo(null);
        //Make it visible
        setVisible(true);
    }
}
