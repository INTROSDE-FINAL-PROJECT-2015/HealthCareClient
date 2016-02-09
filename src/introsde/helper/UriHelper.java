package introsde.helper;

public class UriHelper{

	static final String storageServiceURL = "http://127.0.1.1:5700/sdelab/";
	static final String businessLogicURL  = "http://127.0.1.1:5900/sdelab/";
	static final String processCentricServiceURL = "https://young-earth-96781.herokuapp.com/sdelab/";

	public static String getStorageServicesURL() {
		return storageServiceURL;
	}

	public static String getBusinessLogicURL(){
		return businessLogicURL;
	}

	public static String getProcessCentricServiceURL(){
		return processCentricServiceURL;
	}
}
