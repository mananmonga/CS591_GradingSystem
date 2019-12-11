package classSrc;
import java.util.UUID;

public class UUIDGenerator
{
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "");
		String uuidStr = str.substring(0,15);
		return uuidStr;
	}
}
