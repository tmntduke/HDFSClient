package InputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/12.
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private Configuration configuration;
    private FileSplit split;
    private Text key = new Text();
    private BytesWritable value = new BytesWritable();
    boolean isRead = true;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        this.split = (FileSplit) inputSplit;
        configuration = taskAttemptContext.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        //核心代码

        if (isRead) {
            byte[] buf = new byte[(int) split.getLength()];

            //1. 获取fs
            Path path = split.getPath();
            FileSystem fileSystem = path.getFileSystem(configuration);

            //2.获取流
            FSDataInputStream fsDataInputStream = fileSystem.open(path);

            //3.copy
            IOUtils.readFully(fsDataInputStream, buf, 0, buf.length);

            //4.put value
            value.set(buf, 0, buf.length);

            //5.put key
            key.set(path.toString());

            //6.close
            IOUtils.closeStream(fsDataInputStream);

            isRead = false;
            return true;

        }
        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
