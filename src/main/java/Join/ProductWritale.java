package Join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by tmnt on 2020/7/15.
 */
public class ProductWritale implements Writable {

    private String orderId;
    private String pId;
    private int count;
    private String name;
    private String flag;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(pId);
        dataOutput.writeInt(count);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        orderId = dataInput.readUTF();
        pId = dataInput.readUTF();
        count = dataInput.readInt();
        name = dataInput.readUTF();
        flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return orderId + "\t" + name + "\t" + count + "\t";
    }

}
