package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ChangeBranch;
import java.util.List;

public class Json {
    public static String format(List<ChangeBranch> differTree) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(differTree);
    }
}
