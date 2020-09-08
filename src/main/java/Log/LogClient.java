package Log;

import JobBuilder.JobBuilder;
import Join.ProductReduce;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/16.
 */
public class LogClient {
    public static void main(String[] args) {

        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(LogClient.class)
                .setMapOutputClass(Text.class, NullWritable.class)
                .setOutputClass(Text.class, NullWritable.class)
                .setMapReduceClass(LogMapper.class, ProductReduce.class)
                .setMapreduceNum(0)
                .setFormatPath("G:\\test\\web.log", "G:\\test\\log")
                .execute();
    }
}

