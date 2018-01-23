package co.za.geartronix.presenters;

import co.za.geartronix.activities.BaseActivity;

public abstract class BaseMenuPresenter extends BaseAppActivityPresenter  {

    public BaseMenuPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
    }

    public BaseMenuPresenter(BaseActivity baseActivity) {
        super(baseActivity);
    }

}