import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerPanel extends JPanel {
    private static final int HGAP = 10, VGAP = 10;
    public String name = "";
    public JTextField txtName, txtMessage;
    public JTextArea txtChat;
    private JButton btnSend, btnSetName;
    private JPanel namePanel, chatPanel, messagePanel;

    public ServerPanel(){
        this.setLayout(new BorderLayout(HGAP,VGAP));
        txtName = new JTextField(10);
        txtMessage = new JTextField(100);
        txtChat = new JTextArea(40,100);
        txtChat.setEditable(false);
        btnSend = new JButton("Send");
        btnSetName = new JButton("Set");
        namePanel = new JPanel();
        chatPanel = new JPanel();
        messagePanel = new JPanel();

        namePanel.add(txtName);
        namePanel.add(btnSetName);

        chatPanel.add(txtChat);

        messagePanel.add(txtMessage);
        messagePanel.add(btnSend);

        this.add(namePanel,BorderLayout.NORTH);
        this.add(chatPanel,BorderLayout.CENTER);
        this.add(messagePanel,BorderLayout.SOUTH);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnSetName)
                    name = txtName.getText();
                else if(e.getSource() == btnSend)
                    Main.send();

            }
        };

        btnSetName.addActionListener(listener);
        btnSend.addActionListener(listener);
    }


}
