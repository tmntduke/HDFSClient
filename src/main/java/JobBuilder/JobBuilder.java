package JobBuilder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class JobBuilder {
    Configuration configuration = new Configuration();
    Job job;

    private static JobBuilder builder;

    public static JobBuilder newInstance() {
        if (builder == null) {
            builder = new JobBuilder();
        }
        return builder;
    }

    private JobBuilder() {
        try {
            job = Job.getInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JobBuilder setJarClass(Class<?> clazz) {
        job.setJarByClass(clazz);
        return builder;
    }

    public JobBuilder setOutputClass(Class<?> key, Class<?> value) {
        job.setOutputKeyClass(key);
        job.setOutputValueClass(value);
        return builder;
    }

    public JobBuilder setMapOutputClass(Class<? extends Writable> key, Class<? extends Writable> value) {
        job.setMapOutputValueClass(value);
        job.setMapOutputKeyClass(key);
        return builder;
    }

    public JobBuilder setMapReduceClass(Class<? extends Mapper> mapperClass, Class<? extends Reducer> reducerClass) {
        job.setMapperClass(mapperClass);
        job.setReducerClass(reducerClass);
        return builder;
    }

    public JobBuilder setFormatPath(String in, String out) {
        try {
            FileInputFormat.addInputPath(job, new Path(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputFormat.setOutputPath(job, new Path(out));
        return builder;
    }

    public JobBuilder setCompress(Class<? extends CompressionCodec> clazz) {
        FileOutputFormat.setCompressOutput(job, true);
        FileOutputFormat.setOutputCompressorClass(job, clazz);
        return builder;
    }

    public JobBuilder setFormatClass(Class<? extends FileInputFormat> inputFormatClass
            , Class<? extends FileOutputFormat> outputFormatClass) {
        job.setInputFormatClass(inputFormatClass);
        job.setOutputFormatClass(outputFormatClass);
        return builder;
    }

    public JobBuilder setMapreduceNum(int num) {
        job.setNumReduceTasks(num);
        return builder;

    }

    public JobBuilder setGroupCompare(Class<? extends RawComparator> clazz) {
        job.setGroupingComparatorClass(clazz);
        return builder;
    }

    public JobBuilder setPartitionClass(Class<? extends Partitioner> clazz) {
        job.setPartitionerClass(clazz);
        return builder;
    }

    /**
     * 加载缓存数据
     *
     * @param path
     * @return
     */
    public JobBuilder setDistributeCacheFile(String path) {
        try {
            job.addCacheFile(new URI(path));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return builder;
    }

    public void execute() {
        try {
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

