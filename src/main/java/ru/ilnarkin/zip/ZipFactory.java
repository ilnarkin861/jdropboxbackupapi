package ru.ilnarkin.zip;

public class ZipFactory {

    public static Zipable getZipFactory(){
        return new Zip();
    }
}
