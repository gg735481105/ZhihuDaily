package com.azheng.hami.presenter.implePresenter;

import com.azheng.hami.api.ApiManage;
import com.azheng.hami.bean.ZhihuStory;
import com.azheng.hami.presenter.IZhihuStoryPresenter;
import com.azheng.hami.presenter.impleView.IZhihuStory;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ZhihuStoryPresenterImpl extends BasePresenterImpl implements IZhihuStoryPresenter {

    private IZhihuStory iZhihuStory;

    public ZhihuStoryPresenterImpl(IZhihuStory zhihuStory) {
        if (zhihuStory == null)
            throw new IllegalArgumentException("zhihuStory must not be null");
        iZhihuStory = zhihuStory;
    }

    @Override
    public void getZhihuStory(String id) {
        Subscription s = ApiManage.getInstence().getZhihuApiService().getZhihuStory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStory>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iZhihuStory.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuStory zhihuStory) {
                        iZhihuStory.showZhihuStory(zhihuStory);
                    }
                });
        addSubscription(s);
    }



}
