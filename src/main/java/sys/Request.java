package sys;

import model.Session;

import java.util.Map;

public class Request {
    private String path;
    private Map<String, String> params;
    private String[] pathParts;
    private Session session;

    public Request(String path, Map<String, String> params, Session session) {
        this.path = path;
        this.params = params;
        this.pathParts = path.split("/");
        this.session = session;
    }


    public String getPath() {
        return path;
    }

    public String[] getPathParts() {
        return pathParts;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Session getSession() {
        return session;
    }
}
