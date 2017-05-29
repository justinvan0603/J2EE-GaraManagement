package utils;

public class PermissionChecker {
	public static boolean isAdministrator(String permission)
	{
		String admin = "Administrator";
		return permission.toLowerCase().equals(admin.toLowerCase());
	}
}
