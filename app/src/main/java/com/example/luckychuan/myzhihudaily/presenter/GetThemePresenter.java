package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.Theme;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetThemeModel;
import com.example.luckychuan.myzhihudaily.model.GetThemeModelImpl;
import com.example.luckychuan.myzhihudaily.view.ThemeView;

/**
 * Created by Luckychuan on 2017/5/4.
 */
public class GetThemePresenter extends BasePresenter {

    private ThemeView mView;
    private GetThemeModel mModel;

    public GetThemePresenter(ThemeView view){
        mView = view;
        mModel = new GetThemeModelImpl();
    }

    public void requestData(){
        mModel.getTheme(new Callback<Theme>() {
            @Override
            public void onSuccess(Theme bean) {
                mView.setDrawerItem(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }
        });
    }


}
