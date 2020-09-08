package Compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * Created by tmnt on 2020/7/16.
 */
public class TestCompress {
    public static void main(String[] args) {

        //compress("G:\\test\\web.log", "org.apache.hadoop.io.compress.BZip2Codec");
        decompress("G:\\test\\web.log.bz2");
    }

    private static void decompress(String s) {

        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(s));
        if (codec == null) {
            System.out.println("the file con not decompress");
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(s));
            CompressionInputStream codecInputStream = codec.createInputStream(fileInputStream);

            FileOutputStream outputStream = new FileOutputStream(new File("G:\\test\\web2.log"));
            IOUtils.copyBytes(codecInputStream, outputStream, 1024 * 1024, false);

            IOUtils.closeStream(codecInputStream);
            IOUtils.closeStream(fileInputStream);
            IOUtils.closeStream(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void compress(String s, String s1) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(s));
            Class com = Class.forName(s1);

            CompressionCodec compressionCodec = (CompressionCodec) ReflectionUtils.newInstance(com, new Configuration());
            FileOutputStream outputStream = new FileOutputStream(new File(s + compressionCodec.getDefaultExtension()));

            CompressionOutputStream compressionCodecOut = compressionCodec.createOutputStream(outputStream);

            IOUtils.copyBytes(fileInputStream, compressionCodecOut, 1024 * 1024 * 5, false);

            IOUtils.closeStream(compressionCodecOut);
            IOUtils.closeStream(fileInputStream);
            IOUtils.closeStream(outputStream);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
