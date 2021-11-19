package com.mthree.dvdlibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DVDWriter {
    String fileName;

    public DVDWriter(String fileName){
        this.fileName = fileName;
    }

    public void writeCollection(HashMap<String, DVD> collection) throws CSVWritingException {
        // Append false means that we re-write the file each time
        try {
            FileWriter fw = new FileWriter(fileName, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (DVD dvd : collection.values()) {
                bw.write(dvd.getTitle() + "," +
                        dvd.getReleaseDate() + "," +
                        dvd.getMpaaRating() + "," +
                        dvd.getDirectorsName() + "," +
                        dvd.getStudio() + "," +
                        dvd.getUserNote() + "\n");
            }

            bw.close();
            fw.close();
        } catch (IOException e){
            throw new CSVWritingException("There has been an error writing to the file.");
        }
    }
}