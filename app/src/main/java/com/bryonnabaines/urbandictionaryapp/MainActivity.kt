package com.bryonnabaines.urbandictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavHost {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val bundle = Bundle()
        when (item?.itemId) {
            R.id.likesAscending -> {
                bundle.putInt("sort_by", FragmentSearch.MOST_LIKES)
                this.navController.navigate(R.id.fragmentSearch, bundle)
                return true
            }
            R.id.likesDescending -> {
                bundle.putInt("sort_by", FragmentSearch.MOST_UNLIKES)
                this.navController.navigate(R.id.fragmentSearch, bundle)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun getNavController(): NavController = this.findNavController(R.id.nav_host_fragment)
}
