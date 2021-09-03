package com.roy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class DynamicWallpaper {

    /*
        Command to change wallpaper in cinnamon
        -   gsettings set org.cinnamon.desktop.background picture-uri file://<file>
        eg: gsettings set org.cinnamon.desktop.background picture-uri file:///home/sougata/Pictures/wall1.jpg

        16 wallpapers - 24 hrs

        00:00, 1:30, 3:00, 4:30, 6:00, 7:30, 9:00, 10:30, 12:00, 13:30, 15:00, 16:30, 18:00, 19:30, 21:00, 22:30
     */

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        String filePath = args[0];
        String fileName = getFileByTimeOfDay();
        setWallpaperByTimeOfDay(filePath, fileName, dateTime);
    }

    private static String getFileByTimeOfDay() {
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int hourMinute = Integer.parseInt(hour + "" + minute);
        System.out.println(hourMinute);
        if (hourMinute >= 0 && hourMinute < 130) {
            return "0000.jpeg";
        } else if (hourMinute >= 130 && hourMinute < 300) {
            return "0130.jpeg";
        } else if (hourMinute >= 300 && hourMinute < 430) {
            return "0300.jpeg";
        } else if (hourMinute >= 430 && hourMinute < 600) {
            return "0430.jpeg";
        } else if (hourMinute >= 600 && hourMinute < 730) {
            return "0600.jpeg";
        } else if (hourMinute >= 730 && hourMinute < 900) {
            return "0730.jpeg";
        } else if (hourMinute >= 900 && hourMinute < 1030) {
            return "0900.jpeg";
        } else if (hourMinute >= 1030 && hourMinute < 1200) {
            return "1030.jpeg";
        } else if (hourMinute >= 1200 && hourMinute < 1330) {
            return "1200.jpeg";
        } else if (hourMinute >= 1330 && hourMinute < 1500) {
            return "1330.jpeg";
        } else if (hourMinute >= 1500 && hourMinute < 1630) {
            return "1500.jpeg";
        } else if (hourMinute >= 1630 && hourMinute < 1800) {
            return "1630.jpeg";
        } else if (hourMinute >= 1800 && hourMinute < 1930) {
            return "1800.jpeg";
        } else if (hourMinute >= 1930 && hourMinute < 2100) {
            return "1930.jpeg";
        } else if (hourMinute >= 2100 && hourMinute < 2330) {
            return "2100.jpeg";
        } else {
            return "2300.jpeg";
        }
    }

    private static void setWallpaperByTimeOfDay(String filePath, String fileName, LocalDateTime dateTime) {
        System.out.printf("%s Setting wallpaper", dateTime);
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isWindows) {
            processBuilder.command("");
        } else {
            File file = new File(filePath + fileName);
            if (file.exists()) {
                processBuilder.command("gsettings", "set", "org.cinnamon.desktop.background",
                        "picture-uri", "file://" + file.getAbsolutePath());
                try {
                    Process process = processBuilder.start();
                    StringBuilder output = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                    int exitValue = process.waitFor();
                    if (exitValue == 0) {
                        System.out.println("Success");
                        System.out.println(output);
                        System.exit(0);
                    } else {
                        System.out.println("ErrorCode: " + exitValue);
                        System.out.println(output);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
