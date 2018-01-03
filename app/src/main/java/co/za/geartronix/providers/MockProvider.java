package co.za.geartronix.providers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import co.za.geartronix.R;
import co.za.geartronix.activities.BaseActivity;

public class MockProvider {

    private BaseActivity activity;

    public MockProvider(BaseActivity activity) {
        this.activity = activity;
    }

    public UserProvider getMockUser()
    {
        UserProvider mockUser = new UserProvider();
        mockUser.setId(10);

        NamesProvider names = new NamesProvider();
        names.setFirstName("Tshepo");
        names.setSecondName("Lucky");
        names.setSurName("Baloyi");
        mockUser.setNames(names);

        mockUser.setPoints(1);

        MemberProvider memberType = new MemberProvider();
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

        List<MessageProvider> messages = new ArrayList<>();
        MessageProvider message = new MessageProvider();
        message.setMessage("Welcome to geartronix this is a test message");
        message.setDate(null);
        message.setSender(0);
        message.setHeader("Message header");
        message.setRead(false);
        Bitmap imageAttachement = BitmapFactory.decodeResource( activity.getResources(), R.drawable.workshop2 );
        message.setImageAttachment(imageAttachement);
        MessageProvider message2 = new MessageProvider();
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

        ProgressBarProvider progressBar1 = new ProgressBarProvider();
        progressBar1.setBarValueA(80);
        progressBar1.setBarValueB(progressBar1.getBarValueA() / 2);
        mockUser.setProgressBar1(progressBar1);

        ProgressBarProvider progressBar2 = new ProgressBarProvider();
        progressBar2.setBarValueA(50);
        progressBar2.setBarValueB(progressBar2.getBarValueA() / 2);
        mockUser.setProgressBar2(progressBar2);

        return mockUser;
    }
}
