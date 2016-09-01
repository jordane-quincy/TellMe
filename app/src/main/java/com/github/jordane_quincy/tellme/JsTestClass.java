package com.github.jordane_quincy.tellme;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class JsTestClass {
    Context context;

    JsTestClass(Context context) {
        context = context;
    }

    @JavascriptInterface
    public String toString() { return "method toString() de la classe JsTestClass"; }
}
