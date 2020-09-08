package Order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by tmnt on 2020/7/14.
 */
public class OrderWritable implements WritableComparable<OrderWritable> {

    private String id;
    private double price;

    public OrderWritable() {
        super();
    }

    public OrderWritable(String id, double price) {
        super();
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(OrderWritable o) {
        int result = 0;
        int id1 = Integer.parseInt(id);
        int id2 = Integer.parseInt(o.getId());
        if (id1 > id2) {
            result = 1;
        } else if (id1 < id2) {
            result = -1;
        } else {
            if (price > o.getPrice()) {
                result = -1;
            } else {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.id.trim());
        dataOutput.writeDouble(this.price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readUTF();
        this.price = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return id + "\t" + String.valueOf(price);
    }
}
