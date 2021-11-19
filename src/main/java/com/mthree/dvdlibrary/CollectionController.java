package com.mthree.dvdlibrary;

import java.util.HashMap;
import java.util.Objects;

public class CollectionController implements CollectionControllerInterface {
    private final CollectionViewInterface view;
    private String fileOpen;
    private HashMap<String, DVD> collection;

    public CollectionController(CollectionViewInterface viewInterface) {
        fileOpen = "";
        view = viewInterface;
        collection = new HashMap<>();
    }

    public void mainControlLoop() {
        boolean exitStatus = false;

        do {
            // Menu option from View
            switch (getMenuOption()) {
                case 1: // OPEN A FILE
                    if (checkFileOpen()) {
                        view.displayFileOpen();
                        break;
                    }
                    openFile();
                    break;

                case 2: // CLOSE A FILE
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    closeFile();
                    break;

                case 7: // LIST COLLECTION
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    view.displayCollection(collection);
                    break;

                case 8: // QUIT
                    exitStatus = true;
                    break;

                case 4: // REMOVE A DVD
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    deleteDVD();
                    break;

                case 3: // Create DVD
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    createDVD();
                    break;

                case 5: // Search for DVD
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    searchDVD();
                    break;

                case 6:
                    if (!checkFileOpen()) {
                        view.displayFileClosed();
                        break;
                    }
                    editDVD();
                    break;
            }

        } while (!exitStatus);

    }

    public void openFile(){
        try {
            // Get filename and open a DVDReader
            String fileToOpen = view.loadFromFile();
            DVDReader reader = new DVDReader(fileToOpen);

            // Try to read the collection from the file
            collection = reader.getCollectionFromFile();
            fileOpen = fileToOpen;
            view.fileOpened();
        } catch (CSVReadingException | CSVFormatException e) {
            // Unable to locate the file and thus can't update collection.
            view.displayExceptionFound(e);
        }
    }

    public void closeFile(){
        // Set the file to closed and erase the current stored collection.
        fileOpen = "";
        collection = null;

        // Inform user of the changes
        view.fileClosed();
    }

    public void editDVD(){
        try {
            // Find the DVD first by searching by name
            DVD existingDVD = findDVD();

            // Update the DVD to have a new review / note
            String newNoteReview = view.editDVDGetNewData();
            existingDVD.setUserNote(newNoteReview);

            // Save the changes and inform user
            writeCollection();
            view.editSuccessful();
        } catch (DVDNotFoundException | CSVWritingException e) {
            view.displayExceptionFound(e);
        }
    }

    public void searchDVD(){
        try {
            // Try to ask user for DVD and search for it - then display if it exists
            view.displayDVD(findDVD());
        } catch (DVDNotFoundException e) {
            view.displayExceptionFound(e);
        }
    }

    public void createDVD(){
        try {
            // Gather all information and create new DVD object from view
            DVD newDVD = view.createDVD();

            // Add DVD to the collection and then write out changes and inform user
            collection.put(newDVD.getTitle(), newDVD);
            writeCollection();
            view.dvdCreated();
        } catch (CSVWritingException e) {
            view.displayExceptionFound(e);
        }
    }

    public void deleteDVD(){
        // Getting user delete request and checking that the DVD exists
        try {
            // Find DVD - might throw custom exception
            DVD found = findDVD();

            // Remove from the collection
            collection.remove(found.getTitle());

            // Write out collection to file and inform the user
            writeCollection();
            view.dvdRemoved();
        } catch (DVDNotFoundException | CSVWritingException e) {
            view.displayExceptionFound(e);
        }
    }

    public void writeCollection() throws CSVWritingException {
        DVDWriter writerCreate = new DVDWriter(fileOpen);
        // Attempting to add DVD to collection write out the new collection
        writerCreate.writeCollection(collection);
    }

    public boolean checkFileOpen() {
        // Confirms whether we have a file open or not and displays message.
        return !Objects.equals(fileOpen, "");
    }

    public DVD findDVD() throws DVDNotFoundException {
        // Returns a DVD if it can be found, otherwise returns an exception which needs to be handled in mainLoop().
        String dvdToFind = view.getDVDSearch();
        DVD dvdFound = collection.get(dvdToFind);

        // Return the DVD if it can be found, otherwise throw a custom exception
        if (dvdFound != null) return dvdFound;
        else throw new DVDNotFoundException("DVD not found.");
    }

    public int getMenuOption() {
        // Tries to parse the user response from the menu options and ensures it corresponds to an option.
        // Otherwise, recursively calls itself until a valid option is given.
        try {
            // Try to parse menu option as an integer
            int choice = Integer.parseInt(view.getCommand());

            // Verify menu option is in valid range
            if (choice > 0 && choice <= 8) return choice;
            else throw new MenuChoiceException("Invalid menu choice.");

        } catch (NumberFormatException | MenuChoiceException e) {
            // The input is invalid!
            view.displayExceptionFound(e);

            // Recursively asks for input until it is valid.
            return getMenuOption();
        }
    }

}
