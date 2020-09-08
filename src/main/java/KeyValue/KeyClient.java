package KeyValue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/12.
 */
public class KeyClient {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(KeyClient.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            job.setMapperClass(KeyMapper.class);
            job.setReducerClass(KeyReduce.class);

            job.setInputFormatClass(KeyValueTextInputFormat.class);

            FileInputFormat.addInputPath(job, new Path("G:\\test\\key_test.txt"));
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\key_test"));

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
