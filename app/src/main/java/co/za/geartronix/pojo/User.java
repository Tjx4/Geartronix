package co.za.geartronix.pojo;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.List;
import co.za.geartronix.models.CarModel;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.providers.ContactDetailsProvider;

public class User implements Serializable {

    private Bitmap profilePic;
    private MemberModel memberType;
    private NamesModel names;
    private int points, id;
    private String city;
    private ContactDetailsProvider contactDetailsProvider;
    private List<CarModel> cars;
    private List<MessageModel> messages;

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public void setMemberType(MemberModel memberType) {
        this.memberType = memberType;
    }

    public void setNames(NamesModel names) {
        this.names = names;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContactDetailsProvider(ContactDetailsProvider contactDetailsProvider) {
        this.contactDetailsProvider = contactDetailsProvider;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public Bitmap getProfilePic() {

        return profilePic;
    }

    public MemberModel getMemberType() {
        return memberType;
    }

    public NamesModel getNames() {
        return names;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public ContactDetailsProvider getContactDetailsProvider() {
        return contactDetailsProvider;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }
}
