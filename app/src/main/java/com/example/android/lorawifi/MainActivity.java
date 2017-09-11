package com.example.android.lorawifi;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.wifi.WifiManager;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

import static android.media.CamcorderProfile.get;


public class MainActivity extends AppCompatActivity {
    private Element[] nets;
    private WifiManager wifiManager;
    private List<ScanResult> wifiList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view) {

                DisplayWifiInfo();
            }
        });
        DisplayWifiInfo();
    }

    public void DisplayWifiInfo()   {
        this.wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        this.wifiManager.startScan();
        this.wifiList = this.wifiManager.getScanResults();
        this.nets = new Element[wifiList.size()];
        for (int i = 0; i<wifiList.size(); i++){
            String item = wifiList.get(i).toString();
            String[] vector_item = item.split(",");
            String item_essid = vector_item[0];
            String item_capabilities = vector_item[1];
            String item_level = vector_item[3];
            String ssid = item_essid.split(": ")[1];
            String security = item_capabilities.split(": ")[1];
            String level = item_level.split(":")[1];
            nets[i] = new Element(ssid, security, level);
        }

        AdapterElements adapterElements = new AdapterElements(this);
        ListView netList = (ListView) findViewById(R.id.WifiList);
        netList.setAdapter(adapterElements);
    }

    class AdapterElements extends ArrayAdapter<Object>{
        Activity context;
        public AdapterElements(Activity context){
            super(context, R.layout.items, nets);
            this.context = context;
        }
        public View getView(int position, View convetrView, View parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.items,null);
            TextView WifiSSID = (TextView) item.findViewById(R.id.WifiSSID);
            TextView WifiBSSID = (TextView) item.findViewById(R.id.WifiBSSID);
            TextView WifiRSSI = (TextView) item.findViewById(R.id.WifiRSSI);
            WifiSSID.setText(nets[position].getSsid());
            WifiBSSID.setText(nets[position].getBssid());
            WifiRSSI.setText(nets[position].getRssi());
            return item;
        }

    }
}
