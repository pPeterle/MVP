package app.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Establishment implements Parcelable {
    private String uuid;
    private String user_uuid;
    private String photo;
    private String name;
    private String state;
    private String city;

    public Establishment(Parcel in) {
        uuid = in.readString();
        user_uuid = in.readString();
        photo = in.readString();
        name = in.readString();
        state = in.readString();
        city = in.readString();
    }

    public static final Creator<Establishment> CREATOR = new Creator<Establishment>() {
        @Override
        public Establishment createFromParcel(Parcel in) {
            return new Establishment(in);
        }

        @Override
        public Establishment[] newArray(int size) {
            return new Establishment[size];
        }
    };

    public Establishment() {

    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUserUUID() {
        return user_uuid;
    }

    public void setUserUUID(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uuid);
        parcel.writeString(user_uuid);
        parcel.writeString(name);
        parcel.writeString(state);
        parcel.writeString(city);
    }
}