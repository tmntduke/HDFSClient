package Join;

import JobBuilder.JobBuilder;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/15.
 */
public class DistributeClient {
    public static void main(String[] args) {

        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(ProductClient.class)
                .setMapOutputClass(Text.class, ProductWritale.class)
                .setOutputClass(ProductWritale.class, NullWritable.class)
                .setMapReduceClass(DistributeMapper.class, ProductReduce.class)
                .setDistributeCacheFile("file:/G:/test/pro_in/pd.txt")
                .setMapreduceNum(0)
                .setFormatPath("G:\\test\\pro_in\\order.txt", "G:\\test\\dis")
                .execute();
    }
}
