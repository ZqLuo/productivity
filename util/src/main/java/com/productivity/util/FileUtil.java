package com.productivity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 读取文件到字节数组
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(File file) throws IOException {
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
