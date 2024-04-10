package edu.ucalgary.oop;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class GenderTextFileReader {
    private static ArrayList<String> genders = new ArrayList<String>();

    private static String FILE_PATH;

    public GenderTextFileReader(String filePath) throws IllegalArgumentException {
        if (filePath == null) {
            throw new IllegalArgumentException("file path cannot be null");
        }
        FILE_PATH = filePath;
    }

    public ArrayList<String> getGenders() {
        Path path = Paths.get(System.getProperty("user.dir"), FILE_PATH);
        try {
            genders = (ArrayList<String>) Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genders;
    }

    public String getSavedFilePath() {
        return this.FILE_PATH;
    }
}
