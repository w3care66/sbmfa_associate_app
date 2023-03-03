package com.associate.sbmfa.Utils;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;

public class Underlinetext {
    public SpannableString getUnderline(String s){
        SpannableString content = new SpannableString(s);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        return  content;
    }
}
