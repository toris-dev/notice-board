package egovframework.example.user.dto;

public class UserVO {
	private String userId;
	private String password;
	private String nickname;
	private boolean otpEnabled;
	private String otpSecret;

	// Getters and Setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isOtpEnabled() {
		return otpEnabled;
	}

	public void setOtpEnabled(boolean otpEnabled) {
		this.otpEnabled = otpEnabled;
	}

	public String getOtpSecret() {
		return otpSecret;
	}

	public void setOtpSecret(String otpSecret) {
		this.otpSecret = otpSecret;
	}
}
