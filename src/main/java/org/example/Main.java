package org.example;

import java.util.Scanner;

import static org.example.HexFileModifier.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("0 - Change MP4 Character Names");
            System.out.println("1 - Batch edit MP4 Text");
            System.out.println("2 - Edit Hex/Text via GUI");
            System.out.println("3 - Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    changeMp4CharacterNames();
                    break;
                case "1":
                    editMp4Text();
                    break;
                case "2":
                    HexTextEditor.main(new String[]{});
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}