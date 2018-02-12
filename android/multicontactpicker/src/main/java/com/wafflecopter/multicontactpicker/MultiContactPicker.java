package com.wafflecopter.multicontactpicker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.wafflecopter.multicontactpicker.RxContacts.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiContactPicker {

    public static class Builder implements Serializable {

        protected transient Activity acc;
        protected transient Fragment frag;
        @StyleRes
        protected int theme = R.style.MultiContactPicker_Azure;
        protected int bubbleColor;
        protected int bubbleTextColor;
        protected int handleColor;
        protected int trackColor;
        protected Integer searchIconColor;
        protected boolean hideScrollbar;
        protected boolean showTrack = true;
        public ArrayList ids;
        protected boolean multiSelectEnabled;

        public Builder(@NonNull Activity act) {
            this.acc = act;
        }

        public Builder(@NonNull Fragment frag) {
            this.frag = frag;
        }

        public Builder theme(@StyleRes int theme) {
            this.theme = theme;
            return this;
        }

        public Builder bubbleColor(@ColorInt int bubbleColor) {
            this.bubbleColor = bubbleColor;
            return this;
        }

        public Builder bubbleTextColor(@ColorInt int textColor) {
            this.bubbleTextColor = textColor;
            return this;
        }

        public Builder ids(ArrayList ids) {
            this.ids = ids;
            return this;
        }

        public Builder handleColor(@ColorInt int handleColor) {
            this.handleColor = handleColor;
            return this;
        }

        public Builder trackColor(@ColorInt int trackColor) {
            this.trackColor = trackColor;
            return this;
        }

        public Builder searchIconColor(@ColorInt Integer searchIconColor) {
            this.searchIconColor = searchIconColor;
            return this;
        }

        public Builder hideScrollbar(boolean hideScrollbar) {
            this.hideScrollbar = hideScrollbar;
            return this;
        }

        public Builder showTrack(boolean showTrack) {
            this.showTrack = showTrack;
            return this;
        }

        public void showPickerForResult(int requestCode) {
            if (acc == null) {
                return;
            }
            Intent intent = new Intent(acc, MultiContactPickerActivity.class);
            intent.putExtra("builder", this);
            if (frag != null) {
                frag.startActivityForResult(intent, requestCode);
            } else {
                acc.startActivityForResult(intent, requestCode);
            }
        }

        public Builder multiSelectEnabled(boolean b) {
            this.multiSelectEnabled = b;
            return this;
        }
    }

    static ArrayList<ContactResult> buildResult(List<Contact> selectedContacts){
        ArrayList<ContactResult> contactResults = new ArrayList<>();
        for(Contact contact : selectedContacts){
            contactResults.add(new ContactResult(contact));
        }
        return contactResults;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<ContactResult> obtainResult(Intent data){
        return data.getParcelableArrayListExtra(MultiContactPickerActivity.EXTRA_RESULT_SELECTION);
    }

    public static ContactResult obtainContact(Intent data){
        return data.getParcelableExtra(MultiContactPickerActivity.EXTRA_RESULT_SELECTION);
        //return data.getParcelableArrayListExtra(MultiContactPickerActivity.EXTRA_RESULT_SELECTION);
    }

}
