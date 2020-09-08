package Partition;

import Phone.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class PhoneClient {
    public static void main(String[] args) {


        Configuration configuration = new Configuration();
        Job job = null;
        try {
            job = Job.getInstance(configuration);
            job.setJarByClass(PhoneClient.class);

            job.setPartitionerClass(PhonePartition.class);
            job.setNumReduceTasks(5);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(PhoneWritable.class);

            job.setMapperClass(PhoneMapper.class);
            job.setReducerClass(PhoneReduce.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(PhoneWritable.class);

            FileInputFormat.addInputPath(job, new Path("G:\\test\\phone.txt"));
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\part"));

            boolean result = job.waitForCompletion(true);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
