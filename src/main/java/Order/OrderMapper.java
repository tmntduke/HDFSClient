package Order;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderWritable, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");
        OrderWritable orderWritable = new OrderWritable();
        orderWritable.setId(split[0].trim());
        orderWritable.setPrice(Double.parseDouble(split[2]));
        context.write(orderWritable, NullWritable.get());
    }
}
