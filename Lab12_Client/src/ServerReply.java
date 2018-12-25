import java.io.Serializable;

public class ServerReply implements Serializable {
    public String reply;

    public ServerReply() {
    }

    public ServerReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Server replied: \"" + reply + "\"\n";
    }
}
