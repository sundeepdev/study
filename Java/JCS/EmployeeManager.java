import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class EmployeeManager {
    private JCS cache;
    public EmployeeManager() {
        try {
            //Load the cache
            cache = JCS.getInstance("empCache");
        }
    }
}

