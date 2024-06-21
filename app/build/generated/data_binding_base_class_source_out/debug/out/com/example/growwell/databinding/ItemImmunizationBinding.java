// Generated by view binder compiler. Do not edit!
package com.example.growwell.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.growwell.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemImmunizationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView descriptionView;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView titleView;

  private ItemImmunizationBinding(@NonNull LinearLayout rootView, @NonNull TextView descriptionView,
      @NonNull ImageView imageView, @NonNull TextView titleView) {
    this.rootView = rootView;
    this.descriptionView = descriptionView;
    this.imageView = imageView;
    this.titleView = titleView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemImmunizationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemImmunizationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_immunization, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemImmunizationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.descriptionView;
      TextView descriptionView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionView == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.titleView;
      TextView titleView = ViewBindings.findChildViewById(rootView, id);
      if (titleView == null) {
        break missingId;
      }

      return new ItemImmunizationBinding((LinearLayout) rootView, descriptionView, imageView,
          titleView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
