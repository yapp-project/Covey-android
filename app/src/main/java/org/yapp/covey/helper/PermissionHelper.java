package org.yapp.covey.helper;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PermissionHelper {
    private Context mContext;
    static final int PERMISSION_CAMERA = 100;
    static final int PERMISSION_LOCATION = 200;

    public PermissionHelper(Context context){
        this.mContext = context;
    }

    private PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Log.d("permission granted", "권한 허용 중");
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(mContext, "권한이 거부되어있습니다",Toast.LENGTH_SHORT).show();
        }
    };

    public void getPermission(int permissionKind){
        TedPermission.Builder permission = new TedPermission().with(mContext)
                .setPermissionListener(permissionlistener);
        if (permissionKind == PERMISSION_CAMERA){
            permission.setDeniedMessage("권한 거부 시 사진을 첨부할 수 없습니다\n\n [설정]>[권한]에서 허용해주세요")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .check();
        }else if (permissionKind == PERMISSION_LOCATION){
            permission.setDeniedMessage("권한 거부 시 위치정보에 엑세스 할 수 없습니다\n\n [설정]>[권한]에서 허용해주세요")
                    .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                    .check();
        }
    }
}
