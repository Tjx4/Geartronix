package co.za.geartronix.providers;

public class ContactDetailsProvider {
    private String[] contactNumbers;
    private String[] emails;

    public String[] getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(String[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

}
