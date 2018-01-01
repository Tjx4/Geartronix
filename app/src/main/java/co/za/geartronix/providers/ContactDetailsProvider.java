package co.za.geartronix.providers;

public class ContactDetailsProvider {
    private int[] contactNumbers;
    private String[] emails;

    public int[] getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(int[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

}
