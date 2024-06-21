// Generated by view binder compiler. Do not edit!
package com.example.growwell.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.growwell.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEditAccountBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final EditText birthDate;

  @NonNull
  public final TextView editProfileTitle;

  @NonNull
  public final EditText email;

  @NonNull
  public final EditText fullName;

  @NonNull
  public final Button saveButton;

  private FragmentEditAccountBinding(@NonNull FrameLayout rootView, @NonNull EditText birthDate,
      @NonNull TextView editProfileTitle, @NonNull EditText email, @NonNull EditText fullName,
      @NonNull Button saveButton) {
    this.rootView = rootView;
    this.birthDate = birthDate;
    this.editProfileTitle = editProfileTitle;
    this.email = email;
    this.fullName = fullName;
    this.saveButton = saveButton;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEditAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEditAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_edit_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEditAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.birth_date;
      EditText birthDate = ViewBindings.findChildViewById(rootView, id);
      if (birthDate == null) {
        break missingId;
      }

      id = R.id.edit_profile_title;
      TextView editProfileTitle = ViewBindings.findChildViewById(rootView, id);
      if (editProfileTitle == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.full_name;
      EditText fullName = ViewBindings.findChildViewById(rootView, id);
      if (fullName == null) {
        break missingId;
      }

      id = R.id.save_button;
      Button saveButton = ViewBindings.findChildViewById(rootView, id);
      if (saveButton == null) {
        break missingId;
      }

      return new FragmentEditAccountBinding((FrameLayout) rootView, birthDate, editProfileTitle,
          email, fullName, saveButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
