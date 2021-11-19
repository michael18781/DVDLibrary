package com.mthree.dvdlibrary;

import java.io.IOException;

public interface CollectionControllerInterface {
    void mainControlLoop();
    void openFile();
    void closeFile();
    void editDVD();
    void searchDVD();
    void createDVD();
    void deleteDVD();
    void writeCollection() throws IOException;

    boolean checkFileOpen();

    DVD findDVD() throws DVDNotFoundException;

    int getMenuOption();
}
