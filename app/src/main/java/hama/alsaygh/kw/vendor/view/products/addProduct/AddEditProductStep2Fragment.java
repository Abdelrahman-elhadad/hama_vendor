package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.databinding.FragmentAddProductStep2Binding;
import hama.alsaygh.kw.vendor.dialog.addOptionDialog.AddOptionProductDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.image.Image;
import hama.alsaygh.kw.vendor.model.image.ImageUpload;
import hama.alsaygh.kw.vendor.model.product.Media;
import hama.alsaygh.kw.vendor.model.product.Option;
import hama.alsaygh.kw.vendor.utils.CheckAndRequestPermission;
import hama.alsaygh.kw.vendor.utils.FileUtils;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.products.addProduct.adapter.AdapterImageProduct;
import hama.alsaygh.kw.vendor.view.products.addProduct.adapter.AdapterOptionProduct;

public class AddEditProductStep2Fragment extends BaseFragment implements OnGeneralClickListener {

    FragmentAddProductStep2Binding binding;
    AddEditProductViewModel model;
    AdapterImageProduct adapter;
    Uri resultUri;
    AdapterOptionProduct optionAdapter;
    FragmentManager fragmentManager;

    public static AddEditProductStep2Fragment newInstance(AddEditProductViewModel model) {
        AddEditProductStep2Fragment fragment = new AddEditProductStep2Fragment();
        fragment.setModel(model);
        return fragment;
    }

    public void setModel(AddEditProductViewModel model) {
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProductStep2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        binding.setModel(model);


        ArrayList<ImageUpload> imageUploads = new ArrayList<>();
        if (model.getAddProduct().getMedia() != null)
            for (Media media : model.addProduct.getMedia()) {

                ImageUpload imageUpload = new ImageUpload();
                Image image = new Image();
                image.setId(media.getId());
                image.setUrl(media.getLink());
                image.setFile_name(media.getDisplay_name());
                imageUpload.setImage(image);
                imageUploads.add(imageUpload);
            }

        adapter = new AdapterImageProduct(getActivity(), imageUploads);
        binding.rvUploadImages.setAdapter(adapter);
        binding.rvUploadImages.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Option> options = new ArrayList<>();
        if (model.getAddProduct().getOptions() != null)
            options = new ArrayList<>(model.getAddProduct().getOptions());

        optionAdapter = new AdapterOptionProduct(fragmentManager, options, AddEditProductStep2Fragment.this);
        binding.rvOptions.setAdapter(optionAdapter);
        binding.rvOptions.setLayoutManager(new LinearLayoutManager(getActivity()));


        binding.llGallery.setOnClickListener(v -> permissionGallery());

        binding.llCamera.setOnClickListener(v -> permissionCamera());

        binding.butAddOption.setOnClickListener(v ->
                AddOptionProductDialog.newInstance(AddEditProductStep2Fragment.this).show(fragmentManager, "add_option")
        );

    }

    private void permissionGallery() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!CheckAndRequestPermission.hasPermissions(getContext(), permissions)) {
            mPermissionResult.launch(permissions);
        } else {
            openGallery();
        }

    }

    private void permissionCamera() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!CheckAndRequestPermission.hasPermissions(getContext(), permissions)) {
            cameraPermissionResult.launch(permissions);
        } else {
            takePhotoFromCamera();
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Utils.PICK_IMAGE);
        pickImageActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    @Override
    public void onItemClick(Object object, int position) {

        if (object instanceof Option) {
            Option option = (Option) object;
            if (optionAdapter != null)
                optionAdapter.addItem(option);
        }
    }

    @Override
    public void onEditClick(Object object, int position) {
        if (object instanceof Option) {
            Option option = (Option) object;
            if (optionAdapter != null)
                optionAdapter.editItem(option, position);
        }
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

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> pickImageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if (data != null && data.getClipData() != null) {
                            ClipData mClipData = data.getClipData();
                            for (int i = 0; i < mClipData.getItemCount(); i++) {

                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                final String profilePath = FileUtils.getPath(getContext(), uri);
                                ImageUpload imageUpload = new ImageUpload();
                                imageUpload.setPath(profilePath);
                                imageUpload.setUri(uri);
                                adapter.addItem(imageUpload);
                            }
                        } else if (data != null && data.getData() != null) {
                            final String profilePath = FileUtils.getPath(getContext(), data.getData());
                            ImageUpload imageUpload = new ImageUpload();
                            imageUpload.setPath(profilePath);
                            imageUpload.setUri(data.getData());
                            adapter.addItem(imageUpload);
                        }
                    }
                }
            });

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> cameraImageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes

                        final String profilePath = FileUtils.getPath(getContext(), resultUri);
                        ImageUpload imageUpload = new ImageUpload();
                        imageUpload.setPath(profilePath);
                        imageUpload.setUri(resultUri);
                        adapter.addItem(imageUpload);
                    }
                }
            });


    private void takePhotoFromCamera() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        resultUri = requireActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues());
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, resultUri);
        //Log.i("getPackageManager",intent.resolveActivity(requireActivity().getPackageManager()).toString());
//        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
//
//        }
        cameraImageActivityResultLauncher.launch(intent);
    }


    public boolean isValid() {
        boolean isValid = true;

        if (adapter != null && !adapter.isValid()) {
            isValid = false;
            binding.tvError.setVisibility(View.VISIBLE);
        } else
            binding.tvError.setVisibility(View.GONE);

        return isValid;
    }

    public List<Media> getImages() {
        List<Media> images = new ArrayList<>();

        if (adapter != null) {
            images.addAll(adapter.getImageId());
        }
        return images;
    }

    public List<Option> getOptions() {
        List<Option> images = new ArrayList<>();
        if (optionAdapter != null) {
            images.addAll(optionAdapter.getItems());
        }
        return images;
    }
}