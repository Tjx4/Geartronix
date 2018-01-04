package co.za.geartronix.activities;


import android.os.Bundle;
public abstract class BaseAsyncActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
/*
    protected int[] getWidthAndHeight() {
        int Measuredwidth = 0;
        int Measuredheight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
            w.getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }

        return new int[]{Measuredwidth, Measuredheight};
    }

    protected int getWidth() { return getWidthAndHeight()[1];}
    protected int getHeight() {return getWidthAndHeight()[1];}
    protected void openNewActivityWithNoPayload(Class activity) {
        Intent i = createIntent(activity);
        callStartActivity(i);
    }

    protected void openNewActivityWithPayload(Class activity, Bundle payload) {
        Intent i = createIntent(activity);
        i.putExtras(payload);
        callStartActivity(i);
    }

    private Intent createIntent(Class activity) {
        return new Intent(context, activity);
    }

    private void callStartActivity(Intent i) {
        startActivity(i);
    }

    */

}