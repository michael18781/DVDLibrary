package com.mthree.dvdlibrary;

import java.util.HashMap;
import java.util.Scanner;

public class CollectionView implements CollectionViewInterface {
    private final HashMap<Integer, String> optionsMenu;

    public CollectionView() {
        optionsMenu = new HashMap<>();
        displayMessage("Welcome to the DVD Library");

        // Adding the options for the menu into optionsMenu.
        optionsMenu.put(1, "Open a file");
        optionsMenu.put(2, "Close and save a file");
        optionsMenu.put(3, "Create a DVD in this collection");
        optionsMenu.put(4, "Remove a DVD from the collection");
        optionsMenu.put(5, "Search for a DVD in the collection");
        optionsMenu.put(6, "Edit a DVD in the collection");
        optionsMenu.put(7, "List the collection");
        optionsMenu.put(8, "Exit");
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayExceptionFound(Exception ex){
        displayMessage(ex.getMessage());
    }

    public void fileOpened() {
        displayMessage("File has been opened successfully!");
    }

    public void fileClosed() {
        displayMessage("File has been closed successfully!");
    }

    public void dvdRemoved() {
        displayMessage("DVD removed successfully!");
    }

    public void dvdCreated() {
        displayMessage("DVD created successfully!");
    }

    public void editSuccessful() {
        displayMessage("Edit was successful.");
    }

    public void displayFileOpen() {
        displayMessage("A file is already open.");
    }

    public void displayFileClosed() {
        displayMessage("No file/collection has been loaded. Try opening a file!");
    }

    public void displayDVD(DVD dvd) {
        displayMessage(dvd.toString());
    }

    public void displayCollection(HashMap<String, DVD> dvdCollection) {
        for (String title : dvdCollection.keySet()) {
            displayDVD(dvdCollection.get(title));
        }
    }

    public String getCommand() {
        // Display the menu and take in the user input for their selection.
        Scanner inputReader = new Scanner(System.in);
        displayMessage("------");

        for (Integer key : optionsMenu.keySet()) {
            displayMessage(key + ": " + optionsMenu.get(key));
        }

        return inputReader.nextLine();
    }

    public String getDVDSearch() {
        // Search for DVD by name and return input to controller
        Scanner inputReader = new Scanner(System.in);
        displayMessage("Enter DVD name: ");
        return inputReader.nextLine();
    }

    public String editDVDGetNewData() {
        // Return new review / note for a DVD (unknown to the view)
        Scanner inputReader = new Scanner(System.in);
        displayMessage("Enter new review / note: ");
        return inputReader.nextLine();
    }

    public String loadFromFile() {
        // Return new filename from the user
        Scanner inputReader = new Scanner(System.in);
        displayMessage("Enter filename: ");
        return inputReader.nextLine();
    }

    public DVD createDVD() {
        // Messages to help the view take input for a new DVD object.
        Scanner inputReader = new Scanner(System.in);
        displayMessage("Enter title: ");
        String title = inputReader.nextLine();

        displayMessage("Enter release date DDMMYYYY: ");
        String date = inputReader.nextLine();

        displayMessage("Enter MPAA Rating: ");
        double rating = Double.parseDouble(inputReader.nextLine());

        displayMessage("Enter directors name: ");
        String director = inputReader.nextLine();

        displayMessage("Enter studio: ");
        String studio = inputReader.nextLine();

        displayMessage("Enter review / notes: ");
        String note = inputReader.nextLine();

        return new DVD(title, date, rating, director, studio, note);
    }
}
