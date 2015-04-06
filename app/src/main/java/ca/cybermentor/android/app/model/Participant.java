package ca.cybermentor.android.app.model;

import java.util.Hashtable;

public class Participant {

    public String id;
    public String name;
    private Hashtable userList = new Hashtable();

    public Participant(String id) {
        this.id = id;
        this.userList = initializeList();
        this.name = getName(id);
    }

    public String getName(String id) {
        Object lookup = userList.get(id);
        return (lookup == null) ? "" : lookup.toString();
    }

    private Hashtable initializeList() {
        Hashtable list = new Hashtable();
        list.put("1",    "*** Redacted with BFG ***");
        list.put("*** Redacted with BFG ***",  "*** Redacted with BFG ***");
        list.put("*** Redacted with BFG ***", "*** Redacted with BFG ***");
        return list;
    }
}
