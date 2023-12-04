package tees.ac.uk.q2078619.newsapp

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import tees.ac.uk.Q2078619.newsapp.app.NewsApp
import tees.ac.uk.Q2078619.newsapp.viewmodels.homeViewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    lateinit var  fusedLocationProviderClient : FusedLocationProviderClient
    var lat = 0.0;
    var long = 0.0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()
        setContent {
            var homeViewModel: HomeViewModel = viewModel()
            homeViewModel.latitude =lat;
            homeViewModel.longitude = long;
            NewsApp(homeViewModel)
        }
    }
    private fun getCurrentLocation()
    {
        if(checkPermission())
        {
            if(isLocationEnabled())
            {
                if(ActivityCompat.checkSelfPermission(
                    this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){task ->
                    val location: Location?= task.result
                    if(location == null)
                    {
                        Toast.makeText(this,"Failed to get Location", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Granted",Toast.LENGTH_SHORT).show()
                        lat = location.latitude;
                        long = location.longitude
                    }
                }
            }
            else
            {
                Toast.makeText(this,"Turn on Location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        else
        {
            requestPermission()
        }
    }
    private fun isLocationEnabled():Boolean {
        val locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(this, arrayOf(
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION,),
            PERMISSION_ACCESS_LOCATION
        )
    }


    companion object {
        private  const val PERMISSION_ACCESS_LOCATION = 100
    }

    private  fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_ACCESS_LOCATION)
        {
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation()
            }
            else
            {
                Toast.makeText(applicationContext,"Location Denied, You get only United Kingdom News",Toast.LENGTH_SHORT).show()
            }
        }
    }


}
