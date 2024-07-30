package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.example.Dictionary.marioPartyMapping;

public class HexFileModifier {

    public static void changeMp4CharacterNames() {
        String[] inputFilePaths = {"E:\\Mario Party modding\\Mario Party 4\\french\\original\\board_f_modded10.dat", "E:\\Mario Party modding\\Mario Party 4\\french\\original\\mini_f.dat"};
        String outputDir = "E:\\Mario Party modding\\Mario Party 4\\french\\modded\\";

        HexFileModifier.modifyHexFiles(inputFilePaths, outputDir);
    }
    public static void editMp4Text() {
        // Implementation for editMp4Text goes here
        System.out.println("editMp4Text functionality not yet implemented.");
    }



    public static void modifyHexFiles(String[] inputFilePaths, String outputDir) {

        try {
            Scanner scanner = new Scanner(System.in);
            Map<String, String> newHexMappings = new HashMap<>();

            for (Map.Entry<String, String> entry : Dictionary.Mp4PlayableCharactersMapping.entrySet()) {
                String character = entry.getKey();
                String originalHex = entry.getValue().replaceAll(" ", "");
                int originalLength = originalHex.length() / 2;

                String newName;
                byte[] newNameBytes;
                StringBuilder newHex;

                while (true) {
                    System.out.println("Enter a new name for " + character + ": ");
                    System.out.println("Number of maximum characters: " + originalLength);
                    newName = scanner.nextLine().trim();  // Read user input and trim whitespace

                    if (newName.isEmpty()) {
                        // Keep the original name if no input is provided
                        newName = character;
                    }

                    try {
                        newNameBytes = convertToMarioPartyBytes(newName);

                        if (newNameBytes.length > originalLength) {
                            System.out.println("Error: New name is too long! Please enter a name with at most " + originalLength + " characters.");
                        } else {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                newHex = new StringBuilder(bytesToHex(newNameBytes));

                // Pad with null bytes (00) if necessary
                while (newHex.length() < originalHex.length()) {
                    newHex.append("00");
                }

                newHexMappings.put(originalHex, newHex.toString());
            }

            for (String inputFilePath : inputFilePaths) {
                String hexString = bytesToHex(readFile(inputFilePath));

                for (Map.Entry<String, String> mapping : newHexMappings.entrySet()) {
                    hexString = hexString.replace(mapping.getKey(), mapping.getValue());
                }

                byte[] modifiedFileContent = hexToBytes(hexString);
                String outputFilePath = outputDir + File.separator + new File(inputFilePath).getName();
                writeFile(outputFilePath, modifiedFileContent);
                System.out.println("File modified and saved as " + outputFilePath);
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }

    public static byte[] convertToMarioPartyBytes(String text) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (char c : text.toCharArray()) {
            Integer hexValue = marioPartyMapping.get(c);
            if (hexValue != null) {
                baos.write(hexValue);
            } else {
                throw new IllegalArgumentException("Character " + c + " not mapped in Mario Party format.");
            }
        }
        return baos.toByteArray();
    }

    private static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] fileContent = new byte[(int) file.length()];
        fis.read(fileContent);
        fis.close();
        return fileContent;
    }

    private static void writeFile(String filePath, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);
        fos.close();
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    private static byte[] hexToBytes(String hexString) {
        int length = hexString.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    public static void modifyHexFilesWithMappings(String[] inputFilePaths, String outputDir, Map<String, String> newHexMappings) {
        try {
            for (String inputFilePath : inputFilePaths) {
                String hexString = bytesToHex(readFile(inputFilePath));

                for (Map.Entry<String, String> mapping : newHexMappings.entrySet()) {
                    hexString = hexString.replace(mapping.getKey(), mapping.getValue());
                }

                byte[] modifiedFileContent = hexToBytes(hexString);
                String outputFilePath = outputDir + File.separator + new File(inputFilePath).getName();
                writeFile(outputFilePath, modifiedFileContent);
                System.out.println("File modified and saved as " + outputFilePath);
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
}