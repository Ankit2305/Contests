package com.example.programmingcontest.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.programmingcontest.R
import com.example.programmingcontest.core.api.KontestsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel.updateDB()
        viewModel.checkVersion()

        viewModel.isLatestVersionAvailable.observe(this) {
            if(it) {
                AlertDialog.Builder(this)
                    .setTitle("Newer Version Available")
                    .setMessage("A newer version of the Contests App is available. ")
                    .setPositiveButton("Download", object: DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Ankit2305/Contests/releases/"))
                            startActivity(intent)
                        }
                    })
                    .setNegativeButton("Close", null)
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        navController = nav_host_fragment_container.findNavController()

        bottomNav.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.exploreFragment,
                R.id.homeFragment,
                R.id.categoriesFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}