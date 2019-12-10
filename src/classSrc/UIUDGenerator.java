package classSrc;
import java.util.UUID;

public class UIUDGenerator
{
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replace("-", "");
		String uuidStr = str.substring(0,15);
		return uuidStr;
	}
}
