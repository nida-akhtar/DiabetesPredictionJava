import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputApp extends JFrame {

    private JTextField textField;
    private JTextField numericField;
    private JLabel imageLabel;

    public InputApp() {
        // Set up the frame
        setTitle("Input Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create a blue banner at the top
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(Color.BLUE);
        JLabel bannerLabel = new JLabel("Input Application");
        bannerLabel.setForeground(Color.WHITE);
        bannerPanel.add(bannerLabel);
        add(bannerPanel, BorderLayout.NORTH);

        // Main panel for inputs with white background
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Text input field
        JLabel textLabel = new JLabel("Text Input:");
        textLabel.setForeground(Color.BLACK);
        textField = new JTextField();
        mainPanel.add(textLabel);
        mainPanel.add(textField);

        // Numeric input field
        JLabel numericLabel = new JLabel("Numeric Input:");
        numericLabel.setForeground(Color.BLACK);
        numericField = new JTextField();
        mainPanel.add(numericLabel);
        mainPanel.add(numericField);

        // Image upload button and label
        JLabel imageInputLabel = new JLabel("Upload Image:");
        imageInputLabel.setForeground(Color.BLACK);
        JButton uploadButton = new JButton("Choose File");
        uploadButton.addActionListener(new UploadButtonListener());
        imageLabel = new JLabel();
        
        mainPanel.add(imageInputLabel);
        mainPanel.add(uploadButton);
        mainPanel.add(new JLabel()); // Placeholder to center-align the image preview
        mainPanel.add(imageLabel);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Action listener for image upload button
    private class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(InputApp.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(file.getPath());
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InputApp app = new InputApp();
            app.setVisible(true);
        });
    }
}
