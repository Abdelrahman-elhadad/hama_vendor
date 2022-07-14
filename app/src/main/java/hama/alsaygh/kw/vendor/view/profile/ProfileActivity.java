package hama.alsaygh.kw.vendor.view.profile;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityProfileBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.image.ImageResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.CheckAndRequestPermission;
import hama.alsaygh.kw.vendor.utils.FileUtils;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class ProfileActivity extends BaseActivity implements OnGeneralClickListener {

    ActivityProfileBinding binding;
    EditProfileViewModel model;
    Skeleton skeleton;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new EditProfileViewModel(this, getSupportFragmentManager(), this);
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());
        skeleton = SkeletonLayoutUtils.createSkeleton(binding.nsMain);
        Utils.getInstance().setSkeletonMaskAndShimmer(this, skeleton);


        model.getProfileObserver().observe(this, loginResponse -> {

            skeleton.showOriginal();
            if (loginResponse.isStatus()) {

                model.setUser(loginResponse.getData().getDelegate());
                binding.setModel(model);
                setGender();

            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.nsMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getEditProfileObserver().observe(this, loginResponse -> {
            model.editVisibility.set(View.VISIBLE);
            model.pbEditVisibility.set(View.GONE);
            if (loginResponse.isStatus()) {

                model.setUser(loginResponse.getData());
                binding.setModel(model);
                SharedPreferenceConstant.setSharedPreferenceUser(this, loginResponse.getData());
                setGender();
                Snackbar.make(binding.nsMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            } else {
                if (loginResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.nsMain, loginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });


        skeleton.showSkeleton();
        model.getProfile(this);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, model.arrayForSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spGender.setAdapter(arrayAdapter);
        binding.spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    model.getUser().setGender("0");
                } else {
                    model.getUser().setGender("1");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        setGender();

        binding.btnSend.setOnClickListener(v -> {

            if (isValid()) {
                model.editVisibility.set(View.GONE);
                model.pbEditVisibility.set(View.VISIBLE);
                model.sendChanges(v.getContext());
            }

        });


    }

    private boolean isValid() {
        boolean isValid = true;

        if (model.getUser().getName() == null || model.getUser().getName().isEmpty()) {
            isValid = false;
            binding.edtFullName.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtFullName.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getEmail() == null || model.getUser().getEmail().isEmpty()) {
            isValid = false;
            binding.edtEmail.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtEmail.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getId_no() == null || model.getUser().getId_no().isEmpty()) {
            isValid = false;
            binding.edtIdNo.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtIdNo.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getTranslations().getEn().getCompany_name() == null || model.getUser().getTranslations().getEn().getCompany_name().isEmpty()) {
            isValid = false;
            binding.edtStoreNameEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStoreNameEn.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getTranslations().getAr().getCompany_name() == null || model.getUser().getTranslations().getAr().getCompany_name().isEmpty()) {
            isValid = false;
            binding.edtStoreNameAr.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStoreNameAr.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getTranslations().getAr().getCompany_description() == null || model.getUser().getTranslations().getAr().getCompany_description().isEmpty()) {
            isValid = false;
            binding.edtStoreDescAr.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStoreDescAr.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getTranslations().getEn().getCompany_description() == null || model.getUser().getTranslations().getEn().getCompany_description().isEmpty()) {
            isValid = false;
            binding.edtStoreDescEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStoreDescEn.setBackgroundResource(R.drawable.back_ground_edit_text);


        if (model.getUser().getTranslations().getEn().getCompany_description() == null || model.getUser().getTranslations().getEn().getCompany_description().isEmpty()) {
            isValid = false;
            binding.edtStoreDescEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStoreDescEn.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getMobile() == null || model.getUser().getMobile().isEmpty()) {
            isValid = false;
            binding.edtStorePhoneNumber.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtStorePhoneNumber.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getLandline() == null || model.getUser().getLandline().isEmpty()) {
            isValid = false;
            binding.edtLandlineNumber.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.edtLandlineNumber.setBackgroundResource(R.drawable.back_ground_edit_text);

        if (model.getUser().getLicense_deadline() == null || model.getUser().getLicense_deadline().isEmpty()) {
            isValid = false;
            binding.tvExpiryDateLicense.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.tvExpiryDateLicense.setBackgroundResource(R.drawable.back_ground_edit_text);

        return isValid;
    }

    private void setGender() {
        if (model.getUser().getGender().equalsIgnoreCase("0")) {
            binding.spGender.setSelection(1);
        } else if (model.getUser().getGender().equalsIgnoreCase("1")) {
            binding.spGender.setSelection(0);
        }
    }

    @Override
    public void onItemClick(Object object, int position) {
        if (position == 0) {

            permissionGallery();
        } else {
            permissionCamera();
        }

    }


    private void permissionFile() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!CheckAndRequestPermission.hasPermissions(this, permissions)) {
            //below android 11
            filePermissionResult.launch(permissions);

        } else {
            takePDFFile();
        }
    }

    private void permissionGallery() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!CheckAndRequestPermission.hasPermissions(this, permissions)) {
            mPermissionResult.launch(permissions);
        } else {
            openGallery();
        }

    }

    private void permissionCamera() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!CheckAndRequestPermission.hasPermissions(this, permissions)) {
            cameraPermissionResult.launch(permissions);
        } else {
            takePhotoFromCamera();
        }

    }

    @Override
    public void onEditClick(Object object, int position) {

        permissionFile();
    }

    @Override
    public void onDeleteClick(Object object, int position) {

    }

    private final ActivityResultLauncher<String[]> mPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {

                boolean isAccepted = true;
                for (String key : result.keySet()) {
                    if (Boolean.FALSE.equals(result.get(key))) {
                        isAccepted = false;
                        break;
                    }
                }
                if (isAccepted)
                    openGallery();
            });


    private final ActivityResultLauncher<String[]> cameraPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {

                boolean isAccepted = true;
                for (String key : result.keySet()) {
                    if (Boolean.FALSE.equals(result.get(key))) {
                        isAccepted = false;
                        break;
                    }
                }
                if (isAccepted)
                    takePhotoFromCamera();
            });

    private final ActivityResultLauncher<String[]> filePermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {

                boolean isAccepted = true;
                for (String key : result.keySet()) {
                    if (Boolean.FALSE.equals(result.get(key))) {
                        isAccepted = false;
                        break;
                    }
                }
                if (isAccepted)
                    takePDFFile();
            });

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> pickImageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            final String profilePath = FileUtils.getPath(ProfileActivity.this, data.getData());
                            startUploadImage(model.getImageType(), profilePath, data.getData());
                        }
                    }
                }
            });

    private void startUploadImage(final int type, final String path, Uri uri) {

        MutableLiveData<ImageResponse> imageMutableLiveData = new MutableLiveData<>();
        imageMutableLiveData.observe(this, imageResponse -> {
            if (imageResponse.isStatus()) {
                ImageView iv_item = getImageView(type);
                setImagePath(type, imageResponse.getData().getFile_name(), imageResponse.getData().getId());
                if (imageResponse.getData() != null) {
                    String imageUrl = imageResponse.getData().getUrl();

                    if (imageUrl != null && !imageUrl.isEmpty())
                        Picasso.get().load(imageUrl).into(iv_item, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {

                                Picasso.get().load(R.drawable.image_not_foundpng).into(iv_item);
                            }
                        });
                    else
                        Picasso.get().load(R.drawable.image_not_foundpng).into(iv_item);
                } else
                    Picasso.get().load(R.drawable.image_not_foundpng).into(iv_item);

            }
        });

        new GeneralRepo().uploadImage(this, path, imageMutableLiveData);
        ImageView iv_item = getImageView(type);
        iv_item.setImageURI(uri);
    }

    @NonNull
    private ImageView getImageView(int type) {
        return binding.ivProfile;
    }

    private TextView getTextView(int type) {
        TextView iv_item;
        switch (type) {
            case AppConstants.ID:
                iv_item = binding.tvIDName;
                break;
            case AppConstants.COMMERCIAL_LICENSE:
                iv_item = binding.tvCommercialName;
                break;
            case AppConstants.COMMERCIAL_RECORD:
                iv_item = binding.tvCommercialRecordName;
                break;
            default:
                iv_item = binding.tvSignatureName;
        }
        return iv_item;
    }

    private void setImagePath(int type, String path, String id) {

        switch (type) {
            case AppConstants.PROFILE:
                model.setLogo(id + "");
                break;

            case AppConstants.ID:
                model.setID(path);
                break;
            case AppConstants.COMMERCIAL_LICENSE:
                model.setCommercialLicense(path);
                break;
            case AppConstants.COMMERCIAL_RECORD:
                model.setCommercialRecord(path);
                break;
            default:
                model.setSignature(path);
        }

    }


    private void takePhotoFromCamera() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        resultUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues());
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, resultUri);
        cameraImageActivityResultLauncher.launch(intent);
    }

    private void takePDFFile() {
        Intent pdfIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pdfIntent.setType("application/pdf");
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE);
        fileActivityResultLauncher.launch(pdfIntent);
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> cameraImageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes

                        final String profilePath = FileUtils.getPath(ProfileActivity.this, resultUri);
                        startUploadImage(model.getImageType(), profilePath, resultUri);
                    }
                }
            });


    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> fileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        startUploadFile(model.getImageType(), data.getData());
                    }
                }
            });


    private void startUploadFile(final int type, Uri uri) {

        MutableLiveData<ImageResponse> imageMutableLiveData = new MutableLiveData<>();
        imageMutableLiveData.observe(this, imageResponse -> {
            if (imageResponse.isStatus()) {
                TextView tv_item = getTextView(type);
                setImagePath(type, imageResponse.getData().getFile_name(), imageResponse.getData().getId());
                if (imageResponse.getData() != null) {
                    String imageUrl = imageResponse.getData().getFile_name();
                    tv_item.setText(imageUrl);
                }
            }
        });

        String path = copyFileToInternalStorage(uri, "HAMA");
        new GeneralRepo().uploadFile(this, path, imageMutableLiveData);
        TextView tv_item = getTextView(type);
        tv_item.setText(path.substring(path.lastIndexOf("/") + 1));
    }

    private String copyFileToInternalStorage(Uri uri, String newDirName) {
        Uri returnUri = uri;

        Cursor returnCursor = getContentResolver().query(returnUri, new String[]{
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
        }, null, null, null);


        /*
         * Get the column indexes of the data in the Cursor,
         *     * move to the first row in the Cursor, get the data,
         *     * and display it.
         * */
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
        returnCursor.moveToFirst();
        String name = (returnCursor.getString(nameIndex));
        String size = (Long.toString(returnCursor.getLong(sizeIndex)));

        File output;
        if (!newDirName.equals("")) {
            File dir = new File(getFilesDir() + "/" + newDirName);
            if (!dir.exists()) {
                dir.mkdir();
            }
            output = new File(getFilesDir() + "/" + newDirName + "/" + name);
        } else {
            output = new File(getFilesDir() + "/" + name);
        }
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(output);
            int read = 0;
            int bufferSize = 1024;
            final byte[] buffers = new byte[bufferSize];
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {

            Log.e("Exception", e.getMessage());
        }

        return output.getPath();
    }

}