package Fliter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class FilerRecordWriter extends RecordWriter<LongWritable, Text> {


    private TaskAttemptContext context;
    private FileSystem fileSystem;
    private FSDataOutputStream other;
    private FSDataOutputStream at;

    public FilerRecordWriter(TaskAttemptContext context) {
        this.context = context;
        //1.创建文件系统
        //2.创建输出路径
        try {
            fileSystem = FileSystem.get(context.getConfiguration());
            other = fileSystem.create(new Path("G:\\test\\other.log"));
            at = fileSystem.create(new Path("G:\\test\\at.log"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void write(LongWritable longWritable, Text text) throws IOException, InterruptedException {
        //判断
        String value = text.toString();
        if (value.contains("atguigu")) {
            at.write(text.toString().getBytes());
        } else {
            other.write(text.toString().getBytes());
        }

    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(at);
        IOUtils.closeStream(other);
    }
}
