package materialdesign.sriyank.com;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Author : Sriyank Siddhartha
 * Module 3 : Navigation View with three categories of ITEMS GROUPING
 *
 * 		"AFTER" demo project
 * */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout mDrawerLayout;
	private TextView mTxvMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mTxvMenuItem = (TextView) findViewById(R.id.txvMenuItem);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		// set listener
		navigationView.setNavigationItemSelectedListener(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout,
				toolbar,
				R.string.drawer_open,
				R.string.drawer_close);

		mDrawerLayout.addDrawerListener(drawerToggle);

		// makes the icon and title in the action bar clickable so that “up” navigation can be provided
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// This method just controls whether to show the Activity icon/logo or not.
		// Generally shows just the title with caret if passed
//		getSupportActionBar().setDisplayShowHomeEnabled(true);
		/*
		Both setDisplayHomeAsUpEnabled() and setHomeButtonEnabled() calls onOptionsItemSelected()
		on the Activity where you can check the item.getItemId() against android.R.id.home
		 and perform some action

		 */
		// sync with hamburger button
		drawerToggle.syncState();
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
				break;

			case R.id.item_ios:
				// Your item specific Codes
				break;
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
