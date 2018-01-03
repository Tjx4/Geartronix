package co.za.geartronix.providers;

import android.graphics.Bitmap;
import java.util.List;

public class UserProvider {
    private Bitmap profilePic;
    private MemberProvider memberType;
    private NamesProvider names;
    private int points, id;
    private String city;
    private ContactDetailsProvider contactDetailsProvider;
    private List<CarProvider> cars;
    private List<MessageProvider> messages;
    private ProgressBarProvider progressBar1;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ProgressBarProvider getProgressBar1() {
        return progressBar1;
    }

    public void setProgressBar1(ProgressBarProvider progressBar1) {
        this.progressBar1 = progressBar1;
    }

    public ProgressBarProvider getProgressBar2() {
        return progressBar2;
    }

    public void setProgressBar2(ProgressBarProvider progressBar2) {
        this.progressBar2 = progressBar2;
    }

    private ProgressBarProvider progressBar2;

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public NamesProvider getNames() {
        return names;
    }

    public void setNames(NamesProvider names) {
        this.names = names;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactDetailsProvider getContactDetailsProvider() {
        return contactDetailsProvider;
    }

    public void setContactDetailsProvider(ContactDetailsProvider contactDetailsProvider) {
        this.contactDetailsProvider = contactDetailsProvider;
    }

    public List<CarProvider> getCars() {
        return cars;
    }

    public void setCars(List<CarProvider> cars) {
        this.cars = cars;
    }

    public List<MessageProvider> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageProvider> messages) {
        this.messages = messages;
    }

    public MemberProvider getMemberType() {
        return memberType;
    }

    public void memberType(MemberProvider userType) {
        this.memberType = userType;
    }
}
