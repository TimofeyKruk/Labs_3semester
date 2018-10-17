package library.stage_1;

import java.io.Serializable;
import java.util.ArrayList;

public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Reader> blackList = new ArrayList<Reader>();

    //_____Methods______
    public Administrator() {
    }

    public Administrator(ArrayList<Reader> blackList) {
        this.blackList = blackList;
    }

    public ArrayList<Reader> getBlackList() {
        return blackList;
    }

    public void addToBlackList(Reader reader) {
        if (!isReaderInBlackList(reader))
            this.blackList.add(reader);
    }

    public boolean isReaderInBlackList(Reader reader) {
        return (blackList.indexOf(reader) != -1);
    }

    public void removeFromBlackList(Reader reader) {
        this.blackList.remove(reader);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "blackList=" + blackList +
                '}';
    }

}
