package xiao.android.function.constants;

public class LeakyFunctions {

	private static final String[] functionSet = { "getLatitude",
			"getLongitude", "getLastKnownLocation", "requestLocationUpdates",
			"getLastLocation", "getConnectionInfo", "getMacAddress",
			"getDeviceId", "ContactsContract", "pictureFile", "getCameraInfo",
			"FEATURE_CAMERA", "getLine1Number", "startRecording",
			"MediaRecorder", "mRecorder", "CallLog.Calls", "content://sms",
			"CalendarContract", "ClipboardManager", "Secure.ANDROID_ID" };

	public static boolean checkFunction(String line) {

		// System.out.println("Checking line: " + line);

		for (String eachFunction : functionSet) {

			if (line.contains(eachFunction))
				return true;

		}

		return false;
	}

}
