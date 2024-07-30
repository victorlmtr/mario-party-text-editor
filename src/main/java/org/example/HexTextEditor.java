package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class HexTextEditor extends JFrame {
    private JTextArea originalTextArea;
    private JTextArea editedTextArea;
    private JButton saveButton;

    public HexTextEditor(Map<String, String> originalTextMap) {
        setTitle("Hex/Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        originalTextArea = new JTextArea();
        editedTextArea = new JTextArea();

        // Populate the original text area
        for (Map.Entry<String, String> entry : originalTextMap.entrySet()) {
            originalTextArea.append(entry.getKey() + "\n");
            editedTextArea.append(entry.getKey() + "\n");
        }

        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        textPanel.add(new JScrollPane(originalTextArea));
        textPanel.add(new JScrollPane(editedTextArea));

        add(textPanel, BorderLayout.CENTER);

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new SaveButtonListener());

        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] originalLines = originalTextArea.getText().split("\n");
            String[] editedLines = editedTextArea.getText().split("\n");

            if (originalLines.length != editedLines.length) {
                JOptionPane.showMessageDialog(null, "The number of lines in both columns must be the same.");
                return;
            }

            Map<String, String> newHexMappings = new HashMap<>();

            for (int i = 0; i < originalLines.length; i++) {
                String originalHex = Dictionary.Mp4PlayableCharactersMapping.get(originalLines[i].trim()).replaceAll(" ", "");
                try {
                    byte[] newNameBytes = HexFileModifier.convertToMarioPartyBytes(editedLines[i].trim());
                    StringBuilder newHex = new StringBuilder(HexFileModifier.bytesToHex(newNameBytes));

                    while (newHex.length() < originalHex.length()) {
                        newHex.append("00");
                    }

                    newHexMappings.put(originalHex, newHex.toString());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Error converting text: " + ex.getMessage());
                    return;
                }
            }

            String[] inputFilePaths = {"E:\\Mario Party modding\\Mario Party 4\\french\\original\\board_f.dat", "E:\\Mario Party modding\\Mario Party 4\\french\\original\\mini_f.dat"};
            String outputDir = "E:\\Mario Party modding\\Mario Party 4\\french\\modded\\";

            HexFileModifier.modifyHexFilesWithMappings(inputFilePaths, outputDir, newHexMappings);

            JOptionPane.showMessageDialog(null, "Changes saved successfully!");
        }
    }

    public static void main(String[] args) {
        Map<String, String> originalTextMap = Dictionary.Mp4PlayableCharactersMapping;
        new HexTextEditor(originalTextMap);
    }
}

