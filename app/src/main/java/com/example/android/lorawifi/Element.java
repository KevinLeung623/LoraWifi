package com.example.android.lorawifi;

/**
 * Created by Kevin on 9/11/2017.
 */

public class Element {
        private String ssid;
        private String bssid;
        private String rssi;

        public String getSsid() {
            return ssid;
        }

        public String getBssid() {
            return bssid;
        }

        public String getRssi() {
            return rssi;
        }

        public Element(String ssid, String bssid, String rssi) {
            this.ssid = ssid;
            this.bssid = bssid;
            this.rssi = rssi;
        }
}
