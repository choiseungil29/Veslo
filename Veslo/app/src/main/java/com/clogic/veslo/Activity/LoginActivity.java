package com.clogic.veslo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.clogic.veslo.API.API;
import com.clogic.veslo.Model.Server.Info;
import com.clogic.veslo.R;
import com.clogic.veslo.Util.Key;
import com.clogic.veslo.Util.Logger;
import com.clogic.veslo.Util.Provider;
import com.kakao.auth.APIErrorResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.auth.SessionCallback;
import com.kakao.usermgmt.MeResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.pixplicity.easyprefs.library.Prefs;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by clogic on 2015. 11. 14..
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btn_login_kakao) Button btn_loginKakao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_kakao)
    public void kakaoLogin() {
        Session session = Session.getCurrentSession();
        session.addCallback(new SessionCallback() {
            @Override
            public void onSessionOpened() {
                UserManagement.requestMe(new MeResponseCallback() {
                    @Override
                    public void onSuccess(final UserProfile userProfile) {
                        // login success
                        Logger.i("SUCCESS");
                        API.getUserAPI().signupWithLogin(String.valueOf(userProfile.getId()), userProfile.getNickname()).enqueue(new retrofit.Callback<Info>() {
                            @Override
                            public void onResponse(Response<Info> response, Retrofit retrofit) {
                                Logger.i("SUCCEED");
                                if(!response.isSuccess()) {
                                    Logger.i("FAILED");
                                    return;
                                }

                                Info info = response.body();

                                //User profile = new User(userProfile.getNickname(), String.valueOf(userProfile.getId()));
                                info.user.profilePath = userProfile.getProfileImagePath();
                                Provider.getInstance().setUserProfile(info.user);
                                Prefs.putString(Key.ID, info.user.id);
                                Prefs.putString(Key.USERNAME, info.user.username);
                                Prefs.putString(Key.LOGIN_TYPE, Key.LoginType.KAKAO);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in, R.anim.step_back);
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Logger.i("FAILED");
                            }
                        });
                    }

                    @Override
                    public void onNotSignedUp() {
                        Logger.i("SUCCESS");
                    }

                    @Override
                    public void onSessionClosedFailure(APIErrorResult errorResult) {
                        Logger.i("SUCCESS");
                    }

                    @Override
                    public void onFailure(APIErrorResult errorResult) {
                        Logger.i("SUCCESS");
                    }
                });
            }

            @Override
            public void onSessionClosed(KakaoException exception) {
                Logger.i("SUCCESS");
            }

            @Override
            public void onSessionOpening() {
                Logger.i("SUCCESS");
            }
        });
        session.open(AuthType.KAKAO_TALK, this);
        if(session.isOpenable()) {
            Logger.i("openable");
        }
        if(session.isOpened()) {
            Logger.i("opened");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.i("Activity Result");
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
