package com.example.kawabe.aidlexercise;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Locale;

public class AdditionService extends Service {
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IAdditionService.Stub mBinder = new IAdditionService.Stub() {
        @Override
        public String getLocale() throws RemoteException {
            String localeId = "ja";

            // ロケールの取得
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();
            String country = locale.getCountry();

            if (language.equals("ja")) {
                localeId = "日本語";
            } else if (language.equals("en")) {
                localeId = "英語";
            } else if ((language.equals("zh")) && (country.equals("CN"))) {
                localeId = "簡体字";
            } else if ((language.equals("zh")) && ((country.equals("TW")) || (country.equals("HK")))) {
                localeId = "繁体字";
            }

            return localeId;
        }
    };
}
