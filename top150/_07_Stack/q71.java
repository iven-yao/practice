package top150._07_Stack;

import java.util.ArrayList;
import java.util.List;

public class q71 {
    public String simplifyPath(String path) {
        if (path==null || path.length()==0) return "/";
        StringBuilder sPath = new StringBuilder();
        
        String[] dirs = path.split("/");

        List<String> list = new ArrayList<>();

        for(String dir: dirs) {
            if(dir.equals("..")) {
                if(list.size()>0) list.remove(list.size()-1);
            } else if(!dir.equals(".") && !dir.equals("")) list.add(dir);
        }

        if(list.size() == 0) return "/";

        for(String dir: list) {
            sPath.append("/").append(dir);
        }

        return sPath.toString();
    }
}
