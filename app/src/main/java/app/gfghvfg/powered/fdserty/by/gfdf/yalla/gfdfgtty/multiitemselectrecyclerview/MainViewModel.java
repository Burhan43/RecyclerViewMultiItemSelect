package app.gfghvfg.powered.fdserty.by.gfdf.yalla.gfdfgtty.multiitemselectrecyclerview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<String> mutableLiveData = new MutableLiveData<>();


    public void setText(String str) {
        mutableLiveData.setValue(str);

    }

    public MutableLiveData<String> getText() {
        return mutableLiveData;

    }
}
