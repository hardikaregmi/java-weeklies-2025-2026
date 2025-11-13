import java.io.*;
import java.time.*;
import java.util.Scanner;

public class App {

    // Log today's Pomodoro count to a file
    static void logSession() {
        LocalDate today = LocalDate.now();
        File file = new File("pomodoro-log.txt");

        try {
            int count = 0;

            // If file exists, read today's past count
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(today.toString())) {
                        // line format: 2025-11-13 : 2
                        count = Integer.parseInt(line.split(":")[1].trim());
                    }
                }
                br.close();
            }

            // Add this session
            count++;

            // Write the new count (append new line)
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(today + " : " + count);
            bw.newLine();
            bw.close();

            System.out.println("\n✔ Pomodoro session complete!");
            System.out.println("Today's total sessions: " + count);

        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter session length (minutes, default 25): ");

        String input = scanner.nextLine();
        int minutes = input.isEmpty() ? 25 : Integer.parseInt(input);

        System.out.println("\nStarting a " + minutes + "-minute Pomodoro...");
        System.out.println("Press CTRL + C to stop early.\n");

        try {
            // Countdown loop
            for (int i = minutes; i > 0; i--) {
                System.out.println("Time left: " + i + " min");
                Thread.sleep(60 * 1000);   // Wait 1 minute
            }

            // When time is up, log session
            logSession();

        } catch (InterruptedException e) {
            System.out.println("Session interrupted.");
        }
    }
}

