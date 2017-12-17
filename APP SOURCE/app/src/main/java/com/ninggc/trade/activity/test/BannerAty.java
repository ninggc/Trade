package com.ninggc.trade.activity.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ninggc.trade.R;
import com.ninggc.trade.activity.base.BaseActivity;
import com.ninggc.trade.factory.constants.Constant;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;


/**
 * Created by Ning on 10/30/2017 0030.
 */

public class BannerAty extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.test_banner);
        super.onCreate(savedInstanceState);

        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        String[] images = {Constant.image1, Constant.image2, Constant.image3};
        banner.setImages(Arrays.asList(images));
        //banner设置方法全部调用完毕时最后调用
        banner.start();

//        String url1 = "http://api.kkmh.com/v1/topic_new/discovery_list?gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODM5MDM5MzE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tRmluZENhdGVnb3J5VGFiTmFtZSI6IuWFqOmDqCIsIklzQXV0b0xvYWQiOmZhbHNlLCIkbGliX3ZlcnNpb24iOiIxLjYuMzQiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiR3aWZpIjp0cnVlLCIkbWFudWZhY3R1cmVyIjoic2Ftc3VuZyIsIkZyb21GaW5kVGFiTmFtZSI6IuaOqOiNkCIsIiRzY3JlZW5faGVpZ2h0Ijo1NzYsIkNhdGVnb3J5Ijoi5peg5rOV6I635Y-WIiwiSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCJQcm9wZXJ0eUV2ZW50IjoiUmVhZEZpbmRQYWdlIiwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjQ2LCIkc2NyZWVuX3dpZHRoIjoxMDI0LCJGaW5kQ2F0ZWdvcnlUYWJOYW1lIjoi5YWo6YOoIiwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNNQ0MiLCIkbW9kZWwiOiJHVC1QNTIxMCIsIiRhcHBfdmVyc2lvbiI6IjMuOC4xIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5Iiwib3JpZ2luYWxfaWQiOiJBOjkwNTEwNDI3NjM3NTUxMDkiLCJldmVudCI6IlJlYWRGaW5kUGFnZSJ9";
//        RequestParams params1 = new RequestParams();
//        params1.setUri(url1);
//        x.http().get(params1, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(final String result) {
//                if (result != null) {
//                    Gson gson = new Gson();
//                    LunboImage lunboImage = gson.fromJson(result, LunboImage.class);
//                    images = new ArrayList<>();
//                    List<LunboImage.DataBean.InfosBean> infos = lunboImage.getData().getInfos();
//                    for (LunboImage.DataBean.InfosBean bean : infos) {
//                        List<LunboImage.DataBean.InfosBean.BannersBean> banners = bean.getBanners();
//                        for (LunboImage.DataBean.InfosBean.BannersBean ban : banners) {
//                            String pic = ban.getPic();
//                            images.add(pic);
//                        }
//                    }
//                    //设置banner样式
//                    // banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
//                    //设置图片的加载器
//                    banner.setImageLoader(new GliderImageloader());
//                    banner.setDelayTime(2000);
//                    //设置图片集合
//                    banner.setAlbumFiles(images);
//                    //banner设置方法全部调用完毕时最后调用
//                    banner.isAutoPlay(true);
//                    banner.start();
//                }
//            }
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//            }
//            @Override
//            public void onCancelled(CancelledException cex) {
//            }
//            @Override
//            public void onFinished() {
//            }
//        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
//            eg：

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }
}
