package Index;

import JobBuilder.JobBuilder;
import Join.ProductReduce;
import Log.LogMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/16.
 */
public class IndexClient {
    public static void main(String[] args) {
        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(IndexClient.class)
                .setMapOutputClass(Text.class, IntWritable.class)
                .setOutputClass(Text.class, IntWritable.class)
                .setMapReduceClass(IndexMapper.class, IndexReducer.class)
                .setFormatPath("G:\\test\\index", "G:\\test\\index_out")
                .execute();
    }
}
