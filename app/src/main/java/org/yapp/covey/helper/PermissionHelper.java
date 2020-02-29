package org.yapp.covey.helper;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PermissionHelper {
    private Context mContext;

    public PermissionHelper(Context context){
        this.mContext = context;
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(mContext, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(mContext, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    public PermissionListener getPermissionlistener() {
        return permissionlistener;
    }
    public void getPermissionCallback(){
        TedPermission.with(mContext)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("사진 업로드를 위해 권한을 허용해주세요")
                .setDeniedMessage("권한 거부 시 사진을 첨부할 수 없습니다\n\n [설정]>[권한]에서 허용해주세요")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }
}
