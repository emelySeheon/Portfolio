import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MatrixAdditionGUI extends JFrame {
    private JTextArea txtResults = new JTextArea();
    private JButton btnEnd = new JButton();

    public MatrixAdditionGUI(String results) {
        super("Matrix Addition Calculator");

        setLayout(new FlowLayout());
        txtResults.setText(results);
        add(txtResults);

        btnEnd = new JButton("END");
        add(btnEnd);

        btnEnd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}