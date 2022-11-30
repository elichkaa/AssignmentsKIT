/*
 * @author ufszm
 */
import java.math.BigInteger;

public class IBAN {
    private static final int REQUIRED_DIGITS_IN_BANK_CODE = 10;
    private static final int REQUIRED_FORMAT_OF_CHECKSUM = 10;
    private static final int ASCII_CODE_OF_LETTER_A = 65;
    private static final int COUNT_OF_ELEMENTS_IN_IBAN_FRAGMENT = 4;
    private static final int LETTER_VALUE_ADJUSTER = 9;
    private static final String MODULO_VALUE = "97";
    private static final int IDENTITY_COUNTRY_CONSTANT = 98;
    private final String countryCode;
    private final int bankCode;
    private final long accountNumber;
    private final String checkSum;
    private String formattedIBAN;
    private BigInteger identityNumber;


    public IBAN(String countryCode, int bankCode, long accountNumber) {
        this.countryCode = countryCode;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.checkSum = this.calculateCheckSum();
        this.formattedIBAN = this.formatIBAN();
    }

    public String getFormattedIBAN() {
        return formattedIBAN;
    }

    private String calculateCheckSum() {
        this.identityNumber = this.generateIdentityNumber();
        int countryCodeAsNumber = this.translateCountryCodeToNumber();
        int result = this.combineIdentityNumberAndCountryCode(countryCodeAsNumber);

        String resultAsString = String.valueOf(result);
        if (result < REQUIRED_FORMAT_OF_CHECKSUM) {
            resultAsString = "0" + result;
        }
        return resultAsString;
    }

    private BigInteger generateIdentityNumber() {
        int accountNumberLength = String.valueOf(this.accountNumber).length();
        String accountNumberAsString = "";
        if (accountNumberLength < REQUIRED_DIGITS_IN_BANK_CODE) {
            int difference = REQUIRED_DIGITS_IN_BANK_CODE - accountNumberLength;
            String zeroes = String.format("%0" + difference + "d", 0);
            accountNumberAsString += zeroes;
        }
        accountNumberAsString += this.accountNumber;

        String identityNumber = this.bankCode + accountNumberAsString;
        return new BigInteger(identityNumber);
    }

    private int translateCountryCodeToNumber() {
        int firstLetter = calculateLetterValue(this.countryCode.charAt(0));
        int secondLetter = calculateLetterValue(this.countryCode.charAt(1));
        String result = String.valueOf(firstLetter) + secondLetter + "00";
        return Integer.parseInt(result);
    }

    private int calculateLetterValue(char letter) {
        int asciiStartValue = ASCII_CODE_OF_LETTER_A - 1;
        return letter - asciiStartValue + LETTER_VALUE_ADJUSTER;
    }

    private int combineIdentityNumberAndCountryCode(int countryCodeAsNumber) {
        String combined = this.identityNumber + String.valueOf(countryCodeAsNumber);
        BigInteger combinedAsNumber = new BigInteger(combined);
        int moduloValue = combinedAsNumber.mod(new BigInteger(MODULO_VALUE)).intValue();
        return IDENTITY_COUNTRY_CONSTANT - moduloValue;
    }

    private String formatIBAN() {
        String identityNumber = String.valueOf(this.identityNumber);
        StringBuilder output = new StringBuilder(this.countryCode + this.checkSum + " ");
        int counter = 0;
        for (int i = 0; i < identityNumber.length(); i++) {
            counter++;
            if (counter % COUNT_OF_ELEMENTS_IN_IBAN_FRAGMENT == 0) {
                output.append(identityNumber.charAt(i)).append(" ");
            } else {
                output.append(identityNumber.charAt(i));
            }
        }
        this.formattedIBAN = output.toString();
        return this.formattedIBAN;
    }
}
