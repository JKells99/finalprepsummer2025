package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomLogger {
    private static final String filePath = "applicationlogs.txt";
    BufferedWriter bufferedWriter;

    public static void logError(String message) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Error: " + message);
            writer.newLine();
        }
    }

    public static void logInfo(String message) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Info: " + message);
            writer.newLine();
        }
    }
}
