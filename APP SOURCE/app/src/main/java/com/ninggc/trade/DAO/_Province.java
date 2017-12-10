package com.ninggc.trade.DAO;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;
import com.ninggc.trade.address.IEntity;

import java.util.List;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class _Province implements IBean, Parcelable, IEntity {
    @JSONField(name = "id")
    private String id;

    @JSONField(name = "name")
    private String name;

    /**
     * 子项。
     */
    @JSONField(name = "children")
    private List<Campus> campusList;
    /**
     * 是否选中。
     */
    private boolean isSelect;

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

    public List<Campus> getCampusList() {
        return campusList;
    }

    public void setCampusList(List<Campus> campusList) {
        this.campusList = campusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        _Province province = (_Province) o;

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


    public _Province() {}

    protected _Province(Parcel in) {
        id = in.readString();
        name = in.readString();
        campusList = in.createTypedArrayList(Campus.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeTypedList(campusList);
    }

    public static final Creator<_Province> CREATOR = new Creator<_Province>() {

        @Override
        public _Province createFromParcel(Parcel source) {
            return new _Province(source);
        }

        @Override
        public _Province[] newArray(int size) {
            return new _Province[size];
        }
    };

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(name);
//        dest.writeTypedList(campusList);
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
