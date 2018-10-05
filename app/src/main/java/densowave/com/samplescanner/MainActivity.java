//**************************************************************************//
//***                                                                    ***//
//***    Product Name    : Sample Program for BHT 1600                   ***//
//***    File Name       : MainActivity.java                             ***//
//***                                                                    ***//
//***    Edition History                                                 ***//
//***    Version  Date      Comments                                     ***//
//***    -------  --------  ---------------------------------------------***//
//***    1.0.0    3/1/17  Original                                       ***//
//***    1.1.6    06/16/18  Update SDK to 1.1.6                          ***//
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
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.densowave.barcode.*;
import com.densowave.barcode.decoder.*;
import com.densowave.barcode.decoderparams.*;


public class MainActivity extends AppCompatActivity{

    private ReaderManager _myReaderManager  = null;
    private int readCount = 0;
    public static final String TAG = "MainActivity";



    private final BroadcastReceiver myDataReceiver = new BroadcastReceiver() {



        @Override
        public void onReceive(Context context, Intent intent) {

//201809追加　open sqlite
            MyOpenHelper helper = new MyOpenHelper(MainActivity.this);
            final SQLiteDatabase db = helper.getWritableDatabase();
//************************

            //Receiver to receive Reader Event
            switch (intent.getAction()){
                case GeneralString.Intent_READERSERVICE_CONNECTED:
                    // Connected to the Reader service(Barcode APIs are available after this event)

                    //Barcode Reader configuration
                    initReadParam();

                    //Get Barcode Enable state
                    Switch _switch = (Switch)findViewById(R.id.switchEnableReader);
                    _switch.setChecked(_myReaderManager.GetActive());
                    break;
                case GeneralString.Intent_SOFTTRIGGER_DATA:
                case GeneralString.Intent_PASS_TO_APP:
                    //When Read Data Hardware Key or Soft Trigger

                    TextView _textBarData = (TextView)findViewById(R.id.textBarData);
                    TextView _textBarType = (TextView)findViewById(R.id.textBarType);
                    TextView _textBarDigit = (TextView)findViewById(R.id.textBarDigit);
                    TextView _textReadCount = (TextView)findViewById(R.id.textReadCount);

                            // Fetch data from the intent
                    _textBarData.setText(intent.getStringExtra(GeneralString.BcReaderData));
                    _textBarType.setText(intent.getStringExtra(GeneralString.BcReaderCodeTypeStr));
                    _textBarDigit.setText(String.valueOf(_textBarData.getText().length()));
                    _textReadCount.setText(String.valueOf(++readCount));
                    String sDataStr = intent.getStringExtra(GeneralString.BcReaderData);
                    //Toast.makeText(MainActivity.this, sDataStr,Toast.LENGTH_LONG).show();

//201809追加　読んだバーをsqliteにてwrite
                    String name = sDataStr;
                    String age = "9";
                    ContentValues insertValues = new ContentValues();
                    insertValues.put("name", name);
                    insertValues.put("age", age);
                    long id = db.insert("person", name, insertValues);
//******************************************
                    break;
            }

            //Enable Scan Button
            Button buttonScan = (Button) findViewById(R.id.buttonScan);
            buttonScan.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

//201809追加 open sqlite

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

//***********************


        //Register check changed event of Switch
        Switch _switch = (Switch)findViewById(R.id.switchEnableReader);
        _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Change Active state of scanner
                _myReaderManager.SetActive(isChecked);
            }
        });

        // Register onclick event of SCAN button
        Button buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setEnabled(false);
        buttonScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (_myReaderManager != null) {
                    if (_myReaderManager.GetActive()) {
                        //Start to read Barcode
                        _myReaderManager.SoftScanTrigger();
                        Button buttonScan = (Button) findViewById(R.id.buttonScan);
                        buttonScan.setEnabled(false);
                    }
                }
            }


        });
//201809追加 履歴照会
        Button buttonScan2 = (Button) findViewById(R.id.buttonScan2);
        buttonScan2.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
          Intent dbIntent = new Intent(MainActivity.this,
          ShowDataBase.class);
          startActivity(dbIntent);
          }
        });
//********************

//201809追加 履歴削除
        Button buttonScan3 = (Button) findViewById(R.id.buttonScan3);
        buttonScan3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //String name = nameText.getText().toString();
                //String age = ageText.getText().toString();

                db.delete("person", null, null);

            }
        });
//**********************


        // Get View text from bundle
        TextView _tvBarData = (TextView)findViewById(R.id.textBarData);
        if (bundle != null){
            _tvBarData.setText(bundle.getString("@string/BarData", ""));
         }
        else{
            _tvBarData.setText("");
        }

        TextView _tvBarType = (TextView)findViewById(R.id.textBarType);
        if (bundle != null) {
            _tvBarType.setText(bundle.getString( "@string/barType", ""));
        }
        else{
            _tvBarType.setText("");
        }

        TextView _tvBarDigit = (TextView)findViewById(R.id.textBarDigit);
        if (bundle != null) {
            _tvBarDigit.setText(bundle.getString("@string/barDigit", ""));
        }
        else {
            _tvBarDigit.setText("");
        }

        TextView _tvReadCount = (TextView)findViewById(R.id.textReadCount);
        if (bundle != null)
        {
            readCount = bundle.getInt("@string/readCount", 0);
            _tvReadCount.setText(String.valueOf(readCount));
        }
        else{
            _tvReadCount.setText("");
        }

       //Toast.makeText(this, "aaa",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Make menu
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //Create Intent Filter to get event from Reader Service
        IntentFilter _filter = new IntentFilter();
        _filter.addAction(GeneralString.Intent_SOFTTRIGGER_DATA);
        _filter.addAction(GeneralString.Intent_PASS_TO_APP);
        _filter.addAction(GeneralString.Intent_READERSERVICE_CONNECTED);
        registerReceiver(myDataReceiver, _filter);

        //Initialise Reader Instance
        _myReaderManager = ReaderManager.InitInstance(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Release Scanner Resources
        unregisterReceiver(myDataReceiver);
        _myReaderManager.Release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle _bundle)
    {
        //MyOpenHelper helper = new MyOpenHelper(this);
        //final SQLiteDatabase db = helper.getWritableDatabase();

        super.onSaveInstanceState(_bundle);

        //Save View Text
        TextView _tvBarData = (TextView) findViewById(R.id.textBarData);
        _bundle.putString("@string/BarData", _tvBarData.getText().toString());

        TextView _tvBarType = (TextView)findViewById(R.id.textBarType);
        _bundle.putString( "@string/barType", _tvBarType.getText().toString());

        TextView _tvBarDigit = (TextView)findViewById(R.id.textBarDigit);
        _bundle.putString("@string/barDigit", _tvBarDigit.getText().toString());

        _bundle.putInt("@string/readCount", readCount);


        //String name = _tvBarData.getText().toString();
        //String age = "8";
        //ContentValues insertValues = new ContentValues();
        //insertValues.put("name", name);
        //insertValues.put("age", age);
        //long id = db.insert("person", name, insertValues);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem _item){
        boolean ret;

        super.onOptionsItemSelected(_item);

        switch (_item.getItemId()) {
            case R.id.action_settings:
                // Open setting view
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                ret = true;
                break;
            default:
                ret = false;
        }
        return ret;
    }

    //Initialise Reader Parameter
    private void initReadParam(){
        ClResult ret;

        //Set to default
        //ret = _myReaderManager.ResetReaderToDefault();
        //if (ret != ClResult.S_OK)
        //{
        //    Toast.makeText(this, "Fail to ResetReaderToDefault()", Toast.LENGTH_SHORT).show();
        //    return;
        //}

        //Reader Output Configuration
        {
            ReaderOutputConfiguration settings = new ReaderOutputConfiguration();

            //Get Settings
            ret = _myReaderManager.Get_ReaderOutputConfiguration(settings);
            if (ret != ClResult.S_OK)
            {
                Toast.makeText(this, "Fail to Get_ReaderOutputConfiguration()", Toast.LENGTH_SHORT).show();
                return;
            }

            settings.enableKeyboardEmulation = KeyboardEmulationType.None;
            //settings.enableKeyboardEmulation = KeyboardEmulationType.InputMethod;
            //settings.enableKeyboardEmulation = KeyboardEmulationType.KeyEvent;

            settings.autoEnterWay = OutputEnterWay.Disable;
            //settings.autoEnterWay = OutputEnterWay.SuffixData;
            //settings.autoEnterWay = OutputEnterWay.PreffixData;

            settings.autoEnterChar = OutputEnterChar.None;
            //settings.autoEnterChar = OutputEnterChar.Return;
            //settings.autoEnterChar = OutputEnterChar.Tab;
            //settings.autoEnterChar = OutputEnterChar.Space;
            //settings.autoEnterChar = OutputEnterChar.Comma;
            //settings.autoEnterChar = OutputEnterChar.Semicolon;

            settings.showCodeType = Enable_State.FALSE;
            //settings.showCodeType = Enable_State.TRUE;

            settings.showCodeLen = Enable_State.FALSE;
            //settings.showCodeLen = Enable_State.TRUE;

            settings.szPrefixCode = "";

            settings.szSuffixCode = "";

            settings.szPrefixCode = "";

            //settings.szCharsetName = "windows-1252";
            //settings.szCharsetName = "big5";
            settings.szCharsetName = "Shift_JIS";

            //settings.clearPreviousData = Enable_State.FALSE;
            settings.clearPreviousData = Enable_State.TRUE;

            settings.szSuffixCode = "";

            settings.useDelim = ':';

            // Set settings
            ret = _myReaderManager.Set_ReaderOutputConfiguration(settings);
            if (ret != ClResult.S_OK)
            {
                Toast.makeText(this, "Fail to Set_ReaderOutputConfiguration()", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //User Preference Configuration
        {
            UserPreference _userPreference = new UserPreference();

            //Get settings
            ret = _myReaderManager.Get_UserPreferences(_userPreference);
            if (ret != ClResult.S_OK)
            {
                Toast.makeText(this, "Fail to Get_UserPreferences()", Toast.LENGTH_SHORT).show();
                return;
            }

            _userPreference.addonSecurityLevel = 10;

            _userPreference.displayMode = Enable_State.FALSE;
            //_userPreference.displayMode = Enable_State.TRUE;

            _userPreference.laserOnTime = 3000;

            _userPreference.negativeBarcodes = InverseType.RegularOnly;
            //_userPreference.negativeBarcodes = InverseType.InverseOnly;
            //_userPreference.negativeBarcodes = InverseType.AutoDetect;

            _userPreference.pickListMode=Enable_State.FALSE;
            //_userPreference.pickListMode=Enable_State.TRUE;

            _userPreference. redundancyLevel = RedundancyLevel.One;
            //_userPreference. redundancyLevel = RedundancyLevel.Two;
            //_userPreference. redundancyLevel = RedundancyLevel.Three;
            //_userPreference. redundancyLevel = RedundancyLevel.Four;

            _userPreference.securityLevel = SecurityLevel.Zero;
            //_userPreference.securityLevel = SecurityLevel.One;
            //_userPreference.securityLevel = SecurityLevel.Two;
            //_userPreference.securityLevel = SecurityLevel.Three;

            _userPreference.timeoutBetweenSameSymbol = 1000;

            _userPreference.timeoutPresentationMode = 60000;

            _userPreference. transmitCodeIdChar = TransmitCodeIDType.None;
            //_userPreference. transmitCodeIdChar = TransmitCodeIDType.AimCodeId;

            _userPreference.triggerMode = TriggerType.LevelMode;
            // _userPreference.triggerMode = TriggerType.PresentationMode;

            //_userPreference.decodingIllumination = Enable_State.FALSE;
            _userPreference.decodingIllumination = Enable_State.TRUE;

            _userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Ten;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Nine;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Eight;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Seven;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Six;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Five;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Four;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Three;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Two;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.One;
            //_userPreference.decodingIlluminationPowerLevel = IlluminationPowerLevel.Zero;

            //_userPreference.decodingAimingPattern = Enable_State.FALSE;
            _userPreference.decodingAimingPattern = Enable_State.TRUE;


            //Set settings
            _myReaderManager.Set_UserPreferences(_userPreference);
            if (ret != ClResult.S_OK)
            {
                Toast.makeText(this, "Fail to Set_UserPreferences()", Toast.LENGTH_SHORT).show();
            }

            // Notification Setting
            {
                NotificationParams _notification = new NotificationParams();
                //Get Settings
                ret = _myReaderManager.Get_NotificationParams(_notification);
                if (ret != ClResult.S_OK)
                {
                    Toast.makeText(this, "Fail to Get_NotificationParams()", Toast.LENGTH_SHORT).show();
                    return;
                }

                _notification.ReaderBeep = BeepType.Default;
                //_notification.ReaderBeep = BeepType.Mute;
                //_notification.ReaderBeep = BeepType.Hwandsw;
                //_notification.ReaderBeep = BeepType.MenuPop;
                //_notification.ReaderBeep = BeepType.MsgBox;
                //_notification.ReaderBeep = BeepType.Notify;
                //_notification.ReaderBeep = BeepType.VoicBeep;
                //_notification.ReaderBeep = BeepType.Alarm2;
                //_notification.ReaderBeep = BeepType.Alarm3;
                //_notification.ReaderBeep = BeepType.LowBatt;
                //_notification.ReaderBeep = BeepType.Beep3;

                _notification.enableVibrator = Enable_State.FALSE;
                //_notification.EnableVibrator = Enable_State.True;

                _notification.vibrationCounter = 1;

                _notification.ledDuration = 500;

                ret = _myReaderManager.Set_NotificationParams(_notification);
                if (ret != ClResult.S_OK)
                {
                    Toast.makeText(this, "Fail to Set_NotificationParams()", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }
    }
}







