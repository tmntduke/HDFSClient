package Fliter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class FliterClient {
    public static void main(String[] args) {

        Configuration configuration=new Configuration();
        try {
            Job job=Job.getInstance(configuration);
            job.setJarByClass(FliterClient.class);

            job.setMapOutputKeyClass(LongWritable.class);
            job.setMapOutputValueClass(Text.class);

            job.setOutputKeyClass(LongWritable.class);
            job.setOutputValueClass(Text.class);

            job.setOutputFormatClass(FliterOutPutFormat.class);

            job.setMapperClass(FliterMapper.class);
            job.setReducerClass(FliteReduce.class);

            FileInputFormat.addInputPaths(job, "G:\\test\\log.txt");
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\filter"));

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
