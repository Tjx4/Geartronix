package co.za.geartronix.providers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.List;
import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;
import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.MessageModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.ProgressBarModel;
import co.za.geartronix.models.ServiceModel;
import co.za.geartronix.models.UserModel;

public class MockProvider {

    private BaseActivity activity;

    public MockProvider(BaseActivity activity) {
        this.activity = activity;
    }

    public List<ServiceModel>  getMockServiceList() {
        ServiceModel service1 = new ServiceModel();
        service1.setId(0);
        service1.setService("Update the ECU software");
        service1.setServiceDescription("The scope of the update services in a transmission control unit includes the diagnostic procedure, the determination of the software version and the updating to the latest version. Depending on the specification, the reset of the adaptation values is necessary and is therefore carried out automatically.");

        ServiceModel service2 = new ServiceModel();
        service2.setId(1);
        service2.setService("Software changes");
        service2.setServiceDescription("We have the possibility, if the hardware allows, to reprogram a transmission control unit of a vehicle for another vehicle. For example, we are able to reprogram a Multitronic control unit of an Audi A8 3.0 petrol engine for an Audi A4 1.9 TDI. For further information on which hardware fits all vehicles, please contact us by e-mail or telephone.");

        ServiceModel service3 = new ServiceModel();
        service3.setId(2);
        service3.setService("Repair the transmission controls");
        service3.setServiceDescription("Typical faults of the individual sensors, such as speed sensors or the speed sensors, as well as the solenoid valves of the most widely used control units from Audi, VW, can be fixed at a fair price without having to exchange the entire control system. After an accurate diagnosis, we are able to determine whether your control unit can be repaired at a reasonable price. After repair, there is no need to re-program or encode the transmission control unit, which in turn significantly reduces the repair costs as well as the time required.");

        ServiceModel service4 = new ServiceModel();
        service4.setId(3);
        service4.setService("Remote diagnosis");
        service4.setServiceDescription("Our modern diagnostic platform significantly simplifies all diagnostic procedures for the given autotype. With the help of our interface, it is possible to read out all necessary transmission parameters in the installed state of the control unit via the OBD socket of the vehicle, as well as in the removed state via the transmission plug, and to determine the type of damage. Simply send us the data online. After the evaluation, we are able to identify the most common problems. You will receive a summary of the steps necessary to correct the damage.");

        List<ServiceModel> serviceList = new ArrayList<>();
        serviceList.add(service1);
        serviceList.add(service2);
        serviceList.add(service3);
        serviceList.add(service4);

        return serviceList;
    }

    public UserModel getMockUser()
    {
        UserModel mockUser = new UserModel();
        mockUser.setId(1);

        NamesModel names = new NamesModel();
        names.setFirstName("Tshepo");
        names.setSecondName("Lucky");
        names.setSurName("Baloyi");
        mockUser.setNames(names);

        mockUser.setPoints(10);

        MemberModel memberType = new MemberModel();
        memberType.setMemberType(1);
        mockUser.memberType(memberType);

        mockUser.setCity("Pretoria");

        Bitmap propic = BitmapFactory.decodeResource( activity.getResources(), R.drawable.profpic );
        mockUser.setProfilePic(propic);

        ContactDetailsProvider contactDetailes = new ContactDetailsProvider();
        int[] contactnumbers = new int[]{823835792, 842630120};
        contactDetailes.setContactNumbers(contactnumbers);
        String[] emails = new String[]{"rocboyt@gmail.com", "tlmahlaula@gmail.com"};
        contactDetailes.setEmails(emails);
        mockUser.setContactDetailsProvider(contactDetailes);

        List<MessageModel> messages = new ArrayList<>();
        MessageModel message = new MessageModel();
        message.setMessage("Welcome to geartronix this is a test message");
        message.setDate(null);
        message.setSender(0);
        message.setHeader("Message header");
        message.setRead(false);
        Bitmap imageAttachement = BitmapFactory.decodeResource( activity.getResources(), R.drawable.workshop2 );
        message.setImageAttachment(imageAttachement);
        MessageModel message2 = new MessageModel();
        message2.setMessage("Hello again this is the second test message");
        message2.setDate(null);
        message2.setSender(0);
        message2.setHeader("Message 2 header");
        message2.setRead(false);
        message2.setImageAttachment(null);
        messages.add(message);
        messages.add(message2);
        mockUser.setMessages(messages);

        List<CarProvider> cars = new ArrayList<>();
        CarProvider car = new CarProvider();
        car.setMake("VW");
        car.setColor("white");
        car.setModel("Polo GTI");
        car.setModelYear(null);
        Bitmap carPic = BitmapFactory.decodeResource( activity.getResources(), R.drawable.workshop2 );
        car.setPicture(carPic);
        cars.add(car);
        mockUser.setCars(cars);

        ProgressBarModel progressBar1 = new ProgressBarModel();
        progressBar1.setBarValueA(80);
        progressBar1.setBarValueB(progressBar1.getBarValueA() / 2);
        mockUser.setProgressBar1(progressBar1);

        ProgressBarModel progressBar2 = new ProgressBarModel();
        progressBar2.setBarValueA(50);
        progressBar2.setBarValueB(progressBar2.getBarValueA() / 2);
        mockUser.setProgressBar2(progressBar2);

        return mockUser;
    }
}
