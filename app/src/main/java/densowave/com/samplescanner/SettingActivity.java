//**************************************************************************//
//***                                                                    ***//
//***    Product Name    : Sample Program for BHT                        ***//
//***    File Name       : SettingActivity.java                          ***//
//***                                                                    ***//
//***    Edition History                                                 ***//
//***    Version  Date      Comments                                     ***//
//***    -------  --------  ---------------------------------------------***//
//***    1.0.0    17/3/1  Original                                       ***//
//***                                                                    ***//
//***                    Copyright (C) 2017 DENSO WAVE INCORPORATED      ***//
//***                    All rights reserved.                            ***//
//**************************************************************************//
//+------------------------------------------------------------------------+//
//|                                                                        |//
//|  DENSO WAVE INCORPORATED retains the copyright to this source code,    |//
//|  but allows users to use it, in whole or in part, and make             |//
//|  modifications without prior permission of DENSO WAVE INCORPORATED.    |//
//|  DENSO WAVE INCORPORATED shall not, however, be held responsible for   |//
//|  any damages arising from such use or modifications.                   |//
//|                                                                        |//
//+------------------------------------------------------------------------+//


package densowave.com.samplescanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.Toast;

import com.densowave.barcode.GeneralString;
import com.densowave.barcode.ReaderManager;
import com.densowave.barcode.decoder.ClResult;
import com.densowave.barcode.decoder.Decoders;
import com.densowave.barcode.decoder.Enable_State;


public class SettingActivity extends AppCompatActivity {
    private ReaderManager _readerManager;

    private final BroadcastReceiver myDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case GeneralString.Intent_READERSERVICE_CONNECTED:
                    // Connected to the Reader service(Barcode APIs are available after this event)
                    //Get Barcode Enable state
                    GetSettings();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Create Intent Filter to get event from Reader Service
        IntentFilter _filter = new IntentFilter();
        _filter.addAction(GeneralString.Intent_READERSERVICE_CONNECTED);
        registerReceiver(myDataReceiver, _filter);

        //Initialise Reader Instance
        _readerManager = ReaderManager.InitInstance(this);
    }

    @Override
    protected void onPause(){
        super.onPause();

        // Apply Settings and Release Reader Resources
        unregisterReceiver(myDataReceiver);
        SetSettings();
        _readerManager.Release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void GetSettings(){
        ClResult ret;
        Decoders settings = new Decoders();

        // Get current settings of reader
        ret = _readerManager.Get_Decoders_Status(settings);
        if (ret  != ClResult.S_OK){
            Toast.makeText(this, "Fail to Get_Decoders_Status()", Toast.LENGTH_SHORT).show();
            return;
        }

        CheckBox chk1 = (CheckBox)  findViewById(R.id.checkBox1);
        CheckBox chk2 = (CheckBox)  findViewById(R.id.checkBox2);
        CheckBox chk3 = (CheckBox)  findViewById(R.id.checkBox3);
        CheckBox chk4 = (CheckBox)  findViewById(R.id.checkBox4);
        CheckBox chk5 = (CheckBox)  findViewById(R.id.checkBox5);
        CheckBox chk6 = (CheckBox)  findViewById(R.id.checkBox6);
        CheckBox chk7 = (CheckBox)  findViewById(R.id.checkBox7);
        CheckBox chk8 = (CheckBox)  findViewById(R.id.checkBox8);
        CheckBox chk9 = (CheckBox)  findViewById(R.id.checkBox9);
        CheckBox chk10 = (CheckBox) findViewById(R.id.checkBox10);
        CheckBox chk11 = (CheckBox) findViewById(R.id.checkBox11);
        CheckBox chk12 = (CheckBox) findViewById(R.id.checkBox12);
        CheckBox chk13 = (CheckBox) findViewById(R.id.checkBox13);
        CheckBox chk14 = (CheckBox) findViewById(R.id.checkBox14);
        CheckBox chk15 = (CheckBox) findViewById(R.id.checkBox15);
        CheckBox chk16 = (CheckBox) findViewById(R.id.checkBox16);
        CheckBox chk17 = (CheckBox) findViewById(R.id.checkBox17);
        CheckBox chk18 = (CheckBox) findViewById(R.id.checkBox18);
        CheckBox chk19 = (CheckBox) findViewById(R.id.checkBox19);
        CheckBox chk20 = (CheckBox) findViewById(R.id.checkBox20);
        CheckBox chk21 = (CheckBox) findViewById(R.id.checkBox21);
        CheckBox chk22 = (CheckBox) findViewById(R.id.checkBox22);
        CheckBox chk23 = (CheckBox) findViewById(R.id.checkBox23);
        CheckBox chk24 = (CheckBox) findViewById(R.id.checkBox24);
        CheckBox chk25 = (CheckBox) findViewById(R.id.checkBox25);
        CheckBox chk26 = (CheckBox) findViewById(R.id.checkBox26);
        CheckBox chk27 = (CheckBox) findViewById(R.id.checkBox27);
        CheckBox chk28 = (CheckBox) findViewById(R.id.checkBox28);
        CheckBox chk29 = (CheckBox) findViewById(R.id.checkBox29);
        CheckBox chk30 = (CheckBox) findViewById(R.id.checkBox30);
        CheckBox chk31 = (CheckBox) findViewById(R.id.checkBox31);
        CheckBox chk32 = (CheckBox) findViewById(R.id.checkBox32);
        CheckBox chk33 = (CheckBox) findViewById(R.id.checkBox33);
        CheckBox chk34 = (CheckBox) findViewById(R.id.checkBox34);
        CheckBox chk35 = (CheckBox) findViewById(R.id.checkBox35);
        CheckBox chk36 = (CheckBox) findViewById(R.id.checkBox36);
        CheckBox chk37 = (CheckBox) findViewById(R.id.checkBox37);
        CheckBox chk38 = (CheckBox) findViewById(R.id.checkBox38);
        CheckBox chk39 = (CheckBox) findViewById(R.id.checkBox39);
        CheckBox chk40 = (CheckBox) findViewById(R.id.checkBox40);
        CheckBox chk41 = (CheckBox) findViewById(R.id.checkBox41);
        CheckBox chk42 = (CheckBox) findViewById(R.id.checkBox42);

        if (settings.enableAustrailianPostal == Enable_State.FALSE) {
            chk1.setChecked(false);
        }else{
            chk1.setChecked(true);}

        if (settings.enableAztec == Enable_State.FALSE) {
            chk2.setChecked(false);
        }else{
            chk2.setChecked(true);}

        if (settings.enableCompositeCC_AB == Enable_State.FALSE) {
            chk3.setChecked(false);
        }else{
            chk3.setChecked(true);}

        if (settings.enableCompositeCC_C == Enable_State.FALSE) {
            chk4.setChecked(false);
        }else{
            chk4.setChecked(true);}

        if (settings.enableCompositeTlc39 == Enable_State.FALSE) {
            chk5.setChecked(false);
        }else{
            chk5.setChecked(true);}

        if (settings.enableCode11 == Enable_State.FALSE) {
            chk6.setChecked(false);
        }else{
            chk6.setChecked(true);}

        if (settings.enableCode39 == Enable_State.FALSE) {
            chk7.setChecked(false);
        }else{
            chk7.setChecked(true);}

        if (settings.enableCode93 == Enable_State.FALSE) {
            chk8.setChecked(false);
        }else{
            chk8.setChecked(true);}

        if (settings.enableCode128 == Enable_State.FALSE) {
            chk9.setChecked(false);
        }else{
            chk9.setChecked(true);}

        if (settings.enableCodabar == Enable_State.FALSE) {
            chk10.setChecked(false);
        }else{
            chk10.setChecked(true);}

        if (settings.enableChinese2Of5 == Enable_State.FALSE) {
            chk11.setChecked(false);
        }else{
            chk11.setChecked(true);}

        if (settings.enableDataMatrix == Enable_State.FALSE) {
            chk12.setChecked(false);
        }else{
            chk12.setChecked(true);}

        if (settings.enableDutchPostal == Enable_State.FALSE) {
            chk13.setChecked(false);
        }else{
            chk13.setChecked(true);}

        if (settings.enableEanJan8 == Enable_State.FALSE) {
            chk14.setChecked(false);
        }else{
            chk14.setChecked(true);}

        if (settings.enableEanJan13 == Enable_State.FALSE) {
            chk15.setChecked(false);
        }else{
            chk15.setChecked(true);}

        if (settings.enableGs1128 == Enable_State.FALSE) {
            chk16.setChecked(false);
        }else{
            chk16.setChecked(true);}

        if (settings.enableGs1DataBar14 == Enable_State.FALSE) {
            chk17.setChecked(false);
        }else{
            chk17.setChecked(true);}

        if (settings.enableGs1DataBarLimited == Enable_State.FALSE) {
            chk18.setChecked(false);
        }else{
            chk18.setChecked(true);}

        if (settings.enableGs1DataBarExpanded == Enable_State.FALSE) {
            chk19.setChecked(false);
        }else{
            chk19.setChecked(true);}

        if (settings.enableGs1DatabarToUpcEan == Enable_State.FALSE) {
            chk20.setChecked(false);
        }else{
            chk20.setChecked(true);}

        if (settings.enableIsbt128 == Enable_State.FALSE) {
            chk21.setChecked(false);
        }else{
            chk21.setChecked(true);}

        if (settings.enableIndustrial2Of5 == Enable_State.FALSE) {
            chk22.setChecked(false);
        }else{
            chk22.setChecked(true);}

        if (settings.enableInterleaved2Of5 == Enable_State.FALSE) {
            chk23.setChecked(false);
        }else{
            chk23.setChecked(true);}

        if (settings.enableJapanPostal == Enable_State.FALSE) {
            chk24.setChecked(false);
        }else{
            chk24.setChecked(true);}

        if (settings.enableKorean3Of5 == Enable_State.FALSE) {
            chk25.setChecked(false);
        }else{
            chk25.setChecked(true);}

        if (settings.enableMatrix2Of5 == Enable_State.FALSE) {
            chk26.setChecked(false);
        }else{
            chk26.setChecked(true);}

        if (settings.enableMaxiCode == Enable_State.FALSE) {
            chk27.setChecked(false);
        }else{
            chk27.setChecked(true);}

        if (settings.enableMicroPDF417 == Enable_State.FALSE) {
            chk28.setChecked(false);
        }else{
            chk28.setChecked(true);}

        if (settings.enableMicroQR == Enable_State.FALSE) {
            chk29.setChecked(false);
        }else{
            chk29.setChecked(true);}

        if (settings.enableMsi == Enable_State.FALSE) {
            chk30.setChecked(false);
        }else{
            chk30.setChecked(true);}

        if (settings.enablePDF417 == Enable_State.FALSE) {
            chk31.setChecked(false);
        }else{
            chk31.setChecked(true);}

        if (settings.enableQRcode == Enable_State.FALSE) {
            chk32.setChecked(false);
        }else{
            chk32.setChecked(true);}

        if (settings.enableTriopticCode39 == Enable_State.FALSE) {
            chk33.setChecked(false);
        }else{
            chk33.setChecked(true);}

        if (settings.enableUpcA == Enable_State.FALSE) {
            chk34.setChecked(false);
        }else{
            chk34.setChecked(true);}

        if (settings.enableUpcE == Enable_State.FALSE) {
            chk35.setChecked(false);
        }else{
            chk35.setChecked(true);}

        if (settings.enableUpcE1 == Enable_State.FALSE) {
            chk36.setChecked(false);
        }else{
            chk36.setChecked(true);}

        if (settings.enableUccCoupon == Enable_State.FALSE) {
            chk37.setChecked(false);
        }else{
            chk37.setChecked(true);}

        if (settings.enableUKPostal == Enable_State.FALSE) {
            chk38.setChecked(false);
        }else{
            chk38.setChecked(true);}

        if (settings.enableUPUFICSPostal == Enable_State.FALSE) {
            chk39.setChecked(false);
        }else{
            chk39.setChecked(true);}

        if (settings.enableUSPostnet == Enable_State.FALSE) {
            chk40.setChecked(false);
        }else{
            chk40.setChecked(true);}

        if (settings.enableUSPlanet == Enable_State.FALSE) {
            chk41.setChecked(false);
        }else{
            chk41.setChecked(true);}

        if (settings.enableUSPSPostal == Enable_State.FALSE) {
            chk42.setChecked(false);
        }else{
            chk42.setChecked(true);}
    }

    private void SetSettings(){
        ClResult ret;
        Decoders settings = new Decoders();

        // Get Current Reader Settings before using Decoders instance
        ret = _readerManager.Get_Decoders_Status(settings);
        if (ret  != ClResult.S_OK){
            Toast.makeText(this, "Fail to Get_Decoders_Status()", Toast.LENGTH_SHORT).show();
            return;
        }

        CheckBox chk1 = (CheckBox)  findViewById(R.id.checkBox1);
        CheckBox chk2 = (CheckBox)  findViewById(R.id.checkBox2);
        CheckBox chk3 = (CheckBox)  findViewById(R.id.checkBox3);
        CheckBox chk4 = (CheckBox)  findViewById(R.id.checkBox4);
        CheckBox chk5 = (CheckBox)  findViewById(R.id.checkBox5);
        CheckBox chk6 = (CheckBox)  findViewById(R.id.checkBox6);
        CheckBox chk7 = (CheckBox)  findViewById(R.id.checkBox7);
        CheckBox chk8 = (CheckBox)  findViewById(R.id.checkBox8);
        CheckBox chk9 = (CheckBox)  findViewById(R.id.checkBox9);
        CheckBox chk10 = (CheckBox) findViewById(R.id.checkBox10);
        CheckBox chk11 = (CheckBox) findViewById(R.id.checkBox11);
        CheckBox chk12 = (CheckBox) findViewById(R.id.checkBox12);
        CheckBox chk13 = (CheckBox) findViewById(R.id.checkBox13);
        CheckBox chk14 = (CheckBox) findViewById(R.id.checkBox14);
        CheckBox chk15 = (CheckBox) findViewById(R.id.checkBox15);
        CheckBox chk16 = (CheckBox) findViewById(R.id.checkBox16);
        CheckBox chk17 = (CheckBox) findViewById(R.id.checkBox17);
        CheckBox chk18 = (CheckBox) findViewById(R.id.checkBox18);
        CheckBox chk19 = (CheckBox) findViewById(R.id.checkBox19);
        CheckBox chk20 = (CheckBox) findViewById(R.id.checkBox20);
        CheckBox chk21 = (CheckBox) findViewById(R.id.checkBox21);
        CheckBox chk22 = (CheckBox) findViewById(R.id.checkBox22);
        CheckBox chk23 = (CheckBox) findViewById(R.id.checkBox23);
        CheckBox chk24 = (CheckBox) findViewById(R.id.checkBox24);
        CheckBox chk25 = (CheckBox) findViewById(R.id.checkBox25);
        CheckBox chk26 = (CheckBox) findViewById(R.id.checkBox26);
        CheckBox chk27 = (CheckBox) findViewById(R.id.checkBox27);
        CheckBox chk28 = (CheckBox) findViewById(R.id.checkBox28);
        CheckBox chk29 = (CheckBox) findViewById(R.id.checkBox29);
        CheckBox chk30 = (CheckBox) findViewById(R.id.checkBox30);
        CheckBox chk31 = (CheckBox) findViewById(R.id.checkBox31);
        CheckBox chk32 = (CheckBox) findViewById(R.id.checkBox32);
        CheckBox chk33 = (CheckBox) findViewById(R.id.checkBox33);
        CheckBox chk34 = (CheckBox) findViewById(R.id.checkBox34);
        CheckBox chk35 = (CheckBox) findViewById(R.id.checkBox35);
        CheckBox chk36 = (CheckBox) findViewById(R.id.checkBox36);
        CheckBox chk37 = (CheckBox) findViewById(R.id.checkBox37);
        CheckBox chk38 = (CheckBox) findViewById(R.id.checkBox38);
        CheckBox chk39 = (CheckBox) findViewById(R.id.checkBox39);
        CheckBox chk40 = (CheckBox) findViewById(R.id.checkBox40);
        CheckBox chk41 = (CheckBox) findViewById(R.id.checkBox41);
        CheckBox chk42 = (CheckBox) findViewById(R.id.checkBox42);


        if (chk1.isChecked()) {
            settings.enableAustrailianPostal = Enable_State.TRUE;
        } else {
            settings.enableAustrailianPostal = Enable_State.FALSE;
        }
        if (chk2.isChecked()) {
            settings.enableAztec = Enable_State.TRUE;
        } else {
            settings.enableAztec = Enable_State.FALSE;
        }
        if (chk3.isChecked()) {
            settings.enableCompositeCC_AB = Enable_State.TRUE;
        } else {
            settings.enableCompositeCC_AB = Enable_State.FALSE;
        }
        if (chk4.isChecked()) {
            settings.enableCompositeCC_C = Enable_State.TRUE;
        } else {
            settings.enableCompositeCC_C = Enable_State.FALSE;
        }
        if (chk5.isChecked()) {
            settings.enableCompositeTlc39 = Enable_State.TRUE;
        } else {
            settings.enableCompositeTlc39 = Enable_State.FALSE;
        }
        if (chk6.isChecked()) {
            settings.enableCode11 = Enable_State.TRUE;
        } else {
            settings.enableCode11 = Enable_State.FALSE;
        }
        if (chk7.isChecked()) {
            settings.enableCode39 = Enable_State.TRUE;
        } else {
            settings.enableCode39 = Enable_State.FALSE;
        }
        if (chk8.isChecked()) {
            settings.enableCode93 = Enable_State.TRUE;
        } else {
            settings.enableCode93 = Enable_State.FALSE;
        }
        if (chk9.isChecked()) {
            settings.enableCode128 = Enable_State.TRUE;
        } else {
            settings.enableCode128 = Enable_State.FALSE;
        }
        if (chk10.isChecked()) {
            settings.enableCodabar = Enable_State.TRUE;
        } else {
            settings.enableCodabar = Enable_State.FALSE;
        }
        if (chk11.isChecked()) {
            settings.enableChinese2Of5 = Enable_State.TRUE;
        } else {
            settings.enableChinese2Of5 = Enable_State.FALSE;
        }
        if (chk12.isChecked()) {
            settings.enableDataMatrix = Enable_State.TRUE;
        } else {
            settings.enableDataMatrix = Enable_State.FALSE;
        }
        if (chk13.isChecked()) {
            settings.enableDutchPostal = Enable_State.TRUE;
        } else {
            settings.enableDutchPostal = Enable_State.FALSE;
        }
        if (chk14.isChecked()) {
            settings.enableEanJan8 = Enable_State.TRUE;
        } else {
            settings.enableEanJan8 = Enable_State.FALSE;
        }
        if (chk15.isChecked()) {
            settings.enableEanJan13 = Enable_State.TRUE;
        } else {
            settings.enableEanJan13 = Enable_State.FALSE;
        }
        if (chk16.isChecked()) {
            settings.enableGs1128 = Enable_State.TRUE;
        } else {
            settings.enableGs1128 = Enable_State.FALSE;
        }
        if (chk17.isChecked()) {
            settings.enableGs1DataBar14 = Enable_State.TRUE;
        } else {
            settings.enableGs1DataBar14 = Enable_State.FALSE;
        }
        if (chk18.isChecked()) {
            settings.enableGs1DataBarLimited = Enable_State.TRUE;
        } else {
            settings.enableGs1DataBarLimited = Enable_State.FALSE;
        }
        if (chk19.isChecked()) {
            settings. enableGs1DataBarExpanded= Enable_State.TRUE;
        } else {
            settings.enableGs1DataBarExpanded = Enable_State.FALSE;
        }
        if (chk20.isChecked()) {
            settings.enableGs1DatabarToUpcEan = Enable_State.TRUE;
        } else {
            settings.enableGs1DatabarToUpcEan = Enable_State.FALSE;
        }
        if (chk21.isChecked()) {
            settings.enableIsbt128 = Enable_State.TRUE;
        } else {
            settings.enableIsbt128 = Enable_State.FALSE;
        }
        if (chk22.isChecked()) {
            settings.enableIndustrial2Of5 = Enable_State.TRUE;
        } else {
            settings.enableIndustrial2Of5 = Enable_State.FALSE;
        }
        if (chk23.isChecked()) {
            settings.enableInterleaved2Of5 = Enable_State.TRUE;
        } else {
            settings.enableInterleaved2Of5 = Enable_State.FALSE;
        }
        if (chk24.isChecked()) {
            settings.enableJapanPostal = Enable_State.TRUE;
        } else {
            settings.enableJapanPostal = Enable_State.FALSE;
        }
        if (chk25.isChecked()) {
            settings.enableKorean3Of5 = Enable_State.TRUE;
        } else {
            settings.enableKorean3Of5 = Enable_State.FALSE;
        }
        if (chk26.isChecked()) {
            settings.enableMatrix2Of5 = Enable_State.TRUE;
        } else {
            settings.enableMatrix2Of5 = Enable_State.FALSE;
        }
        if (chk27.isChecked()) {
            settings.enableMaxiCode = Enable_State.TRUE;
        } else {
            settings.enableMaxiCode = Enable_State.FALSE;
        }
        if (chk28.isChecked()) {
            settings.enableMicroPDF417 = Enable_State.TRUE;

        } else {
            settings.enableMicroPDF417 = Enable_State.FALSE;
        }
        if (chk29.isChecked()) {
            settings.enableMicroQR = Enable_State.TRUE;
        } else {
            settings.enableMicroQR = Enable_State.FALSE;
        }
        if (chk30.isChecked()) {
            settings.enableMsi = Enable_State.TRUE;
        } else {
            settings.enableMsi = Enable_State.FALSE;
        }
        if (chk31.isChecked()) {
            settings.enablePDF417 = Enable_State.TRUE;
        } else {
            settings.enablePDF417 = Enable_State.FALSE;
        }
        if (chk32.isChecked()) {
            settings.enableQRcode = Enable_State.TRUE;
        } else {
            settings.enableQRcode = Enable_State.FALSE;
        }
        if (chk33.isChecked()) {
            settings.enableTriopticCode39 = Enable_State.TRUE;
        } else {
            settings.enableTriopticCode39 = Enable_State.FALSE;
        }
        if (chk34.isChecked()) {
            settings.enableUpcA = Enable_State.TRUE;
        } else {
            settings.enableUpcA = Enable_State.FALSE;
        }
        if (chk35.isChecked()) {
            settings.enableUpcE = Enable_State.TRUE;
        } else {
            settings.enableUpcE = Enable_State.FALSE;
        }
        if (chk36.isChecked()) {
            settings.enableUpcE1 = Enable_State.TRUE;
        } else {
            settings.enableUpcE1 = Enable_State.FALSE;
        }
        if (chk37.isChecked()) {
            settings.enableUccCoupon = Enable_State.TRUE;
        } else {
            settings.enableUccCoupon = Enable_State.FALSE;
        }
        if (chk38.isChecked()) {
            settings.enableUKPostal = Enable_State.TRUE;
        } else {
            settings.enableUKPostal = Enable_State.FALSE;
        }
        if (chk39.isChecked()) {
            settings.enableUPUFICSPostal = Enable_State.TRUE;
        } else {
            settings.enableUPUFICSPostal = Enable_State.FALSE;
        }
        if (chk40.isChecked()) {
            settings.enableUSPostnet = Enable_State.TRUE;
        } else {
            settings.enableUSPostnet = Enable_State.FALSE;
        }
        if (chk41.isChecked()) {
            settings.enableUSPlanet = Enable_State.TRUE;
        } else {
            settings.enableUSPlanet = Enable_State.FALSE;
        }
        if (chk42.isChecked()) {
            settings.enableUSPSPostal = Enable_State.TRUE;
        } else {
            settings.enableUSPSPostal = Enable_State.FALSE;
        }

        //Apply settings
        ret = _readerManager.Set_Decoders_Status(settings);
        if (ret  != ClResult.S_OK){
            Toast.makeText(this, "Fail to Set_Decoders_Status()", Toast.LENGTH_SHORT).show();
        }
    }
}
