package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.content.Context;
import android.text.Html;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.widget.GlideCircleTransform;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.luckychuan.myzhihudaily.R.id.comment_avatar;

/**
 * Created by Luckychuan on 2017/9/3.
 */

public class CommentViewHolder extends BaseViewHolder<Comment> {

    private Context context;
    private TextView mAuthor;
    private TextView mLike;
    private TextView mContent;
    private TextView mReplyTo;
    private TextView mTime;
    private ImageView mAvatar;
    private Button mButton;
    //展开更多replyTo的item,点击展开时add,收回时remove
    private static List<Integer> mShowMoreList = new ArrayList<>();

    public CommentViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mAuthor = (TextView) itemView.findViewById(R.id.comment_author);
        mLike = (TextView) itemView.findViewById(R.id.comment_like);
        mContent = (TextView) itemView.findViewById(R.id.comment_content);
        mReplyTo = (TextView) itemView.findViewById(R.id.comment_replyTo);
        mTime = (TextView) itemView.findViewById(R.id.comment_time);
        mAvatar = (ImageView) itemView.findViewById(comment_avatar);

        //设置作者名字加粗
        TextPaint paint = mAuthor.getPaint();
        paint.setFakeBoldText(true);

        mButton = (Button) itemView.findViewById(R.id.comment_showMore);
        mButton.setVisibility(View.INVISIBLE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isShowMore = false;
                for (Integer i : mShowMoreList) {
                    if (i == getLayoutPosition()) {
                        isShowMore = true;
                        break;
                    }
                }
                if (!isShowMore) {
                    mShowMoreList.add(Integer.valueOf(getLayoutPosition()));
                    mButton.setText("收起");
                    mReplyTo.setMaxLines(99);
                } else {
                    mShowMoreList.remove(Integer.valueOf(getLayoutPosition()));
                    mButton.setText("展开");
                    mReplyTo.setMaxLines(2);
                }

            }
        });


    }

    @Override
    public void bindViewHolder(Comment bean) {

    }


    @Override
    public void bindViewHolder(Comment bean, final int position) {
        mAuthor.setText(bean.getAuthor());
        mLike.setText(bean.getLikes() + "");
        mContent.setText(bean.getContent());

        if (bean.getReplyTo() != null) {
            mReplyTo.setVisibility(View.VISIBLE);
            mReplyTo.setMaxLines(99);
            mReplyTo.setText(Html.fromHtml("<b>//" + bean.getReplyTo().getAuthor() + "：</b>" + bean.getReplyTo().getContent()));
            //获取textView的长度
            mReplyTo.post(new Runnable() {
                @Override
                public void run() {
                    if (mReplyTo.getLineCount() > 2) {
                        mButton.setVisibility(View.VISIBLE);

                        //判断是否已经展开
                        if (mShowMoreList.size() == 0) {
                            mButton.setText("展开");
                            mReplyTo.setMaxLines(2);
                        } else {
                            for (Integer i : mShowMoreList) {
                                if (i == position) {
                                    mButton.setText("收起");
                                    mReplyTo.setMaxLines(99);
                                    break;
                                } else {
                                    mButton.setText("展开");
                                    mReplyTo.setMaxLines(2);
                                }
                            }
                        }

                    } else {
                        //行数不大于2的replyTo不用展开
                        mButton.setVisibility(View.INVISIBLE);
                    }
                }
            });

            if (bean.getReplyTo().getAuthor() == null) {
                mReplyTo.setText("抱歉！原点评已被删除");
                mButton.setVisibility(View.INVISIBLE);
            }
        } else {
            //没有replyTo，不显示展开按钮
            mButton.setVisibility(View.INVISIBLE);
            mReplyTo.setVisibility(View.GONE);
            mReplyTo.setText("");
        }

        //设置回复时间
        //api提供的时间数据有误，少了3位
        mTime.setText(new SimpleDateFormat("MM-dd HH:mm").format(new Date(bean.getTime()*1000)));

        Glide.with(context)
                .load(bean.getAvatar())
                .placeholder(R.color.white)
                .error(R.color.white)
                .transform(new GlideCircleTransform(context))
                .into(mAvatar);

    }


    public static void clearShowMoreList() {
        mShowMoreList.clear();
    }
}
