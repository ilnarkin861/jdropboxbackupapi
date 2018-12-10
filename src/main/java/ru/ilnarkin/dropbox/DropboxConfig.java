package ru.ilnarkin.dropbox;

import com.dropbox.core.DbxRequestConfig;

public class DropboxConfig {


    public static DbxRequestConfig getConfig(String identifier, String locale) {
        return new DbxRequestConfig(identifier, locale);
    }
}
