package com.dpcsa.jura.compon.interfaces_classes;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public interface IVolleyListener extends Response.ErrorListener{
        public void onErrorResponse(VolleyError error);

        public void onResponse(String response);
}
