package com.mthree.dvdlibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DVDReader {
    String fileName;

    public DVDReader(String fileName){
        this.fileName = fileName;
    }

    public HashMap<String, DVD> getCollectionFromFile() throws CSVReadingException, CSVFormatException {
        HashMap<String, DVD> newCollection = new HashMap<>();

        String line;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)   // Returns a Boolean value
            {
                // Split each line with delimiter and then create a DVD object
                String[] dvdData = line.split(",");

                DVD dvdToRead = new DVD(
                        dvdData[0],
                        dvdData[1],
                        Double.parseDouble(dvdData[2]),
                        dvdData[3],
                        dvdData[4],
                        dvdData[5]);

                newCollection.put(dvdToRead.getTitle(), dvdToRead);
            }
        } catch (IOException e){
            throw new CSVReadingException("There has been an error reading the file.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            throw new CSVFormatException("The CSV format is incorrect.");
        }

        return newCollection;
    }
}
