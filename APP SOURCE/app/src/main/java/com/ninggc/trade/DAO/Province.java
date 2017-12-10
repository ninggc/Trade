package com.ninggc.trade.DAO;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import com.ninggc.trade.address.IEntity;

import java.util.List;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class Province implements IBean, Parcelable, IEntity {
    @SerializedName("id")
    private String id;

    @JSONField(name = "name")
    private String name;

    /**
     * 子项。
     */
    @SerializedName("children")
    private List<String> children;
    /**
     * 是否选中。
     */
    private boolean isSelect;

    protected Province(Parcel in) {
        id = in.readString();
        name = in.readString();
        children = in.createStringArrayList();
        isSelect = in.readByte() != 0;
    }

    public static final Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel in) {
            return new Province(in);
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<String> getCampusList() {
        return children;
    }

    public void setCampusList(List<String> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Province province = (Province) o;

        if (id != province.id) return false;
        return name != null ? name.equals(province.name) : province.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isSelect ? 1 : 0);
        return result;
    }


    public Province() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeStringList(children);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }


//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(name);
//        dest.writeTypedList(children);
//        dest.writeByte((byte) (isSelect ? 1 : 0));
//    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<String> CREATOR = new Creator<Province>() {
//        @Override
//        public String createFromParcel(Parcel in) {
//            return new String(in);
//        }
//
//        @Override
//        public String[] newArray(int size) {
//            return new String[size];
//        }
//    };
}
