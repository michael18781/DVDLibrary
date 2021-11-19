package com.mthree.dvdlibrary;

import java.util.HashMap;

public interface CollectionViewInterface {
    // Selection of messages displayed when controller requests.
    void displayExceptionFound(Exception ex);

    void fileOpened();
    void fileClosed();
    void dvdRemoved();
    void dvdCreated();
    void editSuccessful();
    void displayFileOpen();
    void displayFileClosed();

    void displayDVD(DVD dvd);
    void displayCollection(HashMap<String, DVD> dvdCollection);

    String getCommand();
    String getDVDSearch();
    String editDVDGetNewData();
    String loadFromFile();

    DVD createDVD();
}
