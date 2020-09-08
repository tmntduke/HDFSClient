package InputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/12.
 */
public class WholeFileClient {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(WholeFileClient.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(BytesWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(BytesWritable.class);

            job.setMapperClass(WholeFileMapper.class);
            job.setReducerClass(WholeFileReducer.class);

            //设置自定义切片
            job.setInputFormatClass(WholeFileInputFormat.class);
            job.setOutputFormatClass(SequenceFileOutputFormat.class);

            FileInputFormat.addInputPaths(job, "G:\\test\\in");
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\whole"));

            job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
