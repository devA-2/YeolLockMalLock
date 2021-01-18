package com.androidstudy.ylml_test_web;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Base64;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    TextView tv_urlBox, tv_data;
    EditText ev_ipAddress, ev_port, ev_projectName;

    Button btn_openLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getSharedPreferences("url", MODE_PRIVATE);
        setLayout();
        setListener();
        setUrlText();



        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        NfcManager nfcManager= (NfcManager) getSystemService(Context.NFC_SERVICE);
        NfcAdapter nfcAdapter= null;
        if (nfcManager != null) {
            nfcAdapter = nfcManager.getDefaultAdapter();
        }else{
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            String data = new StringBuffer()
                    .append("DeviceId : ")
                    .append(tm.getDeviceId())
//                        .append("\nIMEI : ")
//                        .append(tm.getImei())
                    .append("\nPhone Number : ")
                    .append(tm.getLine1Number())
                    .append("\nSim Serial Number : ")
                    .append(tm.getSimSerialNumber())
                    .append("\nSim Operator Name : ")
                    .append(tm.getSimOperatorName())
//                        .append("\nMeid : ")
//                        .append(tm.getMeid())
                    .append("\nAndroid ID : ")
                    .append(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID))
                    .append("\nWidevineID : ")
                    .append(getWidevineID())
                    .append("\nNFC : ")
                    .append("")
                    .toString();
            tv_data.setText(data);
        }
    }

    private String getWidevineID(){
        UUID WIDEVINE_UUID = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        MediaDrm wvDrm = null;
        try {
            wvDrm = new MediaDrm(WIDEVINE_UUID);
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
        //assert wvDrm != null;
        byte[] widevineId = wvDrm != null ? wvDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID) : new byte[0];

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return Base64.getEncoder().encodeToString(widevineId).trim();
        }else{
            return "error";
        }
    }
    //리스너 설정
    private void setListener() {
        btn_openLink.setOnClickListener(view -> {
            Intent intent2 =new Intent(this, WebViewActivity.class);
            String url_text=tv_urlBox.getText().toString();
            //Toast.makeText(getApplication(),et_urlBox.getText(),Toast.LENGTH_SHORT).show();
            intent2.putExtra("url_text", url_text);

            startActivity(intent2);
        });

        TextWatcher event =new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(getApplication(),"동작함",Toast.LENGTH_SHORT).show();
                setUrlText();
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        };

        ev_ipAddress.addTextChangedListener(event);
        ev_port.addTextChangedListener(event);
        ev_projectName.addTextChangedListener(event);

    }




    private void setUrlText() {
        tv_urlBox.setText(
                new StringBuilder().append("http://")
                        .append(ev_ipAddress.getText().toString())
                        .append(":")
                        .append(ev_port.getText().toString())
                        .append("/")
                        .append(ev_projectName.getText().toString()
                        ).toString());
    }

    //View 불러오기
    private void setLayout(){
        tv_urlBox=findViewById(R.id.tv_urlBox);
        tv_data=findViewById(R.id.tv_data);
        ev_ipAddress=findViewById(R.id.ev_ipAddress);
        ev_port=findViewById(R.id.ev_port);
        ev_projectName=findViewById(R.id.ev_projectName);

        btn_openLink=findViewById(R.id.btn_openLink);

        String ev_ipAddressS=sp.getString("ev_ipAddressS","");
        String ev_portS=sp.getString("ev_portS","");
        String ev_projectNameS=sp.getString("ev_projectNameS","");

        ev_ipAddress.setText(ev_ipAddressS);
        ev_port.setText(ev_portS);
        ev_projectName.setText(ev_projectNameS);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String ev_ipAddressS=ev_ipAddress.getText().toString();
        String ev_portS=ev_port.getText().toString();
        String ev_projectNameS=ev_projectName.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ev_ipAddressS", ev_ipAddressS);
        editor.putString("ev_portS", ev_portS);
        editor.putString("ev_projectNameS", ev_projectNameS);
        editor.apply();
    }




    @Override
    protected void onPause() {
        super.onPause();
    }
}