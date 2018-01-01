package co.za.geartronix.providers;

import android.graphics.Bitmap;
import java.util.List;

public class UserProvider {
    private Bitmap profilePic;
    private NamesProvider names;
    private int points, id;
    private ContactDetailsProvider contactDetailsProvider;
    private List<CarProvider> cars;
    private List<MessageProvider> messages;

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
}
