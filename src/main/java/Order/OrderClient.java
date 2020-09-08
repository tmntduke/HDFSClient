package Order;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class OrderClient {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(OrderClient.class);

            job.setMapOutputKeyClass(OrderWritable.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(OrderWritable.class);
            job.setOutputValueClass(NullWritable.class);

            job.setMapperClass(OrderMapper.class);
            job.setReducerClass(OrderReducer.class);

            job.setGroupingComparatorClass(OrderComparetor.class);

            FileInputFormat.addInputPath(job, new Path("G:\\test\\GroupingComparator.txt"));
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\order"));

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
