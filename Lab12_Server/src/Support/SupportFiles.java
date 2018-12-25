package Support;

import java.io.*;

public class SupportFiles {
    private DataOutputStream dos;
    private DataInputStream dis;

    public SupportFiles(DataInputStream dis, DataOutputStream dos) {
        this.dos = dos;
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public void sendFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] info = fis.readAllBytes();
            this.dos.writeUTF(file.getName());
            this.dos.writeInt(info.length);
            this.dos.write(info);
            this.dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String fileName) {
        File file = new File(fileName);
        this.sendFile(file);
    }

    public String receiveFile() {
        try {
            String fileName = dis.readUTF();
            int length = dis.readInt();
            byte[] info = new byte[length];
            dis.read(info);

            FileOutputStream fos = new FileOutputStream(new File(fileName));
            fos.write(info);
            fos.close();

            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
