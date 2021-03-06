package com.veibloc.cole.BLOC;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import static com.veibloc.cole.BLOC.R.id.about;
import static com.veibloc.cole.BLOC.R.id.contact;
import static com.veibloc.cole.BLOC.R.id.explore;
import static com.veibloc.cole.BLOC.R.id.products;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton[] icon;
    private int screen;
    private Fragment currentFragment;
    private ConstraintLayout layout;
    private float x1, x2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setOnClickListeners();
        setUpHomeScreen();
    }

    private void wireWidgets() {
        icon = new ImageButton[5];
        icon[0] = (ImageButton) findViewById(R.id.home);
        icon[1] = (ImageButton) findViewById(about);
        icon[2] = (ImageButton) findViewById(products);
        icon[3] = (ImageButton) findViewById(contact);
        icon[4] = (ImageButton) findViewById(explore);

        layout = (ConstraintLayout) findViewById(R.id.layout_main_background);
    }

    private void setOnClickListeners() {
        icon[0].setOnClickListener(this);
        icon[1].setOnClickListener(this);
        icon[2].setOnClickListener(this);
        icon[3].setOnClickListener(this);
        icon[4].setOnClickListener(this);
    }

    private void setUpHomeScreen() {
        screen = 0;
        currentFragment = new FragmentHome();
        switchToNewScreen();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layout.setBackgroundColor(getColor(R.color.colorBackground));
        } else { //ECEFF1
            layout.setBackgroundColor(Color.rgb(236, 239, 241));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                currentFragment = new FragmentHome();
                switchToNewScreen();
                changeIconColors(0);
                break;
            case about:
                currentFragment = new FragmentAbout();
                switchToNewScreen();
                changeIconColors(1);
                break;
            case products:
                currentFragment = new FragmentProducts();
                switchToNewScreen();
                changeIconColors(2);
                break;
            case contact:
                currentFragment = new FragmentContact();
                switchToNewScreen();
                changeIconColors(3);
                break;
            case explore:
                currentFragment = new FragmentExplore();
                switchToNewScreen();
                changeIconColors(4);
                break;
            default:
                break;
        }
    }

    private void switchToNewScreen() {
        //tell the fragment manager that if our current fragment isn't null, to replace whatever is there with it
        FragmentManager fm = getSupportFragmentManager();
        if (currentFragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, currentFragment)
                    .commit();
        }
    }

    private void changeIconColors(int newScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            icon[screen].setColorFilter(getColor(R.color.colorNotClicked));
            screen = newScreen;
            icon[screen].setColorFilter(getColor(R.color.colorAccent));
        } else {
            icon[screen].setColorFilter(Color.rgb(255, 255, 255)); //colorNotClicked
            screen = newScreen;
            icon[screen].setColorFilter(Color.rgb(245, 124, 0)); //colorAccent
        }
    }

    public boolean onTouchEvent(MotionEvent touchEvent)
    {
        switch (touchEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchEvent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchEvent.getX();

                if(x1 > x2 + 150)
                {

                    switch (screen)
                    {
                        case 0:
                            currentFragment = new FragmentAbout();
                            changeIconColors(1);
                            break;
                        case 1:
                            currentFragment = new FragmentProducts();
                            changeIconColors(2);
                            break;
                        case 2:
                            currentFragment = new FragmentContact();
                            changeIconColors(3);
                            break;
                        case 3:
                            currentFragment = new FragmentExplore();
                            changeIconColors(4);
                            break;
                        default:
                            break;
                    }
                    switchToNewScreen();
                }

                if(x1 + 150 < x2)
                {
                    switch (screen)
                    {
                        case 1:
                            currentFragment = new FragmentHome();
                            changeIconColors(0);
                            break;
                        case 2:
                            currentFragment = new FragmentAbout();
                            changeIconColors(1);
                            break;
                        case 3:
                            currentFragment = new FragmentProducts();
                            changeIconColors(2);
                            break;
                        case 4:
                            currentFragment = new FragmentContact();
                            changeIconColors(3);
                            break;
                        default:
                            break;
                    }
                    switchToNewScreen();

                }
                break;
            }
        }
        return false;
    }
}
