package easyestate.dto;

public class PasswordDTO {
	
	private String currentPassword;
	private String newPassword;
	private String matchNewPassword;
	
	public PasswordDTO() {}

	public PasswordDTO(String currentPassword, String newPassword, String matchNewPassword) {
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.matchNewPassword = matchNewPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getMatchNewPassword() {
		return matchNewPassword;
	}

	public void setMatchNewPassword(String matchNewPassword) {
		this.matchNewPassword = matchNewPassword;
	}

}