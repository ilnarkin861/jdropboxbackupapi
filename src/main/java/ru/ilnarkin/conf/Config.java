package ru.ilnarkin.conf;

public class Config {

    public static final String ZIP_BASE_DIR = System.getProperty("java.io.tmpdir").concat(System.getProperty("file.separator"));
    public static final String FILES_LIST = "fileslist.txt";
    public static final String DROPBOX_DIR = "/backup/";
    public static final String ACCESS_TOKEN = "your_dropbox_access_token";
    public static final String IDENTIFIER = "your_dropbox_identifier";
    public static final String LOCALE = "ru-RU";
    public static final String LOG_DIR = System.getProperty("user.home")
                                        .concat(System.getProperty("file.separator"))
                                        .concat("dropboxlog")
                                        .concat(System.getProperty("file.separator"));
    public static final String LOG_FILE_NAME = "dropbox.log";

    private Config() {}
}
