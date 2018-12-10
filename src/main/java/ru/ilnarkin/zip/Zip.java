package ru.ilnarkin.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import ru.ilnarkin.conf.Config;
import ru.ilnarkin.logs.DropboxLog;
import ru.ilnarkin.utils.Util;

class Zip extends SimpleFileVisitor<Path> implements Zipable {

    private String zipFileName;
    private Set<String> filesList;
    private  Logger logger;

    public Zip() {
        this.logger = DropboxLog.getLogger(Zip.class.getName());
        this.filesList = new HashSet<>();
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {

        filesList.add(path.toAbsolutePath().toString());
        return FileVisitResult.CONTINUE;
    }

    private void initFilesList(){

        try {

            Path path = Paths.get(System.getProperty("user.home")
                                    .concat(System.getProperty("file.separator")
                                    .concat(Config.FILES_LIST)));

            List <String>contents = Files.readAllLines(path);
            for(String content:contents) Files.walkFileTree(Paths.get(content), this);


        } catch (IOException e) {
            logger.severe(e.toString());
            System.exit(1);
        }

    }


    @Override
    public String compress(){
        zipFileName = Util.getZipFileName();

        initFilesList();
        try (FileOutputStream fos = new FileOutputStream(Config.ZIP_BASE_DIR.concat(zipFileName));
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {


            for (String file : filesList){
                File fileToZip = new File(file);

                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getPath());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }

            logger.info(zipFileName.concat(" created"));

            return this.zipFileName;


        } catch (FileNotFoundException e) {
            logger.severe(e.toString());
            return null;
        } catch (IOException e) {
            logger.severe(e.toString());
            return null;
        }

    }


    @Override
    public void deleteZipFile(){

        try {
            File zipFile = new File(Config.ZIP_BASE_DIR.concat(this.zipFileName));

            if (!zipFile.exists()) throw new FileNotFoundException();
            zipFile.delete();
            logger.info(zipFileName.concat(" deleted..."));

        }catch (FileNotFoundException e) {
            logger.severe(e.toString());
        }

    }
}
