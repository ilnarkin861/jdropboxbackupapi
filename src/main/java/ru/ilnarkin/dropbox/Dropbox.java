package ru.ilnarkin.dropbox;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import ru.ilnarkin.conf.Config;
import ru.ilnarkin.logs.DropboxLog;


public class Dropbox {

    private DbxClientV2 client;


    public Dropbox(DbxRequestConfig config) {
        this.client = new DbxClientV2(config, Config.ACCESS_TOKEN);
    }

    public void uploadFile(String fileName){

        Logger dbxLogger = DropboxLog.getLogger(Dropbox.class.getName());
        try (InputStream in = new FileInputStream(Config.ZIP_BASE_DIR.concat(fileName))) {
                client.files().uploadBuilder(Config.DROPBOX_DIR.concat(fileName)).uploadAndFinish(in);
                dbxLogger.info(fileName.concat(" uploaded..."));
        } catch (IOException e ){
            dbxLogger.severe(e.toString());
        }catch (DbxException dex){
            dbxLogger.severe(dex.toString());
        }
    }



}
