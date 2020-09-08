package Join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class ProductMapper extends Mapper<LongWritable, Text, Text, ProductWritale> {

    private String name;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //get the input file name
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split("\t");
        ProductWritale productWritale = new ProductWritale();
        if (name.startsWith("order")) {
            productWritale.setOrderId(split[0]);
            productWritale.setpId(split[1]);
            productWritale.setCount(Integer.parseInt(split[2]));
            productWritale.setFlag("order");
            productWritale.setName("");
        } else {
            productWritale.setpId(split[0]);
            productWritale.setName(split[1]);
            productWritale.setFlag("pd");
            productWritale.setOrderId("");
            productWritale.setCount(0);
        }

        context.write(new Text(productWritale.getpId()), productWritale);
    }
}
