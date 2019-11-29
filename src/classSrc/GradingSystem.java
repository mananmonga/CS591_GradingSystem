package classSrc;

public class GradingSystem
{	
	
	private static class GradingSystemHolder {
		private static GradingSystem instance = new GradingSystem();
	}
	
	public static GradingSystem getInstance() {    //use singleton module to get the GradingSystem instance
		return GradingSystemHolder.instance;
	}
	
	private GradingSystem() {
		//initBank();  //initialize bank with fixed data
	}
	
	public boolean LoginCheck(String Uid, String password) {
        return true;
    }
}
