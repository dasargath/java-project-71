package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public class ChangesTree {
    public static List<ChangeBranch> buildChangesTree(Map<String, Object> file1map, Map<String, Object> file2Map) {
        Set<String> keys = new TreeSet<>(file1map.keySet());
        keys.addAll(file2Map.keySet());
        List<ChangeBranch> diffList = new ArrayList<>();
        for (String key: keys) {
            if (!file1map.containsKey(key)) {
                diffList.add(new ChangeBranch("added", key, file2Map.get(key), file1map.get(key)));
            } else if (!file2Map.containsKey(key)) {
                diffList.add(new ChangeBranch("deleted", key, file2Map.get(key), file1map.get(key)));
            } else if (!Objects.equals(file1map.get(key), file2Map.get(key))) {
                diffList.add(new ChangeBranch("changed", key, file2Map.get(key), file1map.get(key)));
            } else {
                diffList.add(new ChangeBranch("unchanged", key, file2Map.get(key), file1map.get(key)));
            }
        }
        return diffList;
    }
}
