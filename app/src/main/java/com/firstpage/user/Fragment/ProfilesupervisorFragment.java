package com.firstpage.user.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.firstpage.user.Common.SessionManager;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class ProfilesupervisorFragment extends Fragment {
    public ProfilesupervisorFragment() {
// Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public AppCompatTextView tv_profile_name;
    public AppCompatTextView tv_profile_email;
    public AppCompatTextView tv_company_id;
    public AppCompatTextView tv_operator_code;
    public AppCompatTextView tv_profile;
    public Button btn_logout;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    CircleImageView circularImageView;
    SessionManager session;
    SharedPreference sp;
    public android.support.v7.app.AlertDialog deleteDialog;
    AlertDialog alertDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        //requestMultiplePermissions();
        session = new SessionManager(getActivity());
        sp=new SharedPreference();

        tv_profile_name=(AppCompatTextView)view.findViewById(R.id.tv_profile_name);
        tv_profile_email=(AppCompatTextView)view.findViewById(R.id.tv_profile_emailid);
        tv_company_id=(AppCompatTextView)view.findViewById(R.id.tv_company_id);
        tv_operator_code=(AppCompatTextView)view.findViewById(R.id.tv_operator_code);
        tv_profile=(AppCompatTextView)view.findViewById(R.id.tv_profile_role);
        btn_logout=(Button) view.findViewById(R.id.btn_logout);
        circularImageView=(CircleImageView)view.findViewById(R.id.iv_profile_image);

//        Tooltip.Builder builder = new Tooltip.Builder(circularImageView, R.style.Tooltip2)
//                .setCancelable(true)
//                .setDismissOnClick(false)
//                .setCornerRadius(20f)
//                .setGravity(Gravity.BOTTOM)
//                .setText("This is a profile");
//        builder.show();
        String image=sp.getString(getActivity(),"image");
        Glide.with(getActivity())
                .load(image)
                .apply(centerCropTransform()
                        .error(R.drawable.profile_pic)
                        .priority(Priority.HIGH))
                .into(circularImageView);
        String name=sp.getString(getActivity(),"name");
        tv_profile_name.setText(""+name);
        String role=sp.getString(getActivity(),"role");
        tv_profile.setText(""+role);
        String email=sp.getString(getActivity(),"emailid");
        tv_profile_email.setText(""+email);
        String compid=sp.getString(getActivity(),"companyid");
        tv_company_id.setText(""+compid);
        String operatorcode=sp.getString(getActivity(),"ocode");
        tv_operator_code.setText(""+operatorcode);
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // showPictureDialog();

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View deleteDialogView = factory.inflate(R.layout.logout_alert_layout, null);
                deleteDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
                deleteDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                deleteDialog.setView(deleteDialogView);
                deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        session.logoutUser();

                    }
                });
                deleteDialogView.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                    }
                });
                deleteDialog.show();
            }
        });

        return view;
    }




//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(Dummy dummy) {
//        Log.e("ragu", "onMessageEvent:dummy" );
//        String a=dummy.getName();
//        Log.e("ragu", "onMessageEvent: "+a );
//
//    };

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                " Gallery",
                " Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
                    Log.e("ragu", "onActivityResult: "+bitmap );
                    circularImageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            circularImageView.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
        }
    }
//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//        // have the object build the directory structure, if needed.
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance()
//                    .getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(getActivity(),
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//            Log.d("TAG", "File Saved::---&gt;" + f.getPath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//    }
    private void  requestMultiplePermissions(){
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();

                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getActivity(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(Dummy dummy)
//    {
//        Log.e("ragu", "onMessageEvent: " );
//        Toast.makeText(getActivity(), dummy.name, Toast.LENGTH_SHORT).show();
////        Log.e("ragu", "onMessageEvent:dummy" );
////        String a=dummy.getName();
////        Log.e("ragu", "onMessageEvent:"+a );
//
//    };

//    @Override
//    public void onStart() {
//        Log.e("ragu", "onStart: eventbus " );
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e("ragu", "onDestroy: eventbus " );
//        super.onDestroy();
//        EventBus.getDefault().register(this);
//    }
}
