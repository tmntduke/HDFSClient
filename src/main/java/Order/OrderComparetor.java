package Order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by tmnt on 2020/7/14.
 */
public class OrderComparetor extends WritableComparator {

    protected OrderComparetor() {
        super(OrderWritable.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderWritable order1 = (OrderWritable) a;
        OrderWritable order2 = (OrderWritable) b;
        int id1 = Integer.parseInt(order1.getId());
        int id2 = Integer.parseInt(order2.getId());
        int result;
        if (id1 > id2) {
            result = 1;
        } else if (id1 < id2) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }

}
