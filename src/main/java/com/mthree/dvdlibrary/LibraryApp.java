package com.mthree.dvdlibrary;

public class LibraryApp {
    public static void main(String[] args) {
        CollectionView view = new CollectionView();
        CollectionController c = new CollectionController(view);
        c.mainControlLoop();
    }
}
