package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.Comments;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetCommentModel;
import com.example.luckychuan.myzhihudaily.model.GetCommentModelImpl;
import com.example.luckychuan.myzhihudaily.view.CommentView;

/**
 * Created by Luckychuan on 2017/6/5.
 */

public class CommentPresenter extends BasePresenter {

    private GetCommentModel mModel;
    private CommentView mView;

    public CommentPresenter(CommentView view){
        mView = view;
        mModel = new GetCommentModelImpl();
    }

    public void requestLongComment(String id){
        mView.showProgressDialog();
        mModel.getLongComment(id, new Callback<Comments>() {
            @Override
            public void onSuccess(Comments bean) {
                mView.initLongComment(bean.getComments());
                mView.hideProgressDialog();
            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
    }

    public void requestShortComment(String id){
        mView.showProgressDialog();
        mModel.getShortComment(id, new Callback<Comments>() {
            @Override
            public void onSuccess(Comments bean) {
                mView.initShortComment(bean.getComments());
                mView.hideProgressDialog();
            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
    }


}
