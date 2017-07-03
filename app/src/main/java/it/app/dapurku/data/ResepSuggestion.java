package it.app.dapurku.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.List;

import it.app.dapurku.model.DiscoverResponse;

/**
 * Created by Islam on 12/05/2016.
 */
public class ResepSuggestion implements SearchSuggestion {
    private DiscoverResponse mResult;
    private String name;
    private int id;
    private boolean mIsHistory;

    public ResepSuggestion(DiscoverResponse mResult) {
        this.mResult = mResult;
        this.name = mResult.getResepNama();
        this.id = mResult.getResepId();
    }

    public ResepSuggestion(Parcel source){
        this.name = source.readString();
    }

    public DiscoverResponse getmResult(){
        return mResult;
    }

    public void setmIsHistory(boolean isHistory){
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory(){
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return mResult.getResepNama();
    }

    public int getId(){
        return mResult.getResepId();
    }

    @Override
    public Creator getCreator() {
        return CREATOR;
    }

    public static final Creator<ResepSuggestion> CREATOR = new Creator<ResepSuggestion>() {
        @Override
        public ResepSuggestion createFromParcel(Parcel source) {
            return new ResepSuggestion(source);
        }

        @Override
        public ResepSuggestion[] newArray(int size) {
            return new ResepSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
