package Index;

import JobBuilder.JobBuilder;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by tmnt on 2020/7/16.
 */
public class Index2Client {
    public static void main(String[] args) {

        JobBuilder builder = JobBuilder.newInstance();
        builder.setJarClass(Index2Client.class)
                .setMapOutputClass(Text.class, Text.class)
                .setOutputClass(Text.class, Text.class)
                .setMapReduceClass(Index2Mapper.class, Index2Reducer.class)
                .setFormatPath("G:\\test\\index2", "G:\\test\\index_out2")
                .execute();

    }
}
