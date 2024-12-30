package egovframework.example.auth.totp;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Base64;

public class TOTPUtil {

    private static final String HMAC_ALGO = "HmacSHA1";
    private static final int TIME_STEP_SECONDS = 30;
    private static final int OTP_DIGITS = 6;

    public static String generateSecretKey() {
        byte[] randomBytes = new byte[10];
        new java.security.SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    public static String generateTOTP(String secretKey) {
        long timeIndex = Instant.now().getEpochSecond() / TIME_STEP_SECONDS;
        return generateHOTP(secretKey, timeIndex);
    }

    public static boolean validateTOTP(String secretKey, String otp) {
        String generatedOtp = generateTOTP(secretKey);
        return generatedOtp.equals(otp);
    }

    private static String generateHOTP(String secretKey, long counter) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, HMAC_ALGO);

            Mac mac = Mac.getInstance(HMAC_ALGO);
            mac.init(keySpec);

            byte[] counterBytes = ByteBuffer.allocate(8).putLong(counter).array();
            byte[] hmacResult = mac.doFinal(counterBytes);

            int offset = hmacResult[hmacResult.length - 1] & 0x0F;
            int binary =
                    ((hmacResult[offset] & 0x7F) << 24) |
                            ((hmacResult[offset + 1] & 0xFF) << 16) |
                            ((hmacResult[offset + 2] & 0xFF) << 8) |
                            (hmacResult[offset + 3] & 0xFF);

            int otp = binary % (int) Math.pow(10, OTP_DIGITS);
            return String.format("%0" + OTP_DIGITS + "d", otp);
        } catch (Exception e) {
            throw new RuntimeException("Error generating TOTP", e);
        }
    }
}
