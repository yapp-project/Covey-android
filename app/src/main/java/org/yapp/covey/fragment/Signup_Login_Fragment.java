package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;
import org.yapp.covey.util.FacebookLoginCallback;

import java.util.Arrays;

import androidx.fragment.app.Fragment;

public class Signup_Login_Fragment extends Fragment {

    private static final String TAG = "Login";

    KakaoSessionCallback kakaoCallback;
    FacebookLoginCallback facebookCallback;
    CallbackManager facebookCallbackManager;

    public static Signup_Login_Fragment newInstance() {
        return new Signup_Login_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_login, container, false);
        RelativeLayout login_kakao = view.findViewById(R.id.signup_login_btn_kakao);
        RelativeLayout login_facebook = view.findViewById(R.id.signup_login_btn_facebook);
        final LoginButton kakaoButton = view.findViewById(R.id.com_kakao_login);
        final com.facebook.login.widget.LoginButton facebookButton = view.findViewById(R.id.btn_facebook_login);

        kakaoCallback = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(kakaoCallback);
        requestMe();

        facebookCallbackManager = CallbackManager.Factory.create();
        facebookCallback = new FacebookLoginCallback();

        facebookButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebookButton.registerCallback(facebookCallbackManager, facebookCallback);
        facebookButton.setFragment(this);

        login_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakaoButton.performClick();
            }
        });
        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookButton.performClick();
            }
        });

        return view;
    }

    class KakaoSessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        //에러로 인한 로그인 실패
                        // finish();
                    } else {
                        //redirectMainActivity();
                    }
                }
                @Override
                public void onSessionClosed(ErrorResult errorResult) { }
                @Override
                public void onNotSignedUp() { }
                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.e("UserProfile", userProfile.toString());
                    Log.e("UserProfile", userProfile.getId() + "");

                    Fragment next = Signup_01_Fragment.newInstance();
                    /*
                    * user 정보 bundle에 넣어야함
                    */
                    ((SignupActivity)getActivity()).replaceFragment(next);
                }
            });
        }
        // 세션 실패시
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
        }
    }

    public void requestMe() {
        //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "error message=" + errorResult);
//                super.onFailure(errorResult);
            }
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d(TAG, "onSessionClosed1 =" + errorResult);
            }
            @Override
            public void onNotSignedUp() {
                //카카오톡 회원이 아닐시
                Log.d(TAG, "onNotSignedUp ");
            }
            @Override
            public void onSuccess(UserProfile result) {
                Log.e("UserProfile", result.toString());
                Log.e("UserProfile", result.getId() + "");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);

        Log.d("facebook",data.toString());

        Fragment next = Signup_01_Fragment.newInstance();
        /*
         * user 정보 bundle에 넣어야함
         */
        ((SignupActivity)getActivity()).replaceFragment(next);
    }
}
