package in_java;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class JavaSignatureVerificationTest {

    public static final String AUTH_CERTIFICATE_EE = "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";
    public static final String AUTH_CERTIFICATE_EE_PEM = "-----BEGIN CERTIFICATE-----" + "\n" + AUTH_CERTIFICATE_EE + "\n" + "-----END CERTIFICATE-----";
    public static final String HASH_WITH_ADDED_PADDING_BASE64 = "MFEwDQYJYIZIAWUDBAIDBQAEQKXFiU3Dr5pOV3L8n64rfUheeUt6lPjXxBFcYOH1X/hncTDdFtsEqSVYr3H/dSBviYt2ghpTTfKebHARJ+poFDo=";
    private static final String VALID_SIGNATURE_IN_BASE64 = "F84UserdWKmmsZeu5trpMT+yhqZ3aMYMhQatSrRkq3TrYWS/xaE1yzmuzNdYXELs3ZGURuXsePfPKFBvc+PTU7oRHT8dxq3zuAqhDZO8VN5iWKpjF0LTwcA4sO6+uw5hXewG/e8I/CutyYlfcobFvLIqXvXXLl2fcAeQbMvKhj/6yuwwz3b7INVDKQnz/8y+v5/XXBFnlniNJNx7d4Kk+IL7r3DMzttKrldOUzUOuIVb6sdBcrg0+LWClMIt6nCP+T006iRruGqvPpbIsEOs2JIuZo3eh7j6nX2xtMzzgd87BDUzHIFJTj8ZVQu/Yp5A4O3iL2k3E+oOX/5wQkleC6sJ94M6kPliK0LCBv7xcMUmSnwPR3ZjNCX315F21k+ikwK6JlXxBS9pvfLNi2574112yBCq4hB7VKRdORSja9XF4jhoL/rbqisuHRqIMCg3weK6dprSJB1+3pyDGzYPLsV+6RnAb958e/0A7Mq1wg4qjjlqpn32CifoGbwABjUzBhOJC/IFp5ftVQfq3KPLPviyHZN8uIuwwDfI3A9PIOOqu5jt31G777DKGW1xMwd3yRErZ2fbNbNAKjpjeNQtQmS0rcX+l0efBMe4PCmRpT3Sv0i/vNkTlZfqB2NkVSLzTevDt0N1UU+N6u4v5ZEmuEqtoXGWT4ZRlUTUc1oUG8w=";

    public static void main(String[] args) {
        JavaSignatureVerificationTest.verifySignature();
    }

    public static void verifySignature() {
        X509Certificate x509Certificate = parseX509Certificate(AUTH_CERTIFICATE_EE_PEM);
        PublicKey publicKey = x509Certificate.getPublicKey();

        byte[] signature = Base64.getDecoder().decode(VALID_SIGNATURE_IN_BASE64);
        byte[] signedData = Base64.getDecoder().decode(HASH_WITH_ADDED_PADDING_BASE64);

        boolean verificationResult = verify(signedData, signature, publicKey);

        System.out.println("verification passed: " + verificationResult);
    }

    private static X509Certificate parseX509Certificate(String pemCertificateString) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(pemCertificateString.getBytes(
                 StandardCharsets.UTF_8)));
        } catch (CertificateException e) {
            throw new RuntimeException("Failed to parse X509 certificate from " + pemCertificateString + ". Error " + e.getMessage(), e);
        }
    }

    public static boolean verify(byte[] signedData, byte[] signature, PublicKey signersPublicKey) {

        try {
            Signature signatureObject = Signature.getInstance("NONEwith" + signersPublicKey.getAlgorithm());
            signatureObject.initVerify(signersPublicKey);
            signatureObject.update(signedData);

            return signatureObject.verify(signature);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Signature verification failed", e);
        }

    }

}
