package zeus.quantm.a247news.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import zeus.quantm.a247news.R;

/**
 * Created by QuanT on 3/21/2018.
 */

public class ScreenManager {

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment,
                                    int layoutID, boolean addToBackStack, boolean haveAnimation){
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(layoutID, fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        if(haveAnimation){
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_bot, 0, 0, R.anim.exit_from_bot);
        }
        fragmentTransaction.commit();
    }

    public static void backFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }
}

