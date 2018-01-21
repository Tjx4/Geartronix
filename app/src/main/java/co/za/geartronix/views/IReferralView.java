package co.za.geartronix.views;

import co.za.geartronix.presenters.ReferralPresenter;

public interface IReferralView extends IBaseAsyncView {
    ReferralPresenter getPresenter();
}
