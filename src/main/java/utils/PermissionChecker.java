package utils;

public class PermissionChecker {
	public static boolean isAdministrator(String permission)
	{
		return permission.equals("Administrator");
	}
}
