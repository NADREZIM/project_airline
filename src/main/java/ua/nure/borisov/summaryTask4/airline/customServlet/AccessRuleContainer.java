package ua.nure.borisov.summaryTask4.airline.customServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccessRuleContainer {
    private static final Map<String, List<String>> roles = new HashMap<String, List<String>>();

    static {
        roles.put("/AdminPage", new ArrayList<String>(){{
               add("admin");
        }});

        roles.put("/AdminUpdateFlight", new ArrayList<String>(){{
            add("admin");
        }});


        roles.put("/AdminPage/Employee", new ArrayList<String>(){{
            add("admin");
        }});

        roles.put("/AdminPage/Requests", new ArrayList<String>(){{
            add("admin");
        }});

        roles.put("/AdminPage/Requests/DetailInfo", new ArrayList<String>(){{
            add("admin");
        }});
    }

    public static boolean checkAccess(String path, String role){
        List<String> roleList = roles.get(path);
        if(roleList != null){
            return roleList.contains(role);
        }
        return true;
    }

}
