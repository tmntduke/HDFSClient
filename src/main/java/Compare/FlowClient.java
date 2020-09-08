package Compare;

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
public class FlowClient {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration);
            job.setJarByClass(FlowClient.class);

            job.setMapOutputKeyClass(FlowWritable.class);
            job.setMapOutputValueClass(Text.class);

            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);

            job.setOutputKeyClass(FlowWritable.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, new Path("G:\\test\\phone.txt"));
            FileOutputFormat.setOutputPath(job, new Path("G:\\test\\flow"));

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
