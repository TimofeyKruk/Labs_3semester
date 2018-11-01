import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapUtility {
    //Fields:
    private FileSupport datFile;
    private Map<Object, Long> objectsMap = new HashMap<>();


    //  Methods:


    public MapUtility(FileSupport datFile) {
        this.datFile = datFile;
        this.createMapFromDATFile();
    }

    public void createMapFromDATFile() {
        try (RandomAccessFile raf = new RandomAccessFile(datFile.getFileNameDat(), "r")) {
            long position;
            UtilityBill bill;
            while ((position = raf.getFilePointer()) < raf.length()) {
                bill = (UtilityBill) Buffer.readObject(raf, position);
                if (!bill.isDeleted())
                    this.objectsMap.put(bill.getFieldName(), position);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public MapUtility(FileSupport datFile, Map<Object, Long> objectsMap) {
        this.datFile = datFile;
        this.objectsMap = objectsMap;
    }

    public FileSupport getDatFile() {
        return datFile;
    }

    public void setDatFile(FileSupport datFile) {
        this.datFile = datFile;
    }

    public Map<Object, Long> getObjectsMap() {
        return objectsMap;
    }

    public void setObjectsMap(Map<Object, Long> objectsMap) {
        this.objectsMap = objectsMap;
    }

    public UtilityBill readByKey(Object key) {
        try (RandomAccessFile raf = new RandomAccessFile(datFile.getFileNameDat(), "r")) {
            if (this.objectsMap.containsKey(key)) {
                long position = this.objectsMap.get(key);
                UtilityBill bill = (UtilityBill) Buffer.readObject(raf, position);
                if (bill.isDeleted()) return null;
                else
                    return bill;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeByKey(Object key) {
        try (RandomAccessFile raf = new RandomAccessFile(datFile.getFileNameDat(), "rw")) {
            if (this.objectsMap.containsKey(key)) {
                UtilityBill bill = readByKey(key);
                if (bill != null) {
                    bill.setIsDeleted(true);
                    raf.seek(objectsMap.get(key));
                    byte[] temp = Buffer.toByteArray(bill);
                    raf.writeInt(temp.length);
                    raf.write(temp);
                }
                this.objectsMap.remove(key);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showMap() {
        System.out.println("_______ Utility Bill Map _______");
        for (var entry : objectsMap.entrySet()) {
            System.out.println("Key:  " + entry.getKey() + ";  Position:  " + entry.getValue());
        }
        System.out.println("________________________________");
    }

    public void showObjectsAscending() {
        Object[] entries = this.objectsMap.keySet().toArray();
        Arrays.sort(entries);

        System.out.println("_______ UtilityBills ascending  _______");
        for (Object entry : entries) {
            System.out.println("Key:  " + entry + " ::  " + readByKey(entry));
        }
        System.out.println("________________________________");

    }

    public void showObjectsDescending() {
        Object[] entries = this.objectsMap.keySet().toArray();
        Arrays.sort(entries, Collections.reverseOrder());

        System.out.println("_______ UtilityBills descending  _______");
        for (Object entry : entries) {
            System.out.println("Key:  " + entry + " ::  " + readByKey(entry));
        }
        System.out.println("________________________________");
    }

    public void showObjectBiggerThanKey(Object Key) {
        Object[] entries = this.objectsMap.keySet().toArray();
        Arrays.sort(entries);


        System.out.println("_______ UtilityBill with key bigger than   " + Key + "  _______");
        int i;
        boolean somethingFound = false;
        for (i = 0; i < entries.length - 1; ++i) {
            if (entries[i].equals(Key)) {
                System.out.println("Key:  " + entries[i + 1] + " ::  " + readByKey(entries[i + 1]));
                somethingFound = true;
                break;
            }
        }
        if (!somethingFound) System.out.println("No suitable object!");
        System.out.println("________________________________");
    }

    public void showObjectSmallerThanKey(Object Key) {
        Object[] entries = this.objectsMap.keySet().toArray();
        Arrays.sort(entries);


        System.out.println("_______ UtilityBill with key smaller than   " + Key + "  _______");
        int i;
        boolean somethingFound = false;
        for (i = 1; i < entries.length; ++i) {
            if (entries[i].equals(Key)) {
                System.out.println("Key:  " + entries[i - 1] + " ::  " + readByKey(entries[i - 1]));
                somethingFound = true;
                break;
            }
        }
        if (!somethingFound) System.out.println("No suitable object!");
        System.out.println("________________________________");
    }


}
