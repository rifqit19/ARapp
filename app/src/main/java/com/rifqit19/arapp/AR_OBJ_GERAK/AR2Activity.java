package com.rifqit19.arapp.AR_OBJ_GERAK;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.rifqit19.arapp.R;

public class AR2Activity extends AppCompatActivity {

    private ModelAnimator modelAnimator;
    private int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar2);


        ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            creatModel(hitResult.createAnchor(), arFragment);
        });

        ImageButton back = findViewById(R.id.backBtn2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


    }

    @SuppressLint("NewApi")
    private void creatModel(Anchor anchor, ArFragment arFragment) {

        ModelRenderable.builder()
                .setSource(this, Uri.parse("skeletal.sfb"))
                .build()
                .thenAccept(modelRenderable ->{

                    AnchorNode anchorNode = new AnchorNode(anchor);

                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);

                    arFragment.getArSceneView().getScene().addChild(anchorNode);

                    animateModel(modelRenderable);

                    ImageButton button = findViewById(R.id.button);

                    button.setOnClickListener(view -> animateModel(modelRenderable));
                });
    }

    private void animateModel(ModelRenderable modelRenderable) {

        int animationCount = modelRenderable.getAnimationDataCount();

        if (i == animationCount)
            i = 0;

        AnimationData animationData = modelRenderable.getAnimationData(0);

        modelAnimator = new ModelAnimator(animationData, modelRenderable);
        modelAnimator.start();
        i++;

//        if(/*modelAnimator != null && */!modelAnimator.isRunning()){
////            modelAnimator.end();
//
//
//        }
    }



}
