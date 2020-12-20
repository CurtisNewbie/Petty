package com.curtisnewbie.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yongjie.zhuang
 */
public final class IOUtil {

    /**
     * Read files' content under resources directory as a string
     *
     * @param path relative path to resources folder
     * @return content
     * @throws IOException
     */
    public static String readResourceAsString(String path) throws IOException {
        try {
            return Files.readString(Paths.get(IOUtil.class.getClassLoader().getResource(path).toURI()), Charset.forName("UTF-8"));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
