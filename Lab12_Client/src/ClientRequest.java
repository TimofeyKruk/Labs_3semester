import Support.SupportXML;

import java.io.Serializable;

public class ClientRequest implements Serializable {
    public String clientName;
    public String request;


    public ClientRequest(String clientName, String request) {
        this.clientName = clientName;
        this.request = request;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return clientName +
                " requested: \"" + request + "\"\n";
    }
}
