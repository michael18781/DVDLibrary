package com.mthree.dvdlibrary;

import java.io.IOException;

class DVDNotFoundException extends RuntimeException {
    public DVDNotFoundException(String msg){
        super(msg);
    }
}

class MenuChoiceException extends RuntimeException {
    public MenuChoiceException(String msg){
        super(msg);
    }
}

class CSVFormatException extends IOException {
    public CSVFormatException(String msg){
        super(msg);
    }
}

class CSVReadingException extends IOException {
    public CSVReadingException(String msg) {
        super(msg);
    }
}

class CSVWritingException extends IOException {
    public CSVWritingException(String msg) {
        super(msg);
    }
}