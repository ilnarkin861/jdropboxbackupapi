package ru.ilnarkin;

import ru.ilnarkin.conf.Config;
import ru.ilnarkin.dropbox.Dropbox;
import ru.ilnarkin.dropbox.DropboxConfig;
import ru.ilnarkin.zip.ZipFactory;
import ru.ilnarkin.zip.Zipable;


public class Main {


    public static void main(String args[]) {

        Zipable zip = ZipFactory.getZipFactory();

        String zipFileName = zip.compress();
        Dropbox dropbox = new Dropbox(DropboxConfig.getConfig(Config.IDENTIFIER, Config.LOCALE));
        dropbox.uploadFile(zipFileName);
        zip.deleteZipFile();



    }
}
