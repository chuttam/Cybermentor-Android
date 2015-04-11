package ca.cybermentor.android.app.model;

import java.util.Hashtable;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.model.constant.Secret;

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
        list.put(Secret.ME_ID, Secret.ME_NAME);
        list.put(Secret.PARTICIPANT_ID1,  Secret.PARTICIPANT_NAME1);
        list.put(Secret.PARTICIPANT_ID2,  Secret.PARTICIPANT_NAME2);
        return list;
    }
}
