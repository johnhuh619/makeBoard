package sys;

import java.util.Map;

public class Request {
    private String path;
    private Map<String, String> params;
    private String[] pathParts;

    public Request(String path, Map<String, String> params) {
        this.path = path;
        this.params = params;
        this.pathParts = path.split("/");
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
}
