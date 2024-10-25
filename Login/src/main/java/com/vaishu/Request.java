package com.vaishu;

public class Request {
    private String id;
    private String username;
    private String accessType;
    private String softwareName;

    public Request(String id, String username, String accessType, String softwareName) {
        this.id = id;
        this.username = username;
        this.accessType = accessType;
        this.softwareName = softwareName;
    }

   
	public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getSoftwareName() {
        return softwareName;
    }
}
