package ViewUB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Status {
    JPanel statusPanel;
    JLabel statusLabel;
    JToolBar statusBar;
    JTextField status;

    JButton previous;
    JButton last;

    ArrayList<String> statusDB;
    int currentShown;
    int length;

    public Status() {

        statusPanel = new JPanel();
        statusLabel = new JLabel("Status: ");
        statusBar = new JToolBar();
        statusBar.setLayout(new BoxLayout(statusBar,BoxLayout.X_AXIS));

        status = new JTextField();
        statusDB = new ArrayList<>();


        previous = new JButton("Previous status");
        last = new JButton("Last status");

        //Buttons actions
        previous.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShown--;
                if(currentShown<0)
                {
                    status.setText("You reached the end!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    currentShown=0;
                }
                status.setText(statusDB.get(currentShown));
            }
        });

        last.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShown=length;
                status.setText(statusDB.get(currentShown));
            }
        });

        statusBar.add(previous);
        statusBar.add(last);

        statusBar.add(status);

        //      DB
        status.setText("The programme was launched!\t\t");
        statusDB.add("The programme was launched!\t\t");
        length=0;
        currentShown=0;

        statusPanel.add(statusLabel);
        statusPanel.add(statusBar);
    }

    public void addStatus(String s)
    {
        statusDB.add(s);
        length++;
        currentShown=length;
        status.setText(statusDB.get(currentShown));
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }
}
