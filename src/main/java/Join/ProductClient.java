package Join;

import JobBuilder.JobBuilder;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/15.
 */
public class ProductClient {

    public static void main(String[] args) {
        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(ProductClient.class)
                .setMapOutputClass(Text.class, ProductWritale.class)
                .setOutputClass(ProductWritale.class, NullWritable.class)
                .setMapReduceClass(ProductMapper.class, ProductReduce.class)
                .setFormatPath("G:\\test\\pro_in", "G:\\test\\produce")
                .execute();

    }
}
