package utils;

public class PermissionChecker {
	public static boolean isAdministrator(String permission)
	{
		String admin = "Admin";
		return permission.toLowerCase().equals(admin.toLowerCase());
	}
}
