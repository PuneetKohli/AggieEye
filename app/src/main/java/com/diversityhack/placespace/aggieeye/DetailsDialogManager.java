package com.diversityhack.placespace.aggieeye;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.daimajia.androidanimations.library.GenAnimations;
import com.daimajia.androidanimations.library.Techniques;
import com.diversityhack.placespace.aggieeye.Fragments.MainActivityFragments.EventDetailsPagerContainer;

/**
 * Created by Srujan on 7/3/2017.
 */

/* This class is made to manage all the dialogs that contains the details of a hospital or a clinic.
It has callbacks which gets called when dialog is shown, and in the beginning and end of animations.
 */

public class DetailsDialogManager {

    private OnDialogInteractionInterface onDialogInteraction;

    public DetailsDialogManager(Context context){
        if (context instanceof OnDialogInteractionInterface)
            onDialogInteraction = (OnDialogInteractionInterface) context;
    }

    public void showDetailDialog(final Activity activity,final Context context){
        onDialogInteraction.onDetailsDialogShown();
        GenAnimations.with(Techniques.SlideInUp)
                .duration(300)
                .onStart(new GenAnimations.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        onDialogInteraction.onDetailsDialogAnimationStart();
                        EventDetailsPagerContainer container = new EventDetailsPagerContainer();
                        FragmentManager manager = ((MainActivity)activity).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.add(R.id.center_fragment,container,"detailDialog");
                        transaction.commit();
                    }
                })
                .onEnd(new GenAnimations.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        onDialogInteraction.onDetailsDialogAnimationEnd();
                    }
                })
                .playOn(activity.findViewById(R.id.center_fragment));

    }

    public void hideDetailDialog(final Activity activity){
        onDialogInteraction.onDetailsDialogClosed();
        GenAnimations.with(Techniques.SlideOutDown)
                .duration(300)
                .onStart(new GenAnimations.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        onDialogInteraction.onDetailsDialogCloseAnimationStart();
                    }
                })
                .onEnd(new GenAnimations.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        onDialogInteraction.onDetailsDialogCloseAnimationEnd();
                        FragmentManager manager = ((MainActivity)activity).getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.remove(((MainActivity)activity).getSupportFragmentManager().findFragmentByTag("detailDialog")).commit();
                    }
                })
                .playOn(activity.findViewById(R.id.center_fragment));
    }

    public interface OnDialogInteractionInterface{
        void onDetailsDialogShown();

        void onDetailsDialogAnimationStart();

        void onDetailsDialogAnimationEnd();

        void onDetailsDialogClosed();

        void onDetailsDialogCloseAnimationStart();

        void onDetailsDialogCloseAnimationEnd();
    }
}
