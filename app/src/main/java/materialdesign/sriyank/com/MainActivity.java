package materialdesign.sriyank.com;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Author : Sriyank Siddhartha
 * Module 3 : Navigation View with three categories of ITEMS GROUPING
 *
 * 		"AFTER" demo project
 * */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout mDrawerLayout;
	private TextView mTxvMenuItem;
	private Button mLoginButton;
	private EditText mUsernameEt;
	private EditText mPasswordEt;
	private TextInputLayout mInputLayoutName, mInputLayoutPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mUsernameEt= (EditText) findViewById(R.id.etUsername);
		mPasswordEt= (EditText) findViewById(R.id.etPassword);

		mInputLayoutName= (TextInputLayout) findViewById(R.id.inputLayoutUsername);
		mInputLayoutPassword= (TextInputLayout) findViewById(R.id.inputLayoutPassword);


		mTxvMenuItem = (TextView) findViewById(R.id.txvMenuItem);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		// set listener
		navigationView.setNavigationItemSelectedListener(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout,
				toolbar, // linked to toolbar -> syncState()
				R.string.drawer_open,
				R.string.drawer_close);

		mDrawerLayout.addDrawerListener(drawerToggle);

		// specifies whether or not the Home button has the arrow used for Up Navigation next to it.
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// specifies whether or not the Home button is shown.
//		getSupportActionBar().setDisplayShowHomeEnabled(true);
		/*
		Both setDisplayHomeAsUpEnabled() and setHomeButtonEnabled() calls onOptionsItemSelected()
		on the Activity where you can check the item.getItemId() against android.R.id.home
		 and perform some action

		 */
		// sync with hamburger button
		drawerToggle.syncState();
	}

	public void validateAndLogin(View view){
		if(validateUsername() && validatePassword()){
			Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
		}
	}

	private boolean validatePassword() {
		String pwd = mPasswordEt.getText().toString().trim();
		if(pwd.length()<8){
//			mInputLayoutPassword.setError("Minimum 8 characters required");
			mPasswordEt.setError("Minimum 8 characters required");
			return false;
		}else{
//			mInputLayoutPassword.setErrorEnabled(false);
			return true;
		}
	}

	private boolean validateUsername() {
		if(mUsernameEt.getText().toString().isEmpty()){
			mInputLayoutName.setError("Username cannot be blank!");
			return false;
		}else{
			// remove validation message if it exists
			mInputLayoutName.setErrorEnabled(false);
			return true;
		}
	}

	@Override // Called when Any Navigation Item is Clicked
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		// is VERY similar to onOptionsItemSelected

//		menuItem.setCheckable(true);
//		menuItem.setChecked(true);  // This helps to know which Menu Item is Clicked

		String itemName = (String) menuItem.getTitle();

		mTxvMenuItem.setText(itemName);

		hideDrawer();

		switch (menuItem.getItemId()) {

			case R.id.item_android:
				// Your Item specific Codes
				Toast.makeText(this, "Best OS evur!", Toast.LENGTH_SHORT).show();
				break;

			case R.id.item_ios:
				// Your item specific Codes
				break;
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			Toast.makeText(this, "Home button selected!", Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	// Open the Drawer
	private void showDrawer() {
		mDrawerLayout.openDrawer(GravityCompat.START);
	}

	// Close the Drawer
	private void hideDrawer() {
		mDrawerLayout.closeDrawer(GravityCompat.START);
	}

	// handle back button press to close drawer if it is open rather than closing activity
	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
			hideDrawer();
		else
			super.onBackPressed();
	}
}
