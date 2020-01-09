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
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.navigation.fragment.NavHostFragment



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
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val searchFragment = fragment!!.childFragmentManager.fragments[0] as FragmentSearch
        when (item?.itemId) {
            R.id.likesAscending -> {
                searchFragment.sortBy(FragmentSearch.MOST_LIKES, searchFragment.words)
                return true
            }
            R.id.likesDescending -> {
                searchFragment.sortBy(FragmentSearch.MOST_UNLIKES, searchFragment.words)
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
