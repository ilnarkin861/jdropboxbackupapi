package ru.ilnarkin.logs;

import ru.ilnarkin.conf.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DropboxLog {

    public static final Logger getLogger(String logName){

        Logger logger = Logger.getLogger(logName);

        try {

            Path path = Paths.get(Config.LOG_DIR);
            if (!Files.exists(path)) Files.createDirectory(path);

            FileHandler logHandler = new FileHandler(Config.LOG_DIR
                                                    .concat(Config.LOG_FILE_NAME), true);
            SimpleFormatter formatter = new SimpleFormatter();
            logHandler.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.addHandler(logHandler);
        } catch (IOException e) {
            return null;
        }

        return logger;
    }
}
