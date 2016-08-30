package com.github.jordane_quincy.tellme;

//import android.content.Intent;
//import android.support.test.espresso.intent.Intents;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import com.collectiveidea.example.ui.LoginActivity;
//import com.collectiveidea.example.ui.MainActivity;
//import com.collectiveidea.example.util.AccountUtility;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.espresso.intent.Intents.intended;
//import static android.support.test.espresso.intent.Intents.times;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
//
//    @Rule
//    public final ActivityTestRule<MainActivity> rule =
//            new ActivityTestRule<>(MainActivity.class, true, false);
//
//    @Test
//    public void testWhenThereInNoExistingAccount_LaunchesLoginActivity() throws Exception {
//        Intents.init();
//
//        AccountUtility.removeAccounts();
//        rule.launchActivity(new Intent());
//
//        intended(hasComponent(LoginActivity.class.getName()));
//
//        Intents.release();
//    }
//
//    @Test
//    public void testWhenThereIsAnExistingAccount_DoesNotLaunchLoginActivity() throws Exception {
//        Intents.init();
//
//        AccountUtility.addAccount("email@example.com", "TOKEN");
//        rule.launchActivity(new Intent());
//
//        intended(hasComponent(MainActivity.class.getName()));
//        intended(hasComponent(LoginActivity.class.getName()), times(0));
//
//        Intents.release();
//    }
}