package app.mvp.helper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.mvp.R;

public class FragmentHelper {
    public static void load(Fragment fragment, boolean addToBackStack, Bundle args, Activity activity) {
        fragment.setArguments(args);
        FragmentTransaction transaction = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.up, R.anim.down, R.anim.up, R.anim.down);
        transaction.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
