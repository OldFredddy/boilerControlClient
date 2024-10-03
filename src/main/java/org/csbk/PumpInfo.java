package org.csbk;

import java.io.*;
import java.nio.file.Paths;

public class PumpInfo {
    private static String currentDir1 = Paths.get("").toAbsolutePath().toString();
    private String[] correctionPumps;

    // Конструктор: считывание массива из файла при инициализации
    public PumpInfo() {
        correctionPumps = readFromFile("pump_changer.txt");
    }

    public void correctPumps() {
        writeToFile(correctionPumps);
    }

    public String[] getCorrectionPumps() {
        if (correctionPumps == null) {
            correctionPumps = new String[24]; // Инициализация массива длиной 24
            for (int i = 0; i < correctionPumps.length; i++) {
                correctionPumps[i] = "0"; // Заполняем массив нулями по умолчанию
            }
        }
        return correctionPumps;
    }

    public void setCorrectionPumps(String[] correctionPumps) {
        if (this.correctionPumps == null || this.correctionPumps.length != correctionPumps.length) {
            this.correctionPumps = new String[correctionPumps.length]; // Инициализация массива
        }
        for (int i = 0; i < correctionPumps.length; i++) {
            this.correctionPumps[i] = String.valueOf(Integer.parseInt(correctionPumps[i]));
        }
    }

    private static void writeToFile(String[] numbers) {
        try {
            File file = new File(currentDir1, "pump_changer.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
                for (String number : numbers) {
                    bw.write(number + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] readFromFile(String fileName) {
        try {
            File file = new File(currentDir1, fileName);
            if (!file.exists()) {
                // Если файл не найден, возвращаем массив по умолчанию
                return new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                return br.lines().toArray(String[]::new);  // Читаем все строки в массив
            }
        } catch (Exception e) {
            e.printStackTrace();
            // В случае ошибки возвращаем массив по умолчанию
            return new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
        }
    }
}
