package co.za.geartronix.presenters;

import co.za.geartronix.activities.BaseActivity;

public abstract class BaseOverflowMenuPresenter extends BaseAppActivityPresenter  {

    public BaseOverflowMenuPresenter(BaseActivity baseActivity, int index) {
        super(baseActivity, index);
    }

    public BaseOverflowMenuPresenter(BaseActivity baseActivity) {
        super(baseActivity);
    }

}