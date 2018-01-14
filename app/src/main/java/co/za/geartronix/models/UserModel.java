package co.za.geartronix.models;

import android.graphics.Bitmap;
import java.util.List;

import co.za.geartronix.providers.ContactDetailsProvider;

public class UserModel extends BaseModel {
    private Bitmap profilePic;
    private MemberModel memberType;
    private NamesModel names;
    private int points, id;
    private String city;
    private ContactDetailsProvider contactDetailsProvider;
    private List<CarModel> cars;
    private List<MessageModel> messages;
    private ProgressBarModel progressBar1;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ProgressBarModel getProgressBar1() {
        return progressBar1;
    }

    public void setProgressBar1(ProgressBarModel progressBar1) {
        this.progressBar1 = progressBar1;
    }

    public ProgressBarModel getProgressBar2() {
        return progressBar2;
    }

    public void setProgressBar2(ProgressBarModel progressBar2) {
        this.progressBar2 = progressBar2;
    }

    private ProgressBarModel progressBar2;

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public NamesModel getNames() {
        return names;
    }

    public void setNames(NamesModel names) {
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

    public List<CarModel> getCars() {
        return cars;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public MemberModel getMemberType() {
        return memberType;
    }

    public void memberType(MemberModel userType) {
        this.memberType = userType;
    }
}
