package co.za.geartronix.models;

import android.graphics.Bitmap;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class UserModel extends BaseModel{
    private Bitmap profilePic;
    private MemberModel memberType;
    private NamesModel names;
    private char gender;
    private int points;
    private int id;
    private int remoteId;
    private String city;
    private String idNo;
    private String password;
    private ContactDetailsModel contactDetailsProvider;
    private List<CarModel> cars;
    private List<MessageModel> messages;
    private ProgressBarModel progressBar1;
    private ProgressBarModel progressBar2;

    @Override
    public void setModel(JSONObject responseJson) throws JSONException {
        super.setModel(responseJson);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProgressBar2(ProgressBarModel progressBar2) {
        this.progressBar2 = progressBar2;
    }

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

    public ContactDetailsModel getContactDetailsProvider() {
        return contactDetailsProvider;
    }

    public void setContactDetailsProvider(ContactDetailsModel contactDetailsProvider) {
        this.contactDetailsProvider = contactDetailsProvider;
    }

    public void setMemberType(MemberModel memberType) {
        this.memberType = memberType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public MemberModel getMember() {
        return memberType;
    }

    public void memberType(MemberModel userType) {
        this.memberType = userType;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}