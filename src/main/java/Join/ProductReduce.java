package Join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by tmnt on 2020/7/15.
 */
public class ProductReduce extends Reducer<Text, ProductWritale, ProductWritale, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<ProductWritale> values, Context context) throws IOException, InterruptedException {
        ArrayList<ProductWritale> order = new ArrayList<>();
        ArrayList<ProductWritale> pd = new ArrayList<>();
        for (ProductWritale value : values) {
            if (value.getFlag().equals("order")) {
                //必需用此方法 否则无法将对象写入
                ProductWritale tmp = new ProductWritale();
                try {
                    BeanUtils.copyProperties(tmp, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                order.add(tmp);
            } else {
                ProductWritale tmp = new ProductWritale();
                try {
                    BeanUtils.copyProperties(tmp, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                pd.add(tmp);
            }
        }

        for (ProductWritale productWritale : order) {
            for (ProductWritale writale : pd) {
                productWritale.setName(writale.getName());
                context.write(productWritale, NullWritable.get());
            }
        }
    }
}
