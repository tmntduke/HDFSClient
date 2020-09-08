package Compare;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");
        int size = split.length;
        FlowWritable flowWritable = new FlowWritable();
        flowWritable.setPhoneNum(split[1]);
        flowWritable.setUpload(Long.parseLong(split[size - 3]));
        flowWritable.setDownload(Long.parseLong(split[size - 2]));
        long sum = Long.parseLong(split[size - 3]) + Long.parseLong(split[size - 2]);
        flowWritable.setSum(sum);
        context.write(flowWritable, new Text(split[0]));
    }
}
