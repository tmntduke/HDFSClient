package HDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tmnt on 2020/7/4.
 */
public class HDFSClient {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        //configuration.set("fs.defaultFS","hdfs://192.168.6.128:9000");
        try {
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.6.128:9000"), configuration, "zzy");
            //fileSystem.mkdirs(new Path("/user/test0704"));
            //fileSystem.copyFromLocalFile(false,new Path("G:\\test\\sample.txt"),new Path("/user/test0704"));
            //fileSystem.copyToLocalFile(false,new Path("/user/test0704/sample.txt"),new Path("g:\\test\\t_0724.txt"));

            //lookAtFileDetail(fileSystem);

            //uploadFileByStreaam(fileSystem, configuration);
            //downloadFormStream(fileSystem, configuration);
            //downloadFileViaSpilt(fileSystem, configuration);
            fileSystem.close();
            System.out.println("over");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFileViaSpilt(FileSystem fileSystem, Configuration configuration) {
        try {
            FSDataInputStream fsIn = fileSystem.open(new Path("/user/hadoop-3.1.3.tar.gz"));
            FileOutputStream out=new FileOutputStream(new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFormStream(FileSystem fileSystem, Configuration configuration) {
        try {
            FSDataInputStream fsIn = fileSystem.open(new Path("/user/test_hdfs.txt"));
            FileOutputStream out = new FileOutputStream(new File("g:\\test\\test_hdfs.txt"));
            IOUtils.copyBytes(fsIn, out, configuration);
            fsIn.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void uploadFileByStreaam(FileSystem fileSystem, Configuration c) {
        try {
            FileInputStream in = new FileInputStream(new File("g:\\test\\sample1.txt"));

            FSDataOutputStream fsOut = fileSystem.create(new Path("/user/sample1.txt"));

            IOUtils.copyBytes(in, fsOut, c);
            in.close();
            fsOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lookAtFileDetail(FileSystem fileSystem) {
        try {
            RemoteIterator<LocatedFileStatus> it = fileSystem.listFiles(new Path("/"), true);
            while (it.hasNext()) {
                LocatedFileStatus status = it.next();
                System.out.println(status.getPath().getName() + "\n");
                System.out.println(status.getLen() + "\n");
                System.out.println(status.getGroup() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
