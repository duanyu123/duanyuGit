package example.ykdemon9_20.bean;

// FIXME generate failure  field _$236
// FIXME generate failure  field _$Comment160

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 段昱 on 2017/9/20.
 */

public class Data {
    public long code;
    public String msg;
    @SerializedName("[]")
    public List<zqfBean> zqf;

    public static class zqfBean {

        public MomentBean Moment;
        public UserBean User;
        @SerializedName("Comment[]")
        public List<CommentzqfBean> Commentzqf;

        public static class MomentBean {
            public long id;
            public long userId;
            public String date;
            public String content;
            public List<Long> praiseUserIdList;
            public List<String> pictureList;
        }

        public static class UserBean {
            public long id;
            public String name;
            public String head;
        }

        public static class CommentzqfBean {

            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;

            public static CommentzqfBean objectFromData(String str) {
                return new Gson().fromJson(str, CommentzqfBean.class);
            }
        }

        public static class Comment {
            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;
        }
    }
}

