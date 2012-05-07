package net.minecraft.src;

import java.io.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CopyFile {

  public static final void zipDirectory( File directory, File zip ) throws IOException {
    ZipOutputStream zos = new ZipOutputStream( new FileOutputStream( zip ) );

      zos.close();
  }
  



  public static final void unzip(File zip, File extractTo) throws IOException {
    ZipFile archive = new ZipFile(zip);
    Enumeration e = archive.entries();
    while (e.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) e.nextElement();
      File file = new File(extractTo, entry.getName());
      if (entry.isDirectory() && !file.exists()) {
        file.mkdirs();
      } else {
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }

        InputStream in = archive.getInputStream(entry);
        BufferedOutputStream out = new BufferedOutputStream(
            new FileOutputStream(file));

        byte[] buffer = new byte[8192];
        int read;

        while (-1 != (read = in.read(buffer))) {
          out.write(buffer, 0, read);
        }

        in.close();
        out.close();
      }
    }
  }
}