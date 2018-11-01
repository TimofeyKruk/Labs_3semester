import java.io.*;

public class Buffer {
    static byte[] toByteArray(Serializable object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        }

    }

    static Object fromByteArray(byte[] array) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        }


    }

    public static long writeObject(RandomAccessFile file, Serializable object) throws IOException {
        long result = file.length();
        file.seek(result);
        byte[] temp = toByteArray(object);
        file.writeInt(temp.length);
        file.write(temp);
        file.setLength(file.getFilePointer());
        return result;
    }

    public static Object readObject(RandomAccessFile file, long position) throws IOException, ClassNotFoundException {
        file.seek(position);
        int length = file.readInt();
        byte[] temp = new byte[length];
        file.read(temp);
        return fromByteArray(temp);
    }
}
